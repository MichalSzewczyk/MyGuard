package com.guard.myguard.utils;

import android.app.Activity;
import android.content.Intent;

public class Utils {
    public static void login(Activity activity, Class<? extends Activity> activityClass){
        Intent intent = new Intent(activity, activityClass);
        activity.finish();
        activity.startActivity(intent);
    }
}
