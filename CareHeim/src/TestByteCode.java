import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class TestByteCode {
	public static void main(String[] args) throws IOException {
		String filePath = "C:\\Users\\hhzzo\\conda\\careheim\\seg_result\\1686405500_f5bc8edd-fc5e-4c27-b266-4536aff9a4f9.txt";
		JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            // Now you can work with the parsed JSON array
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                // Access the properties of each JSON object
                long predClass = (long) jsonObject.get("class");
                JSONArray coordinates = (JSONArray) jsonObject.get("coordinate");
                
                for(int i = 0; i < 3; i++) {
                	System.out.println(coordinates.get(i));
                }
                // ...

                // Process the data as needed
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
	}
}


