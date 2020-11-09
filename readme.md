car driver client 通过valid变量判断是否有效（1有效），因此删除一个记录是update而不是delete
在创建这些类的对象时一定要注意valid的控制，因为默认情况下新建的对象valid为1


要保证用户看到的是valid信息，但是管理员可以看到所有的信息，包括是否有效要展示出来


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