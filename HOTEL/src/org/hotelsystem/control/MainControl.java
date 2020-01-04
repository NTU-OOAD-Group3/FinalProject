package org.hotelsystem.control;

import org.hotelsystem.model.DBUtil;
import org.hotelsystem.view.MainFrame;

public class MainControl{
    private HostControl hostControl;
    private InquireControl inquireControl;
    private LoginControl loginControl;
    private ModifyControl modifyControl;
    private SearchControl searchControl;
    private AccountControl accountControl;
    private MainFrame mainFrame;
    private DBUtil dbutil;
    public int currentUserID;
    
    public MainControl(){   
        this.dbutil = new DBUtil("140.112.21.82", "ooad", "ooad", "HOTEL");
        hostControl = new HostControl(this, this.dbutil);
        inquireControl = new InquireControl(this, this.dbutil);
        loginControl = new LoginControl(this, this.dbutil);
        modifyControl = new ModifyControl(this, this.dbutil);
        searchControl = new SearchControl(this, this.dbutil);
    }
    public HostControl getHostControl(){
        return this.hostControl;
    }

    public InquireControl getInquireControl(){
        return this.inquireControl;
    }

    public LoginControl getLoginControl(){
        return this.loginControl;
    }

    public ModifyControl getModifyControl(){
        return this.modifyControl;
    }

    public SearchControl getSearchControl(){
        return this.searchControl;
    }
    public AccountControl getAccountControl(){
        return this.accountControl;
    }

    public void setUI(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }

    public void switchPane(int switchTo){
        this.mainFrame.switchPane(switchTo);
    }
    public DBUtil getDbutil(){
        return this.dbutil;
    }

    public int getcurrentUserId(){ return this.currentUserID;}
    public void setcurrentUserId(int id){this.currentUserID=id;}
}