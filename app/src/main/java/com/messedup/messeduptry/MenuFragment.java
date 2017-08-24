package com.messedup.messeduptry;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuFragment extends Fragment {
    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }


    ArrayList<String> MessNameList=new ArrayList<>();
    public ArrayList<HashMap<String,String>> AllMessInfoFromDatabase=new ArrayList<>();
    ArrayList<MenuCardView> AllMessMenu = new ArrayList<>();
    RecyclerView MyRecyclerView;

    //  private HashMap<String ,String> MenuHashMap=new HashMap<>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeHashMaps(); // this will get deleted later as the hasmap will
        // be retrieved from the database ..  then uncomment the initilizeList method!

       // intializeList();  uncomment this later
    }

    private void initializeHashMaps() {

        new LoadAllMess().execute();

/*




        HashMap<String,String>Mess1=new HashMap<>();
        Mess1.put("messid","Anand Food Xprs"); //here there will be ,messid but change it to MessName
        Mess1.put("rice","Jeera Rice");
        Mess1.put("vegieone","Methi");
        Mess1.put("vegietwo","Baingan");
        Mess1.put("vegiethree",null);
        Mess1.put("roti","Roti");
        Mess1.put("special","Gulab Jamun");
        Mess1.put("specialextra",null);
        Mess1.put("other","achar");
        HashMap<String,String>Mess2=new HashMap<>();
        Mess2.put("messid","Kwality Mess");//here there will be ,messid but change it to MessName
        Mess2.put("rice","Rice");
        Mess2.put("vegieone","Bhindi");
        Mess2.put("vegietwo","Daal Tadka");
        Mess2.put("vegiethree","Aloo Gravy");
        Mess2.put("roti","Roti");
        Mess2.put("special","Kheer");
        Mess2.put("specialextra","Raita");
        Mess2.put("other","ButterMilk");
        HashMap<String,String>Mess3=new HashMap<>();
        Mess3.put("messid","Gujrati Mess");//here there will be ,messid but change it to MessName
        Mess3.put("rice","Masala Rice");
        Mess3.put("vegieone",null);
        Mess3.put("vegietwo",null);
        Mess3.put("vegiethree",null);
        Mess3.put("roti","Paratha");
        Mess3.put("special",null);
        Mess3.put("specialextra",null);
        Mess3.put("other","achar");
        HashMap<String,String>Mess4=new HashMap<>();
        Mess4.put("messid","PICT College Mess");//here there will be ,messid but change it to MessName
        Mess4.put("rice","Rice");
        Mess4.put("vegieone","Mutter Gravy");
        Mess4.put("vegietwo","Mix Veg.");
        Mess4.put("vegiethree",null);
        Mess4.put("roti","Roti");
        Mess4.put("special",null);
        Mess4.put("specialextra",null);
        Mess4.put("other","Dahi");
        HashMap<String,String>Mess5=new HashMap<>();
        Mess5.put("messid","Navruchi Veg.");//here there will be ,messid but change it to MessName
        Mess5.put("rice","Rice");
        Mess5.put("vegieone","Paneer Masala Makhanwala");
        Mess5.put("vegietwo","Daal");
        Mess5.put("vegiethree","Aloo Fry");
        Mess5.put("roti","Roti / Chapati");
        Mess5.put("special","Kheer");
        Mess5.put("specialextra",null);
        Mess5.put("other","ButterMilk");
        AllMessInfoFromDatabase.add(Mess1);
        AllMessInfoFromDatabase.add(Mess2);
        AllMessInfoFromDatabase.add(Mess3);
        AllMessInfoFromDatabase.add(Mess4);
        AllMessInfoFromDatabase.add(Mess5);
*/




    }

    private void intializeList() {

        Log.d("In initialize List",AllMessInfoFromDatabase.toString());

        AllMessMenu.clear();

        for (HashMap<String,String> MessInfoObj : AllMessInfoFromDatabase)
        {
            MenuCardView MessMenuObj = new MenuCardView();

            Log.d("In initialize List 2",MessInfoObj.toString());



            for (Map.Entry<String,String> entry: MessInfoObj.entrySet()) {



                switch (entry.getKey()) {
                    case "messid":
                        MessMenuObj.setMessID(entry.getValue());
                        break;
                    case "rice":
                        MessMenuObj.setRice(entry.getValue());
                        break;
                    case "vegieone":
                        MessMenuObj.setVegieOne(entry.getValue());
                        break;
                    case "vegietwo":
                        MessMenuObj.setVegieTwo(entry.getValue());
                        break;
                    case "vegiethree":
                        MessMenuObj.setVegieThree(entry.getValue());
                        break;
                    case "roti":
                        MessMenuObj.setRoti(entry.getValue());
                        break;
                    case "special":
                        MessMenuObj.setSpecial(entry.getValue());
                        break;
                    case "specialextra":
                        MessMenuObj.setSpecialExtra(entry.getValue());
                        break;
                    case "other":
                        MessMenuObj.setOther(entry.getValue());
                        break;

                }


            }

            AllMessMenu.add(MessMenuObj);


        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        MyRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardView);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (AllMessMenu.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(AllMessMenu));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);

        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }




    /**
     * Created by saurabh on 24/8/17.
     */

    class LoadAllMess extends AsyncTask<String, String, String> {

        private ProgressDialog pDialog;

        // products JSONArray
        JSONArray mess = null;
        JSONArray mess2 = null;

        JSONParser jParser = new JSONParser();
        private String url_all_products = "https://wanidipak56.000webhostapp.com/receiveall.php";
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
           /* pDialog = new ProgressDialog(getActivity().getApplicationContext());
            pDialog.setMessage("Loading products. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();*/
        }

        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<>();
            // getting JSON string from URL
            JSONObject json_obj_all = jParser.makeHttpRequest(url_all_products, "GET", params);
            JSONObject json_obj_menu = jParser.makeHttpRequest(url_mess_menu, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json_obj_all.toString());

            try {
                // Checking for SUCCESS TAG
                int success1 = json_obj_all.getInt(TAG_SUCCESS);
                int success2 = json_obj_menu.getInt(TAG_SUCCESS);
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
*/

                if(success2==1){
                    // products found
                    // Getting Array of Products
                    mess2 = json_obj_menu.getJSONArray(TAG_MESSINFO);

                    // looping through All Products
                    for (int i = 0; i < mess2.length(); i++) {
                        JSONObject c = mess2.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_MESSID);
                        String rice = c.getString(TAG_RICE);
                        String vegie1 = c.getString(TAG_VEGIE1);
                        String vegie2 = c.getString(TAG_VEGIE2);
                        String vegie3 = c.getString(TAG_VEGIE3);
                        String roti = c.getString(TAG_ROTI);
                        String special = c.getString(TAG_SPECIAL);
                        String special_extra = c.getString(TAG_SPECIAL_EXTRA);
                        String other = c.getString(TAG_OTHER);


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


                        Log.d("inDoinBackground: ID", map.toString());


                        // adding HashList to ArrayList
                        AllMessInfoFromDatabase.add(map);
                    }
                    Log.d("inDoinBackground: ID", AllMessInfoFromDatabase.toString());


                } else {
                    // no products found
                    Log.d("Dipak: ", "No Mess found!");
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {

            intializeList();
            // dismiss the dialog after getting all products
//            pDialog.dismiss();
            // updating UI from Background Thread

        }

    }


}