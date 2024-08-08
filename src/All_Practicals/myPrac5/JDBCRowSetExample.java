// Program to create JDBC RowSet.
package All_Practicals.myPrac5;

import javax.sql.rowset.*;
import java.sql.*;

public class JDBCRowSetExample {
    public static void main(String[] args) {
        JdbcRowSet rowSet = null;
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create JdbcRowSet instance and set connection parameters
            rowSet = RowSetProvider.newFactory().createJdbcRowSet();
            rowSet.setUrl("jdbc:mysql://localhost:3306/classicmodels?useSSL=true");
            rowSet.setUsername("root");
            rowSet.setPassword("KHUpr@1208");

            // Set the SQL command and execute it
            rowSet.setCommand("SELECT * FROM tempemp");
            rowSet.execute();

            // Process the result set
            while (rowSet.next()) {
                System.out.println("__________________________________");
                System.out.println("EmpID  : " + rowSet.getInt(1));
                System.out.println("Name   : " + rowSet.getString(2));
                System.out.println("Salary : " + rowSet.getInt(3));
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (rowSet != null) {
                try {
                    rowSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
