import java.util.Scanner;

public class Index {

    public static void main(String[] agrs) {
        Mainpage();
    }

    public static void Mainpage() {
        int role = 0;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Select you Role");
            System.out.println("1:Customer");
            System.out.println("2:Employee");
            System.out.println("3:Admin");
            System.out.println("4:Exit");
            System.out.print("Enter your Role : ");
            if (input.hasNextInt()) {
                role = input.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // Consume the invalid input
                continue;
            }

            switch (role) {
                case 1: {
                    Cus2product customer = new Cus2product();
                    customer.Customer();
                    break;
                }
                case 2: {
                    Calemp employee = new Calemp();
                    employee.Emp();
                    break;
                }
                case 3: {
                    Adminpage admin = new Adminpage();
                    admin.adminLogin();
                    break;
                }
                case 4: {
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                }
                default:
                    System.out.println("Invalid role");
                    break;
            }
        }
    }
}
