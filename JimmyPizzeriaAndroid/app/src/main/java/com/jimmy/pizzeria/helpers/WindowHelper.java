package com.jimmy.pizzeria.helpers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.jimmy.pizzeria.MainApplication;
import com.jimmy.pizzeria.util.Version;

public class WindowHelper {
    public static void setWindow(Window window) {
        if (Version.isAndroidKitkat()) {
            if (Version.isAndroidLollipop()) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public static void setWindowStatusNav(Window window, int statusbarColor, int navbarColor) {
        if (Version.isAndroidKitkatOrKitkatWatch()) {
            int flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            window.getAttributes().flags |= flags;
        }

        if (Version.isAndroidKitkat()) {
            int uiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            window.getDecorView().setSystemUiVisibility(uiVisibility);
        }

        if (Version.isAndroidLollipop()) {
            int flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            window.getAttributes().flags &= ~flags;

            window.setStatusBarColor(statusbarColor);
            window.setNavigationBarColor(navbarColor);
        }
    }

    public static void setWindowNoStatus(Window window) {
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Version.isAndroidLollipop()) {
            window.setNavigationBarColor(Color.BLACK);
        }
    }

    public static void setWindowStatusNavHidden(Window window) {
        if (Version.isAndroidKitkat()) {
            int uiVisibility = window.getDecorView().getSystemUiVisibility();
            uiVisibility |= View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

            window.getDecorView().setSystemUiVisibility(uiVisibility);
        }
    }

    public static void setInsets(final View rootView) {
        if (Version.isAndroidKitkatWatch()) {
            rootView.setOnApplyWindowInsetsListener((v, insets) -> {
                rootView.setPadding(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop(), insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
                return insets;
            });
        }
    }

    public static void keyboardHide(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm != null) {
            //Find the currently focused view, so we can grab the correct window token from it.
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            View view = activity.getCurrentFocus();
            if (view == null) {
                view = new View(activity);
            }

            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void keyboardHide(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static void keyboardShow(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            View view = activity.getCurrentFocus();
            if (view == null) {
                view = new View(activity);
            }

            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void keyboardShow(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            }
        }
    }

    public static int getScreenWidth() {
        return MainApplication.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return MainApplication.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenSmallestDimension() {
        int width = getScreenWidth();
        int height = getScreenHeight();
        return Math.min(width, height);
    }

    public static int getScreenRotationDegrees(WindowManager windowManager) {
        if (windowManager == null) return 0;

        Display display = windowManager.getDefaultDisplay();
        if (display == null) return 0;

        switch (display.getRotation()) {
            default:
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
        }
    }
}