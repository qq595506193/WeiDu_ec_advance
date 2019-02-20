package com.changhao.weidu_project.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        fresco();
    }

    private void fresco() {
        Fresco.initialize(this);
    }
}
