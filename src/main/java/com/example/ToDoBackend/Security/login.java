package com.example.ToDoBackend.Security;

import org.springframework.stereotype.Service;

@Service
public class login {
  private String userName;
  private String password;
    public login(){

    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
