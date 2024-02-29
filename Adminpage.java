import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.lang.String;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;

public class Adminpage {

    public static void admin(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=============== Information Admin ==============");
        admin.printInfo();
        System.out.println("================================================");
        System.out.println("_________Admin_________");
        System.out.println("[1].Add Employee");
        System.out.println("[2].Add Admin");
        System.out.println("[3].Add Product");
        System.out.println("[4].Back");
        System.out.println("_______________________");
        System.out.println("Type ur choice...");

        String choice = scanner.next();

        if (choice.equalsIgnoreCase("1")) {
            // clearConsole();
            AddEmployee();
            System.out.println("=============== Page Add Employee ==============");

        } else if (choice.equalsIgnoreCase("2")) {
            addAdmin();
            System.out.println("================= Page Add Admin ===============");

        } else if (choice.equalsIgnoreCase("3")) {
            // clearConsole();
            Addproduct addp = new Addproduct();
            addp.menuaddProduct(admin);
            // System.out.println("================= Page Add Menu ================");

            // break;
        } else if (choice.equalsIgnoreCase("4")) {
            // clearConsole();
            Index indexpage = new Index();
            indexpage.Mainpage();
            // break;
        } else {
            System.out.println("Invalid choice. Please try again.");

        }

    }

    public static void AddEmployee() {
        JSONParser parser = new JSONParser();
        try {
            File employeeFile = new File("./employee.json");
            JSONArray employeeArray = new JSONArray();

            if (employeeFile.exists() && employeeFile.length() > 0) {
                Reader readEmp = new FileReader(employeeFile);
                JSONArray existingEmployee = (JSONArray) parser.parse(readEmp);
                employeeArray.addAll(existingEmployee);
            }

            System.out.println("========== Enter Information Employee ==========");

            Scanner scanner = new Scanner(System.in);
            Employee empman = new Employee();

            System.out.println("Enter Employee No: ");
            String emp_no = scanner.nextLine();

            System.out.println("Enter Employee Name: ");
            String emp_name = scanner.nextLine();

            System.out.println("Enter Employee Phone Number: ");
            String emp_pnumber = scanner.nextLine();

            empman.setEmpno(emp_no);
            empman.setName(emp_name);
            empman.setPnumber(emp_pnumber);

            System.out.println("=============================================");

            empman.printInfo();

            JSONObject employeeObj = new JSONObject();
            employeeObj.put("Emp_no", empman.getEmpno());
            employeeObj.put("Emp_name", empman.getName());
            employeeObj.put("Emp_tel", empman.getPnumber());

            employeeArray.add(employeeObj);

            // System.out.println("Completed");

            try (FileWriter file = new FileWriter("./employee.json")) {
                file.write(employeeArray.toJSONString());
                System.out.println("Completed");
            } catch (IOException e) {
                System.out.println("Handle exception here");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addAdmin() {
        Scanner input = new Scanner(System.in);
        Admin admin = new Admin();

        JSONParser parser = new JSONParser();
        if (admin.getName() == null) {
            try {

                File adminFile = new File("./admin.json");
                JSONArray adminArray = new JSONArray();

                if (adminFile.exists() && adminFile.length() > 0) {
                    Reader readAdmin = new FileReader(adminFile);
                    JSONArray existingaddAdmin = (JSONArray) parser.parse(readAdmin);
                    adminArray.addAll(existingaddAdmin);

                }

                System.out.println("============ Enter Information Admin ===========");

                System.out.print("Enter Admin Name : ");
                String admin_name = input.nextLine();

                System.out.print("Enter Admin Phone Number : ");
                String admin_pnumber = input.nextLine();

                System.out.print("Enter Admin No : ");
                String admin_no = input.nextLine().toUpperCase();

                System.out.print("Enter Admin Id : ");
                String admin_id = input.nextLine();

                System.out.print("Enter Admin Pass : ");
                String admin_pass = input.nextLine();

                admin.setName(admin_name);
                admin.setPnumber(admin_pnumber);
                admin.setAdminno(admin_no);
                admin.setAdminid(admin_id);
                admin.setAdminpass(admin_pass);
                admin.printInfo();

                JSONObject adminObj = new JSONObject();

                adminObj.put("Admin_Name", admin.getName());
                adminObj.put("Admin_Phone_Name", admin.getPnumber());
                adminObj.put("Admin_No", admin.getAdminno());
                adminObj.put("Admin_Id", admin.getAdminid());
                adminObj.put("Admin_Pass", admin.getAdminpass());

                adminArray.add(adminObj);

                try (FileWriter file = new FileWriter("./admin.json")) {
                    file.write(adminArray.toString());
                    System.out.println("Insert Admin To Json Successfully");

                } catch (IOException e) {
                    System.out.println("Handle exception can not found file");
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("================================================");
            adminLogin();
        } else {
            System.out.println("================================================");
            admin.printInfo();
        }
    }

    public static void adminLogin() {
        Scanner input = new Scanner(System.in);
        Admin admin = new Admin();

        JSONParser parser = new JSONParser();

        try {
            File adminFile = new File("./admin.json");

            if (adminFile.exists() && adminFile.length() > 0) {
                JSONArray adminArray;

                try (Reader readAdmin = new FileReader(adminFile)) {
                    adminArray = (JSONArray) parser.parse(readAdmin);
                }

                System.out.println("=============== Enter Admin Login =============");

                System.out.print("Enter Admin Id : ");
                String admin_id = input.nextLine();

                System.out.print("Enter Admin Pass : ");
                String admin_pass = input.nextLine();
                System.out.println("================================================");

                for (Object obj : adminArray) {
                    JSONObject adminObject = (JSONObject) obj;

                    if (admin_id.equals(adminObject.get("Admin_Id")) &&
                            admin_pass.equals(adminObject.get("Admin_Pass"))) {
                        admin.setAdminid((String) adminObject.get("Admin_Id"));
                        admin.setAdminpass((String) adminObject.get("Admin_Pass"));
                        admin.setName((String) adminObject.get("Admin_Name"));
                        admin.setPnumber((String) adminObject.get("Admin_Phone_Name"));
                        admin.setAdminno((String) adminObject.get("Admin_No"));

                        admin.printInfo();
                        admin(admin);

                        return;
                    }
                }
                System.out.println("Invalid Admin Id or Password.");
            } else {
                System.out.println("Admin data file not found or empty.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}