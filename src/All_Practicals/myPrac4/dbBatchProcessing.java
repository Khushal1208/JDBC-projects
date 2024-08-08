// Program to do the batfvh processing to undrlying table

package All_Practicals.myPrac4;
import  java.sql.*;

public class dbBatchProcessing {
    public static void main(String[] args) {
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String connURL = "jdbc:mysql://localhost:3306/classicmodels?useSSL=true";
            Connection conn = DriverManager.getConnection(connURL,"root","KHUpr@1208");
            Statement stmt = conn.createStatement();
            //Need to set autocommit = false before batch processing
            conn.setAutoCommit(false);
            stmt.addBatch("insert into tempstud values(1,'Mahesh Shah')");
            stmt.addBatch("insert into tempstud values(2,'Dinesh Shah')");
            stmt.addBatch("insert into tempstud values(3,'Paresh Shah')");
            stmt.addBatch("insert into tempstud values(4,'Umesh Shah')");

            int tot[] = stmt.executeBatch();

            //saving the changes
            conn.commit();
            System.out.println("Records inserted....:"+tot.length);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
