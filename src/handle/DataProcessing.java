package handle;

import DialogGUI.Help.*;
import entity.*;
import entity.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        String url = "jdbc:mysql://localhost:3306/rentDB"
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
            new SQLLinkErr();
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

            String sql = "show tables;";
            ResultSet rs = statement.executeQuery(sql);
//            ResultSet rs = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
            String[] tables = new String[4];
            int i = 0;
            while (rs.next()) {
                tables[i] = rs.getString(1);
                i++;
            }
            if (!Arrays.asList(tables).contains("driver_info"))
                statement.execute(Create_SQL_sen.sql1);
            if (!Arrays.asList(tables).contains("client_info"))
                statement.execute(Create_SQL_sen.sql2);
            if (!Arrays.asList(tables).contains("car_info"))
                statement.execute(Create_SQL_sen.sql3);
            if (!Arrays.asList(tables).contains("transaction_info"))
                statement.execute(Create_SQL_sen.sql4);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("database init出错了");
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
                    "," + car.getWorking_time() + "," + car.getRent_rate() + "," + 1 + "," + 1 + ")";
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
            String sql = "insert into client_info values (" + client.getId() + ",'" + client.getPassword() + "','"
                    + client.getName() + "','" + client.getCompany() + "','" + client.getTel() + "','" + client.getAddr() +
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
                    "," + transaction.getClient_id() + "," + transaction.getDriver_id() + "," + 1 + ")";
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
                    + "'," + driver.getEnroll_date() + "," + driver.getSalary() + "," + 1 + "," + 1 + ")";
            statement.executeUpdate(sql);
            statement.close();
//            doc = new Doc(ID, creator, timestamp, description, filename);
//            docs.put(ID, doc);
            return true;
        }
    }

    /**
     * 查询
     * 请判断返回的长度来确定是查询一个还是查询多个
     */
    public static Car[] searchCar(String sql) throws SQLException {
        Statement statement;
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ArrayList<Car> arrayList = new ArrayList<>();

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

    public static Driver[] searchDriver(String sql) throws SQLException {
        Statement statement;
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ArrayList<Driver> arrayList = new ArrayList<>();

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

    public static Client[] searchClient(String sql) throws SQLException {
        Statement statement;
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ArrayList<Client> arrayList = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String company = resultSet.getString("company");
            String tel = resultSet.getString("tel");
            String addr = resultSet.getString("addr");
            String zipcode = resultSet.getString("zipcode");
            arrayList.add(new Client(id, "", name, company, tel, addr, zipcode));
        }

        return arrayList.toArray(new Client[0]);
    }

    public static Transaction[] searchTransaction(String sql) throws SQLException {
        Statement statement;
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ArrayList<Transaction> arrayList = new ArrayList<>();

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

    public static boolean searchClient(int userid, String password) throws SQLException {
        Statement statement;
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        String sql = "select id,password from client_info where id =" + userid + "and valid=" + 1;
        ResultSet resultSet = statement.executeQuery(sql);

        String pwd = resultSet.getString("password");
        return password.equals(pwd);
    }

    /**
     * 修改（即使是删除也是使用update完成的）
     * 第一部分，修改信息
     */
    public static boolean updateCar(Car car) throws SQLException {  //car 的id不能修改
        //  汽车的类型、牌照、购买日期、价格不可以修改
        if (!connectToDB)
            throw new SQLException("Not Connected to Database");
        if (car.isValid()) {
            Statement statement;
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "update car_info set maintain_date=" + car.getMaintain_date() +
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
        //司机只能修改工资   这里Driver对象只要考虑salary和id是正确的即可
        if (!connectToDB)
            throw new SQLException("Not Connected to Database");
        if (driver.isValid()) {
            Statement statement;
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "update driver_info set salary=" + driver.getSalary() + " where id=" + driver.getId();
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } else {
            //todo 已经是无效的数据了
            return false;
        }
    }

    public static boolean updateClient(Client client) throws SQLException {  //driver 的id不能修改
        //客户除了密码之外都修改的函数  效率低
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

    /**
     * 修改
     * 第二部分，修改关键标志
     * 和外部类Update_SQL_sen直接耦合
     */
    public static void update(String sql) throws SQLException {
        if (!connectToDB)
            throw new SQLException("Not Connected to Database");
        Statement statement;
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        statement.executeUpdate(sql);
        statement.close();
    }


    /**
     * 修改
     * 第三部分，disable删除
     * 和外部类Delete_SQL_sen直接耦合
     */
    //delete只能让管理员操作 必须保证car和driver available是1的情况下才
    public static boolean delete(String sql) throws SQLException { //即使已经是无效信息，再做一次也无妨
        if (!connectToDB)
            throw new SQLException("Not Connected to Database");
        Statement statement;
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
