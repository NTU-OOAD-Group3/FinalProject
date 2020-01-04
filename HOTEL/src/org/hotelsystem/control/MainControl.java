package org.hotelsystem.control;
import org.hotelsystem.view.MainFrame;

public class MainControl{
    private HostControl hostControl;
    private InquireControl inquireControl;
    private LoginControl loginControl;
    private ModifyControl modifyControl;
    private ReserveControl reserveControl;
    private SearchControl searchControl;

    public MainControl(){
        hostControl = new HostControl();
        inquireControl = new InquireControl();
        loginControl = new LoginControl();
        modifyControl = new ModifyControl();
        reserveControl = new ReserveControl();
        searchControl = new SearchControl();
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

    public ReserveControl getReserveControl(){
        return this.reserveControl;
    }

    public SearchControl getSearchControl(){
        return this.searchControl;
    }
}