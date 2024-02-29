import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.HashSet;
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
        String empno_input = input.nextLine();
        //String emp_name;

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
                    emp.setPnumber((String)objectEmployee.get("Emp_tel"));
                    emp.setEmpno((String)objectEmployee.get("Emp_no"));
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

        String emp_no, select_phone ,phone,table_no;
        String status = "Confirm";
        //String itemconfirmed = "Confirmed_Items";
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

                if(status.equals(String.valueOf(objectOrder.get("Customer_Confirm_Status")))) {
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

            for (Object obj : orderArray) {
                JSONObject objectOrder = (JSONObject) obj;
                JSONArray itemList = (JSONArray) objectOrder.get("Confirmed_Items");

                if (objectOrder != null && select_phone.equals(String.valueOf(objectOrder.get("Customer_Phone_name")))) {
                    for (Object item : itemList) {
                        JSONObject itemObject = (JSONObject) item;
                        System.out.println(i + " Order: " + itemObject.get("Product_Name") + " : "
                                + itemObject.get("Product_Price"));
                        sumprice += ((Double) itemObject.get("Product_Price"));
                        i += 1;
                    }
                }

            }
            System.out.println("Total : "+sumprice);
            System.out.println("====================== End ======================");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}