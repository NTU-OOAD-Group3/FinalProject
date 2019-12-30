package org.hotelsystem.model;
public class LoginStatus{
    private boolean loggedStatus;
    public LoginStatus(){
        loggedStatus = false;
    }

    public void setloggedStatus(boolean status){
        loggedStatus = status;
    }
    public boolean getloggedStatus(boolean status){
        return loggedStatus;
    }
}