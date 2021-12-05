package com.kiristudio.lee.jong.hyuck;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by jong on 2015-09-01.
 */
public class MonthItemAdapterInInsert extends BaseAdapter {


    Context mContext;

    int[] month;
   MonthItemViewInInsert monthView;

    public MonthItemAdapterInInsert(Context context) {

        mContext = context;

        month = new int[12];

        for (int i = 0; i == 11; i++) {

            month[i] = i;


        }
    }

    @Override
    public int getCount() {
        return month.length;
    }

    @Override
    public Object getItem(int position) {
        return month[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


            monthView = new MonthItemViewInInsert(mContext, position);

        return monthView;
    }
}
