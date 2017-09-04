package com.messedup.messeduptry;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.transition.Fade;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.widget.Toast;

public class MessInfoActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_info);

        setupWindowAnimations();


        // overridePendingTransition(R.anim.my_slide_in_left, R.anim.my_slide_out_right);


        String MessID;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                MessID= null;
            } else {
                MessID= extras.getString("messid");
            }
        } else {
            MessID= (String) savedInstanceState.getSerializable("messid");
        }

        Toast.makeText(this,"Show Info of : "+MessID,Toast.LENGTH_SHORT).show();

        setTitle(MessID);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {

        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(new Explode());
    }
}
