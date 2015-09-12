package com.rsamadhan;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.rsamadhan.common.ApplicationUtils;

import java.util.Locale;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public class LanguageSelectionActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener, View.OnClickListener {

    private NumberPicker mLanguageSelector;
    private Button mSelecLanguage;
    private PreferenceManager mPrefs;
    private int mLanguageIndex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_selection);
        mPrefs=PreferenceManager.getInstance(this);
        if(!mPrefs.getLoginFirstTime()){
            startMainActivity();
        }
        mSelecLanguage= (Button) findViewById(R.id.btn_select_lan);
        mLanguageSelector= (NumberPicker) findViewById(R.id.numberPicker);
        mLanguageSelector.setMaxValue(1);
        mLanguageSelector.setMinValue(0);
        mLanguageSelector.setDisplayedValues(getResources().getStringArray(R.array.languages));
        mLanguageSelector.setOnValueChangedListener(this);
        mSelecLanguage.setOnClickListener(this);

    }

    private void startMainActivity() {
        setStoredLocale();
        final Context context=this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mPrefs.getLoginId().equals("-1")){
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 10);
    }

    private void setStoredLocale() {
        Locale selectedLocale= ApplicationUtils.getSelectedLocale(mPrefs);
        Locale.setDefault(selectedLocale);
        Configuration configuration=new Configuration();
        configuration.locale=selectedLocale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        mLanguageIndex=newVal;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.btn_select_lan:

                if(mLanguageIndex==ApplicationConstants.ENGLISH_IND){
                    mPrefs.putSelectedLanguage(ApplicationConstants.ENGLISH_LOCALE);
                }else{
                    mPrefs.putSelectedLanguage(ApplicationConstants.HINDI_LOCALE);
                }
                mPrefs.putFirstTimeLogin(false);
                startMainActivity();
                break;
        }
    }



}