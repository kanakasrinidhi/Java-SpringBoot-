package com.example.demo;

public class demo {
    String name;
    String pwd;
    String email;


    demo(){}

    demo(String name,String pwd,String email){
        this.name=name;
        this.pwd=pwd;
        this.email=email;
    }

    public String getName(){
        return this.name;
    }
    public String getPwd(){
        return this.pwd;
    }
    public String getEmail(){
        return this.email;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setPwd(String pwd){
        this.pwd=pwd;
    }
    public void setEmail(String email){
        this.email=email;
    }
}