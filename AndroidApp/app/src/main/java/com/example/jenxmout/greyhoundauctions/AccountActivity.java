package com.example.jenxmout.greyhoundauctions;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * This is the Account Activity class that
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @author Ian Leppo
 * @author Javon Kitson
 * @version 1.0 10/21/19
 */
public class AccountActivity extends AppCompatActivity {

    /**
     * Sets up the account screen view
     *
     * @param savedInstanceState the reference to a Bundle object that is passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button sendEmailButton = (Button) findViewById(R.id.sendEmailButton);
        sendEmailButton.setOnClickListener(new View.OnClickListener(){
            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is sent an email according to the
             * text they put in the email text view
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v){
                sendEmail();
            }
        });


        Button signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener(){

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
