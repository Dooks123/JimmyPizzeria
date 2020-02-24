package com.jimmy.pizzeria.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jimmy.pizzeria.BuildConfig;
import com.jimmy.pizzeria.helpers.TextHelper;
import com.jimmy.pizzeria.util.Logger;
import com.jimmy.pizzeria.util.Utils;

import java.io.File;

public class PizzaContentProvider extends ContentProvider {
    private static final String TAG = Utils.getClassTag(PizzaContentProvider.class);

    public static final String ContentProviderAuthority = BuildConfig.APPLICATION_ID + ".PizzaContentProvider";

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        try {
            if (projection == null) {
                projection = new String[]{OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE, "_data"};
            }
            try (MatrixCursor cursor = new MatrixCursor(projection, 1)) {
                MatrixCursor.RowBuilder builder = cursor.newRow();

                String[] segments = uri.getPathSegments().toArray(new String[0]);
                String localPath = Environment.getExternalStorageDirectory() + File.separator + TextHelper.join(File.separator, segments, 1, segments.length - 1);
                File file = new File(localPath);

                for (String col : projection) {
                    switch (col) {
                        case OpenableColumns.DISPLAY_NAME:
                            builder.add(file.getName());
                            break;
                        case OpenableColumns.SIZE:
                            builder.add(file.length());
                            break;
                        case "_data":
                            builder.add(file.getAbsolutePath());
                            break;
                        default:
                            builder.add(null);
                            break;
                    }
                }

                cursor.setNotificationUri(getContext().getContentResolver(), uri);

                return cursor;
            }
        } catch (Exception ex) {
            Logger.LogError(TAG + ".query", "uri: " + uri.toString() + "\nmimetype: " + Utils.getMimeType(getContext(), uri) + "\nprojection: " + TextHelper.join(",", projection), ex);
            return null;
        }
    }

    @Nullable
    @Override
    public AssetFileDescriptor openAssetFile(@NonNull Uri uri, @NonNull String mode) {
        try {
            String[] segments = uri.getPathSegments().toArray(new String[0]);
            String localPath = Environment.getExternalStorageDirectory() + TextHelper.join(File.separator, segments, 1, segments.length - 1);
            Uri localFileUri = Uri.parse("file://" + localPath);

            ContentResolver cr = getContext().getContentResolver();

            return cr.openAssetFileDescriptor(localFileUri, mode);
        } catch (Exception ex) {
            Logger.LogError(TAG + ".openAssetFile", "uri: " + uri.toString() + "\nmimetype: " + Utils.getMimeType(getContext(), uri) + "\nmode: " + mode, ex);
            return null;
        }
    }

    @Override
    public String getType(Uri uri) {
        String[] segments = uri.getPathSegments().toArray(new String[0]);
        String localPath = Environment.getExternalStorageDirectory() + File.separator + TextHelper.join(File.separator, segments, 1, segments.length - 1);
        Uri localFileUri = Uri.parse(localPath);
        return Utils.getMimeType(getContext(), localFileUri);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        return -1;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return -1;
    }
}
