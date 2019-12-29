package org.hotelsystem.view;

import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;
import javax.swing.border.*;

public class AccountUI extends JPanel implements ActionListener{
    private MainFrame parent;
    private JLabel nameLabel;
    private JLabel iconLabel;
    private JLabel descriptionLabel;

    private JButton changePasswordButton;
    private JButton uploadIconButton;
    private JButton logoutButton;

    public AccountUI(MainFrame parent){
        this.parent =parent;
        iniUI();
    }
    private void iniUI(){
        this.setLayout(new GridBagLayout());

        this.nameLabel = new JLabel("GUEST");
        this.addWithConstraints(nameLabel, 0, 0, 1, 1, 1, 1,
         GridBagConstraints.NONE, GridBagConstraints.SOUTH);
        this.logoutButton=new JButton("Log Out");
        this.addWithConstraints(logoutButton, 0, 1, 1, 1, 1, 1,
         GridBagConstraints.NONE, GridBagConstraints.NORTH);
        this.logoutButton.addActionListener(this);


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
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.logoutButton){
            System.out.println("log out triggered");
            //parent.remove(2);

            this.parent.logoutChange();
            
        }
    }
}