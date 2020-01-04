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
        User user =null;
        //users.getUsers().size();

        try{
            DBUtil db=this.mainControl.getDbutil();
            user=db.getUser(username,password);
            if(user==null){
                return false;
            }
            else{
                this.mainControl.currentUserID=user.getUserID();
                return true;
                }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean verifySignup(String username, String password){
        return false;
    }

 

}