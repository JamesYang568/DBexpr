package CStool;

import entity.Transaction;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 两者使用这个模块交互信息
 * 1、只传递transaction，那么getBytes需要在这里
 * 2、传递的是整个DataMsgEntity则getBytes要在ClientDataSocket中
 */
public class DataMsgEntity {
    private int client_id;
    private Transaction transaction;
    private String client_ip;
    private int port;

    public DataMsgEntity(int client_id, Transaction transaction, String client_ip, int port) {
        this.client_id = client_id;
        this.transaction = transaction;
        this.client_ip = client_ip;
        this.port = port;
    }

    public DataMsgEntity(String client_ip, int port) {
        this.client_ip = client_ip;
        this.port = port;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
        this.client_id = transaction.getClient_id();
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getIP() {
        return this.client_ip;
    }

    public int getPort() {
        return this.port;
    }

    //转化为字节数组
    public byte[] getBytes() {
        byte[] bytes = new byte[0];
        try {
            //object to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(this.transaction);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return (bytes);
    }

    public static Transaction getTransaction(byte[] bytes) {
        Object obj = null;
        try {
            //bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();

            bi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return (Transaction) obj;
    }

    //判断是否是传入的用户号的连接
    public boolean is_who(int other_client_id) {
        return this.client_id == other_client_id;
    }
}
