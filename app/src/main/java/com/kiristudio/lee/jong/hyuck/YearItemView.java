package com.kiristudio.lee.jong.hyuck;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by jong on 2015-09-01.
 */
public class YearItemView extends RelativeLayout {
    TextView textView;


        public YearItemView(Context context, int year) {
            super(context);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.year_item_view, this);

            textView = (TextView) findViewById(R.id.year);
            textView.setTextColor(SystemManager.getInstance().getThemeColor());
            textView.setText(String.valueOf(year + 2015));
            textView.setTypeface(MainPage.typeface2);


    }


}
