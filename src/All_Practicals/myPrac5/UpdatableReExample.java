package All_Practicals.myPrac5;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Stack;

public class UpdatableReExample {
    public static void main(String[] args) {
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String mysqlURL = "jdbc:mysql://localhost:3306/classicmodels?useSSL=false";
            Connection con = DriverManager.getConnection(mysqlURL,"root","KHUpr@1208");
            System.out.println("Connection Established......");

            //Creating a Statement object
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            //Retrieving the data
            ResultSet rs = stmt.executeQuery("Select * from tempemp");

            //Printing the contents of the table
            System.out.println("Contents of the table: ");
            rs.beforeFirst();
            while(rs.next()){
                System.out.print("EmpID : "+ rs.getInt("empid"));
                System.out.print("Name : "+ rs.getString("ename"));
                System.out.println("Salary : "+ rs.getString("salary"));
            }
            System.out.println();
            //Moving the pointer to the starting point in the ResultSet

            rs.beforeFirst();

            //updating the salary of each employee by 5000
            while (rs.next()){
                //Retrive by column name
                int newSal = rs.getInt("Salary") + 5000;
                rs.updateInt("Salary",newSal);
                rs.updateRow();
            }
            System.out.println("Contents of the ResultSet after increasing salaries");
            rs.beforeFirst();
            while (rs.next()){
                System.out.print("ID: "+rs.getInt("empid"));
                System.out.print(", Name: "+rs.getString("ename"));
                System.out.println(", Salary: "+ rs.getString("salary"));
            }
                System.out.println();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
