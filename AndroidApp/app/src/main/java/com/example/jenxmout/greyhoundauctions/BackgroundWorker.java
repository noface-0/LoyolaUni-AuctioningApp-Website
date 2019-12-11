package com.example.jenxmout.greyhoundauctions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
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
 * This is an BackgroundWorker class that...
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @version 1.0 12/15/19
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {

    /**
     * The context
     */
    protected Context context;

    /**
     * Alert Dialog to inform user of success/fail scenarios
     */
    protected AlertDialog alertDialog;

    /**
     * BackgroundWorker Constructor
     * @param ctx
     */
    public BackgroundWorker(Context ctx){
        this.context = ctx;
    }

    /**
     * This method allows the application to perform reads, writes, and checks with the database
     *
     * @param params parameters necessary to perform each interaction with the database
     * @return output from PHP script
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

        if(type.equals("login")) {
            try {
                String email = params[1];
                String password = params[2];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
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
                Log.w("Fetch result", result);
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("sign-up")){
            String first_name = params[1];
            String last_name = params[2];
            String email = params[3];
            String password = params[4];

            try {
                URL url = new URL(signup_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("FirstName","UTF-8")+"="+URLEncoder.encode(first_name,"UTF-8")
                        +"&" +URLEncoder.encode("LastName","UTF-8")+"="+URLEncoder.encode(last_name,"UTF-8")
                        + "&" +URLEncoder.encode("Email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")
                        + "&" +URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
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
                Log.w("Fetch result", result);
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(type.equals("fetch event data")){
            try {
                Log.w("Event Data", "requesting fetch");

                URL url = new URL(event_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                Log.w("URL", "connected!");
                //grab event info from PHP script
                InputStream inputStream = httpURLConnection.getInputStream();
                Log.w("input stream", inputStream.toString());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = type + ",";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.w("Fetch result", result);
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("fetch item data")){
            try{
            Log.w("Item Data", "requesting fetch");
                URL url = new URL(itemdata_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                Log.w("URL", "connected!");
                //grab event info from PHP script
                InputStream inputStream = httpURLConnection.getInputStream();
                Log.w("input stream", inputStream.toString());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = type + ";";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.w("Fetch result", result);
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(type.equals("update user data")){
            String itemTitles = params[1];
            String firstName = params[2];
            String lastName = params[3];

            try {
                Log.w("update user data", "running");
                URL url = new URL(updateuser_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("ItemsBidOn","UTF-8")+"="+
                        URLEncoder.encode(itemTitles,"UTF-8")+
                        URLEncoder.encode("FirstName", "UTF-8")+"="+URLEncoder.encode(firstName,"UTF-8")+URLEncoder.encode("LastName","UTF-8")+"="+
                        URLEncoder.encode(lastName,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
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
                Log.w("Fetch result", result);
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //if(type.equals("update item data")){}
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
     */
    @Override
    protected void onPostExecute(String result) {
        String[] resultArr;
        Log.w("post execute", "running");

        //check for login
        if(result.contains("login")) {
            if(result.equals("login ")){
                Toast.makeText(context, "Email or password are incorrect!", Toast.LENGTH_LONG).show();
            }
            else{
                resultArr = result.split("\\s+");
                for (String str : resultArr) {
                    Log.w("Str in arr", str);
                }
                MainActivity.you.logIn(resultArr[1], resultArr[2], resultArr[3], resultArr[4]);
                if (MainActivity.you.signedIn) {
                    Intent homeIntent = new Intent(context, MainActivity.class);
                    context.startActivity(homeIntent);
                    Toast.makeText(context, "Welcome Back!", Toast.LENGTH_LONG).show();
                }
            }
        }
        else if(result.contains("sign-up")){
            if(result.equals("sign-up That email is already in use. Try a different one.")){
                Toast.makeText(context, "That email is already in use. Try a different one.",
                        Toast.LENGTH_LONG).show();
            }
            else{
                resultArr = result.split("\\s+");
                MainActivity.you.signUp(resultArr[1], resultArr[2], resultArr[3], resultArr[4]);
                Log.w("result", "signed up!");

                Intent homeIntent = new Intent(context, MainActivity.class);
                context.startActivity(homeIntent);
                Toast.makeText(context, "Welcome!", Toast.LENGTH_LONG).show();

            }
        }
        else if(result.contains("fetch event data")){
            resultArr = result.split(",");
            Log.w("post execute", "event");

            String encodedImage = resultArr[1];
            byte [] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap imageBM = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            MainActivity.fInfo = new FundraiserInfo(imageBM,resultArr[2], resultArr[3], resultArr[4]);

            Intent homeIntent = new Intent(context, MainActivity.class);
            context.startActivity(homeIntent);

        }
        else if(result.contains("fetch item data")){
            resultArr = result.split(";");
            MainActivity.ais = new AuctionItems();

            for(int i = 1; i+5 < resultArr.length; i+=6) {
                String encodedImage = resultArr[i+5];
                byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
                Bitmap imageBM = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                String [] tagsArr = resultArr[i+3].split(",");

                MainActivity.ais.items.add(new Item(resultArr[i], resultArr[i+1], Double.valueOf(resultArr[i+2]),
                        tagsArr, Double.valueOf(resultArr[i+4]), imageBM));
            }

            Intent homeIntent = new Intent(context, MainActivity.class);
            context.startActivity(homeIntent);
        }
        else{
            Log.w("if else post", "didnt contain any existing strings");
        }

    }

    /**
     * This method ....
     */
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}