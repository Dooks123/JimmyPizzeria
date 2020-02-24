package com.jimmy.pizzeria.util;

import android.util.Log;

import com.jimmy.pizzeria.BuildConfig;
import com.jimmy.pizzeria.helpers.TextHelper;

public class Logger {

    private static final String TAG = Utils.getClassTag(Logger.class);

    public static void LogDebug(String tag, String text) {
        if (BuildConfig.DEBUG) {
            if (!TextHelper.isNullOrEmpty(tag) && !TextHelper.isNullOrEmpty(text)) {
                Log.d(tag, text);
            }
        }
    }

    public static void LogDebug(String tag, String text, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            if (!TextHelper.isNullOrEmpty(tag) && !TextHelper.isNullOrEmpty(text)) {
                Log.d(tag, text, throwable);
            }
        }
    }

    public static void LogInfo(String tag, String text) {
        if (BuildConfig.DEBUG) {
            if (!TextHelper.isNullOrEmpty(tag) && !TextHelper.isNullOrEmpty(text)) {
                Log.i(tag, text);
            }
        }
    }

    public static void LogInfo(String tag, String text, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            if (!TextHelper.isNullOrEmpty(tag) && !TextHelper.isNullOrEmpty(text)) {
                Log.i(tag, text, throwable);
            }
        }
    }

    public static void LogVerbose(String tag, String text) {
        if (BuildConfig.DEBUG) {
            if (!TextHelper.isNullOrEmpty(tag) && !TextHelper.isNullOrEmpty(text)) {
                Log.v(tag, text);
            }
        }
    }

    public static void LogVerbose(String tag, String text, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            if (!TextHelper.isNullOrEmpty(tag) && !TextHelper.isNullOrEmpty(text)) {
                Log.v(tag, text, throwable);
            }
        }
    }

    public static void LogWarning(String tag, String text) {
        if (BuildConfig.DEBUG) {
            if (!TextHelper.isNullOrEmpty(tag) && !TextHelper.isNullOrEmpty(text)) {
                Log.w(tag, text);
            }
        }
    }

    public static void LogWarning(String tag, String text, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            if (!TextHelper.isNullOrEmpty(tag) && !TextHelper.isNullOrEmpty(text)) {
                Log.w(tag, text, throwable);
            }
        }
    }

    public static void LogError(String tag, String text) {
        if (BuildConfig.DEBUG) {
            if (!TextHelper.isNullOrEmpty(tag) && !TextHelper.isNullOrEmpty(text)) {
                Log.e(tag, text);
            }
        }
    }

    public static void LogError(String tag, String text, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            if (!TextHelper.isNullOrEmpty(tag) && !TextHelper.isNullOrEmpty(text)) {
                Log.e(tag, text, throwable);
            }
        }
    }
}
