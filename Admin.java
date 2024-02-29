import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;

public class Admin {

    public static void admin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("_________Admin_________");
        System.out.println("[1].Add Employee");
        System.out.println("[2].Add Admin");
        System.out.println("[3].Back");
        System.out.println("_______________________");
        System.out.println("Type ur choice...");

        String choice = scanner.next();

        if (choice.equalsIgnoreCase("1")) {
            // clearConsole();
            AddEmployee();

        } else if (choice.equalsIgnoreCase("2")) {
            // AddAdmin();
            System.out.println("eiei ");

        } else if (choice.equalsIgnoreCase("3")) {
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

}
