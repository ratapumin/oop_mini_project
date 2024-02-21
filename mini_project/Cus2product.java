import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class Cus2product {

    public static void main(String[] args) {
        int i = 0;
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
        obj1.put("Customer_Table_Date", cus.getTabledate());

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

            JSONArray product = (JSONArray) parser.parse(readProduct);

            Iterator<JSONObject> iterator = product.iterator();
            System.out.println("==========================================");

            while (iterator.hasNext()) {
                JSONObject tpye = iterator.next();
                JSONObject Tum = (JSONObject) tpye.get("Tum");
                System.out.println("Product: " + Tum.get("Product_Name"));
                System.out.println("Product: " + Tum.get("Product_Price"));
                Tum = iterator.next();
                JSONObject Yum = (JSONObject) Tum.get("Yum");
                System.out.println("Product: " + Yum.get("Product_Name"));
                System.out.println("Product: " + Yum.get("Product_Price"));
                i++;

            }
            System.out.println("==========================================");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
