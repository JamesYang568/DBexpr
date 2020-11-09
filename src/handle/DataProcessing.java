package handle;

import DialogGUI.ExitDialog;
import DialogGUI.SQLLinkErr;
import entity.*;
import entity.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DataProcessing {

    private static boolean connectToDB;
    private static Connection connection;

    static {
        connectToBD();
        Init();// 第一次生成静态时的初始化
        // 我们不需要构造函数注意，所以必须自动调用方法init

    }

    public static void connectToBD() {
        // connect to database
        String driverName = "com.mysql.jdbc.Driver";           // 加载数据库驱动类
        String url = "jdbc:mysql://localhost:3306/"
                + "?useSSL=FALSE&&serverTimezone=UTC&&useUnicode=true&characterEncoding=utf8";
        // 声明数据库的URL以及格式
        String user = "root";                     // 数据库用户
        String password = "1234";
        try {
            Class.forName(driverName);
            System.out.println("连接数据库..."); // 利用反射建立数据库连接
            connection = DriverManager.getConnection(url, user, password);
            connectToDB = true;
        } catch (ClassNotFoundException e) {
            System.out.println("数据驱动错误");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库错误");
            new SQLLinkErr();  //todo
            e.printStackTrace();
        }
    }

    /**
     * 建表
     */
    public static void Init() {
        try {
            Statement statement;
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.execute(Create_SQL_sen.sql1);
            statement.execute(Create_SQL_sen.sql2);
            statement.execute(Create_SQL_sen.sql3);
            statement.execute(Create_SQL_sen.sql4);

            statement.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.print("database init出错了");
        }
    }

    /**
     * 建视图
     */


    /**
     * 插入信息 传入对象
     */
    public static boolean insertCar(Car car) throws SQLException {
        if (!connectToDB)
            throw new SQLException("Not Connected to Database");
        else {
            Statement statement;
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "insert into car_info values ("
                    + car.getId() + ",'" + car.getType() + "','" + car.getLicense() + "'," + car.getPurchase_date() +
                    "," + car.getPrice() + "," + car.getMaintain_date() + "," + car.getMile() +
                    "," + car.getWorking_time() + "," + car.getRent_rate() + "," + 1 + ")";
            statement.executeUpdate(sql);
            statement.close();
//            doc = new Doc(ID, creator, timestamp, description, filename);
//            docs.put(ID, doc);
            return true;
        }
    }

    public static boolean insertClient(Client client) throws SQLException {
        if (!connectToDB)
            throw new SQLException("Not Connected to Database");
        else {
            Statement statement;
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "insert into client_info values (" + client.getId() + ",'" + client.getName() +
                    "','" + client.getCompany() + "','" + client.getTel() + "','" + client.getAddr() +
                    "','" + client.getZipcode() + "'," + 1 + ")";
            statement.executeUpdate(sql);
            statement.close();
//            doc = new Doc(ID, creator, timestamp, description, filename);
//            docs.put(ID, doc);
            return true;
        }
    }

    public static boolean insertTransaction(Transaction transaction) throws SQLException {
        if (!connectToDB)
            throw new SQLException("Not Connected to Database");
        else {
            Statement statement;
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "insert into transaction_info values (" + transaction.getId() +
                    "," + transaction.getDate() + ",'" + transaction.getLicense() + "','" + transaction.getLocal()
                    + "'," + transaction.getMiles() + "," + transaction.getTimes() +
                    "," + transaction.getClient_id() + "," + transaction.getDriver_id() + ")";
            statement.executeUpdate(sql);
            statement.close();
//            doc = new Doc(ID, creator, timestamp, description, filename);
//            docs.put(ID, doc);
            return true;
        }
    }

    public static boolean insertDriver(Driver driver) throws SQLException {
        if (!connectToDB) {
            throw new SQLException("Not Connected to Database");
        } else {
            Statement statement;
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "insert into driver_info values (" + driver.getId() + ",'" + driver.getName()
                    + "'," + driver.getEnroll_date() + "," + driver.getSalary() + "," + 1 + ")";
            statement.executeUpdate(sql);
            statement.close();
//            doc = new Doc(ID, creator, timestamp, description, filename);
//            docs.put(ID, doc);
            return true;
        }
    }

    /**
     * 查询  查询的一定是valid为1的对象
     * flag : true 只查询1个，false查询所有
     * all :true 全部都查询，false只查询有效的
     * 在调用之后请判断数组长度
     */
    public static Car[] searchCar(int maybe_id, boolean flag, boolean all) throws SQLException {
        Statement statement;
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql;
        ArrayList<Car> arrayList = new ArrayList<Car>();
        if (flag && all)  // 一个且全部
            sql = "select * from car_info where id =" + maybe_id;
        else if (flag)  //一个且有效
            sql = "select * from car_info where id =" + maybe_id + "and valid=" + 1;
        else if (all) //全部且全部
            sql = "select * from car_info ";
        else //全部且有效
            sql = "select * from car_info where valid=" + 1;

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String type = resultSet.getString("type");
            String license = resultSet.getString("license");
            Date purchase_date = resultSet.getDate("purchase_date");
            double price = resultSet.getDouble("price");
            Date maintain_date = resultSet.getDate("maintain_date");
            double mile = resultSet.getDouble("mile");
            double working_time = resultSet.getDouble("working_time");
            double rent_rate = resultSet.getDouble("rent_rate");
            arrayList.add(new Car(id, type, license, purchase_date, price, maintain_date, mile, working_time, rent_rate));
        }

        return arrayList.toArray(new Car[0]);
    }

    public static Driver[] searchDriver(int maybe_id, boolean flag, boolean all) throws SQLException {
        Statement statement;
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql;
        ArrayList<Driver> arrayList = new ArrayList<Driver>();
        if (flag && all)
            sql = "select * from driver_info where id =" + maybe_id;
        else if (flag)
            sql = "select * from driver_info where id =" + maybe_id + "and valid=" + 1;
        else if (all)
            sql = "select * from driver_info ";
        else
            sql = "select * from driver_info where valid=" + 1;
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Date enroll_date = resultSet.getDate("enroll_date");
            double salary = resultSet.getDouble("salary");
            arrayList.add(new Driver(id, name, enroll_date, salary));
        }

        return arrayList.toArray(new Driver[0]);
    }

    public static Client[] searchClient(int maybe_id, boolean flag, boolean all) throws SQLException {
        Statement statement;
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql;
        ArrayList<Client> arrayList = new ArrayList<Client>();
        if (flag && all)
            sql = "select * from client_info where id =" + maybe_id;
        else if (flag)
            sql = "select * from client_info where id =" + maybe_id + "and valid=" + 1;
        else if (all)
            sql = "select * from client_info ";
        else
            sql = "select * from client_info where valid=" + 1;
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String company = resultSet.getString("company");
            String tel = resultSet.getString("tel");
            String addr = resultSet.getString("addr");
            String zipcode = resultSet.getString("zipcode");
            arrayList.add(new Client(id, name, company, tel, addr, zipcode));
        }

        return arrayList.toArray(new Client[0]);
    }

    //订单不考虑是否为有效的，因为订单不存在删除的问题
    public static Transaction[] searchTransaction(int maybe_id, boolean flag) throws SQLException {
        Statement statement;
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql;
        ArrayList<Transaction> arrayList = new ArrayList<Transaction>();
        if (flag)
            sql = "select * from client_info where id =" + maybe_id;
        else
            sql = "select * from client_info ";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            Date date = resultSet.getDate("date");
            String license = resultSet.getString("license");
            String local = resultSet.getString("local");
            double miles = resultSet.getDouble("miles");
            double times = resultSet.getDouble("times");
            int client_id = resultSet.getInt("client_id");
            int driver_id = resultSet.getInt("driver_id");
            arrayList.add(new Transaction(id, date, license, local, miles, times, client_id, driver_id));
        }

        return arrayList.toArray(new Transaction[0]);
    }

    /**
     * 修改  即使是删除也是使用update完成的
     */
    public static boolean updateCar(Car car) throws SQLException {  //car 的id不能修改
        if (!connectToDB)
            throw new SQLException("Not Connected to Database");
        if (car.isValid()) {
            Statement statement;
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "update car_info set type='" + car.getType() + "',licese='" + car.getLicense() + "',purchase_date=" + car.getPurchase_date()
                    + ",price=" + car.getPrice() + ",maintain_date=" + car.getMaintain_date() +
                    ",mile=" + car.getMile() + ",working_time =" + car.getWorking_time() + ",rent_rate="
                    + car.getRent_rate() + " where id=" + car.getId(); //todo
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } else {
            //todo 已经是无效的数据了
            return false;
        }
    }

    public static boolean updateDriver(Driver driver) throws SQLException {  //driver 的id不能修改
        if (!connectToDB)
            throw new SQLException("Not Connected to Database");
        if (driver.isValid()) {
            Statement statement;
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "update driver_info set name='" + driver.getName() + "', enroll_date=" + driver.getEnroll_date()
                    + ",salary=" + driver.getSalary() + " where id=" + driver.getId();
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } else {
            //todo 已经是无效的数据了
            return false;
        }
    }

    public static boolean updateClient(Client client) throws SQLException {  //driver 的id不能修改
        if (!connectToDB)
            throw new SQLException("Not Connected to Database");
        if (client.isValid()) {
            Statement statement;
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "update client_info set name='" + client.getName() + "', company='" + client.getCompany()
                    + "',tel='" + client.getTel() + "',addr='" + client.getAddr() + "',zipcode='" + client.getZipcode()
                    + "' where id=" + client.getId();
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } else {
            //todo 已经是无效的数据了
            return false;
        }
    }

    //delete只能让管理员操作
    public static boolean deleteCar(int id) throws SQLException {
        //即使已经是无效信息，再做一次也无妨
        if (!connectToDB)
            throw new SQLException("Not Connected to Database");
        Statement statement;
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = "update car_info set valid=" + 0 + " where id=" + id; //todo
        statement.executeUpdate(sql);
        statement.close();
        return true;
    }

    public static boolean deleteDriver(int id) throws SQLException {
        //由于用户可见的一定是valid为1的对象，因此这里直接进行更改即可
        if (!connectToDB)
            throw new SQLException("Not Connected to Database");
        Statement statement;
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = "update driver_info set valid=" + 0 + " where id=" + id; //todo
        statement.executeUpdate(sql);
        statement.close();
        return true;
    }

    public static boolean deleteClient(int id) throws SQLException {
        //由于用户可见的一定是valid为1的对象，因此这里直接进行更改即可
        if (!connectToDB)
            throw new SQLException("Not Connected to Database");
        Statement statement;
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = "update client_info set valid=" + 0 + " where id=" + id; //todo
        statement.executeUpdate(sql);
        statement.close();
        return true;
    }

    public static void disconnectFromDB() { //当关闭窗口时调用  因为没有更多的操作了，所以不再上抛异常
        if (connectToDB) {
            try {
                Thread ed = new Thread(new ExitDialog());  //todo 做一下提示
                //ThreadGroup tg = new ThreadGroup();
                //ed.sleep(1000);
                connection.close();

            } catch (SQLException e) {
                //e.printStackTrace();
                System.out.print("database disconnect出错了");
            }// catch (InterruptedException e) {
            //				//e.printStackTrace();
            //			}
            finally {           //强制关闭
                connectToDB = false;
            }
        }
    }

}
