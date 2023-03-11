package jsonParser;

import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {
	static int count =1;

	public static void main(String[] args) throws JSONException {
		Scanner sc  = new Scanner(System.in);
		String inputs = null;
		inputs = sc.nextLine();
		parseJSON(new JSONObject(jsonobj),inputs.split(",")[0],Integer.parseInt(inputs.split(",")[1]));
	}
	
	static String jsonobj = "{\r\n"
			+ "  \"__comment0_\": \"This JSON stores the data in the below structure: \",\r\n"
			+ "  \"data\": {\r\n"
			+ "    \"defaultmodule\": {\r\n"
			+ "      \"PostTestWithJSON\": {\r\n"
			+ "        \"createNewUserTest2WithJSONdata\": [\r\n"
			+ "          {\r\n"
			+ "            \"name\": \"testName1\",\r\n"
			+ "            \"job\": \"testJob1\"\r\n"
			+ "          },\r\n"
			+ "          {\r\n"
			+ "            \"name\": \"testName2\",\r\n"
			+ "            \"job\": \"testJob2\"\r\n"
			+ "          },\r\n"
			+ "          {\r\n"
			+ "            \"name\": \"testName3\",\r\n"
			+ "            \"job\": \"testJob3\"\r\n"
			+ "          }\r\n"
			+ "        ],\r\n"
			+ "        \"registerNewUserTest2WithJSONdata\": [\r\n"
			+ "          {\r\n"
			+ "            \"username\": \"testName1\",\r\n"
			+ "            \"email\": \"testName1@test.in\",\r\n"
			+ "            \"password\": \"testPasswd111\"\r\n"
			+ "          },\r\n"
			+ "          {\r\n"
			+ "            \"username\": \"testName2\",\r\n"
			+ "            \"email\": \"testName2@test.in\",\r\n"
			+ "            \"password\": \"testPasswd222\"\r\n"
			+ "          }\r\n"
			+ "        ]\r\n"
			+ "      }\r\n"
			+ "    }\r\n"
			+ "  }\r\n"
			+ "}";
	
	public static void parseJSON(JSONObject json,String key,int Occurance) {
		
		boolean exists = json.has(key);
		Iterator<?> keys;
		String nextKeys;
		if(!exists) {
			keys = json.keys();
			while(keys.hasNext()) {
				nextKeys = (String) keys.next();
				try {
					if(json.get(nextKeys) instanceof JSONObject) {
						if(exists ==false) {
							parseJSON(json.getJSONObject(nextKeys),key,Occurance);
						}
					}else if(json.get(nextKeys) instanceof JSONArray) {
						JSONArray jsonArray  = json.getJSONArray(nextKeys);
						for(int i=0;i<jsonArray.length();i++) {
							String jsonArrayString  = 	jsonArray.get(i).toString();
							JSONObject innerJSON = new JSONObject(jsonArrayString);
							if(exists==false) {
								parseJSON(innerJSON, key, Occurance);
							}
						}
					}
				}catch(Exception e) {
					System.out.println(e.toString());
				}
			}
		}else {
			if(count==Occurance) {
				System.out.println((String) json.get(key));
				count++;
			}else {
				count++;
			}
		}
		
		
	}

}
