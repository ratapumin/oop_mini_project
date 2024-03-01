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
        System.out.println("Enter your Employee ID...");
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
                    ViewOrder();

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
        // String itemconfirmed = "Confirmed_Items";
        double sumprice = 0.0;
        int i = 1;
        Bill bill = new Bill();

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
            JSONObject billObject = new JSONObject();
            JSONArray billArray = new JSONArray();

            Customer cus = new Customer();

            for (Object obj : orderArray) {
                JSONObject objectOrder = (JSONObject) obj;
                JSONArray itemList = (JSONArray) objectOrder.get("Confirmed_Items");

                if (objectOrder != null
                        && select_phone.equals(String.valueOf(objectOrder.get("Customer_Phone_name")))) {

                    // bill.setBillno(((Long) billObject.get("Bill_no")).intValue());
                    cus.setName((String) objectOrder.get("Customer_Name"));
                    cus.setPnumber((String) objectOrder.get("Customer_Phone_name"));
                    cus.setTableno((String) objectOrder.get("Customer_Table_No"));
                    cus.calDate();

                    for (Object item : itemList) {
                        JSONObject itemObject = (JSONObject) item;

                        System.out.println(i + " Order: " + itemObject.get("Product_Name") + " : "
                                + itemObject.get("Product_Price"));

                        JSONObject billItem = new JSONObject();

                        billItem.put("Product_Name", itemObject.get("Product_Name"));
                        billItem.put("Product_Price", itemObject.get("Product_Price"));

                        nbillArray.add(billItem);

                        sumprice += ((Double) itemObject.get("Product_Price"));
                        i += 1;
                    }

                }

            }
            System.out.println("Net : " + bill.getNet());
            System.out.println("Vat 7% : " + bill.getVat());
            System.out.println("Total : " + sumprice);
            System.out.println("====================== End ======================");
            bill.setTotal(sumprice);
            bill.callVat(bill);
            bill.callvat_total(bill);
            billObject.put("Customer_Name", cus.getName());
            billObject.put("Customer_Phone_number", cus.getPnumber());
            billObject.put("Customer_Table_No", cus.getTableno());
            billObject.put("Customer_Confirm_Date", cus.getTableDateAsString());

            billObject.put("Bill_No", "Bill002");
            billObject.put("Bill_Vat", bill.getVat());
            billObject.put("Bill_net", bill.getNet());
            billObject.put("Bill_Total", bill.getTotal());
            billObject.put("Order", nbillArray);

            billArray.add(billObject);
            try (FileReader reader = new FileReader("./bill.json")) {
                JSONArray existingBillArray = (JSONArray) parser.parse(reader);

                // Append new bill data
                existingBillArray.addAll(billArray);

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

}
