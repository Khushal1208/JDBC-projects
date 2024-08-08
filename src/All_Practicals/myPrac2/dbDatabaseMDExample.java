// Program to display Database Metadata.

package All_Practicals.myPrac2;
import java.sql.*;

public class dbDatabaseMDExample {
    public static void main(String[] args) {
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connURL = "jdbc:mysql://localhost:3306/classicmodels?useSSL=true";
            conn = DriverManager.getConnection(connURL,"root","KHUpr@1208");

            DatabaseMetaData dbmd = conn.getMetaData();
            System.out.println("Driver Name : "+ dbmd.getDriverName());
            System.out.println("driver version : "+dbmd.getDriverVersion());
            System.out.println("UserName : "+ dbmd.getUserName());
            System.out.println("Database Product Name : "+ dbmd.getDatabaseProductName());
            System.out.println("Database Product version : "+dbmd.getDatabaseProductVersion());

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
