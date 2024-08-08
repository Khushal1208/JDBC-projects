//Program to get the input from user and perfrom the inset operation
// to underlying table.
//Prepared Statement uses.

package All_Practicals.myPrac1;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class dbExample5 {
    public static void main(String[] args) {
        int mEmpno,mDeptno;
        String mFname,mLname,mMobile,mEmail,mJob;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the valid employee number:");
        mEmpno = sc.nextInt();
        sc.nextLine(); //consume the new line character
        System.out.println("Enter the valid employee fname:");
        mFname = sc.nextLine();
        System.out.println("Enter the valid employee lname:");
        mLname = sc.nextLine();
        System.out.println("Enter the valid employee mobile:");
        mMobile = sc.nextLine();
        System.out.println("Enter the valid employee email:");
        mEmail = sc.nextLine();
        System.out.println("Enter the valid employee job:");
        mJob = sc.nextLine();
        System.out.println("Enter the valid employee deptno:");
        mDeptno = sc.nextInt();

        Connection conn = null ;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?useSSL=false","root","KHUpr@1208");
            String SQL = "insert into empmast values(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(SQL);

            //set the values to each parameter
            pstmt.setInt(1,mEmpno);
            pstmt.setString(2,mFname);
            pstmt.setString(3,mLname);
            pstmt.setString(4,mMobile);
            pstmt.setString(5,mEmail);
            pstmt.setString(6,mJob);
            pstmt.setInt(7,mDeptno);

            int rowAffected = pstmt.executeUpdate();
            if(rowAffected>0){
                System.out.println("Record inserted successfully");
            }
            else {
                System.out.println("Error in insert !");
            }
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        sc.close();
    }
}
