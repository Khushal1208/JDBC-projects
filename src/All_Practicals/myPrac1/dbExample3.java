//program to update the record to underlying table

package All_Practicals.myPrac1;
import java.sql.*;

public class dbExample3 {
    public static void main(String[] args) {
        Connection conn = null ;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

//            if you are connecting to a MySQL database over a secure network or for development purposes,
//            useSSL=false might be acceptable. For production environments, it's advisable to enable SSL(Secure sockets layer)
//            to protect the data transmitted across the network.
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?useSSL=true","root","KHUpr@1208");
            Statement stmt = conn.createStatement();
            String SQL = "update empmast set mobile = '9824098242',"+
                    "email = 'kalpesh@aol.com' where empno = 102";
            int rowAffected = stmt.executeUpdate(SQL);

            if(rowAffected>0){
                System.out.println("Record updated succseefully !");
            }
            else {
                System.out.println("error in update !");
            }
            conn.close();

        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
}
