package com.guard.myguard;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WelcomeActivity extends Activity {
    private Button registerButton;
    private Button loginButton;

    private void initComponents(){
        this.registerButton = (Button) findViewById(R.id.register_button);
        this.loginButton = (Button) findViewById(R.id.login_button);
    }

    private void setListeners(){
        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                finish();
                startActivity(loginIntent);
            }
        });
        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                finish();
                startActivity(registerIntent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initComponents();
        setListeners();
    }
}
