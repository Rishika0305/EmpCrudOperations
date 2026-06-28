import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Employeedao {
    Connection con=null;
    Statement stmt=null;
    PreparedStatement ps1=null, ps2=null;
    ResultSet rs=null;

    private static final String url = "jdbc:mysql://localhost:3306/employee-db";
    private static final String username = "root";
    private static final String password = "";

    public Employeedao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8+
            con = DriverManager.getConnection(url, username, password);

            // Initialize statements
            stmt = con.createStatement();
            ps1 = con.prepareStatement("INSERT INTO employee(name,email,salary) VALUES(?,?,?)");
            ps2 = con.prepareStatement("UPDATE employee SET salary=? WHERE id=?");

            System.out.println("Connection and PreparedStatements initialized successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection or PreparedStatement initialization failed!");
        }
    }

    // --- INSERT ---
    public int save(Employee e) {
        try {
            ps1.setString(1, e.getName());
            ps1.setString(2, e.getEmail());
            ps1.setDouble(3, e.getSalary());

            return ps1.executeUpdate(); // returns 1 if inserted
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    // --- UPDATE ---
    public boolean update(Employee e) {
        try {
            ps2.setDouble(1, e.getSalary());
            ps2.setInt(2, e.getId());

            return ps2.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // --- DELETE ---
    public boolean delete(int id) {
        try {
            String q = "DELETE FROM employee WHERE id=" + id;
            int i = stmt.executeUpdate(q);
            return i > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // --- FIND BY ID ---
    public Employee findById(int id) {
        try {
            String q = "SELECT * FROM employee WHERE id=" + id;
            rs = stmt.executeQuery(q);
            if (rs.next()) {
                return new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getDouble("salary"));
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // --- FIND ALL ---
    public List<Employee> findAll() {
        List<Employee> empList = new ArrayList<Employee>();
        try {
            rs = stmt.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                Employee e = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getDouble("salary"));
                empList.add(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return empList;
    }
}