package com.ebe.maverick;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: EBE13NKTW1
 * Date: 8/30/13
 * Time: 8:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class initValues {

    private String defaultURL;
    private String autoSyncInterval;
    private ArrayList<String> SourceList;

    public initValues(){
        defaultURL = "";
        autoSyncInterval = "240";
        SourceList = new ArrayList<String>();
    }

    public initValues(String URL){
        defaultURL = URL;
        autoSyncInterval = "240";
        SourceList = new ArrayList<String>();
    }

    public initValues(String URL, String interval){
        defaultURL = URL;
        autoSyncInterval = interval;
        SourceList = new ArrayList<String>();
    }

    public initValues(String URL, String interval, ArrayList<String> Sources){
        defaultURL = URL;
        autoSyncInterval = interval;
        SourceList = Sources;
    }

    public void addSource(String Source){
        SourceList.add(Source);
    }

    public String getDefaultURL() {
        return defaultURL;
    }

    public void setDefaultURL(String defaultURL) {
        this.defaultURL = defaultURL;
    }

    public String getAutoSyncInterval() {
        return autoSyncInterval;
    }

    public void setAutoSyncInterval(String autoSyncInterval) {
        this.autoSyncInterval = autoSyncInterval;
    }

    public ArrayList<String> getSourceList() {
        return SourceList;
    }

    public void setSourceList(ArrayList<String> sourceList) {
        SourceList = sourceList;
    }


}
