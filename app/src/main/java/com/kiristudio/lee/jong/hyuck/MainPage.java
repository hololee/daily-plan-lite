package com.kiristudio.lee.jong.hyuck;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.Calendar;

public class MainPage extends Activity {

    //hint 앱에서 나갈때는 상태를 락으로 바꿈,, 앱 내에서 움직일때에는 상태를 풀림상태로 놓고 모든 액티비티에서 상태 확인해서 락일경우 암호 액티비티로 보냄
    //pass 에서 preferences 에 임시적 암호 상태 저장 true 로
    public static Typeface typeface, typeface2;

    final String TABLE_NAME = "dataes";
    final String DATABASE_NAME = "database";
    final int VER = 1;

    DataBaseHelper helper;
    SQLiteDatabase db;

    Cursor cur;

    MainItemAdapter adapter;

    ImageView battery;
    TextClock textClock;
    ImageButton button_add, button_setting;

    ListView listView;

    SystemManager manager;

    boolean isCharging;

    Spinner yearSpinner;
    Spinner monthSpinner;

    int month;
    int year;
    String date;

    MonthItemAdapter monthAdapter;
    YearItemAdapter yearAdapter;

    boolean current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//전체화면
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);
//초기값 설정
        manager = SystemManager.getInstance();

        //저장값 불러오기
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("system", Context.MODE_PRIVATE);
        manager.setFontSize(preferences.getInt("mFontSize", (int) getResources().getDimension(R.dimen.font_size_medium)));
        manager.setThemeColor(preferences.getInt("mThemeColor", SystemManager.COLOR_SKY));
        manager.setmThemeColorAlpha(preferences.getInt("mThemeColorAlpha", SystemManager.COLOR_SKY_ALPHA));
        manager.setPassward(preferences.getInt("mPassward", 0000));
        manager.setPayed(preferences.getBoolean("ispayed", false));
        manager.setProtected(preferences.getBoolean("isprotected", false));
        manager.setCurrent(preferences.getBoolean("current", true));

        typeface = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothicLight.ttf");
        typeface2 = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothicUltraLight.ttf");

        current = SystemManager.getInstance().getCurrent();





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


                    Log.e("battery", "" + intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0));
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


//스피너 설정

        yearSpinner = (Spinner) findViewById(R.id.year);
        monthSpinner = (Spinner) findViewById(R.id.month);

        yearAdapter = new YearItemAdapter(getApplicationContext());
        monthAdapter = new MonthItemAdapter(getApplicationContext());

        yearSpinner.setAdapter(yearAdapter);
        monthSpinner.setAdapter(monthAdapter);

        Calendar today = Calendar.getInstance();

        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        date = ""+today.get(Calendar.DATE);

        yearSpinner.setSelection(year - 2015);
        monthSpinner.setSelection(month);


        //스피너 바꿀시
       yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               year = position + 2015;

               Log.d("error1", ""+year+month+date);
               invalidate();

           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                month = position;

                Log.d("error2", "" + year + month + date);
                invalidate();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        date = ""+today.get(Calendar.DATE);
        Log.d("error3", "" + year + month + date);

//시계 설정

        textClock = (TextClock) findViewById(R.id.textClock);
        textClock.setTextColor(SystemManager.getInstance().getThemeColor());
        textClock.setTypeface(typeface);

//버튼 설정


        button_add = (ImageButton) findViewById(R.id.add);
        button_setting = (ImageButton) findViewById(R.id.setting);

        //색상 필터
        button_add.setColorFilter(SystemManager.getInstance().getThemeColor());
        button_setting.setColorFilter(SystemManager.getInstance().getThemeColor());

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), InsertPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                intent.putExtra("year",year);
                intent.putExtra("month",month);
                intent.putExtra("date", date);

                startActivity(intent);
                overridePendingTransition(R.anim.slide, R.anim.slide2);


            }
        });

        button_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SettingPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.slide, R.anim.slide2);

                // 첫번쩨가 나가는쪽 두번째가 들어오는쪽


            }
        });


