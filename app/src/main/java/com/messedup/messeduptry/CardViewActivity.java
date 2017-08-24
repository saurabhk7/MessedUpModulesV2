package com.messedup.messeduptry;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CardViewActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_item_new);

        String MenuString="<b>"+"Kheer"+"</b>"+", Paneer Masala, Aloo Gobi, Roti, Jeera Rice, Boondi Raita";
        TextView MenuTxt=(TextView)findViewById(R.id.MenuTextView);
        MenuTxt.setText(Html.fromHtml(MenuString));


        ArrayList<String> items1=new ArrayList<>();
        ArrayList<String> items2=new ArrayList<>();


        items1.add(0,"Paneer");
        items1.add(1,"Daal Tadka");
        items2.add(0,"Jeera Rice");
        items2.add(1,"Roti / Chapati");
        items1.add(2,"Bundi Raita");



        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, R.layout.custom_list_view, items1);
        ArrayAdapter<String> itemsAdapter2 =
                new ArrayAdapter<String>(this, R.layout.custom_list_view, items2);


        ListView listView = (ListView) findViewById(R.id.list_view_1);
        listView.setAdapter(itemsAdapter);
        ListView listView2 = (ListView) findViewById(R.id.list_view_2);
        listView2.setAdapter(itemsAdapter2);

        itemsAdapter.notifyDataSetChanged();
        itemsAdapter2.notifyDataSetChanged();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
