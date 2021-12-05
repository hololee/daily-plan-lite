package com.kiristudio.lee.jong.hyuck;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by jongH on 2015-08-30.
 */
public class SettingPage extends Activity {

    float medium, small, large;

    ImageButton button_pass, button_font, button_exit, button_backup;


    TextView title;
    TextView passward;
    TextView fontSize;
    TextView color;
    TextView backup;


    TextView license;
    TextView creator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//전체화면
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_setting);

        final SharedPreferences preferences = getApplicationContext().getSharedPreferences("system", Context.MODE_PRIVATE);

//타이틀 색 설정

        title = (TextView) findViewById(R.id.setting);
        title.setTextColor(SystemManager.getInstance().getThemeColor());
        title.setTypeface(MainPage.typeface);

//TextView 설정

        fontSize = (TextView) findViewById(R.id.font);
        passward = (TextView) findViewById(R.id.pass);
        color = (TextView) findViewById(R.id.color_select);
        backup = (TextView) findViewById(R.id.backup);
        TextView alarm = (TextView) findViewById(R.id.textView9);
        alarm.setTypeface(MainPage.typeface);
        alarm.setTextColor(SystemManager.getInstance().getThemeColor());


        fontSize.setTextColor(SystemManager.getInstance().getThemeColor());
        //passward.setTextColor(SystemManager.getInstance().getThemeColor());
        // color.setTextColor(SystemManager.getInstance().getThemeColor());
        passward.setTextColor(SystemManager.COLOR_GRAY_ALPHA);
        color.setTextColor(SystemManager.COLOR_GRAY_ALPHA);
        backup.setTextColor(SystemManager.getInstance().getThemeColor());
        fontSize.setTypeface(MainPage.typeface);
        passward.setTypeface(MainPage.typeface);
        color.setTypeface(MainPage.typeface);
        backup.setTypeface(MainPage.typeface);

//ImageButton 설정

        button_font = (ImageButton) findViewById(R.id.imageButton);
        button_pass = (ImageButton) findViewById(R.id.imageButton2);
        button_exit = (ImageButton) findViewById(R.id.exit);
        button_backup = (ImageButton) findViewById(R.id.button_backup);
        button_font.setColorFilter(SystemManager.getInstance().getThemeColor());
        //button_pass.setColorFilter(SystemManager.getInstance().getThemeColor());
        button_pass.setColorFilter(SystemManager.COLOR_GRAY_ALPHA);
        button_exit.setColorFilter(SystemManager.getInstance().getThemeColor());
        button_backup.setColorFilter(SystemManager.getInstance().getThemeColor());


        //button_font 초기 이미지 설정

        small = getResources().getDimension(R.dimen.font_size_small);
        medium = getResources().getDimension(R.dimen.font_size_medium);
        large = getResources().getDimension(R.dimen.font_size_large);

        if (SystemManager.getInstance().getFontSize() == small) {
            button_font.setImageResource(R.drawable.font_small);
        } else if (SystemManager.getInstance().getFontSize() == medium) {
            button_font.setImageResource(R.drawable.font_medium);
        } else if (SystemManager.getInstance().getFontSize() == large) {
            button_font.setImageResource(R.drawable.font_large);
        }

        //button_pass 초기 이미지 설정
        if (SystemManager.getInstance().getIsProtected() == false) {
            button_pass.setImageResource(R.drawable.seleter_off);
        } else if (SystemManager.getInstance().getIsProtected() == true) {
            button_pass.setImageResource(R.drawable.seleter_on);
        }


 //button_font 클릭 설정
        button_font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.d("clicked", "font Clicked");

                if (SystemManager.getInstance().getFontSize() == small) {
                    button_font.setImageResource(R.drawable.font_medium);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("mFontSize", (int) getResources().getDimension(R.dimen.font_size_medium));
                    editor.commit();
                    SystemManager.getInstance().setFontSize(getResources().getDimension(R.dimen.font_size_medium));
                } else if (SystemManager.getInstance().getFontSize() == medium) {
                    button_font.setImageResource(R.drawable.font_large);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("mFontSize", (int) getResources().getDimension(R.dimen.font_size_large));
                    editor.commit();
                    SystemManager.getInstance().setFontSize(getResources().getDimension(R.dimen.font_size_large));
                } else if (SystemManager.getInstance().getFontSize() == large) {
                    button_font.setImageResource(R.drawable.font_small);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("mFontSize", (int) getResources().getDimension(R.dimen.font_size_small));
                    editor.commit();
                    SystemManager.getInstance().setFontSize(getResources().getDimension(R.dimen.font_size_small));
                }

            }
        });


