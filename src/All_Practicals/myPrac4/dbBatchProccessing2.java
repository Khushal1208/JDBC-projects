// Program to do the batch processing to undrlying table

package All_Practicals.myPrac4;
import  java.sql.*;

public class dbBatchProccessing2 {
    public static void main(String[] args) {
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String connURL = "jdbc:mysql://localhost:3306/classicmodels?useSSL=true";
            Connection conn = DriverManager.getConnection(connURL,"root","KHUpr@1208");
            Statement stmt = conn.createStatement();
            //Need to set autocommit = false before batch processing
            conn.setAutoCommit(false);
            String SQL = "insert into tempstud values(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(SQL);

            //Record-1

            // it is inserted like this:
            // insert into tempstud values(1,"Mahesh");
            pstmt.setInt(1,1);
            pstmt.setString(2,"Mahesh");
            pstmt.addBatch();

            //Record-2
            // insert into tempstud values(2,"Paresh");
            pstmt.setInt(1,2);
            pstmt.setString(2,"Paresh");
            pstmt.addBatch();

            //Record-3
            // insert into tempstud values(3,"Dinesh");
            pstmt.setInt(1,3);
            pstmt.setString(2,"Dinesh");
            pstmt.addBatch();

            //Record-4
            // insert into tempstud values(4,"Yogesh");
            pstmt.setInt(1,4);
            pstmt.setString(2,"Yogesh");
            pstmt.addBatch();

            //Record-5
            // insert into tempstud values(5,"Umesh");
            pstmt.setInt(1,5);
            pstmt.setString(2,"Umesh");
            pstmt.addBatch();

            //Executing the batch
            int tot[] = pstmt.executeBatch();

            //Saving the changes
            conn.commit();
            System.out.println("Records inserted....:" + tot.length);
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
