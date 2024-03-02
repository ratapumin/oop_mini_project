import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Confirm {

    public static void confirmOrder(Customer cus) {

        if (cus.getName() == null) {
            System.out.println("You should select an order first.");
            Cus2product.inputCus(); // You should call the method statically
        } else {
            int choice;
            String table_no;
            double sumprice = 0.0;
            int i = 1;
            Scanner input = new Scanner(System.in);

            cus.printInfo();
            System.out.println("=========== Please Check Your Order ============");
            System.out.println("===================== End ======================");

            JSONParser parser = new JSONParser();
            try {
                Reader readOrder = new FileReader("./order.json");
                JSONArray orderArray = (JSONArray) parser.parse(readOrder);

                for (Object obj : orderArray) {
                    JSONObject objectOrder = (JSONObject) obj;

                    if (objectOrder != null && cus.getTableno().equals(objectOrder.get("Customer_Table_No"))) {
                        System.out.println(i + " : " + "Product Name: " + objectOrder.get("Product_Name") + " : "
                                + objectOrder.get("Product_Price"));
                        sumprice += ((Double) objectOrder.get("Product_Price"));
                        i += 1;
                    }
                }
                System.out.println("Total : " + sumprice);
                System.out.println("===================== End ======================");
                while (true) {
                    System.out.println("Select your choice ?");
                    System.out.println("1: Confirm Order");
                    System.out.println("2: Return to Menu");
                    System.out.print("Enter You num choice: ");
                    choice = input.nextInt();

                    if (String.valueOf(choice).equalsIgnoreCase("1")) {
                        ConfirmSuccess(cus);
                    } else if (String.valueOf(choice).equalsIgnoreCase("2")) {
                        Cus2product menuCus = new Cus2product();
                        menuCus.Customer();
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void ConfirmSuccess(Customer cus) {
        JSONParser parser = new JSONParser();
        cus.printInfo();
        double sumprice = 0.0;
        int i = 1;

        JSONArray confirmedItems = new JSONArray();
        JSONArray confirmarray = new JSONArray();
        clearConsole();
        System.out.println("========== Confirm Order Successfully ==========");
        try {
            File confirmOrderFile = new File("./confirmOrder.json");
            if (confirmOrderFile.exists() && confirmOrderFile.length() > 0) {
                // If the file exists and is not empty, read its content
                Reader readConfirm = new FileReader(confirmOrderFile);
                JSONArray confarray = (JSONArray) parser.parse(readConfirm);
                confirmarray.addAll(confarray); // Add existing data to confirmarray
            }

            Reader readOrder = new FileReader("./order.json");
            JSONArray orderArray = (JSONArray) parser.parse(readOrder);
            JSONObject confirmOrder = new JSONObject();

            for (Object obj : orderArray) {
                JSONObject objectOrder = (JSONObject) obj;

                if (objectOrder != null && cus.getTableno().equals(objectOrder.get("Customer_Table_No"))) {
                    System.out.println(i + " : " + "Product Name: " + objectOrder.get("Product_Name") + " : "
                            + objectOrder.get("Product_Price"));
                            
                    JSONObject confirmedItem = new JSONObject();

                    confirmedItem.put("Product_Name", objectOrder.get("Product_Name"));
                    confirmedItem.put("Product_Price", objectOrder.get("Product_Price"));

                    confirmedItems.add(confirmedItem);

                    sumprice += ((Double) objectOrder.get("Product_Price"));
                    i += 1;

                }

            }
            System.out.println("\nTotal : " + sumprice);
            System.out.println("===================== End ======================");
            cus.calDate();
            // Add the array of confirmed items to the confirmation object
            confirmOrder.put("Confirmed_Items", confirmedItems);

            // Add other customer details to the confirmation object
            confirmOrder.put("Customer_Name", cus.getName());
            confirmOrder.put("Customer_Phone_name", cus.getPnumber());
            confirmOrder.put("Customer_Table_No", cus.getTableno());
            confirmOrder.put("Customer_Confirm_Date", cus.getTableDateAsString());
            confirmOrder.put("Total", sumprice);
            confirmOrder.put("Customer_Confirm_Status", "Confirm");
            confirmarray.add(confirmOrder);

            try (FileWriter file = new FileWriter("./confirmOrder.json")) {
                file.write(confirmarray.toString());
                System.out.println("Insert Data To ConfirmOrder Json Successfully");
            } catch (IOException e) {
                System.out.println("Handle exception can not found file");
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cus.setName(null);
        cus.setPnumber(null);
        cus.setTableno(null);
        cus.setTabledate(null);
        System.out.println("Confirm Success");
        System.out.println("Thank you for using the service.");
        Index indexpage = new Index();
        indexpage.Mainpage();
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // For Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Unix/Linux
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}

