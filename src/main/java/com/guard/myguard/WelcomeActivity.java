package com.guard.myguard;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.guard.myguard.database.DBOpenHelper;

import static com.guard.myguard.utils.Utils.login;

public class WelcomeActivity extends Activity {
    private Button registerButton;
    private Button loginButton;
    private DBOpenHelper openHelper;

    private void initComponents(){
        this.registerButton = (Button) findViewById(R.id.register_button);
        this.loginButton = (Button) findViewById(R.id.login_button);
    }

    private void setListeners(){
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.openHelper = new DBOpenHelper(this);
        setContentView(R.layout.activity_welcome);

        initComponents();
        setListeners();
    }
}
