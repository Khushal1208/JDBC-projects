// program to insert the record to underlying table.

package All_Practicals.myPrac1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbExample2 {
    public static void main(String[] args) {
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?useSSL=false","root","KHUpr@1208");
            Statement stmt = conn.createStatement();
            String SQL = "insert into empmast values(102,'kalpesh','Shah','9352868523',"+"'Kalpesh@aol.com','Programmer',10)";

            //rowaffected will contain the number of rows that were affected by the execution of the SQL statement.
            int rowaffected = stmt.executeUpdate(SQL);
            if(rowaffected>0){
                System.out.println("record inserted successfully !");
            }
            else{
                System.out.println("Error in insert !");
            }
            conn.close();

        }
        catch (Exception e){
            System.out.println("Error : "+e.getMessage());
        }
    }
}
