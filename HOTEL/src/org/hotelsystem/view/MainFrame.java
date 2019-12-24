package org.hotelsystem.view;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	private JTabbedPane tabbedPane; 
    private SearchUI searchUI;
    private ReserveUI reserveUI;
    private LoginUI loginUI;
    private ModifyUI modifyUI;

    public MainFrame() {
        super("Main Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.tabbedPane = new JTabbedPane();
        
        this.loginUI = new LoginUI();
        tabbedPane.addTab("Login", this.loginUI);
        
        
        this.searchUI = new SearchUI();
        tabbedPane.addTab("Search", this.searchUI);
        
        this.reserveUI = new ReserveUI();
        tabbedPane.addTab("Reserve", this.reserveUI);

        this.modifyUI = new ModifyUI();
        tabbedPane.addTab("ModifyUI",this.modifyUI);        

        
        add(tabbedPane, BorderLayout.CENTER);
        setSize(1400, 700);
        setVisible(true);
    }

    public static void main(String[] args) {
    	MainFrame mainFrame = new MainFrame();
    }
}