/*   // button_pass  설정
       button_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (SystemManager.getInstance().getIsProtected() == false) {
                    button_pass.setImageResource(R.drawable.seleter_on);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isprotected", true);
                    editor.commit();
                    SystemManager.getInstance().setProtected(true);

                    Intent intent = new Intent(getApplicationContext(), createPasswardPage.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide, R.anim.slide2);

                } else if (SystemManager.getInstance().getIsProtected() == true) {
                    button_pass.setImageResource(R.drawable.seleter_off);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isprotected", false);
                    editor.putInt("mPassward", 0000);
                    editor.commit();
                    SystemManager.getInstance().setProtected(false);


                }


            }
        });
*/

//button_exit 클릭 설정
        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                overridePendingTransition(R.anim.slide, R.anim.slide2);

            }
        });


//button_backup 클릭 설정

        button_backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingPage.this);
                builder.setCancelable(true);
                builder.setMessage("   데이터를 백업 및 복원합니다. \n (데이터는 Storage(메모리)/Daily Plan 에 저장 되며 기존 백업은 지워집니다. \n복원시 현재 데이터는 모두 복원 데이터로 전환됩니다.)");


                builder.setNeutralButton("복원", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder restores = new AlertDialog.Builder(SettingPage.this);
                        restores.setCancelable(true);
                        restores.setMessage("정말 복원 하시겠습니까? \n되돌릴 수 없습니다.");
                        restores.setPositiveButton("복원", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                reStore();

                            }
                        });

                        restores.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        AlertDialog dialog1 = restores.create();
                        dialog1.show();

                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("백업", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        final AlertDialog.Builder backups = new AlertDialog.Builder(SettingPage.this);
                        backups.setCancelable(true);
                        backups.setMessage("정말 백업 하시겠습니까? \n되돌릴 수 없습니다.");
                        backups.setPositiveButton("백업", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                backUp();

                            }
                        });

                        backups.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        AlertDialog dialog2 = backups.create();
                        dialog2.show();

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });


//메뉴 버튼 설정


        creator = (TextView) findViewById(R.id.creator);
        license = (TextView) findViewById(R.id.license);

        creator.setTextColor(SystemManager.getInstance().getThemeColor());
        license.setTextColor(SystemManager.getInstance().getThemeColor());

        creator.setTypeface(MainPage.typeface2);
        license.setTypeface(MainPage.typeface2);


        creator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CreatorPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.slide, R.anim.slide2);

            }
        });

        license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LicensePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.slide, R.anim.slide2);

            }
        });


