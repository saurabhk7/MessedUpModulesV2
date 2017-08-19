package com.messedup.messeduptry;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    Button SignOutBtn;
    SearchView searchMess;
    Toolbar toolbar;
    private String[] areaListArray;
    ArrayAdapter<String> adapter;
    public static int NOTIFICATION_COUNT;
    LayerDrawable notif_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initToolBar();
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);



        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = MenuFragment.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = ProfileFragment.newInstance();
                                break;

                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });



        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, MenuFragment.newInstance());
        transaction.commit();

        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);


        SignOutBtn = (Button)findViewById(R.id.LogOutBtn);
        searchMess=(SearchView)findViewById(R.id.action_search);



        SignOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();


                Intent LogoutIntent = new Intent(MainActivity.this,ChooserActivity.class);
                startActivity(LogoutIntent);

            }
        });

        TextView phoneTxtView = (TextView)findViewById(R.id.PhoneNumTxtView);

        if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
            if (FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber() != null)
                phoneTxtView.setText(FirebaseAuth.getInstance().getCurrentUser().getUid());
            else
                phoneTxtView.setText("Error1");

            phoneTxtView.setVisibility(View.GONE);
        }
        else
        {
            phoneTxtView.setText("Error2");
        }



    }

    public void initToolBar() {


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);


        MenuItemCompat.OnActionExpandListener expandListener = new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                Toast.makeText(MainActivity.this, "Search Mess", Toast.LENGTH_SHORT).show();

                 // Do something when action item collapses
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        };

        // Get the MenuItem for the action item

        // Assign the listener to that action item

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Enter Mess Name...");

        MenuItemCompat.setOnActionExpandListener(searchItem, expandListener);

        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item); // get the spinner



        MenuItem itemCart = menu.findItem(R.id.notificon);
        notif_icon = (LayerDrawable) itemCart.getIcon();

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle item selection
        switch (item.getItemId()) {
            case R.id.notificon:
               // Toast.makeText(MainActivity.this, "Go to Notification Page", Toast.LENGTH_SHORT).show();
                NOTIFICATION_COUNT++;
                setBadgeCount(this, notif_icon, ""+NOTIFICATION_COUNT);
                return true;
            case R.id.filtersorticon:
                Toast.makeText(MainActivity.this, "Set The Filter Options", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }

}
