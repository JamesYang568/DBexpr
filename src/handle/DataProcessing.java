package handle;

import java.util.Enumeration;
import java.util.Hashtable;
import dialogpackage.ExitDialog;
import dialogpackage.SQLLinkErr;
import personnel.Administrator;
import personnel.*;
import java.sql.*;

public  class DataProcessing {

	private static boolean connectToDB;
	private static Connection connection;
	static Hashtable<String, User> users;
	static Hashtable<String, Doc> docs;

	static {
		users = new Hashtable<String, User>();
		docs = new Hashtable<String,Doc>();

		connectToBD();
		Init();// 第一次生成静态时的初始化
		// 我们不需要构造函数注意，所以必须自动调用方法init

	}

	public static void connectToBD() {
		// connect to database
		String driverName="com.mysql.jdbc.Driver";           // 加载数据库驱动类
		String url="jdbc:mysql://localhost:3306/document"
				+ "?useSSL=FALSE&&serverTimezone=UTC&&useUnicode=true&characterEncoding=utf8";  
		// 声明数据库的URL以及格式
		String user="root";                     // 数据库用户
		String password="1234";
		try {
			Class.forName(driverName);
			System.out.println("连接数据库..."); // 建立数据库连接
			connection=DriverManager.getConnection(url, user, password);
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

	public static void Init() { //第一次获取文件
		try {
			Statement statement;
			statement = connection.createStatement( 
					ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );

			String sql1="select * from user_info";
			String sql2="select * from doc_info";
			//获得用户信息
			ResultSet resultSet = statement.executeQuery(sql1);
			while (resultSet.next()){
				String username=resultSet.getString("username");
				String pwd=resultSet.getString("password");
				String role=resultSet.getString("role");
				if(role.equalsIgnoreCase("operator"))
					users.put(username,new Operator(username,pwd,role));
				else if(role.equalsIgnoreCase("browser"))
					users.put(username,new Browser(username,pwd,role));
				else
					users.put(username,new Administrator(username,pwd,role));
			}
			//获得文件信息
			resultSet = statement.executeQuery(sql2);
			while(resultSet.next()) {
				String id = resultSet.getString("id");
				String creator = resultSet.getString("creator");
				String timestamp = resultSet.getString("timestamp");
				String description = resultSet.getString("description");
				String filename = resultSet.getString("filename");
				Timestamp ts = Timestamp.valueOf(timestamp);
				docs.put(id, new Doc(id, creator, ts, description, filename));
			}
			resultSet.close();                        
			statement.close(); 
		}
		catch (SQLException e) {
			//e.printStackTrace();
			System.out.print("datap init出错了");
		}
	}



	public static Doc searchDoc(String ID) throws SQLException {
		if (docs.containsKey(ID)) {
			Doc temp =docs.get(ID);
			return temp;
		}
		return null;
	}

	public static Enumeration<Doc> getAllDocs() throws SQLException{		
		Enumeration<Doc> e  = docs.elements();
		return e;
	} 

	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{
		Doc doc;		
		if ( !connectToDB ) 
			throw new SQLException( "Not Connected to Database" );
		if (docs.containsKey(ID)) {
			return false;
		}
		else{
			Statement statement;
			statement = connection.createStatement( 
					ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
			String sql="insert into doc_info values ('"
					+ID+"','"+creator+"','"+timestamp+"','"+description+"','"+filename+"')";
			statement.executeUpdate(sql);
			statement.close();
			doc = new Doc(ID,creator,timestamp,description,filename);
			docs.put(ID, doc);
			return true;
		}
	} 

	public static User searchUser(String name) throws SQLException{
		if ( !connectToDB ) 
			throw new SQLException( "Not Connected to Database" );

		if (users.containsKey(name)) {
			return users.get(name);			
		}
		return null;
	}

	public static User searchUser(String name, String password) throws SQLException {
		if ( !connectToDB ) 
			throw new SQLException( "Not Connected to Database" );

		if (users.containsKey(name)) {
			User temp =users.get(name);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;
	}

	public static Enumeration<User> getAllUser() throws SQLException{
		if ( !connectToDB ) 
			throw new SQLException( "Not Connected to Database" );

		Enumeration<User> e  = users.elements();
		return e;
	}

	/**实现数据库的操作——更新用户*/
	public static boolean updateUser(String name, String password, String role) throws SQLException{
		User user;
		if ( !connectToDB ) 
			throw new SQLException( "Not Connected to Database" );


		if (users.containsKey(name)) {
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);

			Statement statement;
			statement = connection.createStatement( 
					ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
			String sql="update user_info set password='"+password+"' where username='"+name+"'";
			statement.executeUpdate(sql);
			statement.close();
			users.put(name, user);
			return true;
		}else
			return false;
	}

	/**实现数据库的操作——增加用户*/
	public static boolean insertUser(String name, String password, String role) throws SQLException{
		User user;

		if ( !connectToDB ) 
			throw new SQLException( "Not Connected to Database" );

		if (users.containsKey(name))
			return false;
		else{
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);	
			Statement statement;
			statement = connection.createStatement( 
					ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
			String sql="insert into user_info values('"+name+"','"+password+"','"+role+"')";
			statement.executeUpdate(sql);
			users.put(name, user);
			return true;
		}
	}

	/**实现数据库的操作——删除用户*/
	public static boolean deleteUser(String name) throws SQLException{
		if ( !connectToDB ) 
			throw new SQLException( "Not Connected to Database" );


		if (users.containsKey(name)){
			users.remove(name);
			Statement statement;
			statement = connection.createStatement( 
					ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
			String sql="delete from user_info where username='"+name+"'";
			statement.executeUpdate(sql);
			return true;
		}else
			return false;

	}	

	public static void disconnectFromDB() { //当关闭窗口时调用  因为没有更多的操作了，所以不再上抛异常
		if ( connectToDB ){        
			try{
				Thread ed = new Thread(new ExitDialog());
				//ThreadGroup tg = new ThreadGroup();
				//ed.sleep(1000);
				connection.close();

			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.print("datap disconnect出错了");
			}// catch (InterruptedException e) {
			//				//e.printStackTrace();
			//			}
			finally{           //强制关闭                                 
				connectToDB = false;              
			}                             
		} 
	}           

}
