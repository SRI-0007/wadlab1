import java.sql.*;
import java.util.*;

public class Student{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college",
                    "root",
                    "1234"
            );

            System.out.println("Connected to Database!");

            
            PreparedStatement create = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS student (id INT PRIMARY KEY, name VARCHAR(50), age INT)"
            );
            create.executeUpdate();

            int choice;

            do {
                System.out.println("\n1.Insert 2.View 3.Update 4.Delete 5.Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                if (choice == 1) {

                    System.out.print("ID: ");
                    int id = sc.nextInt();

                    System.out.print("Name: ");
                    String name = sc.next();

                    System.out.print("Age: ");
                    int age = sc.nextInt();

                    PreparedStatement ps = con.prepareStatement(
                            "INSERT INTO student VALUES(?,?,?)"
                    );

                    ps.setInt(1, id);
                    ps.setString(2, name);
                    ps.setInt(3, age);

                    ps.executeUpdate();
                    System.out.println("Inserted Successfully");

                }

                else if (choice == 2) {

                    PreparedStatement ps = con.prepareStatement(
                            "SELECT * FROM student"
                    );

                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        System.out.println(
                                rs.getInt(1) + " " +
                                rs.getString(2) + " " +
                                rs.getInt(3)
                        );
                    }

                }

               
                else if (choice == 3) {

                    System.out.print("ID: ");
                    int id = sc.nextInt();

                    System.out.print("New Name: ");
                    String name = sc.next();

                    System.out.print("New Age: ");
                    int age = sc.nextInt();

                    PreparedStatement ps = con.prepareStatement(
                            "UPDATE student SET name=?, age=? WHERE id=?"
                    );

                    ps.setString(1, name);
                    ps.setInt(2, age);
                    ps.setInt(3, id);

                    ps.executeUpdate();
                    System.out.println("Updated Successfully");

                }

               
                else if (choice == 4) {

                    System.out.print("ID: ");
                    int id = sc.nextInt();

                    PreparedStatement ps = con.prepareStatement(
                            "DELETE FROM student WHERE id=?"
                    );

                    ps.setInt(1, id);

                    ps.executeUpdate();
                    System.out.println("Deleted Successfully");
                }

            } while (choice != 5);

            con.close();
            sc.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
