package org.hotelsystem.view;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	private JTabbedPane tabbedPane; 
    private SearchUI searchUI;
    private ReserveUI reserveUI;

    public MainFrame() {
        super("Main Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.tabbedPane = new JTabbedPane();
        
        this.searchUI = new SearchUI();
        tabbedPane.addTab("Search", this.searchUI);
        
        this.reserveUI = new ReserveUI();
        tabbedPane.addTab("Reserve", this.reserveUI);
        
        add(tabbedPane, BorderLayout.CENTER);
        setSize(1200, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
    	MainFrame mainFrame = new MainFrame();
    }
}