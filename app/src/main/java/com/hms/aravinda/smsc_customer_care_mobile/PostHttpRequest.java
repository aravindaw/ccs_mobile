package com.hms.aravinda.smsc_customer_care_mobile;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by aravinda on 7/9/15.
 */

public class PostHttpRequest {
    private InputStream inputStream;


    public List sendHttpRequest(String url) {

        List result = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(new HttpGet(url));
            inputStream = httpResponse.getEntity().getContent();

            //input stream validation and converting
            if (inputStream != null) {
                Gson gson = new Gson();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                GetHttpResponse obj = gson.fromJson(bufferedReader, GetHttpResponse.class);
                result = obj.getSmsc();
            } else {
                result.add("Not valid input Json");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result+"###########");
        return result;
    }
}

