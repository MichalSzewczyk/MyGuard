package com.guard.myguard;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.guard.myguard.database.DBOpenHelper;
import com.guard.myguard.services.impl.StoredLoginHandler;
import com.guard.myguard.services.interfaces.LoginHandler;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.USE_FINGERPRINT;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.STORAGE;
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

        if (checkSelfPermission(CAMERA)
                == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(STORAGE)
                        == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(SEND_SMS)
                        == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(USE_FINGERPRINT)
                        == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED) {

            Log.d("permission", "permission denied to CAMERA - requesting it");
            String[] permissions = {CAMERA, STORAGE, SEND_SMS, READ_EXTERNAL_STORAGE,
                    USE_FINGERPRINT, ACCESS_FINE_LOCATION, WRITE_EXTERNAL_STORAGE};

            requestPermissions(permissions, 1);
        }

        initComponents();
        setListeners();

        if (!handler.isDataAvailable()) {
            fingerprintButton.setEnabled(false);

        } else {
            Log.i("data", handler.getStoredCredentials().toString());
        }
    }
}
