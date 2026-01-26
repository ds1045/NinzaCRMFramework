package com.ninza.crm.generic.fileutility;

import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {

	public String getDataFromJsonFile(String key) throws ParseException, Throwable {

		FileReader fr = new FileReader("./src/main/resources/commondata.json");
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fr);
		JSONObject map = (JSONObject) obj;
		String data = (String) map.get(key);
		
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public void setDataIntoJsonFile(String key, String value) throws Throwable {

	    FileReader fr = new FileReader("");
	    JSONParser parser = new JSONParser();
	    Object obj = parser.parse(fr);
	    JSONObject map = (JSONObject) obj;
	    map.put(key, value);

	    // Step 3: Write back to JSON file
	    FileWriter fw = new FileWriter("./src/main/resources/commondata.json");
	    fw.write(map.toJSONString());
	    fw.close();

	}
}
