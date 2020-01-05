package org.hotelsystem.view;

import org.hotelsystem.control.LoginControl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JRadioButton;
public class SignupDialog extends JDialog implements ActionListener{



    private JLabel blankLableL;
    private JLabel blankLableR;
    private JLabel usernameLable;//username:
    private JLabel passwordLable;//password:
    private JLabel repeatpasswordLable;//repeatpassword:
    private JLabel textLable;//add instruction
    private JTextField tfUsername;/*get username input */
    private JPasswordField tfPassword; //get password input
    private JPasswordField tfRepeatPassword;//get repeatpassword
    private JButton cancleButton; //cancle this dialog
    private JButton signUpButton; //signup
    private LoginControl loginControl;
    private JRadioButton isHost;
    private int userType;

    private MainFrame parent;

    public SignupDialog(MainFrame parent, String name,LoginControl lc){
        super(parent, name,true);
        loginControl = lc;
        init();
    }
    private void init() {
        //DRAW GUI 
        
        //signupPanel = new JPanel(new GridLayout(5,4));
        //signupPanel.setPreferredSize(new Dimension(400,500));
        //bottomPanel = new JPanel(new GridLayout(1,3));
        this.setLayout(new GridBagLayout());
        this.setSize(new Dimension(400,500));

        this.blankLableL = new JLabel();
        this.addWithConstraints(blankLableL, 0, 0, 1, 5, 1, 5,
        GridBagConstraints.BOTH, GridBagConstraints.CENTER);
       // this.blankLableL.setBorder(new LineBorder(Color.BLACK));
        
        this.blankLableR = new JLabel();
        this.addWithConstraints(blankLableR,4,0,1,5,1,5,
        GridBagConstraints.BOTH, GridBagConstraints.CENTER);
       // this.blankLableR.setBorder(new LineBorder(Color.BLACK));
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
        
        this.tfPassword = new JPasswordField();
        this.addWithConstraints(tfPassword, 2, 2, 2, 1, 2, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);

        //line3
        this.repeatpasswordLable=new JLabel("Repeat password:");
        this.addWithConstraints(repeatpasswordLable, 1, 3, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);

        this.tfRepeatPassword = new JPasswordField();
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
        this.signUpButton.addActionListener(this);

        //line5
        this.isHost = new JRadioButton("Are you a Host?",false);
        this.addWithConstraints(isHost, 1, 5, 1, 1, 1, 1,
        GridBagConstraints.NONE, GridBagConstraints.CENTER);


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
           // System.out.println("test"+tfUsername.getText()+String.valueOf(tfPassword.getPassword()));
            JOptionPane.showMessageDialog(this,"Username cannot be empty");
            this.dispose();
        }
        else if(e.getSource()==this.signUpButton){
            System.out.println("Sign Up triggered"+tfUsername.getText()+String.valueOf(tfPassword.getPassword()));
            if(this.tfUsername.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Username cannot be empty");
            }
            else if(String.valueOf(this.tfPassword.getPassword()).equals("")||String.valueOf(this.tfRepeatPassword.getPassword()).equals("")){
                JOptionPane.showMessageDialog(this,"Password cannot be empty");
            }
            else if(!String.valueOf(this.tfPassword.getPassword()).equals(String.valueOf(this.tfRepeatPassword.getPassword()))){
                JOptionPane.showMessageDialog(this,"Repeat password is incorret");
            }
            else if(!this.tfUsername.getText().equals("")&&String.valueOf(this.tfPassword.getPassword()).equals(String.valueOf(this.tfRepeatPassword.getPassword()))){
                //SIGN UP here
                //throw username and password to controller.
                System.out.println("Signup input is correct");
                if(isHost.isSelected()){
                    userType=0;
                }
                else{
                    userType=1;
                }
                boolean B= false;
                try{
                
                System.out.println(this.tfUsername.getText());
                System.out.println(String.valueOf(this.tfPassword.getPassword()));
                System.out.println(userType);
                B = loginControl.verifySignup(tfUsername.getText(),String.valueOf(this.tfPassword.getPassword()),userType);
                }catch (Exception e1) {
                    System.out.println(e1);
                    
                }
                
                
                if(B){ 
                    JOptionPane.showMessageDialog(this,"Sign Up Successfully,please log in");
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(this,"Sign Up failed,this username may be used");
                }
            }
        }

    }
}