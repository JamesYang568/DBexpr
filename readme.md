car driver client 通过valid变量判断是否有效（1有效），因此删除一个记录是update而不是delete
在创建这些类的对象时一定要注意valid的控制，因为默认情况下新建的对象valid为1


要保证用户看到的是valid信息，但是管理员可以看到所有的信息，包括是否有效要展示出来

available 代表当前汽车或者司机是否空闲 1为空闲，0为不空闲


功能：
1、客户和管理员的登录、客户的注册  登录检查需要判断用户的valid

2、客户自己的密码修改 、注销自己

3、管理员增加和删除客户、汽车、司机 

4、管理员可以查看所有有效用户、汽车、司机

5、客户查询可以使用的汽车

6、管理员查询可以使用的汽车、司机

~~7、管理员查看所有正在使用的汽车、司机~~

8、管理员可以查看所有订单

9、客户可以查看自己所有的订单

10、客户发出申请，指定对应的车辆，告知*地点、往返里程、行驶时间*

11、管理员根据用户的申请，核对对应的*车辆、指派司机，加入客户号*生成订单

12、客户对订单进行核对并且确认或者否认 

13、管理员将订单存入数据库 一旦提交，则*修改司机、车辆available为0*

14、客户完成订单 _订单失效，修改汽车的运行公里、运行小时，汽车、司机也就可以空闲了_

15、管理员可以更新车辆、司机、客户信息（但是不能更改密码）   订单信息一旦生成不可以修改（除了valid标志位） 此功能不常使用，因此update函数效率较低

管理员查看所有订单

~~如何保证发票号是自动生成的并且不重复？~~
如何实现两个端消息的传递，管理员这边能够知道顾客的选择？
先输入客户的id号——和顾客的界面绑定！！！之后点击查询申请，获取顾客图形界面的信息。

共用的部分需要在ClientConsole实现好。

到底要不要把id的接口给用户

客户的租赁业务和查询需要放在一个里面，而管理员的租赁是从用户的那边获取的，因此需要传到管理员这里

用户可以注销自己

     public static Entity search_entity(String cls, int id) {
         String entity_name;
         if (cls.equalsIgnoreCase("driver"))
             entity_name = "driver";
         else if (cls.equalsIgnoreCase("Car"))
             entity_name = "car";
         else
             entity_name = "client";
         try {
             Statement statement;
             statement = connection.createStatement(
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
 
             String sql = "select * from " + entity_name + "_info where id =" + id;
             //entity_name = entity_name.substring(0, 1).toUpperCase() + entity_name.substring(1);
             //获得用户信息
             ResultSet resultSet = statement.executeQuery(sql);
             //Class<?> c = Class.forName("entity." + entity_name);
             //Entity obj = (Entity)c.newInstance();
 
             String username = resultSet.getString("username");
             String pwd = resultSet.getString("password");
             String role = resultSet.getString("role");
 
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
     
整体程序分为四个模块：

Entity模块包含了所有需要传递的信息对象，其中数据全部是根据语义值进行赋值。

Handel模块用于外调工具（不安全，大量静态函数）
    包括DataProcessing，是对数据库的操作接口，所有检验都在类内进行；Create|Search|Delete|Update_SQL_sen是高度封装的SQL语句，方便调用，降低耦合；
ParseEntity是专门用于对数据块进行数组向量整合的，同时还包含了sql.date和util.date的转换。

DialogGUI.Help模块进行的是信息提示和与GUI有关的信息处理类
    TableParse用于对表格进行优化，从而降低界面类对数据库的依赖情况，耦合性较高。InputParse用于对输入数据格式化，包括ID和检验和Date的格式规范
    ExitDialog是退出整个程序时弹出的弹窗，使用它的目的是保护进程安全，进程的最后将会在DP类中使用它，作为最后一次调用，防止数据库断连后还有使用的可能
    SQLLinkErr是数据库连接报错，为了降低耦合性，将其作为一个类，可以在任何地方使用。
    
DialogGUI.Interface是图形界面的主要模块，其中包括了服务端、客户端的界面类以及一些共用的接口
    整个程序从welcome运行，下边界在welcome类（调用的起点），所有的功能使用都必须经过welcome的许可。
    服务端的主界面为ServerConsole，客户端的主界面为ClientConsole，整个程序的其他主功能都需要被这两个类调用
    welcome和xxxConsole无关，因此不存在耦合关系，(数据库可以在任何时间崩溃，welcome仍然可以直接运行。)
    界面主要以JPanel实现，内聚较高。