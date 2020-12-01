package entity;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    //发票号、日期、牌照号、地点、往返里程、行驶时间、客户号、司机号、是否正在进行
    private int id;
    private Date date;
    private int car_id;
    private String local;
    private double miles;
    private double times;
    private int client_id;
    private int driver_id;
    private int valid;

    public Transaction() {
        this.valid = 1;
    }

    public Transaction(Date date, int car_id, String local, double miles, double times, int client_id) {
        this.date = date;
        this.car_id = car_id;
        this.local = local;
        this.miles = miles;
        this.times = times;
        this.client_id = client_id;
    }

    public Transaction(int id, Date date, int car_id, String local, double miles, double times,
                       int client_id, int driver_id) {
        this.id = id;
        this.date = date;
        this.car_id = car_id;
        this.local = local;
        this.miles = miles;
        this.times = times;
        this.client_id = client_id;
        this.driver_id = driver_id;
        this.valid = 1;
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

    public int getCar_id() {
        return car_id;
    }

    public String getLocal() {
        return local;
    }

    public boolean isDoing() { //判断是否正在进行
        return valid == 1;
    }
}
