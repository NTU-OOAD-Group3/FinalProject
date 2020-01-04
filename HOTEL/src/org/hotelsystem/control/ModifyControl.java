package org.hotelsystem.control;
import org.hotelsystem.model.DBUtil;

public class ModifyControl {
    private MainControl mainControl;
    private DBUtil dbutil;
    public ModifyControl(MainControl mainControl, DBUtil dbutil){
        this.mainControl = mainControl;
        this.dbutil = dbutil;
    }
}