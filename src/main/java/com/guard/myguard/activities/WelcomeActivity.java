package com.guard.myguard.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.guard.myguard.R;
import com.guard.myguard.database.DBOpenHelper;

import static com.guard.myguard.utils.Utils.login;

public class WelcomeActivity extends Activity {
    private Button registerButton;
    private Button loginButton;
    private Button fingerprintButton;
    private DBOpenHelper openHelper;
    private SharedPreferences settings;

    private void initComponents() {
        this.registerButton = (Button) findViewById(R.id.register_button);
        this.loginButton = (Button) findViewById(R.id.login_button);
        this.fingerprintButton = (Button) findViewById(R.id.fingerprint_button);
        this.settings = PreferenceManager.getDefaultSharedPreferences(this);
    }

    private void setListeners() {
        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(WelcomeActivity.this, LoginActivity.class);
            }
        });
        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(WelcomeActivity.this, RegisterActivity.class);
            }
        });
        this.fingerprintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(WelcomeActivity.this, FingerprintActivity.class);
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

        if(!settings.contains(this.getString(R.string.cred))){
            fingerprintButton.setEnabled(false);
        }
    }
}
