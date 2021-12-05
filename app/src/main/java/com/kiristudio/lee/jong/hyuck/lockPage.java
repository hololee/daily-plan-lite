package com.kiristudio.lee.jong.hyuck;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jongH on 2015-08-30.
 */
public class lockPage extends Activity {

    public static Typeface typeface, typeface2;

    SystemManager manager;



    String[] PASS_WARD = new String[4];

    TextView text;
    ImageView[] passClip;
    ImageView[] pass_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//전체화면
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_lock);

// 초기화 설정

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


        typeface = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothicLight.ttf");
        typeface2 = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothicUltraLight.ttf");


//Text 설정


        text = (TextView) findViewById(R.id.text);
        text.setTextColor(manager.getThemeColor());
        text.setTypeface(typeface);

        TextView text2 = (TextView) findViewById(R.id.textView);
        text2.setTypeface(typeface);


//passClip 설정

        passClip = new ImageView[4];

        passClip[0] = (ImageView) findViewById(R.id.passclip1);
        passClip[1] = (ImageView) findViewById(R.id.passclip2);
        passClip[2] = (ImageView) findViewById(R.id.passclip3);
        passClip[3] = (ImageView) findViewById(R.id.passclip4);
        for (int i = 0; i < 4; i++) {
            passClip[i].setColorFilter(manager.getThemeColor());
        }


