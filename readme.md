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

### **Insert_Change_Item里面不是删除而是修改，此外insert时候不需要再给id，直接生成id就可以**

客户的租赁业务和查询需要放在一个里面，而管理员的租赁是从用户的那边获取的，因此需要传到管理员这里

OtherPanel部分放到最后在增加，主要是插入汽车、司机信息、查找所有信息

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