package com.rsamadhan;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public class LanguageSelectionActivity extends AppCompatActivity{

    private NumberPicker mLanguageSelector;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.language_selection);

    }
}