//button 설정


        pass_button = new ImageView[11];

        pass_button[0] = (ImageView) findViewById(R.id.imageButton3);
        pass_button[1] = (ImageView) findViewById(R.id.imageButton10);
        pass_button[2] = (ImageView) findViewById(R.id.imageButton6);
        pass_button[3] = (ImageView) findViewById(R.id.imageButton14);
        pass_button[4] = (ImageView) findViewById(R.id.imageButton9);
        pass_button[5] = (ImageView) findViewById(R.id.imageButton5);
        pass_button[6] = (ImageView) findViewById(R.id.imageButton13);
        pass_button[7] = (ImageView) findViewById(R.id.imageButton8);
        pass_button[8] = (ImageView) findViewById(R.id.imageButton4);
        pass_button[9] = (ImageView) findViewById(R.id.imageButton12);
        pass_button[10] = (ImageView) findViewById(R.id.imageButton11);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            pass_button[0].setColorFilter(manager.getThemeColor());

            pass_button[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (PASS_WARD[0] == null) {

                        PASS_WARD[0] = "" + 0;
                        passClip[0].setImageResource(R.drawable.passward_true);

                    } else if (PASS_WARD[0] != null) {


                        if (PASS_WARD[1] == null) {

                            PASS_WARD[1] = "" + 0;
                            passClip[1].setImageResource(R.drawable.passward_true);

                        } else if (PASS_WARD[1] != null) {

                            if (PASS_WARD[2] == null) {

                                PASS_WARD[2] = "" + 0;
                                passClip[2].setImageResource(R.drawable.passward_true);

                            } else if (PASS_WARD[2] != null) {

                                if (PASS_WARD[3] == null) {

                                    PASS_WARD[3] = "" + 0;
                                    passClip[3].setImageResource(R.drawable.passward_true);

                                    String passward = PASS_WARD[0] + PASS_WARD[1] + PASS_WARD[2] + PASS_WARD[3];
                                    Log.e("passward", ":" + passward);

                                    if (Integer.parseInt(passward) == SystemManager.getInstance().getPassward()) {
                                        //비밀번호가 맞으면

                                        SharedPreferences preferences = getSharedPreferences("system", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putBoolean("current", true);
                                        editor.commit();

                                        finish();
                                        overridePendingTransition(R.anim.slide, R.anim.slide2);

                                    }else{
                                        //비밀번호가 틀리면

                                        PASS_WARD[0] = null;
                                        PASS_WARD[1] = null;
                                        PASS_WARD[2] = null;
                                        PASS_WARD[3] = null;


                                        text.setText(R.string.alert);

                                        for(int j = 0 ; j < 4 ; j++){

                                            passClip[j].setImageResource(R.drawable.passward_false);

                                        }

                                    }

                                }


                            }


                        }


                    }


                }
            });

        pass_button[1].setColorFilter(manager.getThemeColor());

        pass_button[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PASS_WARD[0] == null) {

                    PASS_WARD[0] = "" + 1;
                    passClip[0].setImageResource(R.drawable.passward_true);

                } else if (PASS_WARD[0] != null) {


                    if (PASS_WARD[1] == null) {

                        PASS_WARD[1] = "" + 1;
                        passClip[1].setImageResource(R.drawable.passward_true);

                    } else if (PASS_WARD[1] != null) {

                        if (PASS_WARD[2] == null) {

                            PASS_WARD[2] = "" + 1;
                            passClip[2].setImageResource(R.drawable.passward_true);

                        } else if (PASS_WARD[2] != null) {

                            if (PASS_WARD[3] == null) {

                                PASS_WARD[3] = "" + 1;
                                passClip[3].setImageResource(R.drawable.passward_true);

                                String passward = PASS_WARD[0] + PASS_WARD[1] + PASS_WARD[2] + PASS_WARD[3];
                                Log.e("passward", ":" + passward);

                                if (Integer.parseInt(passward) == SystemManager.getInstance().getPassward()) {
                                    //비밀번호가 맞으면

                                    SharedPreferences preferences = getSharedPreferences("system", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("current", true);
                                    editor.commit();


                                    finish();
                                    overridePendingTransition(R.anim.slide, R.anim.slide2);

                                } else {
                                    //비밀번호가 틀리면

                                    PASS_WARD[0] = null;
                                    PASS_WARD[1] = null;
                                    PASS_WARD[2] = null;
                                    PASS_WARD[3] = null;


                                    text.setText(R.string.alert);

                                    for (int j = 0; j < 4; j++) {

                                        passClip[j].setImageResource(R.drawable.passward_false);

                                    }

                                }

                            }


                        }


                    }


                }


            }
        });

        pass_button[2].setColorFilter(manager.getThemeColor());

        pass_button[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PASS_WARD[0] == null) {

                    PASS_WARD[0] = "" + 2;
                    passClip[0].setImageResource(R.drawable.passward_true);

                } else if (PASS_WARD[0] != null) {


                    if (PASS_WARD[1] == null) {

                        PASS_WARD[1] = "" + 2;
                        passClip[1].setImageResource(R.drawable.passward_true);

                    } else if (PASS_WARD[1] != null) {

                        if (PASS_WARD[2] == null) {

                            PASS_WARD[2] = "" + 2;
                            passClip[2].setImageResource(R.drawable.passward_true);

                        } else if (PASS_WARD[2] != null) {

                            if (PASS_WARD[3] == null) {

                                PASS_WARD[3] = "" + 2;
                                passClip[3].setImageResource(R.drawable.passward_true);

                                String passward = PASS_WARD[0] + PASS_WARD[1] + PASS_WARD[2] + PASS_WARD[3];
                                Log.e("passward", ":" + passward);

                                if (Integer.parseInt(passward) == SystemManager.getInstance().getPassward()) {
                                    //비밀번호가 맞으면

                                    SharedPreferences preferences = getSharedPreferences("system", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("current", true);
                                    editor.commit();

                                    finish();
                                    overridePendingTransition(R.anim.slide, R.anim.slide2);
                                }else{
                                    //비밀번호가 틀리면

                                    PASS_WARD[0] = null;
                                    PASS_WARD[1] = null;
                                    PASS_WARD[2] = null;
                                    PASS_WARD[3] = null;


                                    text.setText(R.string.alert);

                                    for(int j = 0 ; j < 4 ; j++){

                                        passClip[j].setImageResource(R.drawable.passward_false);

                                    }

                                }

                            }


                        }


                    }


                }


            }
        });



        pass_button[3].setColorFilter(manager.getThemeColor());

        pass_button[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PASS_WARD[0] == null) {

                    PASS_WARD[0] = "" + 3;
                    passClip[0].setImageResource(R.drawable.passward_true);

                } else if (PASS_WARD[0] != null) {


                    if (PASS_WARD[1] == null) {

                        PASS_WARD[1] = "" + 3;
                        passClip[1].setImageResource(R.drawable.passward_true);

                    } else if (PASS_WARD[1] != null) {

                        if (PASS_WARD[2] == null) {

                            PASS_WARD[2] = "" + 3;
                            passClip[2].setImageResource(R.drawable.passward_true);

                        } else if (PASS_WARD[2] != null) {

                            if (PASS_WARD[3] == null) {

                                PASS_WARD[3] = "" + 3;
                                passClip[3].setImageResource(R.drawable.passward_true);

                                String passward = PASS_WARD[0] + PASS_WARD[1] + PASS_WARD[2] + PASS_WARD[3];
                                Log.e("passward", ":" + passward);

                                if (Integer.parseInt(passward) == SystemManager.getInstance().getPassward()) {
                                    //비밀번호가 맞으면

                                    SharedPreferences preferences = getSharedPreferences("system", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("current", true);
                                    editor.commit();

                                    finish();
                                    overridePendingTransition(R.anim.slide, R.anim.slide2);
                                }else{
                                    //비밀번호가 틀리면

                                    PASS_WARD[0] = null;
                                    PASS_WARD[1] = null;
                                    PASS_WARD[2] = null;
                                    PASS_WARD[3] = null;


                                    text.setText(R.string.alert);

                                    for(int j = 0 ; j < 4 ; j++){

                                        passClip[j].setImageResource(R.drawable.passward_false);

                                    }

                                }

                            }


                        }


                    }


                }


            }
        });


        pass_button[4].setColorFilter(manager.getThemeColor());

        pass_button[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PASS_WARD[0] == null) {

                    PASS_WARD[0] = "" + 4;
                    passClip[0].setImageResource(R.drawable.passward_true);

                } else if (PASS_WARD[0] != null) {


                    if (PASS_WARD[1] == null) {

                        PASS_WARD[1] = "" + 4;
                        passClip[1].setImageResource(R.drawable.passward_true);

                    } else if (PASS_WARD[1] != null) {

                        if (PASS_WARD[2] == null) {

                            PASS_WARD[2] = "" + 4;
                            passClip[2].setImageResource(R.drawable.passward_true);

                        } else if (PASS_WARD[2] != null) {

                            if (PASS_WARD[3] == null) {

                                PASS_WARD[3] = "" + 4;
                                passClip[3].setImageResource(R.drawable.passward_true);

                                String passward = PASS_WARD[0] + PASS_WARD[1] + PASS_WARD[2] + PASS_WARD[3];
                                Log.e("passward", ":" + passward);

                                if (Integer.parseInt(passward) == SystemManager.getInstance().getPassward()) {
                                    //비밀번호가 맞으면

                                    SharedPreferences preferences = getSharedPreferences("system", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("current", true);
                                    editor.commit();

                                    finish();
                                    overridePendingTransition(R.anim.slide, R.anim.slide2);

                                }else{
                                    //비밀번호가 틀리면

                                    PASS_WARD[0] = null;
                                    PASS_WARD[1] = null;
                                    PASS_WARD[2] = null;
                                    PASS_WARD[3] = null;


                                    text.setText(R.string.alert);

                                    for(int j = 0 ; j < 4 ; j++){

                                        passClip[j].setImageResource(R.drawable.passward_false);

                                    }

                                }

                            }


                        }


                    }


                }


            }
        });

        pass_button[5].setColorFilter(manager.getThemeColor());

        pass_button[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PASS_WARD[0] == null) {

                    PASS_WARD[0] = "" + 5;
                    passClip[0].setImageResource(R.drawable.passward_true);

                } else if (PASS_WARD[0] != null) {


                    if (PASS_WARD[1] == null) {

                        PASS_WARD[1] = "" + 5;
                        passClip[1].setImageResource(R.drawable.passward_true);

                    } else if (PASS_WARD[1] != null) {

                        if (PASS_WARD[2] == null) {

                            PASS_WARD[2] = "" + 5;
                            passClip[2].setImageResource(R.drawable.passward_true);

                        } else if (PASS_WARD[2] != null) {

                            if (PASS_WARD[3] == null) {

                                PASS_WARD[3] = "" + 5;
                                passClip[3].setImageResource(R.drawable.passward_true);

                                String passward = PASS_WARD[0] + PASS_WARD[1] + PASS_WARD[2] + PASS_WARD[3];
                                Log.e("passward", ":" + passward);

                                if (Integer.parseInt(passward) == SystemManager.getInstance().getPassward()) {
                                    //비밀번호가 맞으면

                                    SharedPreferences preferences = getSharedPreferences("system", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("current", true);
                                    editor.commit();

                                    finish();
                                    overridePendingTransition(R.anim.slide, R.anim.slide2);
                                }else{
                                    //비밀번호가 틀리면

                                    PASS_WARD[0] = null;
                                    PASS_WARD[1] = null;
                                    PASS_WARD[2] = null;
                                    PASS_WARD[3] = null;


                                    text.setText(R.string.alert);

                                    for(int j = 0 ; j < 4 ; j++){

                                        passClip[j].setImageResource(R.drawable.passward_false);

                                    }

                                }

                            }


                        }


                    }


                }


            }
        });

        pass_button[6].setColorFilter(manager.getThemeColor());

        pass_button[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PASS_WARD[0] == null) {

                    PASS_WARD[0] = "" + 6;
                    passClip[0].setImageResource(R.drawable.passward_true);

                } else if (PASS_WARD[0] != null) {


                    if (PASS_WARD[1] == null) {

                        PASS_WARD[1] = "" + 6;
                        passClip[1].setImageResource(R.drawable.passward_true);

                    } else if (PASS_WARD[1] != null) {

                        if (PASS_WARD[2] == null) {

                            PASS_WARD[2] = "" + 6;
                            passClip[2].setImageResource(R.drawable.passward_true);

                        } else if (PASS_WARD[2] != null) {

                            if (PASS_WARD[3] == null) {

                                PASS_WARD[3] = "" + 6;
                                passClip[3].setImageResource(R.drawable.passward_true);

                                String passward = PASS_WARD[0] + PASS_WARD[1] + PASS_WARD[2] + PASS_WARD[3];
                                Log.e("passward", ":" + passward);

                                if (Integer.parseInt(passward) == SystemManager.getInstance().getPassward()) {
                                    //비밀번호가 맞으면

                                    SharedPreferences preferences = getSharedPreferences("system", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("current", true);
                                    editor.commit();

                                    finish();
                                    overridePendingTransition(R.anim.slide, R.anim.slide2);

                                }else{
                                    //비밀번호가 틀리면

                                    PASS_WARD[0] = null;
                                    PASS_WARD[1] = null;
                                    PASS_WARD[2] = null;
                                    PASS_WARD[3] = null;


                                    text.setText(R.string.alert);

                                    for(int j = 0 ; j < 4 ; j++){

                                        passClip[j].setImageResource(R.drawable.passward_false);

                                    }

                                }

                            }


                        }


                    }


                }


            }
        });

        pass_button[7].setColorFilter(manager.getThemeColor());

        pass_button[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PASS_WARD[0] == null) {

                    PASS_WARD[0] = "" + 7;
                    passClip[0].setImageResource(R.drawable.passward_true);

                } else if (PASS_WARD[0] != null) {


                    if (PASS_WARD[1] == null) {

                        PASS_WARD[1] = "" + 7;
                        passClip[1].setImageResource(R.drawable.passward_true);

                    } else if (PASS_WARD[1] != null) {

                        if (PASS_WARD[2] == null) {

                            PASS_WARD[2] = "" + 7;
                            passClip[2].setImageResource(R.drawable.passward_true);

                        } else if (PASS_WARD[2] != null) {

                            if (PASS_WARD[3] == null) {

                                PASS_WARD[3] = "" + 7;
                                passClip[3].setImageResource(R.drawable.passward_true);

                                String passward = PASS_WARD[0] + PASS_WARD[1] + PASS_WARD[2] + PASS_WARD[3];
                                Log.e("passward", ":" + passward);

                                if (Integer.parseInt(passward) == SystemManager.getInstance().getPassward()) {
                                    //비밀번호가 맞으면

                                    SharedPreferences preferences = getSharedPreferences("system", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("current", true);
                                    editor.commit();

                                    finish();
                                    overridePendingTransition(R.anim.slide, R.anim.slide2);
                                }else{
                                    //비밀번호가 틀리면

                                    PASS_WARD[0] = null;
                                    PASS_WARD[1] = null;
                                    PASS_WARD[2] = null;
                                    PASS_WARD[3] = null;


                                    text.setText(R.string.alert);

                                    for(int j = 0 ; j < 4 ; j++){

                                        passClip[j].setImageResource(R.drawable.passward_false);

                                    }

                                }

                            }


                        }


                    }


                }


            }
        });

        pass_button[8].setColorFilter(manager.getThemeColor());

        pass_button[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PASS_WARD[0] == null) {

                    PASS_WARD[0] = "" + 8;
                    passClip[0].setImageResource(R.drawable.passward_true);

                } else if (PASS_WARD[0] != null) {


                    if (PASS_WARD[1] == null) {

                        PASS_WARD[1] = "" + 8;
                        passClip[1].setImageResource(R.drawable.passward_true);

                    } else if (PASS_WARD[1] != null) {

                        if (PASS_WARD[2] == null) {

                            PASS_WARD[2] = "" + 8;
                            passClip[2].setImageResource(R.drawable.passward_true);

                        } else if (PASS_WARD[2] != null) {

                            if (PASS_WARD[3] == null) {

                                PASS_WARD[3] = "" + 8;
                                passClip[3].setImageResource(R.drawable.passward_true);

                                String passward = PASS_WARD[0] + PASS_WARD[1] + PASS_WARD[2] + PASS_WARD[3];
                                Log.e("passward", ":" + passward);

                                if (Integer.parseInt(passward) == SystemManager.getInstance().getPassward()) {
                                    //비밀번호가 맞으면

                                    SharedPreferences preferences = getSharedPreferences("system", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("current", true);
                                    editor.commit();

                                    finish();
                                    overridePendingTransition(R.anim.slide, R.anim.slide2);

                                }else{
                                    //비밀번호가 틀리면

                                    PASS_WARD[0] = null;
                                    PASS_WARD[1] = null;
                                    PASS_WARD[2] = null;
                                    PASS_WARD[3] = null;


                                    text.setText(R.string.alert);

                                    for(int j = 0 ; j < 4 ; j++){

                                        passClip[j].setImageResource(R.drawable.passward_false);

                                    }

                                }

                            }


                        }


                    }


                }


            }
        });

        pass_button[9].setColorFilter(manager.getThemeColor());

        pass_button[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PASS_WARD[0] == null) {

                    PASS_WARD[0] = "" + 9;
                    passClip[0].setImageResource(R.drawable.passward_true);

                } else if (PASS_WARD[0] != null) {


                    if (PASS_WARD[1] == null) {

                        PASS_WARD[1] = "" + 9;
                        passClip[1].setImageResource(R.drawable.passward_true);

                    } else if (PASS_WARD[1] != null) {

                        if (PASS_WARD[2] == null) {

                            PASS_WARD[2] = "" + 9;
                            passClip[2].setImageResource(R.drawable.passward_true);

                        } else if (PASS_WARD[2] != null) {

                            if (PASS_WARD[3] == null) {

                                PASS_WARD[3] = "" + 9;
                                passClip[3].setImageResource(R.drawable.passward_true);

                                String passward = PASS_WARD[0] + PASS_WARD[1] + PASS_WARD[2] + PASS_WARD[3];
                                Log.e("passward", ":" + passward);

                                if (Integer.parseInt(passward) == SystemManager.getInstance().getPassward()) {
                                    //비밀번호가 맞으면

                                    SharedPreferences preferences = getSharedPreferences("system", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("current", true);
                                    editor.commit();

                                    Intent intent = new Intent(getApplicationContext(),MainPage.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide, R.anim.slide2);
                                    finish();

                                }else{
                                    //비밀번호가 틀리면

                                    PASS_WARD[0] = null;
                                    PASS_WARD[1] = null;
                                    PASS_WARD[2] = null;
                                    PASS_WARD[3] = null;


                                    text.setText(R.string.alert);

                                    for(int j = 0 ; j < 4 ; j++){

                                        passClip[j].setImageResource(R.drawable.passward_false);

                                    }

                                }

                            }


                        }


                    }


                }


            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        pass_button[10].setColorFilter(manager.getThemeColor());
        pass_button[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(PASS_WARD[2] != null){

                    PASS_WARD[2] = null;
                    passClip[2].setImageResource(R.drawable.passward_false);

                }else if(PASS_WARD[2] == null){

                    if(PASS_WARD[1] != null){

                        PASS_WARD[1] = null;
                        passClip[1].setImageResource(R.drawable.passward_false);

                    }else if(PASS_WARD[1] == null){

                        if(PASS_WARD[0] != null){

                            PASS_WARD[0] = null;
                            passClip[0].setImageResource(R.drawable.passward_false);

                        }

                    }

                }


            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


    @Override
    protected void onPause() {
        super.onPause();

    }
}
