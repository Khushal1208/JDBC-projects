package Test;

import java.sql.*;

public class CRUDOperetiion {
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
//            String query = "UPDATE students set marks = ? WHERE id = ?";
            String query = "DELETE FROM students  WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setDouble(1,87.8);
//            preparedStatement.setInt(2,3);
            preparedStatement.setInt(1,3);

            int rowAffected = preparedStatement.executeUpdate();

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
