package handle;

public class Delete_SQL_sen {
    public static String delete_car(int id) {
        return "update car_info set valid=" + 0 + " where id=" + id + " and available = 1;";
    }

    public static String delete_driver(int id) {
        return "update driver_info set valid=" + 0 + " where id=" + id + " and available = 1;";
    }

    public static String delete_client(int id) {
        return "update client_info set valid=" + 0 + " where id=" + id + ";";
    }

}
