package com.kiristudio.lee.jong.hyuck;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by jongH on 2015-08-30.
 */
public class InsertPage extends Activity {

    final String TABLE_NAME = "dataes";
    final String DATABASE_NAME = "database";
    final int VER = 1;

    Spinner yearSpinner;
    Spinner monthSpinner;
    Spinner dateSpinner;

    int SelectedYear;
    int SelectedMonth;
    int SelectedDate;

    String am_pm;
    String Hour;
    String Minute;

    boolean isCharging;

    EditText editText;
    ImageView battery;

    String Content;

    View view;
    TextClock textClock;
    ImageButton button_add;
    TextView textView;
    TextView addTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//전체화면
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_insert);


        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.flags &= ~WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        getWindow().setAttributes(params);


//년도 옆에 색상

        view = findViewById(R.id.view);
        view.setBackgroundColor(SystemManager.getInstance().getThemeColor());


//시계 설정

        textClock = (TextClock) findViewById(R.id.textClock);
        textClock.setTextColor(SystemManager.getInstance().getThemeColor());
        textClock.setTypeface(MainPage.typeface);

//배터리 설정

        battery = (ImageView) findViewById(R.id.battery);


        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction() == Intent.ACTION_BATTERY_CHANGED) {

                    //배터리 상태 확인
                    if (intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0) == 0) {

                        isCharging = false;

                    } else {
                        isCharging = true;
                    }


                    if (isCharging != true) {
                        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
                        int percent = (level * 100) / scale;

                        if (percent <= 100 && 90 < percent) {

                            battery.setImageResource(R.drawable.battery_100);
                            battery.setColorFilter(SystemManager.getInstance().getThemeColor());


                        }
                        if (percent <= 90 && 70 < percent) {

                            battery.setImageResource(R.drawable.battery_80);
                            battery.setColorFilter(SystemManager.getInstance().getThemeColor());


                        }
                        if (percent <= 70 && 50 < percent) {

                            battery.setImageResource(R.drawable.battery_60);
                            battery.setColorFilter(SystemManager.getInstance().getThemeColor());

                        }
                        if (percent <= 50 && 30 < percent) {

                            battery.setImageResource(R.drawable.battery_40);
                            battery.setColorFilter(SystemManager.getInstance().getThemeColor());


                        }
                        if (percent <= 30 && 15 < percent) {

                            battery.setImageResource(R.drawable.battery_20);
                            battery.setColorFilter(SystemManager.getInstance().getThemeColor());

                        }
                        if (percent <= 15 && 0 < percent) {

                            battery.setImageResource(R.drawable.battery_0);
                            battery.setColorFilter(Color.RED);
                        }
                    } else {

                        battery.setImageResource(R.drawable.battery_charged);
                        battery.setColorFilter(SystemManager.getInstance().getThemeColor());

                    }

                }
                if (intent.getAction() == Intent.ACTION_POWER_CONNECTED) {

                    isCharging = true;
                    battery.setImageResource(R.drawable.battery_charged);
                    battery.setColorFilter(SystemManager.getInstance().getThemeColor());
                }

                if (intent.getAction() == Intent.ACTION_POWER_DISCONNECTED) {
                    isCharging = false;

                }

            }
        };

        registerReceiver(receiver, filter);


//editText 초기 설정

        editText = (EditText) findViewById(R.id.editText);
        editText.setTypeface(MainPage.typeface);
        editText.setTextColor(SystemManager.getInstance().getThemeColor());
        editText.setHighlightColor(SystemManager.getInstance().getmThemeColorAlpha());
        editText.setTextSize(SystemManager.getInstance().getFontSize());


