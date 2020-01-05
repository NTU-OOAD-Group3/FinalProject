package org.hotelsystem.control;

import org.hotelsystem.model.DBUtil;
import java.awt.*;

public class AccountControl{
    private MainControl mainControl;
    private DBUtil dbutil;

    public AccountControl(MainControl mainControl, DBUtil dbutil) {
        this.mainControl = mainControl;
        this.dbutil = dbutil;
    }

    public Image getBackGroundImage(){
        return this.mainControl.getBackGroundImage();
    }

    public void setLogout(){
        this.mainControl.setCurrentUser(null);
        this.mainControl.currentUserID = -1;
    }
}