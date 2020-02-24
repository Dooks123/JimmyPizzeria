package com.jimmy.pizzeria.util;

import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.jimmy.pizzeria.BuildConfig;
import com.jimmy.pizzeria.MainApplication;
import com.jimmy.pizzeria.R;
import com.jimmy.pizzeria.helpers.TextHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    private static final String TAG = getClassTag(Utils.class);

    private static Toast lastToast;

    public static boolean isOnline() {
        ConnectivityManager cmc = (ConnectivityManager) MainApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cmc != null) {
            NetworkInfo netinfo = cmc.getActiveNetworkInfo();
            return netinfo != null && netinfo.isConnected();
        }
        return false;
    }

    public static String getClassTag(Class classy) {
        return BuildConfig.APPLICATION_ID + "." + classy.getName();
    }

    public static void showToast(Context context, String text, boolean isShort) {
        showToast(context, text, isShort, true);
    }

    public static void showToast(final Context context, final String text, final boolean isShort, final boolean clearPrevious) {
        Handler mainHandler = new Handler(Looper.getMainLooper());
        mainHandler.post(() -> {
            int duration = isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;
            if (clearPrevious) {
                if (lastToast != null) {
                    lastToast.cancel();
                }
                lastToast = Toast.makeText(context, text, duration);
                lastToast.setGravity(Gravity.CENTER, 0, 0);
                lastToast.show();
            } else {
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    public static AlertDialog.Builder getAlert(Context context) {
        return new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AlertDialogCustom));
    }

    public static String getMimeType(Context context, Uri uri) {
        String mimeType = null;
        try {
            if (uri != null) {
                if (uri.getScheme() != null && uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
                    mimeType = context.getContentResolver().getType(uri);
                } else {
                    String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
                    mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.toLowerCase());
                }
            }
        } catch (Exception ex) {
            Logger.LogError(TAG + ".getMimeType", ex.getMessage(), ex);
        }
        return mimeType;
    }

    public static boolean fileExists(String filePath) {
        if (TextHelper.isNullOrEmpty(filePath)) return false;
        try {
            File file = new File(filePath);
            return file.exists();
        } catch (Exception ex) {
            Logger.LogError(TAG + ".fileExists", ex.getMessage() + " " + filePath);
            ex.printStackTrace();
            return false;
        }
    }

    public static String getPublicFolder() {
        String folderPath = Environment.getExternalStorageDirectory() + File.separator + BuildConfig.APPLICATION_ID + File.separator;
        File folderPathFile = new File(folderPath);
        if (!folderPathFile.exists()) {
            return folderPathFile.mkdirs() ? folderPath : null;
        }
        return folderPath;
    }

    public static String getNewOrderFileName() {
        return "order_" + new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault()).format(new Date()) + ".encrypted";
    }
}