//스피너 설정


        //날짜 선택 정보 가져오기
        Bundle bundle = getIntent().getExtras();
        int yyear = (int) bundle.get("year");
        int mmonth = (int) bundle.get("month");
        String ddate = (String) bundle.get("date");

        yearSpinner = (Spinner) findViewById(R.id.year);
        monthSpinner = (Spinner) findViewById(R.id.month);
        dateSpinner = (Spinner) findViewById(R.id.date);

        YearItemAdapter yearAdapter = new YearItemAdapter(getApplicationContext());
        MonthItemAdapterInInsert monthAdapter = new MonthItemAdapterInInsert(getApplicationContext());
        DateItemAdapter dateAdapter = new DateItemAdapter(getApplicationContext());

        yearSpinner.setAdapter(yearAdapter);
        monthSpinner.setAdapter(monthAdapter);
        dateSpinner.setAdapter(dateAdapter);

        Calendar today = Calendar.getInstance();


        //초가화 설정

        SelectedYear = yyear;
        SelectedMonth = mmonth;
        SelectedDate = Integer.parseInt(ddate);

        yearSpinner.setSelection(yyear - 2015);
        monthSpinner.setSelection(mmonth);
        dateSpinner.setSelection(Integer.parseInt(ddate) - 1);

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editText.setText("");
                SelectedYear = position + 2015;
                 Log.e("selected1", "year : " + SelectedYear);

                loadData();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editText.setText("");
                SelectedMonth = position;
                 Log.e("selected2", "month : " + SelectedMonth);
                loadData();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editText.setText("");
                SelectedDate = position + 1;
                 Log.e("selected3", "date : " + SelectedDate);
                loadData();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//버튼 설정


        button_add = (ImageButton) findViewById(R.id.add);

        //색상 필터
        button_add.setColorFilter(SystemManager.getInstance().getThemeColor());

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();

                finish();
                overridePendingTransition(R.anim.slide, R.anim.slide2);
            }
        });


        textView = (TextView) findViewById(R.id.clear);
        textView.setTextColor(SystemManager.getInstance().getThemeColor());
        textView.setTypeface(MainPage.typeface2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(InsertPage.this);
                builder.setCancelable(true);
                builder.setMessage("작성한 글이 초기화 됩니다!");
                MainItemView itemView = new MainItemView(getApplicationContext());
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editText.setText("");
                        saveData();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });


        addTime = (TextView) findViewById(R.id.add_time);
        addTime.setTextColor(SystemManager.getInstance().getThemeColor());
        addTime.setTypeface(MainPage.typeface2);
        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();

                if (cal.get(Calendar.AM_PM) == 0) {
                    am_pm = "AM";
                } else {
                    am_pm = "PM";
                }

                if (String.valueOf(cal.get(Calendar.HOUR)).length() == 1) {

                    Hour = "0" + cal.get(Calendar.HOUR);
                } else {
                    Hour = "" + cal.get(Calendar.HOUR);
                }

                if (String.valueOf(cal.get(Calendar.MINUTE)).length() == 1) {
                    Minute = "0" + cal.get(Calendar.MINUTE);
                } else {
                    Minute = "" + cal.get(Calendar.MINUTE);
                }


                editText.append("\n" + am_pm + " " + Hour + ":" + Minute );
            }
        });

    }


    //데이터 저장하기
    public void saveData() {

        //editText에서 content 불러오기
        String content = editText.getText().toString();
        if(SelectedMonth == 0){
            SelectedMonth = -1;
        }
        Log.d("selected date ", "month1 : " + SelectedMonth);
        String selectedDate = "" + SelectedYear + "" + (SelectedMonth + 1) + "" + SelectedDate;
        Log.d("selected date ", "date : " + selectedDate);
        Log.d("selected date ", "month : " + (SelectedMonth + 1));

        DataBaseHelper helper = new DataBaseHelper(getApplicationContext(), DATABASE_NAME, null, VER);
        SQLiteDatabase db = helper.getWritableDatabase();


        db.execSQL("update " + TABLE_NAME + " set Date = " + selectedDate + ", Content = '" + content + "' where Date = " + selectedDate + "");

        if (content.length() == 0) {

            db.execSQL("delete from " + TABLE_NAME + " where Date = '" + selectedDate + "'");

        }

        db.close();
        helper.close();

    }

    //저장된 데이터 불러오기
    public void loadData() {

        String content = editText.getText().toString();
        String selectedDate = "" + SelectedYear + "" + (SelectedMonth + 1) + "" + SelectedDate;

        DataBaseHelper helper = new DataBaseHelper(getApplicationContext(), DATABASE_NAME, null, VER);
        SQLiteDatabase db = helper.getWritableDatabase();

        //editText로 content 불러오기

        if(SelectedMonth == 0){
            SelectedMonth = -1;
        }
        String[] args = {"" + SelectedYear + "" + (SelectedMonth + 1) + "" + SelectedDate};
        Log.e("selected DAte", "DATE : " + args[0]);
        Cursor cursor = db.rawQuery("select Date, Content from " + TABLE_NAME + " where Date = ?", args);
        cursor.moveToNext();
        //Log.e("selected", "count : " + cursor.getCount());
        if (cursor.getCount() == 0) {

            Content = "";
            db.execSQL("insert into " + TABLE_NAME + "(Date, Content) values (" + selectedDate + ", '" + content + "');");
        } else {
            Content = cursor.getString(1);
        }


        editText.setText(Content);
        Content = "";


        db.close();
        helper.close();

        //커서 위치 잡기
        editText.setSelection(editText.length());
    }



    public void zeroDelete(){

        //내용이 없으면 지우기
        String content = editText.getText().toString();
        if(SelectedMonth == 1){
            SelectedMonth = -1;
        }
        String selectedDate = "" + SelectedYear + "" + (SelectedMonth + 1) + "" + SelectedDate;
        Log.d("selected date ", "date : " + selectedDate);

        DataBaseHelper helper = new DataBaseHelper(getApplicationContext(), DATABASE_NAME, null, VER);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (content.length() == 0) {

            db.execSQL("delete from " + TABLE_NAME + " where Date = '" + selectedDate + "'");

        }

        db.close();
        helper.close();


    }

    @Override
    protected void onResume() {
        super.onResume();


        SystemManager manager = SystemManager.getInstance();
        //저장값 불러오기
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("system", Context.MODE_PRIVATE);
        manager.setFontSize(preferences.getInt("mFontSize", (int) getResources().getDimension(R.dimen.font_size_medium)));
        manager.setThemeColor(preferences.getInt("mThemeColor", SystemManager.COLOR_SKY));
        manager.setmThemeColorAlpha(preferences.getInt("mThemeColorAlpha", SystemManager.COLOR_SKY_ALPHA));
        manager.setPassward(preferences.getInt("mPassward", 0000));
        manager.setPayed(preferences.getBoolean("ispayed", false));
        manager.setProtected(preferences.getBoolean("isprotected", false));

        //color 저장값
        view.setBackgroundColor(SystemManager.getInstance().getThemeColor());
        textClock.setTextColor(SystemManager.getInstance().getThemeColor());
        battery.setColorFilter(SystemManager.getInstance().getThemeColor());
        editText.setTextColor(SystemManager.getInstance().getThemeColor());
        editText.setHighlightColor(SystemManager.getInstance().getmThemeColorAlpha());
        editText.setTextColor(SystemManager.getInstance().getThemeColor());
        editText.setHighlightColor(SystemManager.getInstance().getmThemeColorAlpha());
        button_add.setColorFilter(SystemManager.getInstance().getThemeColor());
        addTime.setTextColor(SystemManager.getInstance().getThemeColor());
        textView.setTextColor(SystemManager.getInstance().getThemeColor());


    }

    //back 버튼 설정
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        zeroDelete();

        finish();
        overridePendingTransition(R.anim.slide, R.anim.slide2);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //내용이 없으면 지우기
        String content = editText.getText().toString();
        if(SelectedMonth == 1){
            SelectedMonth = -1;
        }
        String selectedDate = "" + SelectedYear + "" + (SelectedMonth + 1) + "" + SelectedDate;
        Log.d("selected date ", "date : " + selectedDate);

        DataBaseHelper helper = new DataBaseHelper(getApplicationContext(), DATABASE_NAME, null, VER);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (content.length() == 0) {

            db.execSQL("delete from " + TABLE_NAME + " where Date = '" + selectedDate + "'");

        }

        db.close();
        helper.close();

        finish();
        overridePendingTransition(R.anim.slide, R.anim.slide2);


        SharedPreferences preferences = getSharedPreferences("system", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("current", true);
        editor.commit();

        finish();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences preferences = getSharedPreferences("system", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("current", false);
        editor.commit();
    }
}
