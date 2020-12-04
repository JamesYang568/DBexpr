package CStool;

import entity.Transaction;

import java.io.IOException;
import java.net.*;

public class ClientDataGSocket {
    private int id;
    private DatagramSocket client;
    private int port;
    private InetAddress addr;

    ClientDataGSocket(int id) {
        try {
            this.id = id;
            this.port = 8888;
            this.client = new DatagramSocket(this.port);
            this.addr = InetAddress.getLocalHost();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }

    public void sendData(Transaction transaction) {
        DataMsgEntity dataMsgEntity = new DataMsgEntity(this.id, transaction, this.addr.getHostAddress(), this.port);
        byte[] data = dataMsgEntity.getBytes();
        DatagramPacket MsgPacket = new DatagramPacket(data, data.length);
        try {
            client.send(MsgPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
