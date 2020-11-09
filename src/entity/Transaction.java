package entity;

import java.util.Date;

public class Transaction {
    //发票号、日期、牌照号、地点、往返里程、行驶时间、客户号、司机号
    private int id;
    private Date date;
    private String license;
    private String local;
    private double miles;
    private double times;
    private int client_id;
    private int driver_id;

    public Transaction() {

    }

    public Transaction(int id, Date date, String license, String local, double miles, double times,
                       int client_id, int driver_id) {
        this.id = id;
        this.date = date;
        this.license = license;
        this.local = local;
        this.miles = miles;
        this.times = times;
        this.client_id = client_id;
        this.driver_id = driver_id;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getMiles() {
        return miles;
    }

    public double getTimes() {
        return times;
    }

    public int getClient_id() {
        return client_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public String getLicense() {
        return license;
    }

    public String getLocal() {
        return local;
    }
}
