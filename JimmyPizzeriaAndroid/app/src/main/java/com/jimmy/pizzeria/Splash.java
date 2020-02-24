package com.jimmy.pizzeria;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.jimmy.pizzeria.helpers.ResourceHelper;
import com.jimmy.pizzeria.helpers.WindowHelper;
import com.jimmy.pizzeria.util.Constant;
import com.jimmy.pizzeria.util.Logger;
import com.jimmy.pizzeria.util.Utils;
import com.jimmy.pizzeria.util.Version;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Splash extends AppCompatActivity {
    private static final String TAG = Utils.getClassTag(Splash.class);

    private Context context;

    private FrameLayout flRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        int statusNavColor = ResourceHelper.getColor(R.color.colorPrimaryDark);
        WindowHelper.setWindowStatusNav(getWindow(), statusNavColor, statusNavColor);

        setContentView(R.layout.activity_splash);

        flRoot = findViewById(R.id.flRoot);
        WindowHelper.setInsets(flRoot);

        TextView version = findViewById(R.id.lblVersion);
        String versionText = "v" + BuildConfig.VERSION_NAME;
        version.setText(versionText);

        ImageView imgSplash = findViewById(R.id.imgSplash);

        AlphaAnimation fadein = new AlphaAnimation(0, 1);
        fadein.setStartOffset(333);
        fadein.setDuration(333);
        fadein.setInterpolator(new LinearInterpolator());
        imgSplash.startAnimation(fadein);

        requestPermissionsManually();
    }

    public void requestPermissionsManually() {
        try {
            ArrayList<String> plist = new ArrayList<>(Arrays.asList(
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            ));

            List<String> p = new ArrayList<>();
            for (String permission : plist) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    p.add(permission);
                }
            }

            if (p.size() > 0) {
                ActivityCompat.requestPermissions(this, p.toArray(new String[0]), Constant.REQUEST_SPLASH_PERMISSIONS);
            } else {
                loadNextScreen();
            }

        } catch (Exception ex) {
            Logger.LogError(TAG + ".requestPermissionsManually", ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Constant.REQUEST_SPLASH_PERMISSIONS) {
            boolean allGranted = true;
            boolean shouldAskAgain = true;

            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                }
                if (Version.isAndroidMarshmallow() && !shouldShowRequestPermissionRationale(permissions[i])) {
                    shouldAskAgain = false;
                }
            }

            if (allGranted) {
                loadNextScreen();
            } else {
                if (!shouldAskAgain) {
                    Utils.getAlert(context)
                            .setTitle("Permission Request Denied")
                            .setMessage("Please allow permissions for Jimmy Pizzeria to be able to use the app as intended.")
                            .setPositiveButton("Ok", (dialog, which) -> {
                                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                i.setData(Uri.fromParts("package", BuildConfig.APPLICATION_ID, null));
                                startActivity(i);
                                finish();
                            })
                            .show();
                } else {
                    requestPermissionsManually();
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void loadNextScreen() {
        flRoot.postDelayed(() -> {
            Intent i = new Intent(context, PizzaMenu.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }, 2000);
    }
}
