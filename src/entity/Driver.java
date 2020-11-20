package entity;

import java.util.Date;

public class Driver implements Entity {
    //司机号、姓名、参加工作年月、基本工资、是否空闲、是否在岗
    private int id;
    private String name;
    private Date enroll_date;
    private double salary;
    private int available;
    private int valid;

    public Driver() {
        this.available = 1;
        this.valid = 1;
    }

    public Driver(int id, String name, Date enroll_date, double salary) {
        this.id = id;
        this.name = name;
        this.enroll_date = enroll_date;
        this.salary = salary;
        this.available = 1;
        this.valid = 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getEnroll_date() {
        return enroll_date;
    }

    public double getSalary() {
        return salary;
    }

    public boolean isAvailable() {
        return this.available == 1;
    }

    public boolean isValid() {
        return this.valid == 1;
    }
}
