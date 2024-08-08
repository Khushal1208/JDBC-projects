package All_Practicals.ImageAdd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class image_into_DB {
    public static void main(String[] args) throws ClassNotFoundException{
        String url = "jdbc:mysql://localhost:3306/classicmodels?useSSL=true";
        String username = "root";
        String password = "KHUpr@1208";
        String image_path = "C:\\Users\\khush\\Downloads\\Github_image.jpg" ;
        String query = "insert into image_table(image_data) values(?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully!!!");

        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("connection Established Successfully !!");
            FileInputStream fis = new FileInputStream(image_path); //it will convert image.jpg to binary format
            byte [] imageData = new byte[fis.available()];
            fis.read(imageData);
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setBytes(1,imageData);

            int affectedRows = pstmt.executeUpdate();
            if(affectedRows>0){
                System.out.println("image inserted successfully!!");
            }
            else System.out.println("image not inserted");


        }catch (SQLException  e){
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
