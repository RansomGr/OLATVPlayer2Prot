package com.mohamed.olatvplayer.MainApplication;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    public static App instance = null ;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        App.instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


}
