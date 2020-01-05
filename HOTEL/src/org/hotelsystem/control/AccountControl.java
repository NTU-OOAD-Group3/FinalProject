package org.hotelsystem.control;

import org.hotelsystem.model.DBUtil;
import java.awt.*;
import org.hotelsystem.model.UserInfo;
import org.hotelsystem.view.AccountUI;

public class AccountControl{
    private MainControl mainControl;
    private DBUtil dbutil;
    private AccountUI accountUI;

    public AccountControl(MainControl mainControl, DBUtil dbutil) {
        this.mainControl = mainControl;
        this.dbutil = dbutil;
    }

    public Image getBackGroundImage(){
        return this.mainControl.getBackGroundImage();
    }

    public void setLogout(){
    public void setUI(AccountUI accountUI) {
        this.accountUI = accountUI;
    }

    public void triggerLogin() {
        this.setDisplay();
    }

    public void triggerLogout() {
        this.mainControl.setCurrentUser(null);
        this.mainControl.currentUserID = -1;    // TODO: replace by User object
    }

    public void triggerCancel() {
        this.accountUI.setEntryEnabled(false);
        this.setDisplay();
    }

    public void triggerModify() {
        this.accountUI.setEntryEnabled(true);
    }

    public void triggerSave() {
        this.accountUI.setEntryEnabled(false);
        UserInfo userInfo = this.accountUI.getUserInfoFromEntry();
        boolean saveStatus = this.dbutil.updateUserInfo(userInfo);
        System.out.println("saveStatus: " + saveStatus);
        if ( saveStatus == true ) {
            this.accountUI.createMessageDialog("Update user information successfully.",
                "Success!", "INFORMATION_MESSAGE");
            this.mainControl.refreshCurrentUserInfo();
        } else {
            this.accountUI.createMessageDialog("Update user information failed.",
                "Failed!", "ERROR_MESSAGE");
        }
        this.setDisplay();
    }

    public void setDisplay() {
        this.accountUI.setUserDisplay(this.mainControl.getCurrentUser());
        this.accountUI.setUserInfoDisplay(this.mainControl.getCurrentUserInfo());
    }
}