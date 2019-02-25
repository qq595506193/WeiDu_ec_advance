package com.changhao.weidu_project.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        fresco();
        context=this;
    }

    private void fresco() {
        Fresco.initialize(this);
    }
}