//리스트뷰 설정

        invalidate();

    }


    @Override
    protected void onResume() {
        super.onResume();

        //저장값 불러오기
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("system", Context.MODE_PRIVATE);
        manager.setFontSize(preferences.getInt("mFontSize", (int) getResources().getDimension(R.dimen.font_size_medium)));
        manager.setThemeColor(preferences.getInt("mThemeColor", SystemManager.COLOR_SKY));
        manager.setmThemeColorAlpha(preferences.getInt("mThemeColorAlpha", SystemManager.COLOR_SKY_ALPHA));
        manager.setPassward(preferences.getInt("mPassward", 0000));
        manager.setPayed(preferences.getBoolean("ispayed", false));
        manager.setProtected(preferences.getBoolean("isprotected", false));
        manager.setCurrent(preferences.getBoolean("current", true));

        textClock.setTextColor(SystemManager.getInstance().getThemeColor());
        button_add.setColorFilter(SystemManager.getInstance().getThemeColor());
        button_setting.setColorFilter(SystemManager.getInstance().getThemeColor());
        battery.setColorFilter(SystemManager.getInstance().getThemeColor());

//스피너 설정

        yearSpinner = (Spinner) findViewById(R.id.year);
        monthSpinner = (Spinner) findViewById(R.id.month);

        yearAdapter = new YearItemAdapter(getApplicationContext());
        monthAdapter = new MonthItemAdapter(getApplicationContext());

        yearSpinner.setAdapter(yearAdapter);
        monthSpinner.setAdapter(monthAdapter);

        Calendar today = Calendar.getInstance();

        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);

        yearSpinner.setSelection(year - 2015);
        monthSpinner.setSelection(month);

//passward 설정


        current = SystemManager.getInstance().getCurrent();


        if(SystemManager.getInstance().getIsProtected()) {
            if (current == false) {
                if (SystemManager.getInstance().getIsProtected()) {


                    Intent intent = new Intent(getApplicationContext(), lockPage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide, R.anim.slide2);


                }

            }
        }

        invalidate();

    }


//리스트뷰 갱신
    public void invalidate(){


        adapter = new MainItemAdapter(getApplicationContext());

        helper = new DataBaseHelper(getApplicationContext(), DATABASE_NAME, null, VER);
        db = helper.getWritableDatabase();
        if(month == 0){
            month = -1;
        }
        String[] args = {"" + year + "" + (month + 1) +"%" };
        Log.d("selected date Main :", "" + year + "" + (month + 1) +"%");
        if(db.rawQuery("select Date, Content from " + TABLE_NAME + " where Date like ?",args) != null) {
            cur = db.rawQuery("select Date, Content from " + TABLE_NAME + " where Date like ? order by Date ASC", args);
            int count = cur.getCount();
            if (count != 0) {

                Log.e("count", ":" + count);

                for (int i = 0; i < count; i++) {
                    cur.moveToNext();

                    String Year = ""+year;
                    String YearMonth = "" + year + "" + (month + 1);
                    String YearMonthDate = cur.getString(0);

                    int leg = YearMonth.length();
                    int leg2 = Year.length();

                    String Date = YearMonthDate.substring(leg);
                    String Month = YearMonth.substring(leg2);


                    Log.e("date", ":" + Date);
                    Log.e("month", ":" + Month);

                    String Content = cur.getString(1);

                    MainItem item = new MainItem();

                    item.setYear(Integer.parseInt(Year));
                    item.setMonth(Integer.parseInt(Month));
                    item.setmDate(Integer.parseInt(Date));
                    item.setContent(Content);
                    adapter.addItem(item);

                }
            }


            //content 데이터가 없는 라인은 지우기
            String content = "";
            db.execSQL("delete from " + TABLE_NAME + " where Content = '" +content + "'");



            cur.close();
            db.close();
        }


        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), InsertPage.class);

                TextView views = (TextView) view.findViewById(R.id.date);
                String date = views.getText().toString();

                intent.putExtra("year",year);
                intent.putExtra("month",month);
                intent.putExtra("date",date);

                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.slide, R.anim.slide2);


            }
        });


    }








    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("system", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("current", false);
        editor.commit();



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        overridePendingTransition(R.anim.slide, R.anim.slide2);

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
