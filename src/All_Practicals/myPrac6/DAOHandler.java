package All_Practicals.myPrac6;

import java.sql.*;
import java.util.Scanner;

public class DAOHandler {
    private int mrollno;
    private String mname, mmobile;
    Scanner sc = new Scanner(System.in);

    private static final String URL = "jdbc:mysql://localhost:3306/classicmodels?useSSL=true";
    private static final String USER = "root";
    private static final String PASSWORD = "KHUpr@1208";

    private static final String INSERT_SQL = "INSERT INTO CRUDStudent VALUES (?, ?, ?)";
    private static final String SELECT_SQL = "SELECT * FROM CRUDStudent WHERE rollno = ?";
    private static final String UPDATE_SQL = "UPDATE CRUDStudent SET name = ?, mobile = ? WHERE rollno = ?";
    private static final String DELETE_SQL = "DELETE FROM CRUDStudent WHERE rollno = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM CRUDStudent";

    private Connection getJDBCConnection() {
        Connection conn = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection Error: " + e.getMessage());
        }
        return conn;
    }

    private void getData() {
        System.out.println("Enter the valid rollno:");
        mrollno = sc.nextInt();
        System.out.println("Enter the valid name:");
        mname = sc.next();
        System.out.println("Enter the valid mobile:");
        mmobile = sc.next();
    }

    private void getRollno() {
        System.out.println("Enter the valid rollno:");
        mrollno = sc.nextInt();
    }

    public int addRecord() {
        int result = 0;
        getData();

        try (Connection conn = getJDBCConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {

            pstmt.setInt(1, mrollno);
            pstmt.setString(2, mname);
            pstmt.setString(3, mmobile);
            result = pstmt.executeUpdate();
            System.out.println("Record inserted!");

        } catch (SQLException e) {
            System.out.println("Insert Error: " + e.getMessage());
        }

        return result;
    }

    public void editRecord() {
        getRollno();
        boolean flag = false;

        try (Connection conn = getJDBCConnection();
             PreparedStatement pstmtSelect = conn.prepareStatement(SELECT_SQL)) {

            pstmtSelect.setInt(1, mrollno);
            ResultSet result = pstmtSelect.executeQuery();

            while (result.next()) {
                flag = true;
                System.out.println("Student Name: " + result.getString(2));
                System.out.println("Student Mobile: " + result.getString(3));
            }

            if (flag) {
                System.out.println("Enter the data to be updated:");
                System.out.println("Enter the valid Name:");
                mname = sc.next();
                System.out.println("Enter the valid Mobile:");
                mmobile = sc.next();

                try (PreparedStatement pstmtUpdate = conn.prepareStatement(UPDATE_SQL)) {
                    pstmtUpdate.setString(1, mname);
                    pstmtUpdate.setString(2, mmobile);
                    pstmtUpdate.setInt(3, mrollno);
                    pstmtUpdate.executeUpdate();
                    System.out.println("Record Updated!");
                }
            } else {
                System.out.println("Record not found, try later!");
            }

        } catch (SQLException e) {
            System.out.println("Edit Error: " + e.getMessage());
        }
    }

    public void deleteRecord() {
        getRollno();
        boolean flag = false;

        try (Connection conn = getJDBCConnection();
             PreparedStatement pstmtSelect = conn.prepareStatement(SELECT_SQL)) {

            pstmtSelect.setInt(1, mrollno);
            ResultSet result = pstmtSelect.executeQuery();

            while (result.next()) {
                flag = true;
                try (PreparedStatement pstmtDelete = conn.prepareStatement(DELETE_SQL)) {
                    pstmtDelete.setInt(1, mrollno);
                    pstmtDelete.executeUpdate();
                    System.out.println("Record Deleted!");
                }
            }

            if (!flag) {
                System.out.println("Record not found, try later!");
            }

        } catch (SQLException e) {
            System.out.println("Delete Error: " + e.getMessage());
        }
    }

    public void searchRecord() {
        getRollno();
        boolean flag = false;

        try (Connection conn = getJDBCConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_SQL)) {

            pstmt.setInt(1, mrollno);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                flag = true;
                System.out.println("Student Name: " + result.getString(2));
                System.out.println("Student Mobile: " + result.getString(3));
            }

            if (!flag) {
                System.out.println("Record not found, try later!");
            }

        } catch (SQLException e) {
            System.out.println("Search Error: " + e.getMessage());
        }
    }

    public void showAll() {
        try (Connection conn = getJDBCConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet result = pstmt.executeQuery()) {

            System.out.println("Rollno\tStudent Name\tMobile");

            boolean hasResults = false; // flag to check if there are any results
            while (result.next()) {
                hasResults = true;
                System.out.println(result.getInt(1) + "\t" +
                        result.getString(2) + "\t" +
                        result.getString(3));
            }

            if (!hasResults) {
                System.out.println("No records found.");
            }

        } catch (SQLException e) {
            System.out.println("Show All Error: " + e.getMessage());
        }
    }
}