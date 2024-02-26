import java.util.Scanner;

public class Index {

    public static void main(String[] agrs) {

        int role = 0;
        int cus = 0;
        Scanner input = new Scanner(System.in);

        System.out.println("Select you Role");
        System.out.println("1:Customer");
        System.out.println("2:Employee");
        System.out.print("Enter your Role : ");
        role = input.nextInt();

        switch (role) {
            case 1:
                System.out.println("Select list you want");
                System.out.println("1:Order food");
                System.out.println("2:View the ordered food list");
                System.out.println("3:Calculate");
                System.out.print("Enter your List : ");

                cus = input.nextInt();
                switch (cus) {
                    case 1:
                        Cus2product customer = new Cus2product();
                        customer.Customer();
                        break;
                }

                break;

            case 2:
                Calemp employee = new Calemp();
                employee.Emp();
                break;
            default:
                System.out.println("Invalid role");
                break;
        }
    }
}
