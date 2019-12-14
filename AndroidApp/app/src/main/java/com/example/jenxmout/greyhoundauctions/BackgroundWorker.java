package com.example.jenxmout.greyhoundauctions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * This is an BackgroundWorker class that enables communication
 * between the Android app and the database where the auction information
 * is stored
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @version 1.0 12/15/19
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {

    /**
     * The context of the current activity
     */
    protected Context context;

    /**
     * Alert Dialog to inform user of success/fail scenarios
     */
    protected AlertDialog alertDialog;

    /**
     * BackgroundWorker Constructor
     * @param ctx the context of the current activity
     */
    public BackgroundWorker(Context ctx){
        this.context = ctx;
    }

    /**
     * This method allows the application to perform reads, writes,
     * and checks with the database depending on the type of request
     *
     * @param params parameters necessary to perform each interaction with the database
     * @return the string that is being outputted from PHP script in
     *         communication with the database
     */
    @Override
    protected String doInBackground(String... params) {
        //grabs the type of interaction requested
        String type = params[0];

        //URL for login PHP script
        String login_url = "http://jajeimo.cs.loyola.edu/php/login.php";

        //URL for sign-up PHP script
        String signup_url = "http://jajeimo.cs.loyola.edu/php/signup.php";

        //URL for event info fetch PHP script
        String event_url = "http://jajeimo.cs.loyola.edu/php/event.php";

        //URL for item data fetch PHP script
        String itemdata_url = "http://jajeimo.cs.loyola.edu/php/itemData.php";

        //URL updating user fetch PHP script
        String updateuser_url = "http://jajeimo.cs.loyola.edu/php/updateUser.php";

        //URL updating item fetch PHP script
        String updateitem_url = "http://jajeimo.cs.loyola.edu/php/updateItem.php";

        //URL for autobid PHP script
        String autobid_url = "http://jajeimo.cs.loyola.edu/php/autoBid.php";

        //if the request is login, confirm user exists with database
        if(type.equals("login")) {
            try {
                //save parameters to send to PHP script
                String email = params[1];
                String password = params[2];

                //connect to PHP script via URL
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                //send PHP script user's login
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                        outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Email","UTF-8")+"="+URLEncoder.encode
                        (email,"UTF-8")+"&" +URLEncoder.encode("Password","UTF-8")+"="
                        +URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //read output from PHP script
                InputStream inputStream = httpURLConnection.getErrorStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream,"iso-8859-1"));
                String result = type + ",";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //if request is sign-up, create new user in database with information given
        else if(type.equals("sign-up")){
            //save parameters to send to PHP script
            String first_name = params[1];
            String last_name = params[2];
            String email = params[3];
            String password = params[4];

            try {
                //connect to PHP script via URL
                URL url = new URL(signup_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                //send PHP script info necessary to create a new user in the database
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                        outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("FirstName","UTF-8")+"="+
                        URLEncoder.encode(first_name,"UTF-8") +"&" +URLEncoder.encode(
                                "LastName","UTF-8")+"="+URLEncoder.encode(last_name,
                        "UTF-8") + "&" +URLEncoder.encode("Email","UTF-8")+"="+
                        URLEncoder.encode(email,"UTF-8") + "&" +URLEncoder.encode(
                                "Password","UTF-8")+"="+URLEncoder.encode(password,
                        "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                //grab output from PHP script
                InputStream inputStream = httpURLConnection.getErrorStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream,"iso-8859-1"));
                String result = type + " ";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //if request is fetch event data, populate static FundraiserInfo object with data from database
        else if(type.equals("fetch event data")){
            try {
                //connect to PHP script via URL
                URL url = new URL(event_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                //read event info from PHP script output
                InputStream inputStream = httpURLConnection.getInputStream();
                Log.w("input stream", inputStream.toString());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream,"iso-8859-1"));
                String result = type + ",";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //if request is fetch item data, PHP script dumps all items in database
        else if(type.equals("fetch item data")){
            try{
                //connect to PHP script via URL
                URL url = new URL(itemdata_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                //grab item data from PHP script
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream,"iso-8859-1"));
                String result = type + ";";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //if request is to update user data, send the PHP script the data that needs to be updated
        //in the database
        else if(type.equals("update user data")){
            //save parameters to send to PHP script
            String itemTitles = params[1];
            String firstName = params[2];
            String lastName = params[3];

            try {
                //connect to PHP script via URL
                URL url = new URL(updateuser_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                //send PHP script user information to update in the database
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                        outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("ItemsBidOn","UTF-8")+"="+
                        URLEncoder.encode(itemTitles,"UTF-8")+"&" +
                        URLEncoder.encode("FirstName", "UTF-8")+"="+URLEncoder.encode(
                                firstName,"UTF-8")+"&" + URLEncoder.encode("LastName",
                        "UTF-8")+"="+ URLEncoder.encode(lastName,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                //grab output from PHP script
                InputStream inputStream = httpURLConnection.getErrorStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream,"iso-8859-1"));
                String result = type + " ";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //if request is to update item data, send PHP script the item data that needs updating in
        //the database
        else if(type.equals("update item data")){
            //save parameters to send to PHP script
            String itemCHB = params[1];
            String name = params[2];
            String itemTitle = params[3];

            try {
                //connect to PHP script via URL
                URL url = new URL(updateitem_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                //send item data that needs updating in database to PHP script
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                        outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("CHB","UTF-8")+"="+
                        URLEncoder.encode(itemCHB,"UTF-8")+"&" +
                        URLEncoder.encode("Name", "UTF-8")+"="+URLEncoder.encode(name,
                        "UTF-8")+"&" + URLEncoder.encode("ItemTitle","UTF-8")+"="+
                        URLEncoder.encode(itemTitle,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                //grab PHP scripts output
                InputStream inputStream = httpURLConnection.getErrorStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = type + " ";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.w("Update user result", result);
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * This method executes before superclass's execute function
     */
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    /**
     * This method executes after superclass's execute function
     * and finishes the request with the information from the
     * database
     */
    @Override
    protected void onPostExecute(String result) {
        //String array to hold result data
        String[] resultArr;

        //check for login request
        if(result.contains("login")) {
            //check for PHP output not existing, or a failed login
            if(result.equals("login ")){
                Toast.makeText(context, "Email or password are incorrect!", Toast.LENGTH_LONG).show();
            }
            else{
                //split result string into array
                resultArr = result.split(",");

                //log in static user, you
                MainActivity.you.logIn(resultArr[1], resultArr[2], resultArr[3], resultArr[4]);
                //populate items bid on list, if user has bid on an item
                if(resultArr.length > 5) {
                    for (int i = 5; i < resultArr.length; i++) {
                        for (Item item : MainActivity.ais.items) {
                            if ((item.title).equals(resultArr[i])) {
                                Item bidOn = item;
                                MainActivity.you.itemsBidOn.add(bidOn);
                            }
                        }
                    }
                }
                //navigate user to home on successful login
                if (MainActivity.you.signedIn) {
                    Intent homeIntent = new Intent(context, MainActivity.class);
                    context.startActivity(homeIntent);
                    Toast.makeText(context, "Welcome Back!", Toast.LENGTH_LONG).show();
                }
            }
        }
        //check for sign-up request
        else if(result.contains("sign-up")){
            //check that the email is not already in the database
            if(result.equals("sign-up That email is already in use. Try a different one.")){
                Toast.makeText(context, "That email is already in use. Try a different one.",
                        Toast.LENGTH_LONG).show();
            }
            else{
                //split PHP output into an array
                resultArr = result.split("\\s+");
                //sign up static user, you
                MainActivity.you.signUp(resultArr[1], resultArr[2], resultArr[3], resultArr[4]);

                //navigate back to home
                Intent homeIntent = new Intent(context, MainActivity.class);
                context.startActivity(homeIntent);
                Toast.makeText(context, "Welcome!", Toast.LENGTH_LONG).show();

            }
        }
        //check for fetch event data request
        else if(result.contains("fetch event data")){
            //split PHP output into array of event data
            resultArr = result.split(",");

            //convert string image to bitmap
            String encodedImage = resultArr[1];
            byte [] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap imageBM = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            //create static fundraiser info object
            MainActivity.fInfo = new FundraiserInfo(imageBM,resultArr[2], resultArr[3], resultArr[4]);

            //navigate home
            Intent homeIntent = new Intent(context, MainActivity.class);
            context.startActivity(homeIntent);

        }
        //check for fetch item data request
        else if(result.contains("fetch item data")){
            //split PHP output into array of item data
            resultArr = result.split(";");

            //create new static auction items objects
            MainActivity.ais = new AuctionItems();

            //create new items in auction items object, while the data exists
            for(int i = 1; i+6 < resultArr.length; i+=7) {
                //convert string image to bitmap
                String encodedImage = resultArr[i+5];
                byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
                Bitmap imageBM = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                //create array for items tags
                String [] tagsArr = resultArr[i+3].split(",");

                //create new item for given data
                MainActivity.ais.items.add(new Item(resultArr[i], resultArr[i+1], Double.valueOf(resultArr[i+2]),
                        tagsArr, Double.valueOf(resultArr[i+4]), imageBM, resultArr[i+6]));
            }

            //navigate to home
            Intent homeIntent = new Intent(context, MainActivity.class);
            context.startActivity(homeIntent);
        }
        //if the request was not an expected type, log an error line
        else{
            Log.w("if else post", "didnt contain any existing strings");
        }

    }

    /**
     * This method runs on the UI thread after publishProgress()
     * is invoked
     */
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}