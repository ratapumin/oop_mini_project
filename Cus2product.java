import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.text.SimpleDateFormat;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Scanner;

public class Cus2product {

    public static void main(String[] args) {
        int i = 0;
        String product_no;
        String product_name;
        double product_price;
        Customer cus = new Customer();
        cus.setName("wan");
        cus.setPnumber("0621645650");
        cus.setTableno("T_12");
        cus.calDate();
        cus.printInfo();

        JSONObject obj1 = new JSONObject();

        obj1.put("Customer_Name", cus.getName());
        obj1.put("Customer_Phone_number", cus.getPnumber());
        obj1.put("Customer_Table_No", cus.getTableno());
        obj1.put("Customer_Table_Date", cus.getTableDateAsString());

        JSONArray jsonarray = new JSONArray();
        jsonarray.add(obj1);

        try (FileWriter file = new FileWriter("./customer.json")) {
            file.write(jsonarray.toString());
            System.out.println("Insert Data To Json Successfully");
        } catch (IOException e) {
            System.out.println("Handle exception can not found file");
            e.printStackTrace();

        }
        System.out.println("=====================Read=====================");

        JSONParser parser = new JSONParser();

        try {
            Reader readProduct = new FileReader("./Product.json");
            JSONArray productArray = (JSONArray) parser.parse(readProduct);

            for (Object obj : productArray) {
                JSONObject productObject = (JSONObject) obj;

                JSONArray tumArray = (JSONArray) productObject.get("Tum");
                JSONArray yumArray = (JSONArray) productObject.get("Yum");

                for (Object tumObj : tumArray) {
                    JSONObject tum = (JSONObject) tumObj;
                    System.out.println("Product Tum: " + tum.get("Product_No"));
                    System.out.println("Product Tum: " + tum.get("Product_Name"));
                    System.out.println("Product Tum: " + tum.get("Product_Price"));
                    System.out.println("=============================================");

                }

                for (Object yumObj : yumArray) {
                    JSONObject yum = (JSONObject) yumObj;
                    System.out.println("Product Yum: " + yum.get("Product_No"));
                    System.out.println("Product Yum: " + yum.get("Product_Name"));
                    System.out.println("Product Yum: " + yum.get("Product_Price"));
                    System.out.println("=============================================");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("===================Select====================");

        Scanner input = new Scanner(System.in);
        System.out.print("Select Food : ");
        product_no = input.nextLine();

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
            System.out.println("===================putorder====================");

            try {
                // Read the existing orders from order.json
                Reader readOrder = new FileReader("./order.json");
                JSONArray oldOrder = (JSONArray) parser.parse(readOrder);

                // Create a new JSONObject to store the new orders
                JSONObject newOrder = new JSONObject();

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