package com.axonapi.Model;

public class node {
    public String id;
    public String label;
    public String outbounds;
    public String inbounds;

    public String getOutbounds() {
        return this.outbounds;
    }

    public void setOutbounds(String outbounds) {
        this.outbounds = outbounds;
    }

    public String getInbounds() {
        return this.inbounds;
    }

    public void setInbounds(String inbounds) {
        this.inbounds = inbounds;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setLabel(String label){
        this.label = label;
    } 
    
    public String getId(){
        return id;
    }

    public String getLabel(){
        return label;
    }
}
