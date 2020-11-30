package CStool;

import java.io.EOFException;
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

    //普通信息传输 todo
    public void sendData(String message) {
        try {
            output.writeObject(message);//可以为线程编号
            output.flush(); // flush data to output
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
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
