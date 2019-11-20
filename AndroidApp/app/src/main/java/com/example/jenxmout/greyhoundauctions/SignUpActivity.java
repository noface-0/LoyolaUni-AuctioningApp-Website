package com.example.jenxmout.greyhoundauctions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is the SignUp Activity class that
 *
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @author Ian Leppo
 * @author Javon Kitson
 * @version 1.0 10/21/19
 */
public class SignUpActivity extends AppCompatActivity {

    /**
     * Sets up the sign up screen view
     *
     * @param savedInstanceState the reference to a Bundle object that is passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button backToLoginButton = (Button) findViewById(R.id.backToLoginButton);
        backToLoginButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is taken to the next view
             * AccountActivity
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v) {
                Intent accountIntent = new Intent(SignUpActivity.this, AccountActivity.class);
                startActivity(accountIntent);
            }
        });

        //Sign Up
        Button signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstNameInput = (EditText) findViewById(R.id.firstName);
                String firstName = String.valueOf(firstNameInput.getText());

                EditText lastNameInput = (EditText) findViewById(R.id.lastName);
                String lastName = String.valueOf(lastNameInput.getText());

                EditText emailInput = (EditText) findViewById(R.id.email);
                String userEmail = String.valueOf(emailInput.getText());

                EditText passwordInput = (EditText) findViewById(R.id.password);
                String userPassword = String.valueOf(passwordInput.getText());

                EditText passwordConfirmInput = (EditText) findViewById(R.id.confirmPassword);
                String userPasswordConfirm = String.valueOf(passwordConfirmInput.getText());

                if(!userPassword.equals(userPasswordConfirm)){
                    Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                }
                else {
                    //set user name, email, password, set signedIn flag using signUp method
                    Toast.makeText(SignUpActivity.this, "Welcome", Toast.LENGTH_LONG).show();

                    //bring to home
                    Intent homeIntent = new Intent(SignUpActivity.this, MainActivity.class);

                    startActivity(homeIntent);
                }

            }
        });

    }
}
