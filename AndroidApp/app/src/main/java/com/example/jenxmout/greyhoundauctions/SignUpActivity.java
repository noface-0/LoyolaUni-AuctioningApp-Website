package com.example.jenxmout.greyhoundauctions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * This is the SignUp Activity class that allows a new user to create a GreyhoundAuctions accoount
 *
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @version 1.0 12/15/19
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

        /**
         * This methods creates a user account when button is clicked
         */
        signUpButton.setOnClickListener(new View.OnClickListener() {
            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user's account is either created or an
             * error is diplayed
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v) {
                //grab user input for first name
                EditText firstNameInput = (EditText) findViewById(R.id.firstName);
                String firstName = String.valueOf(firstNameInput.getText());

                //grab user input for last name
                EditText lastNameInput = (EditText) findViewById(R.id.lastName);
                String lastName = String.valueOf(lastNameInput.getText());

                //grab user input for email
                EditText emailInput = (EditText) findViewById(R.id.email);
                String userEmail = String.valueOf(emailInput.getText());

                //grab user input for password
                EditText passwordInput = (EditText) findViewById(R.id.password);
                String userPassword = String.valueOf(passwordInput.getText());

                //grab user input for confirm password
                EditText passwordConfirmInput = (EditText) findViewById(R.id.confirmPassword);
                String userPasswordConfirm = String.valueOf(passwordConfirmInput.getText());

                MainActivity.you = new User();
                if(!userPassword.equals(userPasswordConfirm)){
                    Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                }
                else {
                    //background worker
                    BackgroundWorker bw = new BackgroundWorker(SignUpActivity.this);

                    String encryptedUserPassword = encryptPassword(userPassword);

                    bw.execute("sign-up", firstName, lastName, userEmail, encryptedUserPassword);
                }
            }
        });
    }

    /**
     * This methods takes a password and encrypts it
     *
     * @param password the user's password in the form of a String
     * @return sha1 the encrypted password in the form of a String
     */
    private static String encryptPassword(String password)
    {
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
    private static String byteToHex(final byte[] hash)
    {
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
