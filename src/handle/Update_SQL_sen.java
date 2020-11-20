package handle;

public class Update_SQL_sen {
    public static String car_set_available(int id, int available) {
        return "update car_info set available=" + available + " where id=" + id + ";";
    }

    public static String car_run(int id, int pmile, int ptime) {
        return "update car_info set mile=mile+" + pmile + ", working_time=working_time+" + ptime + " where id=" + id + ";";
    }

    public static String driver_set_available(int id, int available) {
        return "update driver_info set available=" + available + " where id=" + id + ";";
    }

    public static String client_password(int id, String password) {
        return "update client_info set password=" + password + " where id=" + id + ";";
    }

    //租赁业务只能从valid变为invalid，不能变回去
    public static String transaction_invalid(int id) {
        return "update transaction_info set valid=" + 0 + " where id=" + id + ";";
    }
}
