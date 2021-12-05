package com.kiristudio.lee.jong.hyuck;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by jong on 2015-09-01.
 */
public class DateItemAdapter extends BaseAdapter {


    Context mContext;

    int[] date;
    DateItemView dateView;

    public DateItemAdapter(Context context) {

        mContext = context;

        date = new int[31];

        for (int i = 0; i == 30; i++) {

            date[i] = i;


        }
    }

    @Override
    public int getCount() {
        return date.length;
    }

    @Override
    public Object getItem(int position) {
        return date[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


            dateView = new DateItemView(mContext, position);

        return dateView;
    }
}
