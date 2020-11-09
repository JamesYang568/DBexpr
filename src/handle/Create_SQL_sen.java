package handle;

public class Create_SQL_sen {
    public static String sql1 = "CREATE TABLE driver(id int primary key ," +
            "name varchar(20) not null," +
            "enroll_date date, " +
            "salary double" +
            "valid int not null) charset=utf8;";
    public static String sql2 = "CREATE TABLE client(" + "id int primary key ," +
            "name varchar(20) not null," +
            "company varchar(40)," +
            "tel varchar(14)," +
            "addr varchar(50)," +
            "zipcode varchar(8)," +
            "valid int not null)charset=utf8;";
    public static String sql3 = "CREATE TABLE car(" + "id int primary key ,"
            + "type varchar(10), " +
            "purchase_date date not null, " +
            "price double not null," +
            "maintain_date date ," +
            "mile double ," +
            "working_time double ," +
            "rent_rate double ," +
            "valid int not null )charset=utf8;";
    public static String sql4 = "CREATE TABLE transaction(" + "id int primary key ," +
            "date date not null," +
            "license varchar(10) not null ," +
            "local varchar(30) " +
            "miles double ," +
            "times double ," +
            "client_id int foreign key references client(id)," +
            "driver_id int  foreign key references driver(id))charset=utf8;";
}
