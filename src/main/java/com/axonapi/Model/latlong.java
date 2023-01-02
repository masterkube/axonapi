package com.axonapi.Model;

public class latlong {
    public int lattitude;
    public int longitude;
    public String ProcessID;
    public String from;
    public String to;
    public String label;
    public int getLattitude() {
        return lattitude;
    }
    public void setLattitude(int lattitude) {
        this.lattitude = lattitude;
    }
    public int getLongitude() {
        return longitude;
    }
    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
    public String getProcessID() {
        return ProcessID;
    }
    public void setProcessID(String processID) {
        ProcessID = processID;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
}
