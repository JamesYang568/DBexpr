package handle;

import entity.*;

import java.sql.SQLException;
import java.util.Vector;

public class ParseEntity {
    public Vector ParseDriver() {
        Driver[] data = new Driver[0];
        try {
            data = DataProcessing.searchDriver(Search_SQL_sen.get_all_driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Vector vector = new Vector();
        for (Driver driver : data) { //生成一行
            Vector temp = new Vector();
            temp.add(driver.getId());
            temp.add(driver.getName());
            temp.add(driver.getEnroll_date());
            vector.add(temp);
        }
        return vector;
    }

    public Vector ParseCar() {
        Car[] data = new Car[0];
        try {
            data = DataProcessing.searchCar(Search_SQL_sen.get_all_car());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Vector vector = new Vector();
        for (Car car : data) {
            Vector temp = new Vector();
            temp.add(car.getId());
            temp.add(car.getType());
            temp.add(car.getRent_rate());
            temp.add(car.getPurchase_date());
            temp.add(car.getMaintain_date());
            temp.add(car.getMile());
            temp.add(car.getWorking_time());
            vector.add(temp);
        }
        return vector;
    }

    public Vector ParseClient() {
        Client[] data = new Client[0];
        try {
            data = DataProcessing.searchClient(Search_SQL_sen.get_all_client());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Vector vector = new Vector();
        for (Client client : data) {
            Vector temp = new Vector();
            temp.add(client.getId());
            temp.add(client.getName());
            temp.add(client.getCompany());
            temp.add(client.getTel());
            temp.add(client.getAddr());
            temp.add(client.getZipcode());
            vector.add(temp);
        }
        return vector;
    }
}
