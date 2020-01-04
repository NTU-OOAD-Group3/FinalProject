package org.hotelsystem.control;

import org.hotelsystem.model.DBUtil;

public class HostControl {
    private MainControl mainControl;
    private DBUtil dbutil;
    public HostControl(MainControl mainControl, DBUtil dbutil){
        this.mainControl = mainControl;
        this.dbutil = dbutil;
    }
	
}