package com.messedup.messeduptry;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by saurabh on 13/9/17.
 */

public class MenuPreferance {

    public static final String PREF_NAME = "MESS_MENU";
    public static final String KEY = "MESS_MENU_ARRAYLIST";

    public void storeMenu(Context context,ArrayList<HashMap<String, String>> collection)
    {

        //converting the collection into a JSON
        JSONArray result= new JSONArray(collection);
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, 0);

//Storing the string in pref file
        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.putString(KEY, result.toString());
        prefEditor.commit();

    }
}
