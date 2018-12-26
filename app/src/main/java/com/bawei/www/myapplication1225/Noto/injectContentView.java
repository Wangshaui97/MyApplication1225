package com.bawei.www.myapplication1225.Noto;

import android.app.Activity;

public  class injectContentView {

    public static void injectContentView(Activity activity){
        Class a= activity.getClass();
        if(a.isAnnotationPresent(ContentView.class)){
            ContentView contentView = (ContentView) a.getAnnotation(ContentView.class);

        }
    }
}
