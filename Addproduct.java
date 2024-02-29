import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.ParseException;

public class Addproduct {
    public static void menuaddProduct(Admin admin) {
        admin.printInfo();
        Scanner input = new Scanner(System.in);
        int choice;
        Addproduct addProduct = new Addproduct();
        while (true) {
            System.out.println("=============================================");
            System.out.println("=============== Page Add Product ============");
            System.out.println("1: Add Product");
            System.out.println("2: Return to Admin Menu");
            System.out.print("Enter You num choice: ");
            choice = input.nextInt();

            if (String.valueOf(choice).equalsIgnoreCase("1")) {
                Addproduct add = new Addproduct();
                add.addproduct();
            } else if (String.valueOf(choice).equalsIgnoreCase("2")) {
                Adminpage add = new Adminpage();
                add.admin(admin);

            } else {
                System.out.println("Invalid choice. Please try again.");

            }
        }

    }

    public static void addproduct() {

        JSONParser parser = new JSONParser();

        try {

            File productFile = new File("./Product.json");
            JSONArray productArray = new JSONArray();

            if (productFile.exists() && productFile.length() > 0) {
                Reader readProduct = new FileReader(productFile);
                JSONArray existingaddProduct = (JSONArray) parser.parse(readProduct);
                productArray.addAll(existingaddProduct);

            }

            Scanner input = new Scanner(System.in);

            System.out.print("Enter you Product No : ");
            int product_no = input.nextInt();
            input.nextLine();

            System.out.print("Enter you Product Stock : ");
            int product_stock = input.nextInt();
            input.nextLine();

            System.out.print("Enter you Product Status : ");
            String product_status = input.nextLine().toUpperCase();
            ;

            System.out.print("Enter you Product Type : ");
            String product_type = input.nextLine().toUpperCase();

            System.out.print("Enter you Product Name : ");
            String product_name = input.nextLine();

            System.out.print("Enter you Product price : ");
            double product_price = input.nextDouble();

            JSONArray tumArray = new JSONArray();
            JSONArray yumArray = new JSONArray();

            for (Object obj : productArray) {
                JSONObject producttObject = (JSONObject) obj;

                tumArray = (JSONArray) producttObject.get("Tum");
                yumArray = (JSONArray) producttObject.get("Yum");
            }

            if (product_type.equals("T")) {

                Tum tum = new Tum();
                tum.setPno(product_no);
                tum.setPstock(product_stock);
                tum.setPstatus(product_status);
                tum.setPtype(product_type);
                tum.setTname(product_name);
                tum.setTprice(product_price);

                JSONObject pobj = new JSONObject();
                pobj.put("Product_No", tum.getPno());
                pobj.put("Product_Stock", tum.getPstock());
                pobj.put("Product_Status", tum.getPstatus());
                pobj.put("Product_Type", tum.getPtype());
                pobj.put("Product_Name", tum.getTname());
                pobj.put("Product_Price", tum.getTprice());
                tumArray.add(pobj);

            } else if (product_type.equals("Y")) {

                Yum yum = new Yum();
                yum.setPno(product_no);
                yum.setPstock(product_stock);
                yum.setPstatus(product_status);
                yum.setPtype(product_type);
                yum.setYname(product_name);
                yum.setYprice(product_price);

                JSONObject pobj = new JSONObject();
                pobj.put("Product_No", yum.getPno());
                pobj.put("Product_Stock", yum.getPstock());
                pobj.put("Product_Status", yum.getPstatus());
                pobj.put("Product_Type", yum.getPtype());
                pobj.put("Product_Name", yum.getYname());
                pobj.put("Product_Price", yum.getYprice());
                yumArray.add(pobj);

            } else {
                System.out.println("You Can Type Product only 'T' and 'Y'");

            }
            try (FileWriter fileWriter = new FileWriter("./Product.json")) {
                fileWriter.write(productArray.toJSONString());
                fileWriter.flush();
                System.out.println("New object added successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
