import java.util.List;
import java.util.Scanner;

public class Empapp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employeedao dao = new Employeedao();
        int choice;

        do {
            System.out.println("\nList of Operations\n-----------------------");
            System.out.println("1. Insert");
            System.out.println("2. Update (salary)");
            System.out.println("3. Find by id");
            System.out.println("4. Find all");
            System.out.println("5. Delete");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch(choice) {
                case 1:
                    // --- Insert ---
                    System.out.print("Enter employee name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter employee email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter employee salary: ");
                    double salary = sc.nextDouble();
                    sc.nextLine(); // consume leftover newline

                    Employee e1 = new Employee(0, name, email, salary);
                    int res1 = dao.save(e1);
                    if(res1 == 1) {
                        System.out.println("Record Inserted Successfully");
                    } else {
                        System.out.println("Record not inserted");
                    }
                    break;

                case 2:
                    // --- Update ---
                    System.out.print("Enter employee id: ");
                    int id = sc.nextInt();
                    System.out.print("Enter new salary: ");
                    double nsalary = sc.nextDouble();
                    sc.nextLine();

                    Employee e2 = new Employee(id, "", "", nsalary);
                    boolean res2 = dao.update(e2);
                    if(res2) {
                        System.out.println("Record Updated Successfully");
                    } else {
                        System.out.println("Record not updated");
                    }
                    break;

                case 3:
                    // --- Find by ID ---
                    System.out.print("Enter employee id: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    Employee e = dao.findById(id);
                    if(e != null) {
                        System.out.println(e);
                    } else {
                        System.out.println("Record not found");
                    }
                    break;

                case 4:
                    // --- Find all ---
                    List<Employee> empList = dao.findAll();
                    if(empList.isEmpty()) {
                        System.out.println("No records found");
                    } else {
                        for(Employee emp : empList) {
                            System.out.println(emp);
                        }
                    }
                    break;

                case 5:
                    // --- Delete ---
                    System.out.print("Enter employee id: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    boolean res3 = dao.delete(id);
                    if(res3) {
                        System.out.println("Record Deleted Successfully");
                    } else {
                        System.out.println("Record not found");
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while(true);
    }
}