package com.textapp.webservice;

import android.view.View;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class BaseRequestData 
{
	private	int 						tag;
	private	String						id;
    private String                      path1;

    public String getPath1() {
        return path1;
    }

    public void setPath1(String path1) {
        this.path1 = path1;
    }

    public String getPath2() {
        return path2;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

    private String                      path2;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    private	boolean						error   =   false;
    private HashMap<Type,Object> hashMapGson = new HashMap<>();
	private	View						baseView;

	private	ArrayList<View> 			views;

    private	String						mailId;

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public View getBaseView() {
		return baseView;
	}
	public void setBaseView(View baseView) {
		this.baseView = baseView;
	}

	public ArrayList<View> getViews() {
		return views;
	}
	public void setViews(ArrayList<View> views) {
		this.views = views;
	}

    public HashMap<Type, Object> getHashMapGson() {
        return hashMapGson;
    }

    public void setHashMapGson(HashMap<Type, Object> hashMapGson) {
        this.hashMapGson = hashMapGson;
    }
    public void addHashMap(Type type ,Object o)
    {
        hashMapGson.put(type, o);
    }



	
		
		

}
