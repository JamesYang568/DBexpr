package handle;

public class Search_SQL_sen {
    /**
     * 查看某一个有效用户、汽车、司机
     */
    public static String get_a_client(int id) {
        return "select * from client_info where id =" + id + " and valid=" + 1 + ";";
    }

    public static String get_a_car(int id) {
        return "select * from car_info where id =" + id + " and valid=" + 1 + ";";
    }

    public static String get_a_driver(int id) {
        return "select * from driver_info where id =" + id + " and valid=" + 1 + ";";
    }

    /**
     * 查看所有空闲司机、汽车
     */
    public static String get_available_driver() {
        return "select * from driver_info where valid=" + 1 + " and available=" + 1 + ";";
    }

    public static String get_available_car() {
        return "select * from car_info where valid=" + 1 + " and available=" + 1 + ";";
    }

    /**
     * 查看所有效司机、车辆、客户
     */
    public static String get_all_driver() {
        return "select * from driver_info where valid=" + 1 + ";";
    }

    public static String get_all_car() {
        return "select * from car_info where valid=" + 1 + ";";
    }

    public static String get_all_client() {
        return "select * from client_info where valid=" + 1 + ";";
    }

    /**
     * 查找订单记录
     */
    public static String get_a_TR_by_id(int id) {
        // 按照订单号查找 找一个
        return "select * from transaction_info where id =" + id + ";";
    }

    public static String get_all_TR_by_client(int client_id) {
        // 按照客户号查找 找多个
        return "select * from transaction_info where client_id =" + client_id + " and valid=0;";
    }

    public static String get_all_TR_by_driver(int driver_id) {
        // 按照司机号查找 找多个
        return "select * from transaction_info where driver_id =" + driver_id + ";";
    }

    public static String get_available_TR() {
        // 查找待处理的事物
        return "select * from transaction_info where valid =" + 1 + ";";
    }

    public static String get_all_TR_by_client(int client_id, int car_id) {
        // 查找待处理的事物
        return "select * from transaction_info where valid =" + 1 + " and car_id=" + car_id + " and client_id=" + client_id + ";";
    }
}
