package All_Practicals.myPrac3;

import java.sql.SQLException;

public class dbCallableExample {
    public static void main(String[] args) {
        try {
            Employees empObj = new Employees();
            empObj.getEmployees(40);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}