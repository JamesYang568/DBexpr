package handle;

public class Create_SQL_sen {
    public static String sql1 = "CREATE TABLE driver_info(id int primary key auto_increment," +
            "name varchar(20) not null," +
            "enroll_date date, " +
            "salary double," +
            "available int not null," +
            "valid int not null) charset=utf8;";
    public static String sql2 = "CREATE TABLE client_info(" + "id int primary key auto_increment ," +
            "password varchar(15)," +
            "name varchar(20) not null," +
            "company varchar(40)," +
            "tel varchar(14)," +
            "addr varchar(50)," +
            "zipcode varchar(8)," +
            "valid int not null)charset=utf8;";
    public static String sql3 = "CREATE TABLE car_info(" + "id int primary key auto_increment ," +
            "type varchar(10), " +
            "license varchar(10)," +
            "purchase_date date not null, " +
            "price double not null," +
            "maintain_date date ," +
            "mile double ," +
            "working_time double ," +
            "rent_rate double ," +
            "available int not null," +
            "valid int not null )charset=utf8;";
    public static String sql4 = "CREATE TABLE transaction_info(" + "id int primary key auto_increment ," +
            "date date not null," +
            "car_id int , " +
            "local varchar(30), " +
            "miles double ," +
            "times double ," +
            "valid int not null ," +
            "client_id int ," +
            "driver_id int ," +
            "CONSTRAINT C1 foreign key (car_id) references client_info(id) ," +
            "CONSTRAINT C2 foreign key (client_id) references driver_info(id)," +
            "CONSTRAINT C3 foreign key (driver_id) references car_info(id))charset=utf8;";
}
