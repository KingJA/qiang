package com.kingja.qiang.util;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.kingja.qiang.constant.Constants;


public class SharedPreferencesIO {

    private final SharedPreferences sharedPreferences;

    public SharedPreferencesIO(Application application) {
        sharedPreferences = application.getSharedPreferences(Constants.APPLICATION_NAME,
                Context.MODE_PRIVATE);
    }

    public  void put(String key, Object object) {
        String type = object.getClass().getSimpleName();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }
        editor.commit();
    }


    public Object get(String key, Object defaultObject) {
        String type = defaultObject.getClass().getSimpleName();

        if ("String".equals(type)) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        }

        return null;
    }
}

