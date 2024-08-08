// Program to create CachedRowSet
package All_Practicals.myPrac5;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class CachedRowSetExample {
    public static void main(String[] args) {
        CachedRowSet crs = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.setUrl("jdbc:mysql://localhost:3306/classicmodels?useSSl=true");
            crs.setUsername("root");
            crs.setPassword("KHUpr@1208");
            crs.setCommand("select * from tempemp");
            crs.execute();

            while(crs.next()){
                System.out.println("-------------------------");
                if(crs.getInt("empid")==1){
                    System.out.println("CRS found EmpID : 4 and will remove the record");
                    crs.deleteRow();
                    System.out.println("Remaining records are in cached resultSet");
                    while(crs.next()){
                        System.out.println("EmpID : ." + crs.getInt(1)+"\n");
                    }
                    break;
                }
            }
        }catch (Exception e){
            System.out.println("Error:"+e.getMessage());
            e.printStackTrace();
        }
    }
}
