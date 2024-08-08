package All_Practicals.ImageAdd;

import java.io.*;
import java.sql.*;

public class image_into_folder {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/classicmodels?useSSL=true";
        String username = "root";
        String password = "KHUpr@1208";
        String folder_path = "C:\\Users\\khush\\Downloads\\";
        String query = "SELECT image_data FROM image_table WHERE image_id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully!!!");

            try (Connection con = DriverManager.getConnection(url, username, password);
                 PreparedStatement pstmt = con.prepareStatement(query)) {

                System.out.println("Connection Established Successfully!!");

                pstmt.setInt(1, 1);
                try (ResultSet result = pstmt.executeQuery()) {
                    if (result.next()) {
                        byte[] image_data = result.getBytes("image_data");
                        String image_path = folder_path + "Github_Image.jpg";
                        try (OutputStream os = new FileOutputStream(image_path)) {
                            os.write(image_data);
                            System.out.println("Image saved successfully to " + image_path);
                        } catch (IOException e) {
                            System.err.println("Error writing the image file: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Image not found!!!");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found: " + e.getMessage());
        }
    }
}
