package com.textapp.utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;


import com.google.gson.Gson;
import com.textapp.model.ProfileModel;

public class PreferenceConnector 
{
	public static final String PREF_NAME = "appPref";
	public static final int MODE = Context.MODE_PRIVATE;
	public static final String PROFILE = "profile";
    public static final String TOKEN = "token";

	public static void writeBoolean(Context context, String key, boolean value) {
		getEditor(context).putBoolean(key, value).commit();
	}

	public static boolean readBoolean(Context context, String key,
			boolean defValue) {
		return getPreferences(context).getBoolean(key, defValue);
	}

	public static void writeInteger(Context context, String key, int value) {
		getEditor(context).putInt(key, value).commit();

	}

	public static int readInteger(Context context, String key, int defValue) {
		return getPreferences(context).getInt(key, defValue);
	}

	public static void writeString(Context context, String key, String value) {
		getEditor(context).putString(key, value).commit();

	}

	public static String readString(Context context, String key, String defValue) {
		return getPreferences(context).getString(key, defValue);
	}

	public static void writeFloat(Context context, String key, float value) {
		getEditor(context).putFloat(key, value).commit();
	}

	public static float readFloat(Context context, String key, float defValue) {
		return getPreferences(context).getFloat(key, defValue);
	}

	public static void writeLong(Context context, String key, long value) {
		getEditor(context).putLong(key, value).commit();
	}

	public static long readLong(Context context, String key, long defValue) {
		return getPreferences(context).getLong(key, defValue);
	}

	public static SharedPreferences getPreferences(Context context) {
		return context.getSharedPreferences(PREF_NAME, MODE);
	}

	public static Editor getEditor(Context context) {
		return getPreferences(context).edit();
	}
	public static void clear(Context context) 
	{
		getEditor(context).putString(PROFILE, "").commit();
		getEditor(context).putString(TOKEN, "").commit();
	}
    public static ProfileModel getProfile(Context context)
    {
        ProfileModel profileModel=null;
        try {
           profileModel  =  (ProfileModel)ObjectSerializer.deserialize(readString(context,PROFILE,""));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return profileModel;
    }
    public static void saveProfile(Context context,ProfileModel profileModel)
    {
        try {
            getEditor(context).putString(PROFILE, ObjectSerializer.serialize(profileModel)).commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	

	
}
