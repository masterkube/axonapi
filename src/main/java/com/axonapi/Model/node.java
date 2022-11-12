package com.axonapi.Model;

public class node {
    public String id;
    public String label;
    public String parentNode;

    public String getParentnode() {
        return this.parentNode;
    }

    public void setParentnode(String parentNode) {
        this.parentNode = parentNode;
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
