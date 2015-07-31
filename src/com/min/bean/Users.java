package com.min.bean;

public class Users extends AbstractBean{
    
    private String username;
    private String password;
    private TypeUsers type;
    
    public Users(){
        
    }
    
    public Users(String user, String pass){
        this.username = user;
        this.password = pass;
    }
    
    public Users(String user, String pass, TypeUsers type){
        this.username = user;
        this.password = pass;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TypeUsers getType() {
        return type;
    }

    public void setType(TypeUsers type) {
        this.type = type;
    }
    
}
