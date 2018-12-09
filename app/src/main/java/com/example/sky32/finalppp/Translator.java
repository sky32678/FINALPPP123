package com.example.sky32.finalppp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.sky32.finalppp.MainActivity.textview;
import static com.example.sky32.finalppp.MainActivity.transOutput;

public class Translator extends AsyncTask{

    String result = "";

    protected static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbyyO4-WcFfdFbkDOCYu5dX0Q1cpuk_Fgjh1k5Y0zkc2K-R8BQ/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        transOutput.setText("Translating..");
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            result = translate("en","ko",textview.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Object o) {   // this will be executed when doInbackGround finishes
        transOutput.setText(result);
    }
}
