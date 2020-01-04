package org.hotelsystem.control;

import org.hotelsystem.model.LoginStatus;
import org.hotelsystem.model.DBUtil;
import org.hotelsystem.model.Users;
import org.hotelsystem.model.User;
import java.util.*;
public class LoginControl {
    private Encoder encoder;
   // private LoginUI login;
    private MainControl mainControl;
    private DBUtil dbutil;
    public LoginControl(MainControl mainControl, DBUtil dbutil){
        this.mainControl = mainControl;
        this.encoder = new Encoder();
    }

	public boolean verifyLogin(String username, String password) {
        Users users=new Users();//removed into main.java
        //users.getUsers().size();
        for(User tmp:users.getUsers()){//DB interface will replace this.
            if(tmp.getUsername().equals(username)&&tmp.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    public boolean verifySignup(String username, String password){
        return false;
    }
    public void setLoggedIn(){

    }
    public void setLoggedOut(){

    }

 

}