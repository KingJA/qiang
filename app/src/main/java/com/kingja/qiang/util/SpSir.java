package com.kingja.qiang.util;

import android.content.SharedPreferences;

import com.kingja.qiang.base.App;

/**
 * Description：TODO
 * Create Time：2016/8/15 13:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SpSir {
    private static final String TOKEN = "token";
    private static final String NICKNAME = "nickName";
    private static final String HEADIMG = "headImg";
    private static final String USERID = "userId";
    private static final String MOBILE = "mobile";
    private static final String EMPTY_STRING = "";
    private static SpSir mSpSir;
    private SharedPreferences mSp;

    private SpSir() {
        mSp = App.getSp();
    }

    public static SpSir getInstance() {
        if (mSpSir == null) {
            synchronized (SpSir.class) {
                if (mSpSir == null) {
                    mSpSir = new SpSir();
                }
            }
        }
        return mSpSir;
    }

    /*================================GET================================*/

    public String getNickname() {
        return getString(NICKNAME);
    }

    public String getUserId() {
        return getString(USERID);
    }

    public String getToken() {
        return getString(TOKEN);
    }

    public String getMobile() {
        return getString(MOBILE);
    }

    public String getHeadImg() {
        return getString(HEADIMG);
    }

    /*================================PUT================================*/


    public void putNickName(String nickName) {
        putString(NICKNAME, nickName);
    }

    public void putToken(String token) {
        putString(TOKEN, token);
    }

    public void putMobile(String mobile) {
        putString(MOBILE, mobile);
    }

    public void putUserId(String userId) {
        putString(USERID, userId);
    }

    public void putHeadImg(String headImg) {
        putString(HEADIMG, headImg);
    }


    private void putString(String key, String value) {
        if (value != null) {
            mSp.edit().putString(key, value).apply();
        }
    }

    private String getString(String key, String defaultValue) {
        return mSp.getString(key, defaultValue);
    }

    private String getString(String key) {
        return mSp.getString(key, EMPTY_STRING);
    }

    public void clearData() {
        putNickName(EMPTY_STRING);
        putUserId(EMPTY_STRING);
        putHeadImg(EMPTY_STRING);
        putToken(EMPTY_STRING);
        putMobile(EMPTY_STRING);
    }
}
