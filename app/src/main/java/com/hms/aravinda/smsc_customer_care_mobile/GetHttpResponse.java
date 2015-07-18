package com.hms.aravinda.smsc_customer_care_mobile;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aravinda on 11-Jul-15.
 */
public class GetHttpResponse{

    private List<String> smsc = new ArrayList<String>();
//    @Override
//    public String toString() {
//        return String.valueOf(smsc);
//    }

    public List<String> getSmsc() {
        return smsc;
    }

    public void setSmsc(List<String> smsc) {
        this.smsc = smsc;
    }
}
