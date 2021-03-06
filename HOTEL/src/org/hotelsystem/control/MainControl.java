package org.hotelsystem.control;

import org.hotelsystem.model.DBUtil;
import org.hotelsystem.model.User;
import org.hotelsystem.model.UserInfo;
import org.hotelsystem.view.MainFrame;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class MainControl{
    private AccountControl accountControl;
    private HostControl hostControl;
    private InquireControl inquireControl;
    private LoginControl loginControl;
    private ModifyControl modifyControl;
    private SearchControl searchControl;
    private MainFrame mainFrame;
    private DBUtil dbutil;
    public int currentUserID;
    private User currentUser = null;
    private Image img = new ImageIcon("resources/transparent_close.jpg").getImage();
    private UserInfo currentUserInfo;
    
    public MainControl() {
        // Initialize DBUtil before initialization of any control!!!
        this.dbutil = new DBUtil("140.112.21.82", "ooad", "ooad", "HOTEL");
        // this.dbutil = new DBUtil("localhost", "root", "root", "HOTEL");
        this.hostControl = new HostControl(this, this.dbutil);
        this.inquireControl = new InquireControl(this, this.dbutil);
        this.loginControl = new LoginControl(this, this.dbutil);
        this.modifyControl = new ModifyControl(this, this.dbutil);
        this.searchControl = new SearchControl(this, this.dbutil);
        this.accountControl = new AccountControl(this, this.dbutil);
    }

    public AccountControl getAccountControl(){ return this.accountControl; }
    public HostControl getHostControl() { return this.hostControl; }
    public InquireControl getInquireControl() { return this.inquireControl; }
    public LoginControl getLoginControl() { return this.loginControl; }
    public ModifyControl getModifyControl() { return this.modifyControl; }
    public SearchControl getSearchControl() { return this.searchControl; }
    public DBUtil getDBUtil() { return this.dbutil; }
    public int getcurrentUserId() { return this.currentUserID; }    // TODO: replace by User object
    public User getCurrentUser() { return this.currentUser; }
    public Image getBackGroundImage() { return this.img; }
    public UserInfo getCurrentUserInfo() { return this.currentUserInfo; }

    public void setUI(MainFrame mainFrame) { this.mainFrame = mainFrame; }
    
    public void setcurrentUserId(int id) { this.currentUserID = id; }   // TODO: replace by User object
    public void setCurrentUser(User user) {
        this.currentUser = user;
        this.refreshCurrentUserInfo();
        System.out.println(this.currentUser);
        System.out.println(this.currentUserInfo);
        if ( this.currentUser != null ) {    
            this.img = new ImageIcon("resources/transparent_open.jpg").getImage();
            this.accountControl.triggerLogin();
            this.mainFrame.loginChange();
        } else {
            this.mainFrame.logoutChange();
        }
    }
    public void refreshCurrentUserInfo() {
        if ( this.currentUser == null ) {
            this.currentUserInfo = null;
        } else {
            this.currentUserInfo = dbutil.getUserInfo(
                this.currentUser.getUserID(), this.currentUser.getPassword()
            );
        } 
    }

    public void refreshInquireUI(){
        this.inquireControl.refreshUI(this.currentUser);
    }

    public void refreshModifyUI(){
        this.modifyControl.setUser(this.currentUser);
    }

    public void switchPane(int switchTo) {
        this.mainFrame.switchPane(switchTo);
    }

    
}