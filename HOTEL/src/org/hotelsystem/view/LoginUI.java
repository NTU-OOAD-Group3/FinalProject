package org.hotelsystem.view;

import org.hotelsystem.control.LoginControl;

import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;
import javax.swing.border.*;



public class LoginUI extends JPanel implements ActionListener{
    private JLabel blankLableL;
    private JLabel blankLableR;
    private JLabel usernameLable;
    private JLabel passwordLable;
    private JLabel textLable;
    private JTextField tfUsername;/*get username input */
    private JPasswordField tfPassword; //get password input
    private JButton loginButton; //login  event 
    private JButton signupButton; //open up a new dialog
    private Image img = new ImageIcon("resources/transparent_close.jpg").getImage();

    private MainFrame parent;
    private LoginControl loginControl;
	public LoginUI(MainFrame parent, LoginControl loginControl) {
        ////this cannot be used with `addWithConstraints`
        this.parent=parent;
        this.loginControl = loginControl;
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

        this.usernameLable = new JLabel("Username:", JLabel.RIGHT);
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
        this.passwordLable = new JLabel("Password:", JLabel.RIGHT);
        this.addWithConstraints(passwordLable, 3, 2, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);
        this.tfPassword=new JPasswordField();
        this.addWithConstraints(tfPassword, 4, 2, 2, 1, 2, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);
        //line3
        this.loginButton = new JButton("login");
        this.loginButton.addActionListener(this);
        this.addWithConstraints(loginButton, 3, 3, 1, 1, 1, 1, 
        GridBagConstraints.NONE, GridBagConstraints.EAST);
        this.signupButton = new JButton("SignUp");
        this.signupButton.addActionListener(this);
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
    
    public void paintComponent(Graphics g) {
        this.img = this.loginControl.getBackGroundImage();
        g.drawImage(this.img, 0, 0, null);
    }

    public void actionPerformed(ActionEvent e){
        if( e.getSource() == this.loginButton ){
            System.out.println("login triggered.");
            System.out.println(this.tfUsername.getText());
            if(this.tfUsername.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Please input your username");
            }
            if(String.valueOf(this.tfPassword.getPassword()).equals("")){
                JOptionPane.showMessageDialog(this,"Password can't be empty");
            }
            if(!this.tfUsername.getText().equals("") && !String.valueOf(this.tfPassword.getPassword()).equals("")){
                //LOGIN HERE
                //throw username and password to Controller.
                //```pseudocode
                //if(controller.passlogin):
                //  this.parent.loginChange();
                //```
                
                if(this.loginControl.verifyLogin(this.tfUsername.getText(), String.valueOf(this.tfPassword.getPassword()))){
                    //ENCODER SHOULD BE HERE to deal with password.getText()
                    this.parent.setName(this.tfUsername.getText());
                    this.parent.loginChange();
                    this.parent.repaintAll();        
                }
                else{
                    JOptionPane.showMessageDialog(this,"Wrong username or password");
                }

            }


        }
        else if( e.getSource() == this.signupButton ){
            System.out.println("signup triggered.");
            SignupDialog signUpDlg = new SignupDialog(this.parent,"Sign Up",loginControl);
            signUpDlg.setVisible(true);
        }
    }
    
}