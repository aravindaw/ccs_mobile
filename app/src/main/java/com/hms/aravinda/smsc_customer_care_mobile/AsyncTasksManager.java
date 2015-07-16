package com.hms.aravinda.smsc_customer_care_mobile;

import android.os.AsyncTask;

/**
 * Created by Aravinda on 11-Jul-15.
 */
public class AsyncTasksManager extends AsyncTask<String, Void, String> {

    private OnTaskCompleted listener;

    @Override
    protected String doInBackground(String... url) {
        return new PostHttpRequest().sendHttpRequest(url[0]);
    }

    public AsyncTasksManager(OnTaskCompleted listener){
        this.listener=listener;
    }

    @Override
    protected void onPostExecute(String s) {
        listener.onTaskCompleted(s);
    }
}

