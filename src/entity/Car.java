package entity;

import java.util.Date;

public class Car implements Entity {
    //注册号码、汽车型号、车牌照、购入日期、价格、维修日期、运行公里、运行小时、租金率、是否空闲、是否报废
    private int id;
    private String type;
    private String license;
    private Date purchase_date;
    private double price;
    private Date maintain_date;
    private double mile;
    private double working_time;
    private double rent_rate;
    private int available;
    private int valid;

    public Car() {
        this.available = 1;
        this.valid = 1;
    }

    public Car(int id, String type, String license, Date purchase_date, double price, Date maintain_date,
               double mile, double working_time, double rent_rate) {
        this.id = id;
        this.type = type;
        this.license = license;
        this.purchase_date = purchase_date;
        this.price = price;
        this.maintain_date = maintain_date;
        this.mile = mile;
        this.working_time = working_time;
        this.rent_rate = rent_rate;
        this.available = 1;
        this.valid = 1;
    }

    public int getId() {
        return id;
    }

    public Date getMaintain_date() {
        return maintain_date;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public double getMile() {
        return mile;
    }

    public double getPrice() {
        return price;
    }

    public double getRent_rate() {
        return rent_rate;
    }

    public double getWorking_time() {
        return working_time;
    }

    public String getType() {
        return type;
    }

    public String getLicense() {
        return license;
    }

    public boolean isAvailable() {
        return this.available == 1;
    }

    public boolean isValid() {
        return this.valid == 1;
    }
}
