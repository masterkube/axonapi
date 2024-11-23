package com.axonapi.Model;

public class node {
    public String id;
    public String label;
    public String parentnode;

    public String getparentnode() {
        return parentnode;
    }

    public void setparentnode(String parentnode) {
        this.parentnode = parentnode;
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
