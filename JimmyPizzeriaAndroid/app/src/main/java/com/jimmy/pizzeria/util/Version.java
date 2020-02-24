package com.jimmy.pizzeria.util;

import android.os.Build;

public class Version {
    private static final String TAG = Utils.getClassTag(Version.class);

    /**
     * API 17 - JellyBean MR1
     *
     * @return True if build is equal to or greater than JellyBean MR1
     */
    public static boolean isAndroidJellyBeanMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    /**
     * API 19 - Kitkat
     *
     * @return True if build is equal to or greater than Kitkat
     */
    public static boolean isAndroidKitkat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    /**
     * API 20 Kitkat Watch
     *
     * @return True if build is equal to or greater than Kitkat Watch API 20
     */
    public static boolean isAndroidKitkatWatch() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH;
    }

    /**
     * API 19  Kitkat and API 20 Kitkat Watch
     *
     * @return True if build is equal to Kitkat API 19 or Kitkat Watch API 20
     */
    public static boolean isAndroidKitkatOrKitkatWatch() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT_WATCH;
    }

    /**
     * API 21 - Lollipop
     *
     * @return True if build is equal to or greater than Lollipop
     */
    public static boolean isAndroidLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * API 22 - Lollipop MR1
     *
     * @return True if build is equal to or greater than Lollipop MR1
     */
    public static boolean isAndroidLollipopMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    /**
     * API 23 - Marshmallow
     *
     * @return True if build is equal to or greater than Marshmallow
     */
    public static boolean isAndroidMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * API 26 - Oreo
     *
     * @return True if build is equal to or greater than Oreo
     */
    public static boolean isAndroidOreo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    /**
     * API 28 - Pie
     *
     * @return True if build is equal to or greater than Pie
     */
    public static boolean isAndroidPie() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }

    /**
     * API 29 - Q
     *
     * @return True if build is equal to or greater than Q
     */
    public static boolean isAndroidQ() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
    }
}
