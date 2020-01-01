package org.hotelsystem.view;
import org.hotelsystem.model.LoginStatus;
import org.hotelsystem.control.*;
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	private JTabbedPane tabbedPane; 
    private SearchUI searchUI;
    private ReserveUI reserveUI;
    private LoginUI loginUI;
    private ModifyUI modifyUI;
    private InquireUI inquireUI;
    private AccountUI accountUI;

    private SearchControl searchControl;
    private ReserveControl reserveControl;
    private LoginControl loginControl;
    private ModifyControl modifyControl;
    private InquireControl inquireControl;
    
    public void logoutChange(){//will be called when user click `logout Button` in AccountUI
        tabbedPane.remove(0);
        tabbedPane.insertTab("login",null, this.loginUI,null, 0);
    }
    public void loginChange(){//will be called when controller pass the login/signup in LoginUI
        tabbedPane.remove(0);
        tabbedPane.insertTab("Account",null,this.accountUI,null,0);
    }
    public MainFrame() {
        
        super("Main Frame");

        this.searchControl = new SearchControl();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginStatus loginstatus = new LoginStatus();
        this.tabbedPane = new JTabbedPane();
        
        this.loginUI = new LoginUI(this);
        this.accountUI=new AccountUI(this);

        tabbedPane.addTab("Account", this.loginUI);
        
        this.searchUI = new SearchUI(this, this.searchControl);
        this.searchControl.setUI(this.searchUI);
        tabbedPane.addTab("Search", this.searchUI);
        
        this.reserveUI = new ReserveUI();
        tabbedPane.addTab("Reserve", this.reserveUI);

        this.modifyUI = new ModifyUI();
        tabbedPane.addTab("ModifyUI",this.modifyUI);      
        
        this.inquireUI = new InquireUI(this, this.modifyUI);
        tabbedPane.addTab("InquireUI",this.inquireUI);

        
        add(tabbedPane, BorderLayout.CENTER);
        setSize(1200, 600);
        setVisible(true);
    }

    public void switchPanal(int switchTo){
        this.tabbedPane.setSelectedIndex(switchTo);
    } 

    public static void main(String[] args) {
    	MainFrame mainFrame = new MainFrame();
    }
}
