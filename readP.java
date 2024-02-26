import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class readP {
    public static void main(String[] args) {

        try {
            // Parse the JSON file
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("./Product.json"));
            // Convert the object to JSONArray
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            JSONArray tumarray = (JSONArray) jsonObject.get("Tum");

            Tum tum = new Tum();
            tum.setPno(1004);
            tum.setPstock(10);
            tum.setPstatus("Pass");
            tum.setPtype("T"); 
            tum.setTname("Tum Thi");
            tum.setTprice(40);

            JSONObject obj1 = new JSONObject();
            obj1.put("Product_No", tum.getPno());
            obj1.put("Product_Stock", tum.getPstock());
            obj1.put("Product_Status", tum.getPstatus());
            obj1.put("Product_Type", tum.getPtype());
            obj1.put("Product_Name", tum.getTname());
            obj1.put("Product_Price", tum.getTprice());

            tumarray.add(obj1);


            try (FileWriter fileWriter = new FileWriter("./Product.json")) {
                fileWriter.write(jsonArray.toJSONString());
                fileWriter.flush();
                System.out.println("New object added successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
