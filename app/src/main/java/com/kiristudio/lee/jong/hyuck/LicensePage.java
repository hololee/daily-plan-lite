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
public class LicensePage extends Activity{

    SystemManager manager = SystemManager.getInstance();

    TextView text_license;
    TextView text_license_content;
    ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//전체화면
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_license);




        text_license = (TextView) findViewById(R.id.license);
        text_license_content = (TextView)findViewById(R.id.content);

        //theme 설정
        text_license.setTextColor(SystemManager.getInstance().getThemeColor());
        text_license.setTypeface(MainPage.typeface);
        text_license_content.setTextColor(SystemManager.getInstance().getThemeColor());
        text_license_content.setTypeface(MainPage.typeface);


//버튼


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

        text_license.setTextColor(SystemManager.getInstance().getThemeColor());
        text_license_content.setTextColor(SystemManager.getInstance().getThemeColor());
        back.setColorFilter(SystemManager.getInstance().getThemeColor());
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
