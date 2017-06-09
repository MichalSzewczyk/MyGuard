package com.guard.myguard.utils;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.guard.myguard.R;

public class Utils {
    public static void invokeStartActivity(Activity activity, Class<? extends Activity> activityClass) {
        Intent intent = new Intent(activity, activityClass);
        activity.finish();
        activity.startActivity(intent);
    }

    public static void invokeStartActivity(Activity activity, Class<? extends Activity> activityClass, String data) {
        Intent intent = new Intent(activity, activityClass);
        intent.putExtra(String.valueOf(R.string.data), data);
        activity.finish();
        activity.startActivity(intent);
    }

    public static void showToast(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}
