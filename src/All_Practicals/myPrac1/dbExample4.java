//Program to delete the record to underlying table

package All_Practicals.myPrac1;
import java.sql.*;
public class dbExample4 {
    public static void main(String[] args) {
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?useSSL=false","root","KHUpr@1208");
            Statement stmt = conn.createStatement();
            String SQL = "delete from empmast where empno = 102";
            int rowAffected  = stmt.executeUpdate(SQL);
            if(rowAffected>0){
                System.out.println("Record deleted successfully");
            }
            else {
                System.out.println("Error in delete operation");
            }
            conn.close();

        }catch(Exception e){
            System.out.println("Error : "+e.getMessage());
        }
    }
}
