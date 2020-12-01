package CStool;

import entity.Transaction;

import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerPlug {
    private ServerSocket server;//服务器端的socket
    private boolean active;
    private ArrayBlockingQueue<CreateServerThread> arrayBlockingQueue; //todo 线程队列怎么用
    private ExecutorService es;//线程池
    private static ServerPlug instance = null;

    public static ServerPlug getInstance() {
        if (instance == null) {
            instance = new ServerPlug();
        }
        return instance;
    }

    private ServerPlug() {
        this.active = false;
        this.arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        this.es = Executors.newFixedThreadPool(10);
        runServer();
    }

    private void runServer() {
        try {
            this.server = new ServerSocket(12345, 100); // create ServerSocket
            this.active = true;
            while (true) {//等待接收新的客户端连接，这里会发生阻塞，如何保证此时的图形界面不会被阻塞
                try {
                    waitForConnection();
                } catch (EOFException eofException) {
                    disconnect();
                    System.out.println("Server terminated connection");
                } finally {
                    // TODO
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void waitForConnection() throws IOException {
        if (!this.active)
            return;
        System.out.println("Waiting for connection\n");
        Socket connection = server.accept(); //获取一个连接
        System.out.println("Connection " + " received from: " + connection.getInetAddress().getHostName() + "\n");
        //this.arrayBlockingQueue.add(new CreateServerThread(connection));//这里需要给一个队列
        this.es.execute(new CreateServerThread(connection));
        //开启一个服务线程  不同的客户有不同的socket，所以必须要使用客户id作为识别不同socket的标志
    }

    public void disconnect() {
        try {
            if (this.active) {
                server.close();
                this.active = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//客户端发送完数据之后就断开和服务器的联系-也就是CreateServerThread被断开
// 之后等待服务器进行处理，服务器处理之后保存在缓存文件中，之后客户可以再次登录或者更新界面查看之前提交的订单信息，从而得知是否审核通过。
//或者服务器处理完成之后主动通知客户端，给其发消息，但是如果此时客户端已经关闭了呢？
class CreateServerThread extends Thread {  //多服务线程
    private final Socket client;  //和客户端的socket连接
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Transaction transaction = null;
    private int id;

    public CreateServerThread(Socket connection) {
        this.client = connection;
        this.start(); //开始执行线程
    }

    public void run() {  //线程具体执行内容 这里只连接一次？ todo
        try {
            getStreams();
            processConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private void getStreams() throws IOException {
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush();
        input = new ObjectInputStream(client.getInputStream());
    }

    /**
     * 服务端等待客户端发消息
     * 服务端处理用户发来的消息
     * 服务端把处理后的数据还给客户端
     */
    private void processConnection() throws IOException {
        boolean flag = true;
        do {
            //业务逻辑 客户端处理
            id = input.readInt(); //最开始收到的是客户端传过来的他的id
            getData();
            if (transaction != null) {
                //todo 说明收到了从客户端来的信息  把id和transaction传入到主界面中去  返回一个transaction
                //sendData();
            }
            flag = getData(true);//当完成整个交互流程时结束循环
            if (!flag){
                //取消更新的事物
            }
        } while (flag);
    }

    private void sendData(Object message) { //给客户端发消息
        try {
            output.writeObject(message);
            output.flush();
            synchronized (this) {
                wait(); // 发送完消息后，线程进入等待状态
            }
        } catch (IOException | InterruptedException ioException) {
            JOptionPane.showMessageDialog(null, "Error writing object", "出错了", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void getData() { //判断返回值是否为null
        try {
            transaction = (Transaction) input.readObject();
            synchronized (this) { //一旦得到了一个消息就被唤醒
                notify();
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unknown object type received", "出错了", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private boolean getData(boolean flag) {
        try {
            flag = input.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    private void disconnect() {
        try {
            input.close();
            output.close();
            client.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}