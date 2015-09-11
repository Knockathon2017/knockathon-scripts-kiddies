package com.rsamadhan;

import android.app.Application;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public class MainApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


        Locale locale[]=Locale.getAvailableLocales();
        Locale hindiLocale=getHindiLocale(locale);
        Locale.setDefault(hindiLocale);

        Configuration configuration=new Configuration();
        configuration.locale=hindiLocale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
    }

    private Locale getHindiLocale(Locale[] locale) {
        Locale defaultLocale=Locale.getDefault();
        for(int i=0;i<locale.length;i++){
            if(locale[i].getLanguage().equals("hi")){
                return locale[i];
            }
        }
        return defaultLocale;
    }
}
