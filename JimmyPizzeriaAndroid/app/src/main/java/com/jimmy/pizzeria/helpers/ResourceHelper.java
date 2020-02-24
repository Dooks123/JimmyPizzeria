package com.jimmy.pizzeria.helpers;

import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

import com.jimmy.pizzeria.MainApplication;
import com.jimmy.pizzeria.util.Utils;

public class ResourceHelper {

    private static final String TAG = Utils.getClassTag(ResourceHelper.class);

    public static int getColor(@ColorRes int resourceID) {
        return ContextCompat.getColor(MainApplication.getContext(), resourceID);
    }

    public static int getDimension(@DimenRes int resourceID) {
        return (int) MainApplication.getContext().getResources().getDimension(resourceID);
    }

    public static Drawable getDrawable(@DrawableRes int resourceID) {
        return ContextCompat.getDrawable(MainApplication.getContext(), resourceID);
    }

    public static int getDP(float value) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, MainApplication.getContext().getResources().getDisplayMetrics()));
    }
}
