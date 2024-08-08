// Program to display all the records from emp table.
package All_Practicals.myPrac1;
import java.sql.*;
public class dbExample1 {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?useSSL=false","root","KHUpr@1208");

            // Create a statement object to execute the query
            Statement stmt = conn.createStatement();

            // Execute the query to retrieve data from the emp table
            ResultSet result = stmt.executeQuery("select*from emp");

            // Print the column headers
            System.out.println("-----------------------------------");
            System.out.println("Empno\tEname\tDesignation");
            System.out.println("-----------------------------------");

            // Process and print the result set
            while(result.next()){
                System.out.println(result.getInt(1)+"\t"+
                        result.getString(2)+
                        "\t"+result.getString(3));
            }
            // close the connection
            conn.close();

        }catch (Exception e){
            System.out.println("Exception: "+e.getMessage());
        }
    }
}
