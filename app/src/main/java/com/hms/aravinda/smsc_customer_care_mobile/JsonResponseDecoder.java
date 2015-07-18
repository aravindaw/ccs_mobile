package com.hms.aravinda.smsc_customer_care_mobile;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;


public class JsonResponseDecoder {

    @Expose
    private List<Smsc> smsc = new ArrayList<Smsc>();

    /**
     * @return The smsc
     */
    public List<Smsc> getSmsc() {
        return smsc;
    }

    /**
     * @param smsc The smsc
     */
    public void setSmsc(List<Smsc> smsc) {
        this.smsc = smsc;
    }

}

class Smsc {

    @Expose
    private String name;
    @Expose
    private String status;
    @SerializedName("active_sessions")
    @Expose
    private String activeSessions;
    @SerializedName("active_session_count")
    @Expose
    private String activeSessionCount;
    @Expose
    private String throughput;

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The activeSessions
     */
    public String getActiveSessions() {
        return activeSessions;
    }

    /**
     * @param activeSessions The active_sessions
     */
    public void setActiveSessions(String activeSessions) {
        this.activeSessions = activeSessions;
    }

    /**
     * @return The activeSessionCount
     */
    public String getActiveSessionCount() {
        return activeSessionCount;
    }

    /**
     * @param activeSessionCount The active_session_count
     */
    public void setActiveSessionCount(String activeSessionCount) {
        this.activeSessionCount = activeSessionCount;
    }

    /**
     * @return The throughput
     */
    public String getThroughput() {
        return throughput;
    }

    /**
     * @param throughput The throughput
     */
    public void setThroughput(String throughput) {
        this.throughput = throughput;
    }

}