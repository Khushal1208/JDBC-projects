package All_Practicals.myPrac6;

import java.util.Scanner;

public class CRUDUsingJDBC {
    public static void main(String[] args) {
        int choice = 0;
        Scanner getch = new Scanner(System.in);
        DAOHandler crudObj = new DAOHandler();

        while (choice != 6) {
            System.out.println("1 - Add Record");
            System.out.println("2 - Edit Record");
            System.out.println("3 - Delete Record");
            System.out.println("4 - Search Record");
            System.out.println("5 - View All Records");
            System.out.println("6 - Exit");

            System.out.println("Enter the valid choice:");
            choice = getch.nextInt();

            switch (choice) {
                case 1 -> {
                    try {
                        crudObj.addRecord();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        crudObj.editRecord();
                    } catch (Exception e) {
                        System.out.println("Edit Error: " + e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        crudObj.deleteRecord();
                    } catch (Exception e) {
                        System.out.println("Delete Error: " + e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        crudObj.searchRecord();
                    } catch (Exception e) {
                        System.out.println("Search Error: " + e.getMessage());
                    }
                }
                case 5 -> {
                    try {
                        crudObj.showAll();
                    } catch (Exception e) {
                        System.out.println("Show All Error: " + e.getMessage());
                    }
                }
                case 6 -> System.out.println("Thank you for using CRUD operation");
                default -> System.out.println("Invalid choice!");
            }
        }
        getch.close(); // Close Scanner resource
    }
}
