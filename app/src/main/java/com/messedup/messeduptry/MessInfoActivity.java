package com.messedup.messeduptry;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.transition.Fade;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.transition.Explode;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.messedup.messeduptry.R.id.toolbar;

public class MessInfoActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private TextView toolbarTextView,LunchTimeTxt,DinnerTimeTxt;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_info);

        setupWindowAnimations();

        toolbar = (Toolbar)findViewById(R.id.toolbar3);

        LunchTimeTxt=(TextView)findViewById(R.id.LunchTimingTxtView);
        DinnerTimeTxt=(TextView)findViewById(R.id.DinnerTimingTxtView);

        String lunchtxt = "<b>Lunch: </b>11:30 a.m. to 3:30 p.m.";
        String dinnertxt = "<b>Dinner: </b>6:30 a.m. to 10:00 p.m.";

        LunchTimeTxt.setText(Html.fromHtml(lunchtxt));
        DinnerTimeTxt.setText(Html.fromHtml(dinnertxt));


        toolbarTextView=(TextView)findViewById(R.id.toolbar_title);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // overridePendingTransition(R.anim.my_slide_in_left, R.anim.my_slide_out_right);


        String MessID;
        MenuCardView MessObj;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                MessID= null;
            } else {
                MessObj= (MenuCardView)extras.getSerializable("messobj");
                if (MessObj != null) {
                    MessID=MessObj.getMessID();
                    Toast.makeText(this,"Show Info of : "+MessID,Toast.LENGTH_SHORT).show();
                    toolbarTextView.setText(MessID);


                }
            }
        } else {
            MessObj= (MenuCardView) savedInstanceState.getSerializable("messid");
            if (MessObj != null) {
                MessID=MessObj.getMessID();
                Toast.makeText(this,"Show Info of : "+MessID,Toast.LENGTH_SHORT).show();
                toolbarTextView.setText(MessID);


            }

        }



        viewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        tabLayout = (TabLayout) findViewById(R.id.tab);

        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_call:
//                                This is how to ask permission and make calls
                                int REQUEST_PHONE_CALL = 1;
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+919555994342"));

                                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    if (ContextCompat.checkSelfPermission(MessInfoActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions(MessInfoActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
                                    } else {
                                        startActivity(intent);
                                    }
                                } else {
                                    startActivity(intent);
                                }
                                break;

                            case R.id.action_locate:
                                String uri = String.format(Locale.ENGLISH, "https://goo.gl/maps/3r6R7FerPC52");//"geo:18.457542,73.850834?q=life+gym");//, latitude, longitude);
                                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                startActivity(intent1);
                                break;

                            case R.id.action_share:
                                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                                sharingIntent.setType("text/plain");
                                String shareBody = "Ae Bhenchod!";
//                                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
//                                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                                startActivity(sharingIntent);
                                break;
                        }
                        return false;
                    }
                });
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {

        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(new Explode());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}

