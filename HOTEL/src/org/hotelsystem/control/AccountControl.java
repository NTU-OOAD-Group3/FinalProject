package org.hotelsystem.control;
import org.hotelsystem.model.DBUtil;
public class AccountControl{
    private MainControl mainControl;
    private DBUtil dbutil;
    public AccountControl(MainControl mainControl,DBUtil dbutil){
        this.mainControl = mainControl;
        
    }
    public void setloggedout(){
        mainControl.currentUserID=-1;
    }
}