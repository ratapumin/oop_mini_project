import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Scanner;

public class Cus2product {
    int i = 0;
    String product_name;
    double product_price;
    static Customer cus = new Customer();

    public static void Customer() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=================== Cutomer ===================");
            System.out.println("1: Show menu");
            System.out.println("2: My Order'");
            System.out.println("3: View Bill'");
            System.out.println("4: Return main menu");
            System.out.println("___________________________");
            System.out.print("Enter your List : ");

            String choice = scanner.next();

            if (choice.equalsIgnoreCase("1")) {
                clearConsole();
                inputCus();

            } else if (choice.equalsIgnoreCase("2")) {
                Confirm conn = new Confirm();
                conn.confirmOrder(cus);
                // Mybill();
            } else if (choice.equalsIgnoreCase("3")) {
                // Confirm conn = new Confirm();
                // conn.confirmOrder(cus);
                Mybill();
            } else if (choice.equalsIgnoreCase("4")) {
                clearConsole();
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

    public static void inputCus() {
        JSONParser parser = new JSONParser();

        if (cus.getName() == null) {
            try {
                File customerFile = new File("./customer.json");
                JSONArray customerArray = new JSONArray();
                if (customerFile.exists() && customerFile.length() > 0) {
                    Reader readCus = new FileReader(customerFile);
                    JSONArray existingCustomers = (JSONArray) parser.parse(readCus);
                    customerArray.addAll(existingCustomers);
                }
                System.out.println("========== Enter Information youself ===========");

                Scanner input = new Scanner(System.in);

                System.out.print("Enter you Name : ");
                String cus_name = input.nextLine();

                System.out.print("Enter you Phone Number : ");
                String cus_pnumber = input.nextLine();

                System.out.print("Enter you Table No : ");
                String cus_table_no = input.nextLine();

                cus.setName(cus_name);
                cus.setPnumber(cus_pnumber);
                cus.setTableno(cus_table_no);
                cus.calDate();

                cus.printInfo();

                JSONObject customerObj = new JSONObject();

                customerObj.put("Customer_Name", cus.getName());
                customerObj.put("Customer_Phone_number", cus.getPnumber());
                customerObj.put("Customer_Table_No", cus.getTableno());
                customerObj.put("Customer_Table_Date", cus.getTableDateAsString());

                customerArray.add(customerObj);

                try (FileWriter file = new FileWriter("./customer.json")) {
                    file.write(customerArray.toString());
                    System.out.println("Insert Data To Json Successfully");

                } catch (IOException e) {
                    System.out.println("Handle exception can not found file");
                    e.printStackTrace();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            showmenu();
            selectMenu(cus);
        } else {
            System.out.println("================================================");
            cus.printInfo();
            showmenu();
            selectMenu(cus);
        }

    }

    public static void showmenu() {
        JSONParser parser = new JSONParser();
        System.out.println("========== Enter Product No for Select =========");

        try {
            Reader readProduct = new FileReader("./Product.json");
            JSONArray productArray = (JSONArray) parser.parse(readProduct);
            System.out.println("=================== Menu Tum ===================");

            for (Object obj : productArray) {
                JSONObject productObject = (JSONObject) obj;

                JSONArray tumArray = (JSONArray) productObject.get("Tum");
                JSONArray yumArray = (JSONArray) productObject.get("Yum");

                for (Object tumObj : tumArray) {
                    JSONObject tum = (JSONObject) tumObj;
                    System.out.println("Product No: " + tum.get("Product_No"));
                    System.out.println("Product Name: " + tum.get("Product_Name"));
                    System.out.println("Product Price: " + tum.get("Product_Price"));
                    System.out.println("=============================================");

                }
                System.out.println("=================== Menu Yum ===================");

                for (Object yumObj : yumArray) {
                    JSONObject yum = (JSONObject) yumObj;
                    System.out.println("Product No: " + yum.get("Product_No"));
                    System.out.println("Product Name: " + yum.get("Product_Name"));
                    System.out.println("Product Price: " + yum.get("Product_Price"));
                    System.out.println("=============================================");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void selectMenu(Customer cus) {
        String product_no;
        Scanner input = new Scanner(System.in);
        JSONParser parser = new JSONParser();

        while (true) {

            System.out.println("================== Select ===================");
            System.out.println("Out page Menu Please Write '0' ");
            System.out.print("Select Food : ");
            product_no = input.nextLine();

            if (product_no.equalsIgnoreCase("0")) {
                clearConsole();
                Customer();
            }

            try {
                Reader readProduct = new FileReader("./Product.json");
                JSONArray productArray = (JSONArray) parser.parse(readProduct);
                JSONArray ordersArray = new JSONArray();
                // JSONArray newproduct = (JSONArray) ordersArray;
                for (Object obj : productArray) {
                    JSONObject productObject = (JSONObject) obj;

                    JSONArray tumArray = (JSONArray) productObject.get("Tum");
                    JSONArray yumArray = (JSONArray) productObject.get("Yum");

                    for (Object tumObj : tumArray) {
                        JSONObject tum = (JSONObject) tumObj;
                        if (tum != null && product_no.equals(String.valueOf(tum.get("Product_No")))) {

                            // System.out.println("Product Tum: " + tum.get("Product_No"));
                            // System.out.println("Product Tum: " + tum.get("Product_Name"));
                            // System.out.println("Product Tum: " + tum.get("Product_Price"));

                            Tum tumm = new Tum();
                            tumm.setPno(((Long) tum.get("Product_No")).intValue());
                            tumm.setTname((String) tum.get("Product_Name"));
                            tumm.setTprice((double) tum.get("Product_Price"));

                            JSONObject cusOrder = new JSONObject();

                            cusOrder.put("Customer_Name", cus.getName());
                            cusOrder.put("Customer_Phone_number", cus.getPnumber());
                            cusOrder.put("Customer_Table_No", cus.getTableno());
                            cusOrder.put("Customer_Table_Date", cus.getTableDateAsString());
                            cusOrder.put("Product_No", tumm.getPno());
                            cusOrder.put("Product_Name", tumm.getTname());
                            cusOrder.put("Product_Price", tumm.getTprice());
                            ordersArray.add(cusOrder);

                        }
                    }
                    for (Object yumObj : yumArray) {
                        JSONObject yum = (JSONObject) yumObj;
                        if (yum != null && product_no.equals(String.valueOf(yum.get("Product_No")))) {
                            System.out.println("Product Yum: " + yum.get("Product_No"));
                            System.out.println("Product Yum: " + yum.get("Product_Name"));
                            System.out.println("Product Yum: " + yum.get("Product_Price"));

                            Tum yumm = new Tum();
                            yumm.setPno(((Long) yum.get("Product_No")).intValue());
                            yumm.setTname((String) yum.get("Product_Name"));
                            yumm.setTprice((double) yum.get("Product_Price"));

                            JSONObject cusOrder = new JSONObject();

                            cusOrder.put("Customer_Name", cus.getName());
                            cusOrder.put("Customer_Phone_number", cus.getPnumber());
                            cusOrder.put("Customer_Table_No", cus.getTableno());
                            cusOrder.put("Customer_Table_Date", cus.getTableDateAsString());
                            cusOrder.put("Product_No", yumm.getPno());
                            cusOrder.put("Product_Name", yumm.getTname());
                            cusOrder.put("Product_Price", yumm.getTprice());

                            // JSONArray order = new JSONArray();
                            ordersArray.add(cusOrder);

                        }

                    }

                }
                System.out.println("===================put order====================");

                try {
                    // Read the existing orders from order.json
                    Reader readOrder = new FileReader("./order.json");
                    JSONArray oldOrder = (JSONArray) parser.parse(readOrder);

                    // Create a new JSONObject to store the new orders
                    // JSONObject newOrder = new JSONObject();

                    // Add the new orders array to the new order JSONObject
                    // newOrder.put("new_orders", ordersArray);

                    // Iterate over the old orders and append them to the new orders array
                    for (Object obj : ordersArray) {
                        oldOrder.add(obj);
                    }
                    // oldOrder.add(newOrder);
                    // Write the merged orders back to order.json
                    try (FileWriter file = new FileWriter("./order.json")) {
                        file.write(oldOrder.toString());
                        System.out.println("Insert Data To Json Successfully");
                    } catch (IOException e) {
                        System.out.println("Handle exception can not found file");
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    System.out.println("Handle exception can not found file");
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

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

    public static void Mybill() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ur phone number for watch ur bill :");
        String phong = scanner.nextLine();

        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader("./confirmOrder.json");
            JSONArray billArray = (JSONArray) parser.parse(reader);

            Iterator<JSONObject> iterator = billArray.iterator();
            while (iterator.hasNext()) {
                JSONObject billObject = iterator.next();

                if (billObject != null
                        && phong.equals(String.valueOf(billObject.get("Customer_Phone_name")))) {

                    // String billNo = (String) billObject.get("Customer_Table_No");
                    String customerName = (String) billObject.get("Customer_Name");
                    String tableNo = (String) billObject.get("Customer_Table_No");
                    double Total = (double) billObject.get("Total");
                    String status = (String) billObject.get("Customer_Confirm_Status");
                    String tel = (String) billObject.get("Customer_Phone_name");
                    String date = (String) billObject.get("Customer_Confirm_Date");

                    System.out.println("Table No: " + tableNo);
                    System.out.println("Customer Name: " + customerName);
                    System.out.println("Status: " + status);
                    System.out.println("Tel: " + tel);
                    System.out.println("Date: " + date);
                    // System.out.println("Table No: " + tableNo);
                    System.out.println("Total: " + Total);

                    JSONArray orderArray = (JSONArray) billObject.get("Confirmed_Items");
                    Iterator<JSONObject> orderIterator = orderArray.iterator();
                    while (orderIterator.hasNext()) {
                        JSONObject orderItem = orderIterator.next();

                        double productPrice = (double) orderItem.get("Product_Price");
                        String productName = (String) orderItem.get("Product_Name");

                        System.out.println("Product Name: " + productName);
                        System.out.println("Product Price: " + productPrice);
                    }
                    System.out.println("-------------------");
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Print the exception for debugging
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}