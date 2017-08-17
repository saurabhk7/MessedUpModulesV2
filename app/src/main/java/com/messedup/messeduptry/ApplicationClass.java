package com.messedup.messeduptry;

import android.app.Application;
import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by saurabh on 16/8/17.
 */

public class ApplicationClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

      //  FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Raleway-SemiBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        //....




    }


}