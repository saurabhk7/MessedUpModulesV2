package com.messedup.messeduptry;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by saurabh on 24/8/17.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView MessNameTxtView;
    public ListView SpecialList,MenuListView1,MenuListView2;


    public MyViewHolder(View v) {
        super(v);

        MessNameTxtView=(TextView)v.findViewById(R.id.mess_name);
        SpecialList=(ListView)v.findViewById(R.id.SpecialListView);
        MenuListView1=(ListView)v.findViewById(R.id.list_view_1);
        MenuListView2=(ListView)v.findViewById(R.id.list_view_2);



    }
}
