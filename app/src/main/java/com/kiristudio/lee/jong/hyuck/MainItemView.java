package com.kiristudio.lee.jong.hyuck;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by jongH on 2015-08-31.
 */
public class MainItemView extends RelativeLayout {


    private Context context;

    int date;
    int year;
    int month;
    String content;

    String yoil;

    TextView view_yoil, view_date, view_content;

    public MainItemView(Context context) {
        super(context);
       this.context = context;


    }

    public void init(){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_view, this);

        ImageView background = (ImageView) findViewById(R.id.back);
        background.setColorFilter(SystemManager.getInstance().getThemeColor());

        view_yoil = (TextView) findViewById(R.id.yoil);
        view_date = (TextView) findViewById(R.id.date);
        view_content = (TextView) findViewById(R.id.content);

        view_yoil.setTypeface(MainPage.typeface);
        view_date.setTypeface(MainPage.typeface);

        view_yoil.setTextColor(Color.WHITE);
        view_date.setTextColor(Color.WHITE);

        view_content.setTypeface(MainPage.typeface);
        view_content.setText(content);
        view_content.setTextColor(SystemManager.getInstance().getThemeColor());


        Calendar cal = Calendar.getInstance();
        cal.set(year,month-1,date);

        Log.d("current date " ,""+ year+ (month) + date);

        int day_of_week = cal.get(Calendar.DAY_OF_WEEK);

        switch (day_of_week){

            case 1 : {
                yoil = "SUN";
                break;
            }
            case 2 : {
                yoil = "MON";
                break;
            }
            case 3 : {
                yoil = "TUE";
                break;
            }
            case 4 : {
                yoil = "WED";
                break;
            }
            case 5 : {
                yoil = "THU";
                break;
            }
            case 6 : {
                yoil = "FRI";
                break;
            }
            case 7 : {
                yoil = "SAT";
                break;
            }


        }

        view_yoil.setText(yoil);
        view_date.setText("" + date);


    }


    public void setDate(int date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
