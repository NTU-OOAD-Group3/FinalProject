package org.hotelsystem.control;

import org.hotelsystem.model.LoginStatus;
import org.hotelsystem.model.Users;
import org.hotelsystem.model.User;
import java.util.*;
public class LoginControl {

   // private LoginUI login;


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