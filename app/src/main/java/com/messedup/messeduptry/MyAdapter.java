package com.messedup.messeduptry;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by saurabh on 24/8/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private ArrayList<MenuCardView> list;
    private ViewGroup Contextparent;

    public MyAdapter(ArrayList<MenuCardView> data) {
        list = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        Contextparent=parent;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);


        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Log.d("IN MY ADAPTER ",list.get(position).getMessID());

        holder.MessNameTxtView.setText(list.get(position).getMessID());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.MenuUpdatedTextView.setElevation(18f);
        }

        if((position+1)%3==0)
                holder.MenuUpdatedTextView.setVisibility(View.INVISIBLE);
        if((position+1)%2==0) {
            holder.MessOpenBadge.setVisibility(View.INVISIBLE);
            holder.OpenImg.setVisibility(View.INVISIBLE);
        }
        else {
            holder.MessCloseBadge.setVisibility(View.INVISIBLE);
            holder.CloseImg.setVisibility(View.INVISIBLE);

        }

        holder.CurrentObj=list.get(position);


        /*holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent InfoIntent=new Intent(view.getContext(),MessInfoActivity.class);
                InfoIntent.putExtra("messid",list.get(position).getMessID());
                view.getContext().startActivity(InfoIntent);

            }
        });

        holder.MenuLayout.setClickable(true);

        holder.MenuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent InfoIntent=new Intent(view.getContext(),MessInfoActivity.class);
                InfoIntent.putExtra("messid",list.get(position).getMessID());
                view.getContext().startActivity(InfoIntent);
            }
        });*/

/*
        holder.MessInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent InfoIntent=new Intent(view.getContext(),ChooserActivity.class);
                InfoIntent.putExtra("messid",list.get(position).getMessID());


            }
        });*/
/*
        holder.MenuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent InfoIntent=new Intent(view.getContext(),MessInfoActivity.class);
                InfoIntent.putExtra("messid",list.get(position).getMessID());
                view.getContext().startActivity(InfoIntent);

               // Toast.makeText(view.getContext(),"You Clicked : "+list.get(position).getMessID(),Toast.LENGTH_SHORT).show();
            }
        });*/


        setSpecialList(holder,position);
        setMenuLists(holder,position);

    }

    private void setMenuLists(MyViewHolder holder, int position) {
        Log.d("IN MY ADAPTER ",list.get(position).getMessID());


        ArrayList<String> items1=new ArrayList<>();
        ArrayList<String> items2=new ArrayList<>();
        ArrayList<String> items3=new ArrayList<>();


        int i=0;
        int j=0;
        int k=0;

        if(list.get(position).getVegieOne()!=null && !list.get(position).getVegieOne().equals("null")) {
            items1.add(i, list.get(position).getVegieOne());
            i++;
        }
        if(list.get(position).getVegieTwo()!=null && !list.get(position).getVegieTwo().equals("null")) {
            items1.add(i, list.get(position).getVegieTwo());
            i++;
        }
        if(list.get(position).getVegieThree()!=null && !list.get(position).getVegieThree().equals("null")) {
            items2.add(j, list.get(position).getVegieThree());
            j++;
        }
        if(list.get(position).getRice()!=null && !list.get(position).getRice().equals("null")) {
            items2.add(j, list.get(position).getRice());
            j++;
        }
        if(list.get(position).getRoti()!=null && !list.get(position).getRoti().equals("null")) {
            items3.add(k, list.get(position).getRoti());
            k++;
        }
        if(list.get(position).getOther()!=null && !list.get(position).getOther().equals("null")) {
            items3.add(k, list.get(position).getOther());
            k++;
        }

        ArrayAdapter<String> itemsAdapter1 =
                new ArrayAdapter<String>(Contextparent.getContext(), R.layout.custom_list_view, items1);
        ArrayAdapter<String> itemsAdapter2 =
                new ArrayAdapter<String>(Contextparent.getContext(), R.layout.custom_list_view, items2);
        ArrayAdapter<String> itemsAdapter3 =
                new ArrayAdapter<String>(Contextparent.getContext(), R.layout.custom_list_view, items3);

        holder.MenuListView1.setAdapter(itemsAdapter1);
        holder.MenuListView2.setAdapter(itemsAdapter2);
        holder.MenuListView3.setAdapter(itemsAdapter3);


        setListViewHeightBasedOnChildren(holder.MenuListView1);
        setListViewHeightBasedOnChildren(holder.MenuListView2);
        setListViewHeightBasedOnChildren(holder.MenuListView3);


        itemsAdapter1.notifyDataSetChanged();
        itemsAdapter2.notifyDataSetChanged();
        itemsAdapter3.notifyDataSetChanged();



    }



    private void setSpecialList(MyViewHolder holder, int position) {

        ArrayList<String> Specialitems=new ArrayList<>();
        String Special1=list.get(position).getSpecial();
        String Special2=list.get(position).getSpecialExtra();

        if(Special1.equals("null"))
        {
            Special1=null;
        }
        if(Special2.equals("null"))
        {
            Special2=null;
        }

        if((Special1==null||Special1.equals("null")) && (Special2==null||Special2.equals("null")) )
        {
            Specialitems.add("No Special Today!");
        }



        if(Special1!=null)
        {
            Specialitems.add(Special1);
        }
       if(Special2!=null)
        {
            Specialitems.add(Special2);
        }

        ArrayAdapter<String> SpecialitemsAdapter =
                new ArrayAdapter<String>(Contextparent.getContext(),R.layout.special_custom_list_view, Specialitems);


        holder.SpecialList.setAdapter(SpecialitemsAdapter);
        setListViewHeightBasedOnChildren(holder.SpecialList);

        SpecialitemsAdapter.notifyDataSetChanged();
    }

    private static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
      //  Log.d("IN MY ADAPTER ",""+list.size());

        return list.size();
    }


}
