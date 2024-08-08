package Test;

import java.sql.*;

public class PrepareStatementExample {
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
//            String query = "INSERT INTO students(name,age,marks) VALUES(?,?,?)";
            String query = "SELECT marks FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1,"Ankita");
//            preparedStatement.setInt(2,25);
//            preparedStatement.setDouble(3,84.7);
            preparedStatement.setInt(1,1);
//
//            int rowAffected = preparedStatement.executeUpdate();
//
//            if(rowAffected>0){
//                System.out.println("Data Inserted/Updated/Deleted Successfully");
//            }
//            else{
//                System.out.println("Data Not Inserted/Updated/Deleted ");
//            }

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                double marks = resultSet.getDouble("marks");
                System.out.println("Marks: "+marks);
            }else {
                System.out.println("Marks not found ");
            }



        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
