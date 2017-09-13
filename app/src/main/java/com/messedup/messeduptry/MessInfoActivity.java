package com.messedup.messeduptry;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.messedup.messeduptry.R.id.toolbar;

public class MessInfoActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private TextView toolbarTextView, LunchTimeTxt, DinnerTimeTxt;

    private String lunchtxt,dinnertxt;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_info);

        setupWindowAnimations();


        toolbar = (Toolbar) findViewById(R.id.toolbar3);

        LunchTimeTxt = (TextView) findViewById(R.id.LunchTimingTxtView);
        DinnerTimeTxt = (TextView) findViewById(R.id.DinnerTimingTxtView);



        toolbarTextView = (TextView) findViewById(R.id.toolbar_title);

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
            if (extras == null) {
                MessID = null;
            } else {
                MessObj = (MenuCardView) extras.getSerializable("messobj");
                if (MessObj != null) {
                    MessID = MessObj.getMessID();
                    Toast.makeText(this, "Show Info of : " + MessID, Toast.LENGTH_SHORT).show();
                    toolbarTextView.setText(MessID);

                    setMessDetails(MessObj);


                }
            }
        } else {
            MessObj = (MenuCardView) savedInstanceState.getSerializable("messid");
            if (MessObj != null) {
                MessID = MessObj.getMessID();
                Toast.makeText(this, "Show Info of : " + MessID, Toast.LENGTH_SHORT).show();
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

    private void setMessDetails(MenuCardView messObj) {

        lunchtxt = "<b>Lunch: </b>"+messObj.getOTime()+" to "+messObj.getCTime();
        dinnertxt = "<b>Dinner: </b>"+messObj.getOTime()+" to "+messObj.getCTime();

        LunchTimeTxt.setText(Html.fromHtml(lunchtxt));
        DinnerTimeTxt.setText(Html.fromHtml(dinnertxt));


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
/*

    public class LoadAllMess extends AsyncTask<String , Void ,String>

    {

        private  View mPassedView;
        private String MessArea;
        JSONObject jObj = null;
        String json = "";

        LoadAllMess(View PassedView)
        {
            mPassedView=PassedView;
            String preselectArea= getSharedPrefs();
            MessArea=preselectArea;
        }

        LoadAllMess(View view,String area)
        {
            mPassedView=view;

            MessArea=area;
        }


        // products JSONArray
        JSONArray mess = null;
        JSONArray mess2 = null;

        JSONParser jParser = new JSONParser();
        //  private String url_all_products = "https://wanidipak56.000webhostapp.com/receiveall.php";
        private String url_mess_menu="https://wanidipak56.000webhostapp.com/receivemenu.php";
        //ArrayList<HashMap<String, String>> messList;

        // JSON Node names
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSINFO = "messinfo";
        private static final String TAG_MESSID = "messid";
        private static final String TAG_NAME = "name";
        private static final String TAG_RICE = "rice";
        private static final String TAG_ROTI = "roti";
        private static final String TAG_SPECIAL = "special";
        private static final String TAG_SPECIAL_EXTRA = "specialextra";
        private static final String TAG_VEGIE1 = "vegieone";
        private static final String TAG_VEGIE2 = "vegietwo";
        private static final String TAG_VEGIE3 = "vegiethree";
        private static final String TAG_OTHER = "other";






        @Override
        protected void onPreExecute() {
        super.onPreExecute();

        AllMessInfoFromDatabase.clear();
           */
/* pDialog = new ProgressDialog(getActivity().getApplicationContext());
            pDialog.setMessage("Loading products. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();*//*

    }

    protected String doInBackground(String... args) {

        OutputStream os = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            //constants
            URL url = new URL("https://wanidipak56.000webhostapp.com/t.php");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("college", MessArea);

            String message = jsonObject.toString();

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout( 10000 */
/*milliseconds*//*
 );
            conn.setConnectTimeout( 15000 */
/* milliseconds *//*
 );
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(message.getBytes().length);

            //make some HTTP header nicety
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            //open
            conn.connect();

            //setup send
            os = new BufferedOutputStream(conn.getOutputStream());
            os.write(message.getBytes());
            //clean up
            os.flush();


            //do somehting with response
            is = conn.getInputStream();

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                json = sb.toString();
            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }

            // try parse the string to a JSON object
            try {
                jObj = new JSONObject(json);
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }
            //String contentAsString = readIt(is,len);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }  finally {
            //clean up
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            conn.disconnect();
        }
        return null;


            */
/*final String TAG_SUCCESS = "success";
              final String TAG_MESSINFO = "messinfo";
              final String TAG_MESSID = "messid";
              final String TAG_NAME = "name";
              final String TAG_RICE = "rice";
              final String TAG_ROTI = "roti";
              final String TAG_SPECIAL = "special";
              final String TAG_SPECIAL_EXTRA = "specialextra";
              final String TAG_VEGIE1 = "vegieone";
              final String TAG_VEGIE2 = "vegietwo";
              final String TAG_VEGIE3 = "vegiethree";
              final String TAG_OTHER = "other";*//*

            */
/*List<NameValuePair> params = new ArrayList<>();
            // getting JSON string from URL
            //  JSONObject json_obj_all = jParser.makeHttpRequest(url_all_products, "GET", params);
            JSONObject json_obj_menu = jParser.makeHttpRequest(url_mess_menu, "GET", params);

            // Check your log cat for JSON reponse
            //   Log.d("All Products: ", json_obj_all.toString());

            try {
                // Checking for SUCCESS TAG
                //     int success1 = json_obj_all.getInt(TAG_SUCCESS);


                int success2 = json_obj_menu.getInt(TAG_SUCCESS);

*/
/*

                if (success1 == 1) {
                    // products found
                    // Getting Array of Products
                    mess = json_obj_all.getJSONArray(TAG_MESSINFO);

                    // looping through All Products
                    for (int i = 0; i < mess.length(); i++) {
                        JSONObject c = mess.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_MESSID);
                        String name = c.getString(TAG_NAME);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_MESSID, id);
                        map.put(TAG_NAME, name);

                        Log.d("Dipak: Mess Id ", id);
                        Log.d("Dipak: Mess Name ", name);

                        // adding HashList to ArrayList
                       // AllMessInfoFromDatabase.add(map);
                    }
                } else {
                    // no products found
                    Log.d("Dipak: ", "No Mess found!");
                }


                if(success2==1){
                    // products found
                    // Getting Array of Products
                    mess2 = json_obj_menu.getJSONArray(TAG_MESSINFO);

                    // looping through All Products
                    for (int i = 0; i < mess2.length(); i++) {
                        JSONObject c = mess2.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_MESSID).trim();
                        String rice = c.getString(TAG_RICE).trim();
                        String vegie1 = c.getString(TAG_VEGIE1).trim();
                        String vegie2 = c.getString(TAG_VEGIE2).trim();
                        String vegie3 = c.getString(TAG_VEGIE3).trim();
                        String roti = c.getString(TAG_ROTI).trim();
                        String special = c.getString(TAG_SPECIAL).trim();
                        String special_extra = c.getString(TAG_SPECIAL_EXTRA).trim();
                        String other = c.getString(TAG_OTHER).trim();


                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_MESSID, id+" "+MessArea);
                        map.put(TAG_RICE, rice);
                        map.put(TAG_VEGIE1, vegie1);
                        map.put(TAG_VEGIE2, vegie2);
                        map.put(TAG_VEGIE3, vegie3);
                        map.put(TAG_ROTI, roti);
                        map.put(TAG_SPECIAL, special);
                        map.put(TAG_SPECIAL_EXTRA, special_extra);
                        map.put(TAG_OTHER, other);


                        Log.d("inDoinBackground: ID", map.toString());


                        // adding HashList to ArrayList
                        AllMessInfoFromDatabase.add(map);
                    }
                    Log.d("inDoinBackground: ID", AllMessInfoFromDatabase.toString());




                } else {
                    // no products found
                    Log.d("Dipak: ", "No Mess found!");
                }

            }
            catch (Exception e)
            {



                   */
/* SuperActivityToast.create(getActivity(), new Style(), Style.TYPE_STANDARD)
                            .setText("Oops! No Internet Connection!")
                            .setDuration(Style.DURATION_MEDIUM)
                            .setFrame(Style.FRAME_LOLLIPOP)
                            .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED))
                            .setAnimations(Style.ANIMATIONS_POP).show();
                e.printStackTrace();
            }



            return null;*//*

    }

    */
/**
 * After completing background task Dismiss the progress dialog
 * **//*

    protected void onPostExecute(String file_url) {


        try {
            int success = jObj.getInt("success");
            if (success == 1) {
                JSONArray mess2 = jObj.getJSONArray("messinfo");

                for (int i = 0; i < mess2.length(); i++) {
                    JSONObject c = mess2.getJSONObject(i);

                    // Storing each json item in variable
                    String id = c.getString(TAG_MESSID).trim();
                    String rice = c.getString(TAG_RICE).trim();
                    String vegie1 = c.getString(TAG_VEGIE1).trim();
                    String vegie2 = c.getString(TAG_VEGIE2).trim();
                    String vegie3 = c.getString(TAG_VEGIE3).trim();
                    String roti = c.getString(TAG_ROTI).trim();
                    String special = c.getString(TAG_SPECIAL).trim();
                    String special_extra = c.getString(TAG_SPECIAL_EXTRA).trim();
                    String other = c.getString(TAG_OTHER).trim();

                    String gcharge = c.getString(TAG_GCHARGE).trim();
                    String otime = c.getString(TAG_OPENTIME).trim();
                    String ctime = c.getString(TAG_CLOSETIME).trim();

                        */
/*String otime = c.getString(TAG_OPENTIME).trim();
                        String ctime = c.getString(TAG_CLOSETIME).trim();*//*


                    String stat = c.getString(TAG_STATUS).trim();



                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<>();

                    // adding each child node to HashMap key => value
                    map.put(TAG_MESSID, id);
                    map.put(TAG_RICE, rice);
                    map.put(TAG_VEGIE1, vegie1);
                    map.put(TAG_VEGIE2, vegie2);
                    map.put(TAG_VEGIE3, vegie3);
                    map.put(TAG_ROTI, roti);
                    map.put(TAG_SPECIAL, special);
                    map.put(TAG_SPECIAL_EXTRA, special_extra);
                    map.put(TAG_OTHER, other);

                    map.put(TAG_GCHARGE, gcharge);
                    map.put(TAG_OPENTIME, otime);
                    map.put(TAG_CLOSETIME, ctime);
                    map.put(TAG_STATUS, stat);


                    Log.d("inDoinBackground: ID", map.toString());


                    // adding HashList to ArrayList
                    AllMessInfoFromDatabase.add(map);
                }


            }
            else
            {
                Toast.makeText(mPassedView.getContext(),"Oops,Error Updating Mess Menus",Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        View v=intializeList(mPassedView);
        // dismiss the dialog after getting all products
//            pDialog.dismiss();
        // updating UI from Background Thread

    }

}

*/
/*
class DummyBackgroundTask extends AsyncTask<String, String, String> {

    static final int TASK_DURATION = 3 * 1000; // 3 seconds

    @Override
    protected String doInBackground(String... args) {
        // Sleep for a small amount of time to simulate a background-task
        try {
            Thread.sleep(TASK_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;

        // Return a new random list of cheeses
    }

    @Override
    protected void onPostExecute(String file_url) {

        // dismiss the dialog after getting all products
//            pDialog.dismiss();
        // updating UI from Background Thread
       // onRefreshComplete(file_url);
    }


}


}*/

