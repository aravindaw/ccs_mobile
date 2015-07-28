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
import java.util.Vector;

/**
 * Created by aravinda on 7/9/15.
 */

public class PostHttpRequest {

    public List sendHttpRequest(String url) {
        InputStream inputStream;
        Vector<String> result = new Vector<>();

        try {
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(new HttpGet(url));
            inputStream = httpResponse.getEntity().getContent();

            //input stream validation and converting
            if (inputStream != null) {
                Gson gson = new Gson();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                JsonResponseDecoder obj = gson.fromJson(bufferedReader, JsonResponseDecoder.class);
                //result = obj.getSmscs();
                List<Smscs> list = obj.getSmscs();

                Vector<String> smscList = new Vector<>();
                Vector<String> smscDetails = new Vector<>();

                SmscMainFragment smscMain = new SmscMainFragment();
                if (url.equals(smscMain.smscList)) {
                    //for get smsc list
                    for (Smscs x : list) {
                        smscList.add(x.getName());
                    }
                } else if (url.equals(smscMain.smscDetails)) {
                    for (Smscs x : list) {
                        smscList.add(x.getStatus());
                        smscList.add(x.getActiveSessions());
                        smscList.add(x.getThroughput());
                    }
                }
                result = smscList;
            } else {
                result.add("Not valid input Json");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result.toString());
        return result;
    }
}