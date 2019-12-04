package com.example.jenxmout.greyhoundauctions;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is the Account Activity class that...
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @author Ian Leppo
 * @author Javon Kitson
 * @version 1.0 10/21/19
 */
public class AccountActivity extends AppCompatActivity {

    //hardcoded account info
    private String [] hcEmail = new String[]{"jdoe@yahoo.com", "admin@loyola.edu", "test@gmail.com"};
    //hardcoded account info
    private String [] hcPassword = new String[]{"password", "password", "password"};
    private String [] hcFName = new String[]{"John", "Admin", "Test"};
    private String [] hcLName = new String[]{"Doe", "Admin", "Test"};

    /**
     * Sets up the account screen view
     *
     * @param savedInstanceState the reference to a Bundle object that is passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Login Button
        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is logged into their account
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v) {
                MainActivity.you = new User();
                TextView emailTV = findViewById(R.id.email);
                TextView passwordTV = findViewById(R.id.password);
                String userEmail = String.valueOf(emailTV.getText());
                String userPwd = String.valueOf(passwordTV.getText());

                for(int i = 0; i < hcEmail.length; i++) {
                    String em = hcEmail[i];
                    if (userEmail.equals(em)) {
                        if (hcPassword[i].equals(userPwd)) {
                            Intent accountIntent = new Intent(AccountActivity.this, MainActivity.class);
                            MainActivity.you.logIn(userEmail, userPwd);
                            MainActivity.you.firstName = hcFName[i];
                            MainActivity.you.lastName = hcLName[i];
                            startActivity(accountIntent);
                            Toast.makeText(AccountActivity.this, "Welcome Back!", Toast.LENGTH_LONG).show();
                            break;
                        } else {
                            Toast.makeText(AccountActivity.this, "Incorrect Password", Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    else {
                        Toast.makeText(AccountActivity.this,
                                "We don't recognize your email, try making a new account!",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        // Home Button
        Button homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is taken to the home screen or
             * MainActivity
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(AccountActivity.this, MainActivity.class);

                startActivity(homeIntent);
            }

        });

        //Log Out Button
        Button logOutButton = (Button) findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is logged out of their account
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v) {
                MainActivity.you.logOut();
                Intent homeIntent = new Intent(AccountActivity.this, MainActivity.class);

                startActivity(homeIntent);
                Toast.makeText(AccountActivity.this, "Logging Out!", Toast.LENGTH_LONG).show();
            }
        });

        //Forgot Password button
        Button sendEmailButton = (Button) findViewById(R.id.sendEmailButton);
        sendEmailButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is sent an email according to the
             * email they put in the email Text View
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });


        Button signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {

        /**
         * This method sets a click listener for the button in the UI
         * When clicked, the user is taken to the next view
         * SignUpActivity
         *
         * @param v the view of the current state
         */
        @Override
        public void onClick(View v){
            Intent signUpIntent = new Intent(AccountActivity.this, SignUpActivity.class);
            startActivity(signUpIntent);
            }
        });
    }

    /**
     * To send an email to if forgot password is clicked
     */
    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("tag", "Finished sending email...");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(AccountActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
