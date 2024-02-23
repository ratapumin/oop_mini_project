import java.io.FileReader; 
import java.util.Iterator; 
import java.util.Map; 
  
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 

public class Cus2product {

    public static void main(String[] args) {
        int i = 0;
        String product_no;
        String product_name;
        double product_price;
        String cus2p[];
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
                System.out.println("Product: " + Tum.get("Product_No"));
                System.out.println("Product: " + Tum.get("Product_Name"));
                System.out.println("Product: " + Tum.get("Product_Price"));
                Tum = iterator.next();
                System.out.println("==========================================");
                JSONObject Yum = (JSONObject) Tum.get("Yum");
                System.out.println("Product: " + Yum.get("Product_No"));
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

        System.out.println("===================Select====================");

        Scanner input = new Scanner(System.in);
        System.out.print("Select Food : ");
        product_no = input.nextLine();

        try {
            Reader readProduct = new FileReader("./Product.json");
            JSONArray product = (JSONArray) parser.parse(readProduct);

            Iterator<JSONObject> iterator = product.iterator();
            System.out.println("==========================================");

            boolean found = false;

            while (iterator.hasNext()) {
                JSONObject type = iterator.next();
                JSONObject Tum = (JSONObject) type.get("Tum");
                JSONObject Yum = (JSONObject) type.get("Yum");

                if (Tum != null && product_no.equals(String.valueOf(Tum.get("Product_No")))) {
                    found = true;
                    // System.out.println("Product: " + Tum.get("Product_Name"));
                    // System.out.println("Product: " + Tum.get("Product_Price"));
                    Tum tum = new Tum();
                    tum.setPno(((Long) Tum.get("Product_No")).intValue());
                    tum.setTname((String) Tum.get("Product_Name"));
                    tum.setTprice((double) Tum.get("Product_Price"));

                    JSONObject cusOrder = new JSONObject();

                    cusOrder.put("Customer_Name", cus.getName());
                    cusOrder.put("Customer_Phone_number", cus.getPnumber());
                    cusOrder.put("Customer_Table_No", cus.getTableno());
                    cusOrder.put("Customer_Table_Date", cus.getTabledate());
                    cusOrder.put("Product_No", tum.getPno());
                    cusOrder.put("Product_Name", tum.getTname());
                    cusOrder.put("Product_Price", tum.getTprice());

                    JSONArray order = new JSONArray();
                    order.add(cusOrder);

                    try (FileWriter file = new FileWriter("./order.json")) {
                        file.write(order.toString());
                        System.out.println("Insert Data To Json Successfully");
                    } catch (IOException e) {
                        System.out.println("Handle exception can not found file");
                        e.printStackTrace();

                    }

                } else if (Yum != null && product_no.equals(String.valueOf(Yum.get("Product_No")))) {
                    System.out.println("Product: " + Yum.get("Product_Name"));
                    System.out.println("Product: " + Yum.get("Product_Price"));
                    found = true;

                    Yum yum = new Yum();
                    yum.setPno(((Long) Yum.get("Product_No")).intValue());
                    yum.setYname((String) Yum.get("Product_Name"));
                    yum.setYprice((double) Yum.get("Product_Price"));

                    JSONObject cusOrder = new JSONObject();

                    cusOrder.put("Customer_Name", cus.getName());
                    cusOrder.put("Customer_Phone_number", cus.getPnumber());
                    cusOrder.put("Customer_Table_No", cus.getTableno());
                    cusOrder.put("Customer_Table_Date", cus.getTabledate());
                    cusOrder.put("Product_No", yum.getPno());
                    cusOrder.put("Product_Name", yum.getYname());
                    cusOrder.put("Product_Price", yum.getYprice());

                    JSONArray order = new JSONArray();
                    order.add(cusOrder);

                    try (FileWriter file = new FileWriter("./order.json")) {
                        file.write(order.toString());
                        System.out.println("Insert Data To Json Successfully");
                    } catch (IOException e) {
                        System.out.println("Handle exception can not found file");
                        e.printStackTrace();

                    }

                }
            }

            if (!found) {
                System.out.println("Cannot find product with the specified number.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
