package com.guard.myguard;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.guard.myguard.database.DBOpenHelper;
import com.guard.myguard.services.impl.StoredLoginHandler;
import com.guard.myguard.services.interfaces.LoginHandler;

import static com.guard.myguard.utils.Utils.invokeStartActivity;

public class WelcomeActivity extends Activity {
    private Button registerButton;
    private Button loginButton;
    private Button fingerprintButton;
    private DBOpenHelper openHelper;
    private LoginHandler handler;

    private void initComponents() {
        this.registerButton = (Button) findViewById(R.id.register_button);
        this.loginButton = (Button) findViewById(R.id.login_button);
        this.fingerprintButton = (Button) findViewById(R.id.fingerprint_button);
        this.handler = new StoredLoginHandler(this);
    }

    private void setListeners() {
        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invokeStartActivity(WelcomeActivity.this, LoginActivity.class);
            }
        });
        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invokeStartActivity(WelcomeActivity.this, RegisterActivity.class);
            }
        });
        this.fingerprintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invokeStartActivity(WelcomeActivity.this, FingerprintActivity.class);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.openHelper = new DBOpenHelper(this);
        setContentView(R.layout.activity_welcome);

        initComponents();
        setListeners();

        if (!handler.isDataAvailable()) {
            fingerprintButton.setEnabled(false);

        } else {
            Log.i("data", handler.getStoredCredentials().toString());
        }
    }
}
