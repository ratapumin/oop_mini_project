import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.HashSet;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Calemp {

    public static void Emp() {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter your Employee ID : ");
        String empno_input = input.nextLine().toUpperCase();
        // String emp_name;

        JSONParser parser = new JSONParser();

        try {

            Reader readEmp = new FileReader("./employee.json");
            JSONArray empArray = (JSONArray) parser.parse(readEmp);

            Iterator<JSONObject> iterator = empArray.iterator();
            while (iterator.hasNext()) {
                JSONObject objectEmployee = iterator.next();

                if (objectEmployee != null && empno_input.equals(String.valueOf(objectEmployee.get("Emp_no")))) {

                    Employee emp = new Employee();
                    emp.setName((String) objectEmployee.get("Emp_name"));
                    emp.setPnumber((String) objectEmployee.get("Emp_tel"));
                    emp.setEmpno((String) objectEmployee.get("Emp_no"));
                    // ViewOrder();

                    while (true) {
                        System.out.println("=================== Employee ===================");
                        System.out.println("1: Pay transaction");
                        System.out.println("2: Bill History");
                        System.out.println("3: Return main menu");
                        System.out.println("___________________________");
                        System.out.print("Enter your List : ");

                        String choice = input.next();

                        if (choice.equalsIgnoreCase("1")) {
                            // clearConsole();
                            ViewOrder();

                        } else if (choice.equalsIgnoreCase("2")) {
                            // Confirm conn = new Confirm();
                            BillHistory();
                        } else if (choice.equalsIgnoreCase("3")) {
                            // clearConsole();
                            Index indexpage = new Index();
                            indexpage.Mainpage();
                            break;
                            // System.out.println("Exiting the program. Goodbye!");
                            // System.exit(0);
                        } else {
                            System.out.println("Invalid choice. Please try again.");

                        }

                    }

                }
            }

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void ViewOrder() {

        String emp_no, select_phone, phone, table_no;
        String status = "Confirm";
        String newStatus = "Checked";
        // String itemconfirmed = "Confirmed_Items";
        double sumprice = 0.0;
        int i = 1;
        Scanner input = new Scanner(System.in);

        System.out.println("===================View Order===================");

        JSONParser parser = new JSONParser();
        try {
            Reader readOrder = new FileReader("./confirmOrder.json");
            JSONArray orderArray = (JSONArray) parser.parse(readOrder);

            Set<String> uniqueTableNumbers = new HashSet<>();

            Iterator<JSONObject> iterator = orderArray.iterator();
            while (iterator.hasNext()) {
                JSONObject objectOrder = iterator.next();

                table_no = (String) objectOrder.get("Customer_Table_No");
                phone = (String) objectOrder.get("Customer_Phone_name");

                if (status.equals(String.valueOf(objectOrder.get("Customer_Confirm_Status")))) {
                    System.out.println("Table No: " + table_no);
                    System.out.println("Customer phone number: " + phone);
                    System.out.println("_______________________________");
                }
            }

            System.out.println("==================Select Order==================");
            System.out.print("Enter Phone number for create bill : ");
            select_phone = input.nextLine();
            System.out.println("Customer Tel : " + select_phone);
            System.out.println("===================== " + select_phone + " =====================");

            JSONArray nbillArray = new JSONArray();

            for (Object obj : orderArray) {
                JSONObject objectOrder = (JSONObject) obj;
                JSONArray itemList = (JSONArray) objectOrder.get("Confirmed_Items");

                if (objectOrder != null
                        && select_phone.equals(String.valueOf(objectOrder.get("Customer_Phone_name")))) {

                    JSONObject billObject = new JSONObject();

                    objectOrder.put("Customer_Confirm_Status", newStatus);

                    billObject.put("Bill_no.", "bill_001");
                    billObject.put("Customer_Name", objectOrder.get("Customer_Name"));
                    billObject.put("Phone_name", objectOrder.get("Customer_Phone_number"));
                    billObject.put("table", objectOrder.get("Customer_Table_No"));
                    billObject.put("Date", objectOrder.get("Customer_Table_Date"));

                    for (Object item : itemList) {
                        JSONObject itemObject = (JSONObject) item;
                        System.out.println(i + " Order: " + itemObject.get("Product_Name") + " : "
                                + itemObject.get("Product_Price"));
                        sumprice += ((Double) itemObject.get("Product_Price"));
                        i += 1;
                    }
                    billObject.put("Total", sumprice);
                    billObject.put("Vat(7%)", (Double) (sumprice / 100) * 7.00);
                    nbillArray.add(billObject);
                }

            }
            System.out.println("Total : " + sumprice);
            System.out.println("====================== End ======================");

            try (FileWriter fileWriter = new FileWriter("./confirmOrder.json")) {
                fileWriter.write(orderArray.toJSONString());
                System.out.println("Customer_Confirm_Status updated successfully.");
            } catch (IOException e) {
                System.out.println("Error writing to the file.");
                e.printStackTrace();
            }

            try (FileReader reader = new FileReader("./bill.json")) {
                JSONArray existingBillArray = (JSONArray) parser.parse(reader);

                // Append new bill data
                existingBillArray.addAll(nbillArray);

                // Write back to bill.json
                try (FileWriter file = new FileWriter("./bill.json")) {
                    file.write(existingBillArray.toJSONString());
                    System.out.println("Insert Data To Json Successfully");
                } catch (IOException e) {
                    System.out.println("Handle exception cannot find file");
                    e.printStackTrace();
                }
            } catch (IOException e) {
                // TODO: handle exception
            } catch (ParseException e) {

            }

        } catch (IOException e) {
            // TODO: handle exception
        } catch (ParseException e) {

        }

    }

    public static void BillHistory() {

        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader("./bill.json");
            JSONArray billArray = (JSONArray) parser.parse(reader);

            Iterator<JSONObject> iterator = billArray.iterator();
            while (iterator.hasNext()) {
                JSONObject billObject = iterator.next();

                // Accessing properties within each billObject
                String billNo = (String) billObject.get("Bill_No");
                String customerName = (String) billObject.get("Customer_Name");
                String tableNo = (String) billObject.get("Customer_Table_No");
                double billTotal = (double) billObject.get("Bill_Total");

                // You can access other properties in a similar way

                // Process the data as needed
                System.out.println("Bill No: " + billNo);
                System.out.println("Customer Name: " + customerName);
                System.out.println("Table No: " + tableNo);
                System.out.println("Bill Total: " + billTotal);
                
                // Now you can access and process the "Order" array
                JSONArray orderArray = (JSONArray) billObject.get("Order");
                Iterator<JSONObject> orderIterator = orderArray.iterator();
                while (orderIterator.hasNext()) {
                    JSONObject orderItem = orderIterator.next();

                    // Access properties within each orderItem
                    double productPrice = (double) orderItem.get("Product_Price");
                    String productName = (String) orderItem.get("Product_Name");

                    // Process the order data as needed
                    System.out.println("Product Name: " + productName);
                    System.out.println("Product Price: " + productPrice);
                }
                System.out.println("-------------------");
            }

        } catch (IOException e) {
            e.printStackTrace(); // Print the exception for debugging
        } catch (ParseException e) {
            
        }
    }


}