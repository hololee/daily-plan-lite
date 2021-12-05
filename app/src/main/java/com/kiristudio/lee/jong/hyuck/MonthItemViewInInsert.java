package com.kiristudio.lee.jong.hyuck;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by jong on 2015-09-02.
 */
public class MonthItemViewInInsert extends RelativeLayout {
    TextView textView;
    String monthText;

    public MonthItemViewInInsert(Context context, int month) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.month_item_view, this);

        textView = (TextView) findViewById(R.id.month);
        textView.setTextColor(SystemManager.getInstance().getThemeColor());



       switch(month){

           case 0 :
               monthText ="JAN";
               break;
           case 1 :
               monthText = "FEB";
               break;
           case 2 :
               monthText = "MAR";
               break;
           case 3 :
               monthText = "APR";
               break;
           case 4 :
               monthText = "MAY";
               break;
           case 5 :
               monthText = "JUN";
               break;
           case 6 :
               monthText = "JUL";
               break;
           case 7 :
               monthText = "AUG";
               break;
           case 8 :
               monthText = "SEP";
               break;
           case 9 :
               monthText = "OCT";
               break;
           case 10 :
               monthText = "NOV";
               break;
           case 11 :
               monthText = "DEC";
               break;
       }

        textView.setText(monthText);
        textView.setTypeface(MainPage.typeface);
    }


}