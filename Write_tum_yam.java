import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

import netscape.javascript.JSObject;

public class Write_tum_yam {

    public static void main(String arg[]) {
        Tum tum = new Tum();
        tum.setPno(1001);
        tum.setPstock(10);
        tum.setPstatus("Pass");
        tum.setPtype("T");
        tum.setTname("Tum Pu Pa Rar");
        tum.setTprice(45);

        JSONObject obj1 = new JSONObject();
        obj1.put("Product_No", tum.getPno());
        obj1.put("Product_Stock", tum.getPstock());
        obj1.put("Product_Status", tum.getPstatus());
        obj1.put("Product_Type", tum.getPtype());
        obj1.put("Product_Name", tum.getTname());
        obj1.put("Product_Price", tum.getTprice());

        Yum yum = new Yum();
        yum.setPno(2001);
        yum.setPstock(10);
        yum.setPstatus("Pass");
        yum.setPtype("Y");
        yum.setYname("yum Pa Duk");
        yum.setYprice(299);

        JSONObject obj2 = new JSONObject();
        obj2.put("Product_No", yum.getPno());
        obj2.put("Product_Stock", yum.getPstock());
        obj2.put("Product_Status", yum.getPstatus());
        obj2.put("Product_Type", yum.getPtype());
        obj2.put("Product_Name", yum.getYname());
        obj2.put("Product_Price", yum.getYprice());

        JSONArray Tum = new JSONArray();
        Tum.add(obj1);

        JSONArray Yum = new JSONArray();
        Yum.add(obj2);
        JSONObject type = new JSONObject();
        type.put("Tum", Tum);
        type.put("Yum", Yum);

        JSONArray Product = new JSONArray();
        Product.add(type);
        tum.printInfo();
        yum.printInfo();

        try (FileWriter file = new FileWriter("./Product.json")) {
            file.write(Product.toString());
            System.out.println("Insert Data To Json Successfully");
        } catch (IOException e) {
            System.out.println("Handle exception can not found file");
            e.printStackTrace();

        }
    }

}
