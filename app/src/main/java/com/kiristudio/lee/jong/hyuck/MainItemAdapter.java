package com.kiristudio.lee.jong.hyuck;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by jongH on 2015-09-06.
 */
public class MainItemAdapter extends BaseAdapter {


    ArrayList<MainItem> Items = new ArrayList<>();

    Context mContext;

    public MainItemAdapter(Context context) {

        mContext = context;

    }

    public void addItem(MainItem item){
        Items.add(item);
    }


    @Override
    public int getCount() {
        return Items.size();
    }

    @Override
    public Object getItem(int position) {
        return Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        MainItemView layout;




            layout = new MainItemView(mContext);

            layout.setDate(Items.get(position).getmDate());
            layout.setMonth(Items.get(position).getmMonth());
            layout.setYear(Items.get(position).getmYear());
            layout.setContent(Items.get(position).getContent());

            layout.init();







        return layout;
    }
}
