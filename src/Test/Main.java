package Test;
import java.sql.*;

public class Main {
//    private static final String url = "jdbc:mysql://localhost:3306/?user=root ";
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String username = "root";
    private static final String password = "KHUpr@1208";

    public static void main(String[] args) {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection  = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
//          String query = "select * from students";
//          String query = String.format("INSERT INTO STUDENTS(name,age,marks) VALUES('%s',%o,%f)","Rahul",23,74.5);
//          String query= String.format("UPDATE students SET marks = %f where id = %d",89.5,2);
            String query = "DELETE FROM students WHERE ID =2";
//          ResultSet resultSet = statement.executeQuery(query);
            int rowAffected = statement.executeUpdate(query);
//            while(resultSet.next()){
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                int age = resultSet.getInt("age");
//                double marks = resultSet.getDouble("marks");
//                System.out.println("ID: "+id);
//                System.out.println("NAME: "+name);
//                System.out.println("AGE: "+ age);
//                System.out.println("MARKS: "+marks);
//            }
            if(rowAffected>0){
                System.out.println("Data Inserted/Updated/Deleted Successfully");
            }
            else{
                System.out.println("Data Not Inserted/Updated/Deleted ");
            }



        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
