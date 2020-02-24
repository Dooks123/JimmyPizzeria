package com.jimmy.pizzeria;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.jimmy.pizzeria.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = Utils.getClassTag(MainApplication.class);

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private List<String> runningActivities = new ArrayList<>();

    public static boolean appInForeground = false;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        registerActivityLifecycleCallbacks(this);

        log(TAG + ".onCreate");
    }

    @Override
    public void onTerminate() {
        unregisterActivityLifecycleCallbacks(this);

        log(TAG + ".onTerminate");

        super.onTerminate();
    }

    @Override
    public ComponentName startService(Intent service) {
        log(TAG + ".startService", "Action: " + service.getAction());

        return super.startService(service);
    }

    @Override
    public boolean stopService(Intent name) {
        log(TAG + ".stopService", "Action: " + name.getAction());

        return super.stopService(name);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        runningActivities.add(activity.getClass().getSimpleName());

        log(TAG + ".onActivityCreated");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        runningActivities.remove(activity.getClass().getSimpleName());

        log(TAG + ".onActivityDestroyed");
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        appInForeground = false;

        log(TAG + ".onActivityPaused");
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        appInForeground = true;

        log(TAG + ".onActivityResumed");
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        log(TAG + ".onActivityStarted");
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        log(TAG + ".onActivityStopped");
    }

    private void log(String Tag) {
        log(Tag, null);
    }

    private void log(String Tag, String Preinfo) {
        Log.d(Tag, (Preinfo != null ? Preinfo + "   " : "") + "   Foreground:" + appInForeground + "   " + getActivityInfo());
    }

    public static Context getContext() {
        return context;
    }

    private String getActivityInfo() {
        return "(" + runningActivities.size() + ") Activities: " + Arrays.toString(runningActivities.toArray(new String[0]));
    }
}