// 컬러 패드 설정
        ImageButton color_sky = (ImageButton) findViewById(R.id.color_sky);
        ImageButton color_purple = (ImageButton) findViewById(R.id.color_purple);
        ImageButton color_yellow = (ImageButton) findViewById(R.id.color_yellow);
        ImageButton color_orange = (ImageButton) findViewById(R.id.color_orange);
        ImageButton color_red = (ImageButton) findViewById(R.id.color_red);
        ImageButton color_green = (ImageButton) findViewById(R.id.color_green);
        ImageButton color_mint = (ImageButton) findViewById(R.id.color_mint);
        ImageButton color_blue = (ImageButton) findViewById(R.id.color_blue);
        ImageButton color_gray = (ImageButton) findViewById(R.id.color_gray);
        ImageButton color_black = (ImageButton) findViewById(R.id.color_black);

        //패드 색 씌우기
      /*  color_sky.setColorFilter(SystemManager.COLOR_SKY);
        color_purple.setColorFilter(SystemManager.COLOR_PURPLE);
        color_yellow.setColorFilter(SystemManager.COLOR_YELLOW);
        color_orange.setColorFilter(SystemManager.COLOR_ORANGE);
        color_red.setColorFilter(SystemManager.COLOR_RED);
        color_green.setColorFilter(SystemManager.COLOR_GREEN);
        color_mint.setColorFilter(SystemManager.COLOR_MINT);
        color_blue.setColorFilter(SystemManager.COLOR_BLUE);
        color_gray.setColorFilter(SystemManager.COLOR_GRAY);
        color_black.setColorFilter(SystemManager.COLOR_BLACK);
*/
        color_sky.setColorFilter(SystemManager.COLOR_GRAY_ALPHA);
        color_purple.setColorFilter(SystemManager.COLOR_GRAY_ALPHA);
        color_yellow.setColorFilter(SystemManager.COLOR_GRAY_ALPHA);
        color_orange.setColorFilter(SystemManager.COLOR_GRAY_ALPHA);
        color_red.setColorFilter(SystemManager.COLOR_GRAY_ALPHA);
        color_green.setColorFilter(SystemManager.COLOR_GRAY_ALPHA);
        color_mint.setColorFilter(SystemManager.COLOR_GRAY_ALPHA);
        color_blue.setColorFilter(SystemManager.COLOR_GRAY_ALPHA);
        color_gray.setColorFilter(SystemManager.COLOR_GRAY_ALPHA);
        color_black.setColorFilter(SystemManager.COLOR_GRAY_ALPHA);

