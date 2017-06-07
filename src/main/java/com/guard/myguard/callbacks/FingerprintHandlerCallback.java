package com.guard.myguard.callbacks;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.Manifest;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.guard.myguard.services.impl.StoredLoginHandler;
import com.guard.myguard.services.interfaces.LoginHandler;
import com.guard.myguard.services.interfaces.ParsingService;
import com.guard.myguard.tasks.UserLoginTask;
import com.guard.myguard.utils.Tuple;

public class FingerprintHandlerCallback extends FingerprintManager.AuthenticationCallback {

    private CancellationSignal cancellationSignal;
    private Context context;
    private LoginHandler loginHandler;
    private ParsingService parsingService;
    private Activity activity;

    public FingerprintHandlerCallback(Context mContext, ParsingService parsingService, Activity activity) {
        this.context = mContext;
        this.loginHandler = new StoredLoginHandler(mContext);
        this.parsingService = parsingService;
        this.activity = activity;
    }

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {

        cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {

        Toast.makeText(context, "Authentication error\n" + errString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(context, "Authentication failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Toast.makeText(context, "Authentication help\n" + helpString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationSucceeded(
            FingerprintManager.AuthenticationResult result) {
        Toast.makeText(context, "Success!", Toast.LENGTH_LONG).show();
        PackageManager pm = activity.getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(context, activity.getClass()),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        Tuple<String, String> crud = loginHandler.getStoredCredentials();
        if(crud == null){
            Toast.makeText(context, "Credential data unavailable. \nPlease log in for the first time.", Toast.LENGTH_LONG).show();
            return;
        }
        UserLoginTask loginTask = new UserLoginTask(crud.getKey(), crud.getValue(), parsingService, activity);
        loginTask.execute();
    }

}