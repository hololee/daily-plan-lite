package com.kiristudio.lee.jong.hyuck;

import android.graphics.Color;

/**
 * Created by jongH on 2015-08-30.
 */
public class SystemManager {

    private static SystemManager mManager;

    public static int COLOR_SKY = Color.rgb(111, 176, 209);
    public static int COLOR_PURPLE = Color.rgb(187, 107, 168);
    public static int COLOR_YELLOW = Color.rgb(232, 232, 83);
    public static int COLOR_ORANGE = Color.rgb(234, 101, 55);
    public static int COLOR_RED = Color.rgb(232, 61, 58);
    public static int COLOR_GREEN = Color.rgb(111, 187, 76);
    public static int COLOR_MINT = Color.rgb(108, 192, 155);
    public static int COLOR_BLUE = Color.rgb(64, 64, 170);
    public static int COLOR_GRAY = Color.rgb(147, 147, 147);
    public static int COLOR_BLACK = Color.rgb(0, 0, 0);

    public static int COLOR_SKY_ALPHA = Color.argb(55, 111, 176, 209);
    public static int COLOR_PURPLE_ALPHA = Color.argb(55, 187, 107, 168);
    public static int COLOR_YELLOW_ALPHA = Color.argb(55, 232, 232, 83);
    public static int COLOR_ORANGE_ALPHA = Color.argb(55,234, 101, 55);
    public static int COLOR_RED_ALPHA = Color.argb(55,232, 61, 58);
    public static int COLOR_GREEN_ALPHA = Color.argb(55,111, 187, 76);
    public static int COLOR_MINT_ALPHA = Color.argb(55,108, 192, 155);
    public static int COLOR_BLUE_ALPHA = Color.argb(55,64, 64, 170);
    public static int COLOR_GRAY_ALPHA = Color.argb(55,147, 147, 147);
    public static int COLOR_BLACK_ALPHA = Color.argb(55,0, 0, 0);

    private float mFontSize;
    private int mThemeColor;
    private int mThemeColorAlpha;
    private int mPassward;
    private boolean ispayed;
    private boolean isprotected;
    private boolean isStatelocked;
    private boolean current;

    private SystemManager() {

    }

    public static SystemManager getInstance(){


         if(mManager == null) {
             mManager = new SystemManager();
         }

        return mManager;
    }


    public void setFontSize(float mFontSize) {
        this.mFontSize = mFontSize;
    }

    public void setThemeColor(int mThemeColor) {
        this.mThemeColor = mThemeColor;
    }

    public void setPassward(int mPassward) {
        this.mPassward = mPassward;
    }

    public void setPayed(boolean payed) {
        this.ispayed = payed;
    }

    public void setProtected(boolean isprotected) {
        this.isprotected = isprotected;
    }

    public void setStatelocked(boolean statelocked) {
        this.isStatelocked = statelocked;
    }

    public float getFontSize() {
        return mFontSize;
    }

    public int getThemeColor() {
        return mThemeColor;
    }

    public int getPassward() {
        return mPassward;
    }

    public boolean getIsPayed() {
        return ispayed;
    }

    public boolean getIsProtected() {
        return isprotected;
    }

    public boolean isStatelocked() {
        return isStatelocked;
    }

    public int getmThemeColorAlpha() {
        return mThemeColorAlpha;
    }

    public void setmThemeColorAlpha(int mThemeColorAlpha) {
        this.mThemeColorAlpha = mThemeColorAlpha;
    }


    public boolean getCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}



