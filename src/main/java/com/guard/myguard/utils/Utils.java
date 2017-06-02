package com.guard.myguard.utils;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class Utils {
    public static void login(Activity activity, Class<? extends Activity> activityClass){
        Intent intent = new Intent(activity, activityClass);
        activity.finish();
        activity.startActivity(intent);
    }

    public static void showToast(Activity activity, String message){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}
