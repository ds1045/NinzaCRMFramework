package com.ninza.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class FileUtility {

	public String getDataFromPropertiesFile(String key) throws Throwable {
		
		FileInputStream fis = new FileInputStream("./src/main/resources/commondata.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String data = pobj.getProperty(key);
		
		return data;
	}
	
	public void setDataIntoPropertiesFile(String key, String value) throws Throwable {
		
		Properties pobj = new Properties();
		pobj.setProperty(key, value);
		FileOutputStream fos = new FileOutputStream("./src/main/resources/commondata.properties");
		pobj.store(fos, "CommonData");
		
	}

}
