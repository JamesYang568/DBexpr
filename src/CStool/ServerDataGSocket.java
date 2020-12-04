package CStool;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class ServerDataGSocket extends Thread {
    private final String ip = "127.0.0.1";
    private DatagramSocket server;
    private static ServerDataGSocket serverDataGSocket = null;
    private ArrayList<DataMsgEntity> MsgArray;

    private ServerDataGSocket() {
        InetAddress address = null;
        try {
            address = InetAddress.getByName(this.ip);
            this.server = new DatagramSocket(1234, address);
            this.MsgArray = new ArrayList<DataMsgEntity>(5) {
                @Override
                public DataMsgEntity get(int client_id) {
                    for (DataMsgEntity dataMsgEntity : this) {
                        if (dataMsgEntity.is_who(client_id))
                            return dataMsgEntity;
                    }
                    return null;
                }
            }; //重载了get，根据id查而不是根据位置查
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }

    public static ServerDataGSocket getInstance() {
        if (serverDataGSocket == null)
            serverDataGSocket = new ServerDataGSocket();
        return serverDataGSocket;
    }

    public void run() {
        while (true) {
            byte[] receiveBytes = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBytes, receiveBytes.length);
            try {
                server.receive(receivePacket);
                //封装成dataMsgEntity
                DataMsgEntity dataMsgEntity = new DataMsgEntity(receivePacket.getAddress().getHostAddress(), receivePacket.getPort());
                dataMsgEntity.setTransaction(DataMsgEntity.getTransaction(receivePacket.getData()));
                this.MsgArray.add(dataMsgEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //外部粒度，耦合性极大
    public void sendData(DataMsgEntity dataMsgEntity) {
        byte[] sentBytes = dataMsgEntity.getBytes();
        try {
            server.send(new DatagramPacket(sentBytes, sentBytes.length,
                    InetAddress.getByName(dataMsgEntity.getIP()), dataMsgEntity.getPort()));  //todo 这里能否正确发出
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataMsgEntity getMsg(int client_id) {
        return this.MsgArray.get(client_id);
    }
}
