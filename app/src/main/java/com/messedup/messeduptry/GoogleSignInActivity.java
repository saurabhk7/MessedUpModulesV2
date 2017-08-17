package com.messedup.messeduptry;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GoogleSignInActivity extends AppCompatActivity {

    Button PhoneAuthWithoutUIBtn,PhoneAuthWithUIBtn,toMainActBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);

        PhoneAuthWithoutUIBtn=(Button)findViewById(R.id.PhoneAuthWithoutUIBtn);
        PhoneAuthWithUIBtn=(Button)findViewById(R.id.PhoneAuthWithUIBtn);
        toMainActBtn=(Button)findViewById(R.id.MainActBtn);

        toMainActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMainActInt=new Intent(GoogleSignInActivity.this,MainActivity.class);
                startActivity(toMainActInt);
            }
        });


        PhoneAuthWithoutUIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoPhoneAuthInt=new Intent(GoogleSignInActivity.this,PhoneAuthActivity.class);
                startActivity(gotoPhoneAuthInt);
            }
        });
        PhoneAuthWithUIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoPhoneAuthUIInt=new Intent(GoogleSignInActivity.this,PhoneNumberAuthentication.class);
                startActivity(gotoPhoneAuthUIInt);
            }
        });


    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
