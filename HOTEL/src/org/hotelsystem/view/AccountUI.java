package org.hotelsystem.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import org.hotelsystem.control.AccountControl;

public class AccountUI extends JPanel implements ActionListener {
    private JFrame parent;
    private AccountControl accountControl;
    // components in statusPanel
    private JLabel labelUsername;
    private JLabel labelUserType;
    private JButton btnChangePassword;
    private JButton btnLogout;
    // components in personPanel
    private final String[] sexOptions = {"Male", "Female", "Secret"};
    private JComboBox cBoxSex;
    private JTextField tfPhoneNumber;
    private JTextField tfAddress;
    // components in paymentPanel
    private JTextField tfCardOwner;
    private JTextField[] tfCardAccount = new JTextField[4];
    private final String[] monthOptions = {
        "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    private JComboBox cBoxCardValidMonth;
    private final String[] yearOptions = {
        "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    private JComboBox cBoxCardValidYear;
    // components in bottonPanel
    private JButton btnCancel;
    private JButton btnModify;
    private JButton btnSave;

    public AccountUI(JFrame parent, AccountControl accountControl) {
        this.parent = parent;
        this.accountControl = accountControl;
        initUI();
    }

    private void initUI() {
        this.setLayout(new GridBagLayout());

        JPanel statusPanel = new JPanel(new GridBagLayout());
        statusPanel.setBorder(new TitledBorder("Account Status"));

        this.labelUsername = new JLabel("Hello, Sekiro!");
        this.addWithConstraints(statusPanel, this.labelUsername, 0, 0, 5, 1, 5, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER,
            new Insets(10, 10, 0, 10));

        this.btnChangePassword = new JButton("Change Password");
        this.btnChangePassword.addActionListener(this);
        this.addWithConstraints(statusPanel, this.btnChangePassword, 5, 0, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER,
            new Insets(10, 0, 0, 10));

        this.labelUserType = new JLabel("Account Type: Normal User");
        // this.labelUserType.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.addWithConstraints(statusPanel, this.labelUserType, 0, 1, 5, 1, 5, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER,
            new Insets(0, 10, 10, 10));

        this.btnLogout = new JButton("Logout");
        this.btnLogout.addActionListener(this);
        this.addWithConstraints(statusPanel, this.btnLogout, 5, 1, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER,
            new Insets(0, 0, 10, 10));

        JPanel personPanel = new JPanel(new GridLayout(3, 1));
        personPanel.setBorder(new TitledBorder("Personal Information"));
        
        JPanel cBoxSexPanel = new JPanel(new GridLayout());
        cBoxSexPanel.setBorder(new TitledBorder("Sex"));
        this.cBoxSex = new JComboBox(this.sexOptions);
        cBoxSexPanel.add(this.cBoxSex);
        personPanel.add(cBoxSexPanel);

        JPanel tfPhoneNumberPanel = new JPanel(new GridLayout());
        tfPhoneNumberPanel.setBorder(new TitledBorder("Phone Number"));
        this.tfPhoneNumber = new JTextField();
        tfPhoneNumberPanel.add(this.tfPhoneNumber);
        personPanel.add(tfPhoneNumberPanel);

        JPanel tfAddressPanel = new JPanel(new GridLayout());
        tfAddressPanel.setBorder(new TitledBorder("Address"));
        this.tfAddress = new JTextField();
        tfAddressPanel.add(this.tfAddress);
        personPanel.add(tfAddressPanel);

        JPanel paymentPanel = new JPanel(new GridLayout(3, 1));
        paymentPanel.setBorder(new TitledBorder("Payment Information"));

        JPanel tfCardOwnerPanel = new JPanel(new GridLayout());
        tfCardOwnerPanel.setBorder(new TitledBorder("Credit Card Owner"));
        this.tfCardOwner = new JTextField();
        tfCardOwnerPanel.add(this.tfCardOwner);
        paymentPanel.add(tfCardOwnerPanel);

        JPanel tfCardAccountPanel = new JPanel(new GridLayout(1, 4));
        tfCardAccountPanel.setBorder(new TitledBorder("Credit Card Account"));
        for ( int i=0; i<this.tfCardAccount.length; ++i ) {
            this.tfCardAccount[i] = new JTextField();
            tfCardAccountPanel.add(this.tfCardAccount[i]);
        }
        paymentPanel.add(tfCardAccountPanel);
        
        JPanel tfCardValidTimePanel = new JPanel(new GridLayout(1, 2));
        tfCardValidTimePanel.setBorder(new TitledBorder("Credit Card Valid Through"));
        this.cBoxCardValidMonth = new JComboBox(this.monthOptions);
        tfCardValidTimePanel.add(this.cBoxCardValidMonth);
        this.cBoxCardValidYear = new JComboBox(this.yearOptions);
        tfCardValidTimePanel.add(this.cBoxCardValidYear);
        paymentPanel.add(tfCardValidTimePanel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        this.btnCancel = new JButton("Cancel");
        buttonPanel.add(this.btnCancel);
        this.btnModify = new JButton("Modify");
        buttonPanel.add(this.btnModify);
        this.btnSave = new JButton(" Save ");
        buttonPanel.add(this.btnSave);

        this.addWithConstraints(this, statusPanel, 0, 0, 2, 2, 3, 2,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH,
            new Insets(0, 10, 5, 10));
        this.addWithConstraints(this, personPanel, 0, 2, 1, 4, 1, 4,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER,
            new Insets(5, 10, 0, 5));
        this.addWithConstraints(this, paymentPanel, 1, 2, 1, 4, 1, 4,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER,
            new Insets(5, 5, 0, 10));
        this.addWithConstraints(this, buttonPanel, 0, 6, 2, 1, 2, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER,
            new Insets(0, 0, 0, 0));
    }

    private void addWithConstraints(JPanel p, JComponent c, int gridx, int gridy,
        int gridwidth, int gridheight, int weightx, int weighty,
        int fill, int anchor, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = fill;
        gbc.anchor = anchor;
        gbc.insets = insets;
        p.add(c, gbc);
    }

    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == this.btnChangePassword ) {
            System.out.println("change password triggered");

        } else if ( e.getSource() == this.btnLogout ) {
            System.out.println("logout triggered");
            
        } else if ( e.getSource() == this.btnCancel ) {
            System.out.println("cancel triggered");

        } else if ( e.getSource() == this.btnModify ) {
            System.out.println("modify triggered");

        } else if ( e.getSource() == this.btnSave ) {
            System.out.println("save triggered");

        }
    }
}