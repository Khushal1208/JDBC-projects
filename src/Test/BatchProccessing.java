package Test;

import java.sql.*;
import java.util.Scanner;

public class BatchProccessing {
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
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.println("Enter name: ");
                String name = scanner.next();
                System.out.println("Enter age: ");
                int age = scanner.nextInt();
                System.out.println("Enter marks: ");
                Double marks = scanner.nextDouble();
                System.out.println("Enter more data(Y/N): ");
                String choice = scanner.next();
                String query = String.format("INSERT INTO students(name,age,marks) VALUES('%s',%d,%f)",name,age,marks);
                statement.addBatch(query);
                if(choice.toUpperCase().equals("N")){
                    break;
                }
            }
            int [] arr = statement.executeBatch();
//
//            if(rowAffected>0){
//                System.out.println("Data Inserted/Updated/Deleted Successfully");
//            }
//            else{
//                System.out.println("Data Not Inserted/Updated/Deleted ");
//           }
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
////            preparedStatement.setDouble(1,87.8);
////            preparedStatement.setInt(2,3);
//            preparedStatement.setInt(1,3);
            for(int i = 0 ; i < arr.length ; i++){
                if(arr[i] == 0){
                    System.out.println("Query: "+i+" not excecuted successfully!!");
                }
            }


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