/*
        color_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("mThemeColor", SystemManager.COLOR_BLACK);
                editor.putInt("mThemeColorAlpha", SystemManager.COLOR_BLACK_ALPHA);
                editor.commit();
                SystemManager.getInstance().setThemeColor(SystemManager.COLOR_BLACK);
                SystemManager.getInstance().setmThemeColorAlpha(SystemManager.COLOR_BLACK_ALPHA);


                title.setTextColor(SystemManager.getInstance().getThemeColor());
                fontSize.setTextColor(SystemManager.getInstance().getThemeColor());
                passward.setTextColor(SystemManager.getInstance().getThemeColor());
                color.setTextColor(SystemManager.getInstance().getThemeColor());
                button_font.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_pass.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_exit.setColorFilter(SystemManager.getInstance().getThemeColor());
                creator.setTextColor(SystemManager.getInstance().getThemeColor());
                license.setTextColor(SystemManager.getInstance().getThemeColor());
                backup.setTextColor(SystemManager.getInstance().getThemeColor());
                button_backup.setColorFilter(SystemManager.getInstance().getThemeColor());

            }
        });


        color_gray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("mThemeColor", SystemManager.COLOR_GRAY);
                editor.putInt("mThemeColorAlpha", SystemManager.COLOR_GRAY_ALPHA);
                editor.commit();
                SystemManager.getInstance().setThemeColor(SystemManager.COLOR_GRAY);
                SystemManager.getInstance().setmThemeColorAlpha(SystemManager.COLOR_GRAY_ALPHA);


                title.setTextColor(SystemManager.getInstance().getThemeColor());
                fontSize.setTextColor(SystemManager.getInstance().getThemeColor());
                passward.setTextColor(SystemManager.getInstance().getThemeColor());
                color.setTextColor(SystemManager.getInstance().getThemeColor());
                button_font.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_pass.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_exit.setColorFilter(SystemManager.getInstance().getThemeColor());
                creator.setTextColor(SystemManager.getInstance().getThemeColor());
                license.setTextColor(SystemManager.getInstance().getThemeColor());
                backup.setTextColor(SystemManager.getInstance().getThemeColor());
                button_backup.setColorFilter(SystemManager.getInstance().getThemeColor());

            }
        });

        color_sky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("mThemeColor", SystemManager.COLOR_SKY);
                editor.putInt("mThemeColorAlpha", SystemManager.COLOR_SKY_ALPHA);
                editor.commit();
                SystemManager.getInstance().setThemeColor(SystemManager.COLOR_SKY);
                SystemManager.getInstance().setmThemeColorAlpha(SystemManager.COLOR_SKY_ALPHA);


                title.setTextColor(SystemManager.getInstance().getThemeColor());
                fontSize.setTextColor(SystemManager.getInstance().getThemeColor());
                passward.setTextColor(SystemManager.getInstance().getThemeColor());
                color.setTextColor(SystemManager.getInstance().getThemeColor());
                button_font.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_pass.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_exit.setColorFilter(SystemManager.getInstance().getThemeColor());
                creator.setTextColor(SystemManager.getInstance().getThemeColor());
                license.setTextColor(SystemManager.getInstance().getThemeColor());
                backup.setTextColor(SystemManager.getInstance().getThemeColor());
                button_backup.setColorFilter(SystemManager.getInstance().getThemeColor());

            }
        });
        color_purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("mThemeColor", SystemManager.COLOR_PURPLE);
                editor.putInt("mThemeColorAlpha", SystemManager.COLOR_PURPLE_ALPHA);
                editor.commit();
                SystemManager.getInstance().setThemeColor(SystemManager.COLOR_PURPLE);
                SystemManager.getInstance().setmThemeColorAlpha(SystemManager.COLOR_PURPLE_ALPHA);


                title.setTextColor(SystemManager.getInstance().getThemeColor());
                fontSize.setTextColor(SystemManager.getInstance().getThemeColor());
                passward.setTextColor(SystemManager.getInstance().getThemeColor());
                color.setTextColor(SystemManager.getInstance().getThemeColor());
                button_font.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_pass.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_exit.setColorFilter(SystemManager.getInstance().getThemeColor());
                creator.setTextColor(SystemManager.getInstance().getThemeColor());
                license.setTextColor(SystemManager.getInstance().getThemeColor());
                backup.setTextColor(SystemManager.getInstance().getThemeColor());
                button_backup.setColorFilter(SystemManager.getInstance().getThemeColor());
            }
        });

        color_yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("mThemeColor", SystemManager.COLOR_YELLOW);
                editor.putInt("mThemeColorAlpha", SystemManager.COLOR_YELLOW_ALPHA);
                editor.commit();
                SystemManager.getInstance().setThemeColor(SystemManager.COLOR_YELLOW);
                SystemManager.getInstance().setmThemeColorAlpha(SystemManager.COLOR_YELLOW_ALPHA);

                title.setTextColor(SystemManager.getInstance().getThemeColor());
                fontSize.setTextColor(SystemManager.getInstance().getThemeColor());
                passward.setTextColor(SystemManager.getInstance().getThemeColor());
                color.setTextColor(SystemManager.getInstance().getThemeColor());
                button_font.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_pass.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_exit.setColorFilter(SystemManager.getInstance().getThemeColor());
                creator.setTextColor(SystemManager.getInstance().getThemeColor());
                license.setTextColor(SystemManager.getInstance().getThemeColor());
                backup.setTextColor(SystemManager.getInstance().getThemeColor());
                button_backup.setColorFilter(SystemManager.getInstance().getThemeColor());
            }
        });

        color_orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("mThemeColor", SystemManager.COLOR_ORANGE);
                editor.putInt("mThemeColorAlpha", SystemManager.COLOR_ORANGE_ALPHA);
                editor.commit();
                SystemManager.getInstance().setThemeColor(SystemManager.COLOR_ORANGE);
                SystemManager.getInstance().setmThemeColorAlpha(SystemManager.COLOR_ORANGE_ALPHA);

                title.setTextColor(SystemManager.getInstance().getThemeColor());
                fontSize.setTextColor(SystemManager.getInstance().getThemeColor());
                passward.setTextColor(SystemManager.getInstance().getThemeColor());
                color.setTextColor(SystemManager.getInstance().getThemeColor());
                button_font.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_pass.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_exit.setColorFilter(SystemManager.getInstance().getThemeColor());
                creator.setTextColor(SystemManager.getInstance().getThemeColor());
                license.setTextColor(SystemManager.getInstance().getThemeColor());
                backup.setTextColor(SystemManager.getInstance().getThemeColor());
                button_backup.setColorFilter(SystemManager.getInstance().getThemeColor());
            }
        });


        color_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("mThemeColor", SystemManager.COLOR_RED);
                editor.putInt("mThemeColorAlpha", SystemManager.COLOR_RED_ALPHA);
                editor.commit();
                SystemManager.getInstance().setThemeColor(SystemManager.COLOR_RED);
                SystemManager.getInstance().setmThemeColorAlpha(SystemManager.COLOR_RED_ALPHA);

                title.setTextColor(SystemManager.getInstance().getThemeColor());
                fontSize.setTextColor(SystemManager.getInstance().getThemeColor());
                passward.setTextColor(SystemManager.getInstance().getThemeColor());
                color.setTextColor(SystemManager.getInstance().getThemeColor());
                button_font.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_pass.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_exit.setColorFilter(SystemManager.getInstance().getThemeColor());
                creator.setTextColor(SystemManager.getInstance().getThemeColor());
                license.setTextColor(SystemManager.getInstance().getThemeColor());
                backup.setTextColor(SystemManager.getInstance().getThemeColor());
                button_backup.setColorFilter(SystemManager.getInstance().getThemeColor());
            }
        });

        color_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("mThemeColor", SystemManager.COLOR_GREEN);
                editor.putInt("mThemeColorAlpha", SystemManager.COLOR_GREEN_ALPHA);
                editor.commit();
                SystemManager.getInstance().setThemeColor(SystemManager.COLOR_GREEN);
                SystemManager.getInstance().setmThemeColorAlpha(SystemManager.COLOR_GREEN_ALPHA);

                title.setTextColor(SystemManager.getInstance().getThemeColor());
                fontSize.setTextColor(SystemManager.getInstance().getThemeColor());
                passward.setTextColor(SystemManager.getInstance().getThemeColor());
                color.setTextColor(SystemManager.getInstance().getThemeColor());
                button_font.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_pass.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_exit.setColorFilter(SystemManager.getInstance().getThemeColor());
                creator.setTextColor(SystemManager.getInstance().getThemeColor());
                license.setTextColor(SystemManager.getInstance().getThemeColor());
                backup.setTextColor(SystemManager.getInstance().getThemeColor());
                button_backup.setColorFilter(SystemManager.getInstance().getThemeColor());
            }
        });


        color_mint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("mThemeColor", SystemManager.COLOR_MINT);
                editor.putInt("mThemeColorAlpha", SystemManager.COLOR_MINT_ALPHA);
                editor.commit();
                SystemManager.getInstance().setThemeColor(SystemManager.COLOR_MINT);
                SystemManager.getInstance().setmThemeColorAlpha(SystemManager.COLOR_MINT_ALPHA);

                title.setTextColor(SystemManager.getInstance().getThemeColor());
                fontSize.setTextColor(SystemManager.getInstance().getThemeColor());
                passward.setTextColor(SystemManager.getInstance().getThemeColor());
                color.setTextColor(SystemManager.getInstance().getThemeColor());
                button_font.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_pass.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_exit.setColorFilter(SystemManager.getInstance().getThemeColor());
                creator.setTextColor(SystemManager.getInstance().getThemeColor());
                backup.setTextColor(SystemManager.getInstance().getThemeColor());
                button_backup.setColorFilter(SystemManager.getInstance().getThemeColor());
                license.setTextColor(SystemManager.getInstance().getThemeColor());
            }
        });

        color_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("mThemeColor", SystemManager.COLOR_BLUE);
                editor.putInt("mThemeColorAlpha", SystemManager.COLOR_BLUE_ALPHA);
                editor.commit();
                SystemManager.getInstance().setThemeColor(SystemManager.COLOR_BLUE);
                SystemManager.getInstance().setmThemeColorAlpha(SystemManager.COLOR_BLUE_ALPHA);

                title.setTextColor(SystemManager.getInstance().getThemeColor());
                fontSize.setTextColor(SystemManager.getInstance().getThemeColor());
                passward.setTextColor(SystemManager.getInstance().getThemeColor());
                color.setTextColor(SystemManager.getInstance().getThemeColor());
                button_font.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_pass.setColorFilter(SystemManager.getInstance().getThemeColor());
                button_exit.setColorFilter(SystemManager.getInstance().getThemeColor());
                creator.setTextColor(SystemManager.getInstance().getThemeColor());
                license.setTextColor(SystemManager.getInstance().getThemeColor());
                backup.setTextColor(SystemManager.getInstance().getThemeColor());
                button_backup.setColorFilter(SystemManager.getInstance().getThemeColor());
            }
        });


*/
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

        title.setTextColor(SystemManager.getInstance().getThemeColor());
        fontSize.setTextColor(SystemManager.getInstance().getThemeColor());
        passward.setTextColor(SystemManager.COLOR_GRAY_ALPHA);
        color.setTextColor(SystemManager.COLOR_GRAY_ALPHA);
        button_font.setColorFilter(SystemManager.getInstance().getThemeColor());
        button_pass.setColorFilter(SystemManager.COLOR_GRAY_ALPHA);
        button_exit.setColorFilter(SystemManager.getInstance().getThemeColor());
        creator.setTextColor(SystemManager.getInstance().getThemeColor());
        license.setTextColor(SystemManager.getInstance().getThemeColor());
        backup.setTextColor(SystemManager.getInstance().getThemeColor());
        button_backup.setColorFilter(SystemManager.getInstance().getThemeColor());
    }


