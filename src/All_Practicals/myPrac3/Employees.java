// Program to call a procedure from database and display the ResultSet.

package All_Practicals.myPrac3;
import java.sql.*;

// delimiter
// Create procedure getEmployees(IN mdeptno INT)
//BEGIN
// select empno, ename , job, e.deptno, dname from emp e join dept on e.deptno = d.deptno and
// e.deptno = mdeptno;
//END
public class Employees {
    public void getEmployees(int mdeptno) throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        String mysqlURL = "jdbc:mysql://localhost:3306/classicmodels?useSSL=true";

        boolean flag = false;

        try (Connection conn = DriverManager.getConnection(mysqlURL, "root", "KHUpr@1208")) {
            // calling a procedure with parameter
            CallableStatement cstmt = conn.prepareCall("{ call getEmployees(?) }");
            // set the parameter value
            cstmt.setInt(1, mdeptno);
            ResultSet rs = cstmt.executeQuery();
            System.out.println("Department Number : " + mdeptno);
            System.out.println("--------------------------------------------");
            System.out.println("Empno\tEname\tJob\t\tDeptno\tDepartment Name");
            System.out.println("--------------------------------------------");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" +
                        rs.getString(2) + "\t" +
                        rs.getString(3) + "\t" +
                        rs.getInt(4) + "\t" +
                        rs.getString(5) + "\t");
                flag = true;
            }
            if (!flag) System.out.println("There is no record exists for deptno: " + mdeptno);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
