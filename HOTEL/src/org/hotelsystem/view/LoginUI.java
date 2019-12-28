package org.hotelsystem.view;

import java.awt.*;
import javax.swing.*;

public class LoginUI extends JPanel {
    private JPanel usernamePanel;/*get username input */
    private JPanel passwordPanel; //get password input
    private JButton loginButton; //login  event 
    private JButton signupButton; //open up a new dialog
	public LoginUI() {
   
       
        this.setLayout(new BorderLayout());
        initUI();
    }
    private void initUI(){
        this.usernamePanel = new JPanel();
        this.passwordPanel = new JPanel();
        this.loginButton = new JButton();
        this.signupButton = new JButton();
    }
    

    
}