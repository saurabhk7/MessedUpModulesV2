package com.messedup.messeduptry;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.transition.Fade;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.firebase.auth.FirebaseAuth;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Collections;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.support.v4.app.FragmentTransaction.TRANSIT_NONE;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            setupWindowAnimations();

        }
        setContentView(R.layout.activity_main);
        initToolBar();
        /*final BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

            bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = MenuFragment.newInstance();
                                replaceFragmentWithAnimationtoRight(selectedFragment,"tag");
                                break;
                            case R.id.action_item2:
                                selectedFragment = ProfileFragment.newInstance();
                                replaceFragmentWithAnimationtoLeft(selectedFragment,"tag");

                                break;

                        }
                       *//* FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();*//*


                        return true;
                    }
                });
*/


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.setDefaultTab(R.id.tab_menu);



        final BottomBarTab notifs = bottomBar.getTabWithId(R.id.tab_notifs);
        notifs.setBadgeCount(3);

        final int[] PREVIOUS_TAB = {R.id.tab_menu};
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment selectedFragment = null;
                switch (tabId) {
                    case R.id.tab_notifs:
                        selectedFragment = NotifFragment.newInstance();
                        replaceFragmentWithAnimationtoRight(selectedFragment, "tag");
                        notifs.removeBadge();
                        PREVIOUS_TAB[0] =R.id.tab_notifs;
                        break;
                    case R.id.tab_menu:
                        selectedFragment = MenuFragment.newInstance();
                        if(PREVIOUS_TAB[0]==R.id.tab_profile)
                             replaceFragmentWithAnimationtoRight(selectedFragment, "tag");
                        else
                            replaceFragmentWithAnimationtoLeft(selectedFragment, "tag");

                        PREVIOUS_TAB[0] =R.id.tab_menu;
                        break;
                    case R.id.tab_profile:
                        selectedFragment = ProfileFragment.newInstance();
                        replaceFragmentWithAnimationtoLeft(selectedFragment, "tag");
                        PREVIOUS_TAB[0] =R.id.tab_profile;
                        break;

                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();


            }
        });

        Intent temp = new Intent(getApplicationContext(), TempActivity.class);


        replaceFragmentWithAnimationtoRight(MenuFragment.newInstance(), "tag");

        //Manually displaying the first fragment - one time only
       /* FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, MenuFragment.newInstance());
        transaction.commit();
*/
        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);










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

      //  String[] collegeNames={"PICT, Dhankawadi","BVP, Katraj","Sinhgad COE, Vadgoan","SKNCOE, Ambegaon","VIT, Bibvewadi","Cummins COEW, Karve Nagar"};

        ArrayList<String> college_list=new ArrayList<>();
        college_list.add("PICT, Dhankawadi");
        college_list.add("BVP, Katraj");
        college_list.add("SINHAGAD COE, Vadgoan");
        college_list.add("SKNCOE, Ambegaon");
        college_list.add("VIT, Bibvewadi");
        college_list.add("CUMMINS COEW, Karve Nagar");
        Collections.sort(college_list);

        college_list.add(0,"Select your Area");



        //  MenuItem item = menu.findItem(R.id.college_spinner);
     //   Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        Spinner spinner=(Spinner)findViewById(R.id.categorySpinner);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);


       /* ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colleges, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
*/
     /*   ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,collegeNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
*/

        CustomAdapter customAdapter=new CustomAdapter(this,college_list);
        spinner.setAdapter(customAdapter);


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




        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle item selection
        switch (item.getItemId()) {
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

    public void replaceFragmentWithAnimationtoLeft(android.support.v4.app.Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(TRANSIT_NONE);
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void replaceFragmentWithAnimationtoRight(android.support.v4.app.Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(TRANSIT_NONE);
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

   /* public void replaceFragmentWithAnimation(android.support.v4.app.Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(0,0);
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }*/


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {


        Explode explode=new Explode();
        explode.setDuration(5000);
        getWindow().setExitTransition(explode);

    }

}
