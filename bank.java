import java.sql.*;
import java.util.Scanner;

public class bank {

    static final String url = "jdbc:mysql://localhost:3306/bank";
    static final String user = "root";
    static final String password = "root1234";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,password);

            while (true) {
                System.out.println("\n1.Create Account");
                System.out.println("2.Update Balance");
                System.out.println("3.Delete Account");
                System.out.println("4.View Account");
                System.out.println("5.Exit");
                System.out.print("Choose: ");

                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> createAccount(con, sc);
                    case 2 -> updateBalance(con, sc);
                    case 3 -> deleteAccount(con, sc);
                    case 4 -> viewAccount(con, sc);
                    case 5 -> {
                        con.close();
                        System.out.println("Thank you!");
                        return;
                    }
                    default -> System.out.println("Invalid choice");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    static void createAccount(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Account No: ");
        int acc = sc.nextInt();
        System.out.print("Enter Balance: ");
        double bal = sc.nextDouble();

        String q = "INSERT INTO accounts VALUES (?,?)";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, acc);
        ps.setDouble(2, bal);
        ps.executeUpdate();

        System.out.println("Account Created Successfully");
    }

    
    static void updateBalance(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Account No: ");
        int acc = sc.nextInt();
        System.out.print("Enter New Balance: ");
        double bal = sc.nextDouble();

        String q = "UPDATE accounts SET bal=? WHERE acc=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setDouble(1, bal);
        ps.setInt(2, acc);

        int rows = ps.executeUpdate();
        System.out.println(rows > 0 ? "Balance Updated" : "Account Not Found");
    }

    
    static void deleteAccount(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Account No: ");
        int acc = sc.nextInt();

        String q = "DELETE FROM accounts WHERE acc=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, acc);

        int rows = ps.executeUpdate();
        System.out.println(rows > 0 ? "Account Deleted" : "Account Not Found");
    }

    
    static void viewAccount(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Account No: ");
        int acc = sc.nextInt();

        String q = "SELECT * FROM accounts WHERE acc=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, acc);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("Account No: " + rs.getInt("acc"));
            System.out.println("Balance: " + rs.getDouble("bal"));
        } else {
            System.out.println("Account Not Found");
        }
    }
}