///백업 및 복구

    public void backUp() {


        try {
            File sd = Environment.getExternalStorageDirectory();
//체크!!!!!////////////////////////파일 저장 위치 이름 바구기
            File Path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Daily Plan");
            if (!Path.exists())
                Path.mkdirs();

            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {


//체크!!!!!////////////////////////패키지 이름 바구기
                String currentDBPath = "/data/com.kiristudio.lee.jong.hyuck/databases/database";
                String backupDBPath = "database";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(Path, backupDBPath);

                if (currentDB.exists()) {
                    //여기까지는 들어옴
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();

                    Toast toast = Toast.makeText(getApplicationContext(), "데이터 백업 완료!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "error : db not exists", Toast.LENGTH_SHORT);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "error : Can't read ExternalStorageDirectory", Toast.LENGTH_SHORT);
                toast.show();
            }
        } catch (Exception e) {
        }

    }

    public void reStore() {

        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            File Path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Daily Plan");


            if (Path.exists()) {
                if (sd.canWrite()) {
//체크!!!!!////////////////////////패키지 이름 바구기
                    String currentDBPath = "/data/com.kiristudio.lee.jong.hyuck/databases/database";
                    String backupDBPath = "database";
                    File currentDB = new File(data, currentDBPath);
                    File backupDB = new File(Path, backupDBPath);

                    if (currentDB.exists()) {
                        FileChannel src = new FileInputStream(backupDB).getChannel();
                        FileChannel dst = new FileOutputStream(currentDB).getChannel();
                        dst.transferFrom(src, 0, src.size());
                        src.close();
                        dst.close();
                        Toast.makeText(getApplicationContext(), "데이터 복원 완료!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "error : db not exists", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "error : Can't read ExternalStorageDirectory", Toast.LENGTH_SHORT);
                    toast.show();

                }
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "저장된 데이터가 없습니다.", Toast.LENGTH_SHORT);
                toast.show();

            }
        } catch (Exception e) {
        }


    }


    //back 버튼 설정
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        overridePendingTransition(R.anim.slide, R.anim.slide2);
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
}
