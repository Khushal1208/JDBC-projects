// Program to create WebRosSet.
package All_Practicals.myPrac5;

import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;
import java.io.FileOutputStream;

public class dbWebrowSetExample {
    public static void main(String[] args) {
        WebRowSet wrs = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            wrs = RowSetProvider.newFactory().createWebRowSet();
            wrs.setUrl("jdbc:mysql://localhost:3306/classicmodels?useSSL=true");
            wrs.setUsername("root");
            wrs.setPassword("KHUpr@1208");
            wrs.setCommand("select * from emp");
            wrs.execute();

            FileOutputStream fos = new FileOutputStream("emp.xml");
            wrs.writeXml(fos);
            System.out.println("XML file creation is done");

        }catch (Exception e){
            System.out.println("Error : "+e.getMessage());
        }
    }
}
