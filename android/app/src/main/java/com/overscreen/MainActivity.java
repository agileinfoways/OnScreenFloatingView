package com.overscreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "overScreen";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//          navToPerm();
          runsService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent stopServiceIntent = new Intent(MainActivity.this, FloatingService.class);
        stopService(stopServiceIntent);
    }

    void runsService() {
        Intent i = new Intent(MainActivity.this, FloatingService.class);
        startService(i);
    }

    void navToPerm() {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            intent.setAction(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        }
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {

            //Check if the permission is granted or not.
            if (resultCode == RESULT_OK) {
                startService(new Intent(MainActivity.this, FloatingService.class));
                finish();
            } else { //Permission is not available
                Toast.makeText(this,
                        "Draw over other app permission not available. Closing the application",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
