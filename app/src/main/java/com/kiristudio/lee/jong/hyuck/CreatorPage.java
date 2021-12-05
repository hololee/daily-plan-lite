package com.kiristudio.lee.jong.hyuck;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by jongH on 2015-08-30.
 */
public class CreatorPage extends Activity {

    SystemManager manager = SystemManager.getInstance();

    TextView creator;

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//전체화면
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_creator);


//타이틀 설정

        creator = (TextView) findViewById(R.id.creator);

        creator.setTextColor(manager.getThemeColor());
        creator.setTypeface(MainPage.typeface);

//버튼 설정
        back = (ImageButton) findViewById(R.id.goback);
        back.setColorFilter(SystemManager.getInstance().getThemeColor());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences("system", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("current", true);
                editor.commit();
                finish();
                overridePendingTransition(R.anim.slide, R.anim.slide2);

            }
        });



//텍스트 설정

        TextView textView1 = (TextView) findViewById(R.id.textView);
        textView1.setTextColor(SystemManager.getInstance().getThemeColor());
        textView1.setTypeface(MainPage.typeface2);

        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setTextColor(SystemManager.getInstance().getThemeColor());
        textView2.setTypeface(MainPage.typeface2);

        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setTextColor(SystemManager.getInstance().getThemeColor());
        textView3.setTypeface(MainPage.typeface2);

        TextView textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setTextColor(SystemManager.getInstance().getThemeColor());
        textView4.setTypeface(MainPage.typeface2);

        TextView textView5 = (TextView) findViewById(R.id.textView5);
        textView5.setTextColor(SystemManager.getInstance().getThemeColor());
        textView5.setTypeface(MainPage.typeface2);

        TextView textView6 = (TextView) findViewById(R.id.textView6);
        textView6.setTextColor(SystemManager.getInstance().getThemeColor());
        textView6.setTypeface(MainPage.typeface2);

        TextView textView7 = (TextView) findViewById(R.id.textView7);
        textView7.setTextColor(SystemManager.getInstance().getThemeColor());
        textView7.setTypeface(MainPage.typeface2);

        TextView textView8 = (TextView) findViewById(R.id.textView8);
        textView8.setTextColor(SystemManager.getInstance().getThemeColor());
        textView8.setTypeface(MainPage.typeface2);
    }

    @Override
    protected void onResume() {
        super.onResume();

//저장값 불러오기
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("system", Context.MODE_PRIVATE);
        manager.setFontSize(preferences.getInt("mFontSize", (int) getResources().getDimension(R.dimen.font_size_medium)));
        manager.setThemeColor(preferences.getInt("mThemeColor", SystemManager.COLOR_SKY));
        manager.setmThemeColorAlpha(preferences.getInt("mThemeColorAlpha", SystemManager.COLOR_SKY_ALPHA));
        manager.setPassward(preferences.getInt("mPassward", 0));
        manager.setPayed(preferences.getBoolean("ispayed", false));
        manager.setProtected(preferences.getBoolean("isprotected", false));



//암호 설정 상태일때는   암호 액티비티로 이동


    }

    @Override
    protected void onPause() {
        super.onPause();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences preferences = getSharedPreferences("system", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("current", true);
        editor.commit();
        finish();
        overridePendingTransition(R.anim.slide, R.anim.slide2);
    }
}
