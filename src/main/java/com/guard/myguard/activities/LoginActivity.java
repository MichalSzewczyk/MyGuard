package com.guard.myguard.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guard.myguard.R;
import com.guard.myguard.database.DBOpenHelper;

public class LoginActivity extends Activity {
    private Button loginButton;
    private EditText nick;
    private EditText password;
    private DBOpenHelper openHelper;

    private void initComponents(){
        this.loginButton = (Button) findViewById(R.id.login_button);
        this.nick = (EditText) findViewById(R.id.nick_text);
        this.password = (EditText) findViewById(R.id.password_text);
    }

    private void setListeners(){
        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!openHelper.verifyCredentials(nick.getText().toString(), password.getText().toString(), openHelper.getAllData())){
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent registerIntent = new Intent(LoginActivity.this, MapsActivity.class);
                finish();
                startActivity(registerIntent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.openHelper = new DBOpenHelper(this);
        setContentView(R.layout.activity_login);
        initComponents();
        setListeners();

    }
}
