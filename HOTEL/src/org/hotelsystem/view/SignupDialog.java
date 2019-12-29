package org.hotelsystem.view;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
public class SignupDialog extends JDialog implements ActionListener{


    private JPanel signupPanel;
    private JPanel bottomPanel;

    private JLabel blankLableL;
    private JLabel blankLableR;
    private JLabel usernameLable;//username:
    private JLabel passwordLable;//password:
    private JLabel repeatpasswordLable;//repeatpassword:
    private JLabel textLable;//add instruction
    private JTextField tfUsername;/*get username input */
    private JTextField tfPassword; //get password input
    private JTextField tfRepeatPassword;//get repeatpassword
    private JButton cancleButton; //cancle this dialog
    private JButton signUpButton; //signup

    public SignupDialog(JFrame parent, String name){
        super(parent, name,true);
        init();
    }
    private void init() {
        //DRAW GUI 
        
        //signupPanel = new JPanel(new GridLayout(5,4));
        //signupPanel.setPreferredSize(new Dimension(400,500));
        //bottomPanel = new JPanel(new GridLayout(1,3));
        this.setLayout(new GridBagLayout());
        //this.setPreferredSize(new Dimension(400,500));

        this.blankLableL = new JLabel();
        this.addWithConstraints(blankLableL, 0, 0, 1, 5, 1, 5,
        GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        this.blankLableL.setBorder(new LineBorder(Color.BLACK));
        
        this.blankLableR = new JLabel();
        this.addWithConstraints(blankLableR,4,0,1,5,1,5,
        GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        this.blankLableR.setBorder(new LineBorder(Color.BLACK));
        //line0
        
        this.textLable =new JLabel("Fill your info here:");
        this.addWithConstraints(textLable, 1, 0, 3, 1, 3, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        //line1
        this.usernameLable= new JLabel("Username:");
        this.addWithConstraints(usernameLable, 1, 1, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);

        this.tfUsername=new JTextField();
        this.addWithConstraints(tfUsername, 2, 1, 2, 1, 2, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);
        //line2
        this.passwordLable=new JLabel("Password:");
        this.addWithConstraints(passwordLable, 1, 2, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);
        
        this.tfPassword = new JTextField();
        this.addWithConstraints(tfPassword, 2, 2, 2, 1, 2, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);

        //line3
        this.repeatpasswordLable=new JLabel("Repeat password:");
        this.addWithConstraints(repeatpasswordLable, 1, 3, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);

        this.tfRepeatPassword = new JTextField();
        this.addWithConstraints(tfRepeatPassword, 2, 3, 2, 1, 2, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);

        //line4
        this.cancleButton=new JButton("CANCEL");
        this.addWithConstraints(cancleButton, 1, 4, 1, 1, 1, 1,
        GridBagConstraints.NONE, GridBagConstraints.CENTER);
        this.cancleButton.addActionListener(this);

        this.signUpButton=new JButton("Sign up");
        this.addWithConstraints(signUpButton, 3, 4, 1, 1, 1, 1,
        GridBagConstraints.NONE, GridBagConstraints.CENTER);
        this.cancleButton.addActionListener(this);



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
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==this.cancleButton){
            System.out.println("Cancle triggered");
            this.dispose();
        }
        else if(e.getSource()==this.signUpButton){
            System.out.println("Sign Up triggered");
            if(this.tfUsername.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Username cannot be empty");
            }
            if(this.tfPassword.getText().equals("")||this.tfRepeatPassword.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Password cannot be empty");
            }
            if(this.tfPassword.getText().equals(this.tfRepeatPassword.getText())){
                JOptionPane.showMessageDialog(this,"Repeat password is incorret");
            }
            if(!this.tfUsername.getText().equals("")&&this.tfPassword.getText().equals(this.tfRepeatPassword.getText())){
                //SIGN UP here
                //throw username and password to controller.
            }
        }

    }
}