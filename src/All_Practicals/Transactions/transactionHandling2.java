package All_Practicals.Transactions;

import java.sql.*;
import java.util.Scanner;

public class transactionHandling2 {
    static String url = "jdbc:mysql://localhost:3306/transaction_handling?useSSL=true";
    private static final String username = "root";
    private static final String password = "KHUpr@1208";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Scanner scanner = new Scanner(System.in)) {

            connection.setAutoCommit(false);

            String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE acc_no = ?";
            String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE acc_no = ?";

            while (true) {
                System.out.print("Enter account number to debit (or 'exit' to quit): ");
                String debitAccountNumber = scanner.next();
                if (debitAccountNumber.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.print("Enter account number to credit: ");
                String creditAccountNumber = scanner.next();
                System.out.print("Enter amount to transfer: ");
                double amount = scanner.nextDouble();

                if (isSufficient(connection, debitAccountNumber, amount)) {
                    try (PreparedStatement debitPstmt = connection.prepareStatement(debitQuery);
                         PreparedStatement creditPstmt = connection.prepareStatement(creditQuery)) {

                        debitPstmt.setDouble(1, amount);
                        debitPstmt.setString(2, debitAccountNumber);

                        creditPstmt.setDouble(1, amount);
                        creditPstmt.setString(2, creditAccountNumber);

                        int affectedRows1 = debitPstmt.executeUpdate();
                        int affectedRows2 = creditPstmt.executeUpdate();

                        if (affectedRows1 > 0 && affectedRows2 > 0) {
                            connection.commit();
                            System.out.println("Transaction successful.");
                        } else {
                            connection.rollback();
                            System.out.println("Transaction failed.");
                        }
                    } catch (SQLException e) {
                        connection.rollback();
                        System.out.println("Transaction failed. Rolled back.");
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Insufficient balance.");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static boolean isSufficient(Connection connection, String accountNumber, double amount) {
        String query = "SELECT balance FROM accounts WHERE acc_no = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, accountNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    double currentBalance = resultSet.getDouble("balance");
                    return amount <= currentBalance;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
