package CStool;

import entity.Transaction;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientPlug {
    private String chatServer;
    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public ClientPlug(String host) {
        chatServer = host;
        this.runClient();
    }

    public void runClient() {//对每一个客户端启动运行——打开资源
        try {
            connectToServer();
            getStreams();
        } catch (IOException eofException) {
            eofException.printStackTrace();
        }
    }

    private void connectToServer() throws IOException {
        client = new Socket(InetAddress.getByName(chatServer), 12345);
    }

    private void getStreams() throws IOException { //初始化两个流
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush(); //注意这一次是把报头发送出去
        input = new ObjectInputStream(client.getInputStream());
    }

    //失败
    public void sendData() {
        try {
            output.writeBoolean(false); //可以为线程编号
            output.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void sendData(Transaction transaction) {
        try {
            output.writeObject(transaction); //这里面就包含了用户的id
            output.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public Transaction getData() {
        Transaction transaction = null;
        try {
            transaction = (Transaction) input.readObject();
//            //todo 显示一个对话框展示重要信息，点击确认完成，取消则再次发送信息，调用sendData(String message)直接将
//            String message = "业务号" + transaction.getId() + "，顾客号" + transaction.getClient_id() + "，汽车号" + transaction.getCar_id() + "，司机号" + transaction.getDriver_id();
//            int flag = JOptionPane.showConfirmDialog(null, message, "确认业务", JOptionPane.YES_NO_OPTION);
//            if (flag == 0) //取消业务
//                sendData();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    public void closeConnection() {
        try {
            output.close();
            input.close();
            client.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
