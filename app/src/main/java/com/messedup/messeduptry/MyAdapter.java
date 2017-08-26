package com.messedup.messeduptry;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

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
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Log.d("IN MY ADAPTER ",list.get(position).getMessID());

        holder.MessNameTxtView.setText(list.get(position).getMessID());

        setSpecialList(holder,position);
        setMenuLists(holder,position);

    }

    private void setMenuLists(MyViewHolder holder, int position) {
        Log.d("IN MY ADAPTER ",list.get(position).getMessID());


        ArrayList<String> items1=new ArrayList<>();
        ArrayList<String> items2=new ArrayList<>();

        int i=0;
        int k=0;

        if(list.get(position).getVegieOne()!=null) {
            items1.add(i, list.get(position).getVegieOne());
            i++;
        }
        if(list.get(position).getVegieTwo()!=null) {
            items1.add(i, list.get(position).getVegieTwo());
            i++;
        }
        if(list.get(position).getVegieThree()!=null) {
            items1.add(i, list.get(position).getVegieThree());
            i++;
        }
        if(list.get(position).getRice()!=null) {
            items2.add(k, list.get(position).getRice());
            k++;
        }
        if(list.get(position).getRoti()!=null) {
            items2.add(k, list.get(position).getRoti());
            k++;
        }
        if(list.get(position).getOther()!=null) {
            items1.add(i, list.get(position).getOther());
            i++;
        }

        ArrayAdapter<String> itemsAdapter1 =
                new ArrayAdapter<String>(Contextparent.getContext(), R.layout.custom_list_view, items1);
        ArrayAdapter<String> itemsAdapter2 =
                new ArrayAdapter<String>(Contextparent.getContext(), R.layout.custom_list_view, items2);


        holder.MenuListView1.setAdapter(itemsAdapter1);
        holder.MenuListView2.setAdapter(itemsAdapter2);

        setListViewHeightBasedOnChildren(holder.MenuListView1);
        setListViewHeightBasedOnChildren(holder.MenuListView2);

        itemsAdapter1.notifyDataSetChanged();
        itemsAdapter2.notifyDataSetChanged();


    }



    private void setSpecialList(MyViewHolder holder, int position) {

        ArrayList<String> Specialitems=new ArrayList<>();
        String Special1=list.get(position).getSpecial();
        String Special2=list.get(position).getSpecialExtra();

        if(Special1==null && Special2==null)
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
        Log.d("IN MY ADAPTER ",""+list.size());

        return list.size();
    }
}
