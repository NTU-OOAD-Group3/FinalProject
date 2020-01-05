package org.hotelsystem.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChangePasswordDialog extends JDialog implements ActionListener {
    private JFrame parent;
    private JLabel labelOriginPW, labelNewPW, labelNewPWAgain;
    private JPasswordField pfOriginPW, pfNewPW, pfNewPWAgain;
    private JButton btnCancel, btnSubmit;
    private String originPW, newPW, newPWAgain;
    private boolean changeStatus = false;

    public ChangePasswordDialog(JFrame parent) {
        super(parent, "Change Password Dialog", true);
        this.parent = parent;
        this.initUI();
    }

    private void initUI() {
        this.setLayout(new GridLayout(4, 2));

        this.labelOriginPW = new JLabel("Origin Password: ", JLabel.RIGHT);
        this.add(this.labelOriginPW);

        this.pfOriginPW = new JPasswordField();
        this.add(this.pfOriginPW);

        this.labelNewPW = new JLabel("New Password: ", JLabel.RIGHT);
        this.add(this.labelNewPW);

        this.pfNewPW = new JPasswordField();
        this.add(this.pfNewPW);

        this.labelNewPWAgain = new JLabel("New Password AGAIN: ", JLabel.RIGHT);
        this.add(this.labelNewPWAgain);

        this.pfNewPWAgain = new JPasswordField();
        this.add(this.pfNewPWAgain);

        this.btnCancel = new JButton("Cancel");
        this.btnCancel.addActionListener(this);
        this.add(this.btnCancel);

        this.btnSubmit = new JButton("Submit");
        this.btnSubmit.addActionListener(this);
        this.add(this.btnSubmit);

        this.setSize(600, 200);
    }

    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == this.btnCancel ) {
            dispose();
        } else if ( e.getSource() == this.btnSubmit ) {
            this.originPW = new String(this.pfOriginPW.getPassword());
            this.newPW = new String(this.pfNewPW.getPassword());
            this.newPWAgain = new String(this.pfNewPWAgain.getPassword());
            if ( this.newPW.isEmpty() ) {
                JOptionPane.showMessageDialog(this, "Please fill something in New Password!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            } else if ( this.newPW.compareTo(this.newPWAgain) != 0 ) {
                JOptionPane.showMessageDialog(this, "New Passwords are not match.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                this.changeStatus = true;
                dispose();
            }
        }
    }

    public boolean getChangeStatus() { return this.changeStatus; }
    public String getOriginPassword() { return this.originPW; }
    public String getNewPassword() { return this.newPW; }
}