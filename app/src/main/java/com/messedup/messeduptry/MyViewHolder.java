package com.messedup.messeduptry;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by saurabh on 24/8/17.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView MessNameTxtView,MenuUpdatedTextView,MessOpenBadge,MessCloseBadge,costTextView;
    public ListView SpecialList,MenuListView1,MenuListView2,MenuListView3;
    public LinearLayout MenuLayout;
    public ImageView OpenImg,CloseImg;
    public ImageButton MessInfoBtn;
    private Context context;

    public View view;
public MenuCardView CurrentObj;


    public MyViewHolder(final View v) {
        super(v);

        context=v.getContext();

        MessNameTxtView=(TextView)v.findViewById(R.id.mess_name);
        MessOpenBadge=(TextView)v.findViewById(R.id.MessOpenBadge);
        MessOpenBadge.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_access_time_green_24dp, 0, 0, 0);
        MessCloseBadge=(TextView)v.findViewById(R.id.MessCloseBadge);
        MessCloseBadge.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_access_time_white_24dp, 0, 0, 0);
        MenuUpdatedTextView=(TextView)v.findViewById(R.id.menu_update_status);
        MenuUpdatedTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_all_white_24dp, 0, 0, 0);
        costTextView=(TextView)v.findViewById(R.id.costTextView);
        costTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_coins, 0, 0, 0);



        SpecialList=(ListView)v.findViewById(R.id.SpecialListView);
        MenuListView1=(ListView)v.findViewById(R.id.list_view_1);
        MenuListView2=(ListView)v.findViewById(R.id.list_view_2);
        MenuListView3=(ListView)v.findViewById(R.id.list_view_3);
        MessInfoBtn=(ImageButton)v.findViewById(R.id.MessInfoBtn);

        MenuLayout=(LinearLayout)v.findViewById(R.id.menu_layout);

        OpenImg=(ImageView)v.findViewById(R.id.MessOpenIcon);
        CloseImg=(ImageView)v.findViewById(R.id.MessCloseIcon);

        view = v;
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                // item clicked
                Intent InfoIntent=new Intent(view.getContext(),MessInfoActivity.class);
                InfoIntent.putExtra("messid",CurrentObj.getMessID());
                view.getContext().startActivity(InfoIntent);
            }
        });


        /*MenuListView1.setClickable(false);
        MenuListView2.setClickable(false);
        MenuListView3.setClickable(false);*/


     /*   MessInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent InfoIntent=new Intent(context,ChooserActivity.class);
                InfoIntent.putExtra("messid",getAdapterPosition());
                context.startActivity(InfoIntent);

                Toast.makeText(context,"You Clicked : "+getAdapterPosition(),Toast.LENGTH_SHORT).show();
            }
        });*/






    }
}
