/**
 * 
 */
package com.textapp.webservice;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @author new
 *
 */
public class JsonParser { 

	public static BaseResponseData parseJson(String jsonString, Class t, HashMap<Type, Object> hashMap)
	{
		if(jsonString == null) return null;
		GsonBuilder gsonBuilder = new GsonBuilder();
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            gsonBuilder.registerTypeAdapter((Type)pair.getKey(), pair.getValue());
            it.remove();
        }
       // ;
        Gson gson       =   gsonBuilder.setPrettyPrinting().create();

		BaseResponseData response =  (BaseResponseData)gson.fromJson(jsonString, t);

		return response;

	}
	public static BaseResponseData parseJson(String jsonString, Type t) 
	{
		if(jsonString == null) return null;
		Gson gson = new Gson();

		BaseResponseData response =  gson.fromJson(jsonString, t);

		return response;

	}

}

