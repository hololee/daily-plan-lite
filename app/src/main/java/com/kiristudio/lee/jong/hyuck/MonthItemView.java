package com.kiristudio.lee.jong.hyuck;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by jong on 2015-09-02.
 */
public class MonthItemView extends RelativeLayout {
    TextView textView;
    String monthText;

    public MonthItemView(Context context, int month) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.month_item_view, this);

        textView = (TextView) findViewById(R.id.month);
        textView.setTextColor(SystemManager.getInstance().getThemeColor());



       switch(month){

           case 0 :
               monthText ="JANUARY";
               break;
           case 1 :
               monthText = "FEBRUARY";
               break;
           case 2 :
               monthText = "MARCH";
               break;
           case 3 :
               monthText = "APRIL";
               break;
           case 4 :
               monthText = "MAY";
               break;
           case 5 :
               monthText = "JUNE";
               break;
           case 6 :
               monthText = "JULY";
               break;
           case 7 :
               monthText = "AUGUST";
               break;
           case 8 :
               monthText = "SEPTEMBER";
               break;
           case 9 :
               monthText = "OCTOBER";
               break;
           case 10 :
               monthText = "NOVEMBER";
               break;
           case 11 :
               monthText = "DECEMBER";
               break;
       }

        textView.setText(monthText);
        textView.setTypeface(MainPage.typeface);
    }


}