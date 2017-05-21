package com.guard.myguard;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
    private Button loginButton;
    private EditText nick;
    private EditText password;

    private void initComponents(){
        this.loginButton = (Button) findViewById(R.id.login_button);
        this.nick = (EditText) findViewById(R.id.nick_text);
        this.password = (EditText) findViewById(R.id.password_text);
    }

    private void setListeners(){
        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, MapsActivity.class);
                finish();
                startActivity(registerIntent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
        setListeners();

    }
}
