package com.example.jenxmout.greyhoundauctions;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * This is the Account Activity class that, when created, allows the user to log in to an existing
 * account, log out if they are signed in, navigate back to home, or navigate to the SignUpActivity
 * class
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @version 1.0 12/15/19
 */
public class AccountActivity extends AppCompatActivity {
    /**
     * Sets up the account screen view and adds button functionality
     *
     * @param savedInstanceState the reference to a Bundle object that is passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Functionality for login button
        Button loginButton = (Button) findViewById(R.id.loginButton);
        /**
         * This methods logs user into their account when login button is clicked
         */
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
                //save text views
                TextView emailTV = findViewById(R.id.email);
                TextView passwordTV = findViewById(R.id.password);

                //saves input to strings
                String userEmail = String.valueOf(emailTV.getText());
                String userPwd = String.valueOf(passwordTV.getText());
                String encryptedUserPwd = encryptPassword(userPwd);

                //check with database
                BackgroundWorker bw = new BackgroundWorker(AccountActivity.this);
                bw.execute("login", userEmail, encryptedUserPwd);
            }
        });


        // Functionality for home button
        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton);
        /**
         * This method takes user back to home page
         */
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
                //navigate to home
                Intent homeIntent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

        //Functionality for log out button
        Button logOutButton = (Button) findViewById(R.id.logOutButton);
        /**
         * This method logs the user out of their account
         */
        logOutButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is logged out of their account
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v) {
                //clear all user data
                MainActivity.you = null;

                //navigate to home
                Intent homeIntent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(homeIntent);

                //inform user that they have successfully logged out
                Toast.makeText(AccountActivity.this, "Logging Out!", Toast.LENGTH_LONG).show();
            }
        });

        //Functionality for forgot password button
        Button sendEmailButton = (Button) findViewById(R.id.sendEmailButton);
        /**
         * This method sends an email to the user that allows them to reset their password
         */
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

        //Functionality for sign-up button
        Button signUpButton = (Button) findViewById(R.id.signUpButton);

        /**
         * This method takes the user to the page where they can
         * create a Greyhounds Auction account
         */
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
     * This method sends an email to if forgot password is clicked
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

    /**
     * This methods takes a password and encrypts it
     *
     * @param password the user's password in the form of a String
     * @return sha1 the encrypted password in the form of a String
     */
    private static String encryptPassword(String password) {
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return sha1;
    }

    /**
     * This method converts byte to hex for the password String
     *
     * @param hash the user's password in byte form
     * @return result, the user's password hashed
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
