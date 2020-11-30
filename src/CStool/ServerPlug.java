package CStool;

import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPlug {
    private ServerSocket server;
    private Socket connection;

    public void runServer() {
        try {
            server = new ServerSocket(12345, 100); // create ServerSocket
            while (true) {
                try {
                    waitForConnection(); //?????????????????
                } catch (EOFException eofException) {
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
        System.out.println("Waiting for connection\n");
        connection = server.accept();
        System.out.println("Connection " + " received from: " + connection.getInetAddress().getHostName() + "\n");
        new CreateServerThread(connection);//??????????
    }
}

class CreateServerThread extends Thread {
    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public CreateServerThread(Socket connection) {
        this.client = connection;
        this.start();
    }

    public void run() {
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

    private void processConnection() throws IOException {
        String message = "Connection successful";
        do {
            try { //业务逻辑
                message = (String) input.readObject(); // read new message

            } catch (ClassNotFoundException classNotFoundException) {
                JOptionPane.showMessageDialog(null, "Unknown object type received", "出错了", JOptionPane.ERROR_MESSAGE);
            }
            //结束连接  这里需要一个标志被传入
//            Pattern pattern1 = Pattern.compile("DISCONNECT");//?????????????????
//            Matcher matcher1 = pattern1.matcher(message);
//            if (matcher1.find())
//                break;
        } while (true);
    }

    private void sendData(Object message) { //对对应的客户端发消息
        try {
            output.writeObject(message);
            output.flush();
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, "Error writing object", "出错了", JOptionPane.ERROR_MESSAGE);
        }

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