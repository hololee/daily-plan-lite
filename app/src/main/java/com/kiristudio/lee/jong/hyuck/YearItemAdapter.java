package com.kiristudio.lee.jong.hyuck;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by jong on 2015-09-01.
 */
public class YearItemAdapter extends BaseAdapter {


    Context mContext;

    int[] year;
    YearItemView yearView;

    public YearItemAdapter(Context context) {

        mContext = context;

        year = new int[60];

        for (int i = 0; i == 59; i++) {

            year[i] = i;


        }
    }

    @Override
    public int getCount() {
        return year.length;
    }

    @Override
    public Object getItem(int position) {
        return year[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


            yearView = new YearItemView(mContext, position);

        return yearView;
    }
}
