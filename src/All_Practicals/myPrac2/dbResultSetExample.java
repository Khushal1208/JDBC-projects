// Program to display Resultset metadata
package All_Practicals.myPrac2;

import java.sql.*;

public class dbResultSetExample {
    public static void main(String[] args) {
        Connection conn = null ;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionURl = "jdbc:mysql://localhost:3306/classicmodels?useSSL=false";
            conn = DriverManager.getConnection(connectionURl,"root","KHUpr@1208");
            PreparedStatement pstmt = conn.prepareStatement("select empno,ename,job,hiredate from emp");
            ResultSet result = pstmt.executeQuery();
            ResultSetMetaData resmd = result.getMetaData();
            System.out.println("Total coloumns: "+ resmd.getColumnCount());
            System.out.println("Column Name of 1st column: "+ resmd.getColumnName(1));
            System.out.println("Column Type Name of 1st column:" + resmd.getColumnTypeName(1));

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
