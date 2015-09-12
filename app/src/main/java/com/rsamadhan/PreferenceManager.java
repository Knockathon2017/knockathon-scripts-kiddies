package com.rsamadhan;

import android.content.Context;
import android.content.SharedPreferences;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public class PreferenceManager {
    private static PreferenceManager sPref;
    public static PreferenceManager getInstance(Context context) {
        if (sPref == null) {
            sPref = new PreferenceManager(context);
        }
        return sPref;
    }

    private final String PREF_LANGUAGE_SELECTED     = "PREF_LANGUAGE_SELECTED";
    private final String PREF_FIRST_TIME_LOGIN      = "PREF_FIRST_TIME_LOGIN";

    private final String PREF_LOGIN_ID              = "PREF_LOGIN_ID";

    private SharedPreferences mPref;

    private PreferenceManager(Context context) {
        mPref = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }
    public void putFirstTimeLogin(boolean firstLogin){
        mPref.edit().putBoolean(PREF_FIRST_TIME_LOGIN,firstLogin).commit();
    }
    public boolean getLoginFirstTime(){
        return mPref.getBoolean(PREF_FIRST_TIME_LOGIN,true);
    }
    public void putSelectedLanguage(String language){
        mPref.edit().putString(PREF_LANGUAGE_SELECTED, language).commit();
    }
    public String getSelectedLanguage(){
        return mPref.getString(PREF_LANGUAGE_SELECTED, ApplicationConstants.ENGLISH_LOCALE);
    }


    public void putLoginID(String mobileNumber){
        mPref.edit().putString(PREF_LOGIN_ID,mobileNumber).commit();
    }
    public String getLoginId(){
        return mPref.getString(PREF_LOGIN_ID, "-1");
    }



}
