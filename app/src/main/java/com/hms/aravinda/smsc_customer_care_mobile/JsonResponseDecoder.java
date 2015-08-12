package com.hms.aravinda.smsc_customer_care_mobile;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;



public class JsonResponseDecoder {

    private List<Smscs> smscs = new ArrayList<Smscs>();
    public List<Smscs> getSmscs() {
        return smscs;
    }
    public void setSmscs(List<Smscs> smscs) {
        this.smscs = smscs;
    }

}

class Smscs {

    private String name;
    private String id;
    private String status;
    private String activeSessions;
    private String activeSessionCount;
    private String throughput;
    private String sessionDetail;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getActiveSessions() {
        return activeSessions;
    }
    public void setActiveSessions(String activeSessions) {
        this.activeSessions = activeSessions;
    }
    public String getActiveSessionCount() {
        return activeSessionCount;
    }
    public void setActiveSessionCount(String activeSessionCount) {
        this.activeSessionCount = activeSessionCount;
    }
    public String getThroughput() {
        return throughput;
    }
    public void setThroughput(String throughput) {
        this.throughput = throughput;
    }
    public String getSessionDetail() {
        return sessionDetail;
    }
    public void setSessionDetail(String sessionDetail) {
        this.sessionDetail = sessionDetail;
    }

}