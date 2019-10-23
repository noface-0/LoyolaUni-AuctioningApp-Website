package com.example.jenxmout.greyhoundauctions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

}
