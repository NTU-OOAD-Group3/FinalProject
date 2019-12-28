package org.hotelsystem.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class LoginUI extends JPanel {
    private JLabel blankLableL;
    private JLabel blankLableR;
    private JLabel usernameLable;
    private JLabel passwordLable;
    private JLabel textLable;
    private JTextField tfUsername;/*get username input */
    private JTextField tfPassword; //get password input
    private JButton loginButton; //login  event 
    private JButton signupButton; //open up a new dialog
	public LoginUI() {
        ////this cannot be used with `addWithConstraints`
        initUI();
    }
    private void initUI(){
        
        this.setLayout(new GridBagLayout());
        //line0
        this.textLable = new JLabel("welcome to xxxxxxxxxxxxxxxxxx");
        //this.textLable.setBorder(new LineBorder(Color.BLACK));
        this.addWithConstraints(textLable, 3, 0, 2, 1, 1, 1,
        GridBagConstraints.NONE, GridBagConstraints.WEST);
        //line1
        this.blankLableL = new JLabel();
        //this.blankLableL.setBorder(new LineBorder(Color.GREEN));
        this.addWithConstraints(blankLableL, 1, 0, 2, 2, 3, 2,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        this.usernameLable = new JLabel("Username:");
        this.addWithConstraints(usernameLable, 3, 1, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);
        this.tfUsername=new JTextField();
        this.addWithConstraints(tfUsername, 4, 1, 2, 1, 2, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);

        this.blankLableR = new JLabel();
        //this.blankLableR.setBorder(new LineBorder(Color.RED));
        this.addWithConstraints(blankLableR, 6, 0, 2, 3, 2, 2,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);


        //line2
        this.passwordLable = new JLabel("Password:");
        this.addWithConstraints(passwordLable, 3, 2, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);
        this.tfPassword=new JTextField();
        this.addWithConstraints(tfPassword, 4, 2, 2, 1, 2, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);
        //line3
        this.loginButton = new JButton("login");
        this.addWithConstraints(loginButton, 3, 3, 1, 1, 1, 1, 
        GridBagConstraints.NONE, GridBagConstraints.EAST);
        this.signupButton = new JButton("SignUp");
        this.addWithConstraints(signupButton, 4, 3, 1, 1, 1, 1, 
        GridBagConstraints.NONE, GridBagConstraints.WEST);
        
 
    }
    private void addWithConstraints(JComponent c, int gridx, int gridy,
            int gridwidth, int gridheight, int weightx, int weighty,
            int fill, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = fill;
        gbc.anchor = anchor;
        this.add(c, gbc);
}
   

    
}