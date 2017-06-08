package com.guard.myguard;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guard.myguard.database.DBOpenHelper;
import com.guard.myguard.services.impl.StoredLoginHandler;
import com.guard.myguard.services.interfaces.LoginHandler;

import static com.guard.myguard.utils.Utils.login;

public class RegisterActivity extends Activity {
    private Button registerButton;
    private EditText nick;
    private EditText password;
    private EditText passwordAgain;
    private EditText iceNumber;
    private EditText phoneNumber;
    private DBOpenHelper openHelper;
    private LoginHandler loginHandler;


    private void initComponents() {
        this.nick = (EditText) findViewById(R.id.nick);
        this.password = (EditText) findViewById(R.id.password);
        this.passwordAgain = (EditText) findViewById(R.id.password_again);
        this.iceNumber = (EditText) findViewById(R.id.ice_number);
        this.phoneNumber = (EditText) findViewById(R.id.phone_number);
        this.registerButton = (Button) findViewById(R.id.register_button);
        this.loginHandler = new StoredLoginHandler(this.getApplicationContext());
    }

    private void setListeners() {
        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validationMessage = buildValidationMessage();
                if (validationMessage.isEmpty()) {
                    if (openHelper.isAlreadyInDatabase(nick.getText().toString(), openHelper.getAllData())) {
                        Toast.makeText(RegisterActivity.this, "User already in database", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    openHelper.insertUserValue(nick.getText().toString(), password.getText().toString(), iceNumber.getText().toString(), phoneNumber.getText().toString());
                    loginHandler.storeCredentials(nick.getText().toString(), password.getText().toString());
                    login(RegisterActivity.this, MapsActivity.class);
                } else {
                    Toast.makeText(RegisterActivity.this, validationMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private String buildValidationMessage() {
        StringBuilder popupMessage = new StringBuilder();
        if (this.nick.getText().length() == 0) {
            popupMessage.append("Empty nick\n");
        }
        if (this.password.getText().length() == 0) {
            popupMessage.append("Empty password\n");
        }
        if (this.phoneNumber.getText().length() == 0) {
            popupMessage.append("Empty phone number\n");
        }
        if (this.iceNumber.getText().length() == 0) {
            popupMessage.append("Empty ice phone number\n");
        }
        if (this.passwordAgain.getText().length() == 0) {
            popupMessage.append("Empty password again\n");
        }
        if (!this.passwordAgain.getText().toString().equals(this.password.getText().toString())) {
            popupMessage.append("Passwords are different\n");
        }
        return popupMessage.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.openHelper = new DBOpenHelper(this);

        setContentView(R.layout.activity_register);
        initComponents();
        setListeners();

    }


}
