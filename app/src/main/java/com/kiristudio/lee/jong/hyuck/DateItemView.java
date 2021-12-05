package com.kiristudio.lee.jong.hyuck;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by jong on 2015-09-01.
 */
public class DateItemView extends RelativeLayout {
    TextView textView;


        public DateItemView(Context context, int date) {
            super(context);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.date_item_view, this);

            textView = (TextView) findViewById(R.id.date);
            textView.setTextColor(SystemManager.getInstance().getThemeColor());
            textView.setText(String.valueOf(date+1));
            textView.setTypeface(MainPage.typeface);


    }


}
