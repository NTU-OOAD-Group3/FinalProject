package org.hotelsystem.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import org.hotelsystem.model.User;
import org.hotelsystem.model.UserInfo;
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
    private final String[] sexOptions = {"Secret", "Male", "Female"};
    private JComboBox cBoxSex;
    private JTextField tfPhoneNumber;
    private JTextField tfAddress;
    // components in paymentPanel
    private JTextField tfCardOwner;
    private JTextField[] tfCardAccount = new JTextField[4];
    private final String[] monthOptions = {
        "--", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    private JComboBox cBoxCardValidMonth;
    private final String[] yearOptions = {
        "--", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    private JComboBox cBoxCardValidYear;
    // components in bottonPanel
    private JButton btnCancel;
    private JButton btnModify;
    private JButton btnSave;
    private Image img;
    // others
    private int userID;

    public AccountUI(JFrame parent, AccountControl accountControl) {
        this.parent = parent;
        this.accountControl = accountControl;
        initUI();
        this.setEntryEnabled(false);
    }

    private void initUI() {
        this.setLayout(new GridBagLayout());

        JPanel statusPanel = new JPanel(new GridBagLayout());
        statusPanel.setBorder(new TitledBorder("Account Status"));
        statusPanel.setOpaque(false);

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
        personPanel.setOpaque(false);

        JPanel cBoxSexPanel = new JPanel(new GridLayout());
        cBoxSexPanel.setBorder(new TitledBorder("Sex"));
        cBoxSexPanel.setOpaque(false);
        this.cBoxSex = new JComboBox(this.sexOptions);
        cBoxSexPanel.add(this.cBoxSex);
        personPanel.add(cBoxSexPanel);

        JPanel tfPhoneNumberPanel = new JPanel(new GridLayout());
        tfPhoneNumberPanel.setBorder(new TitledBorder("Phone Number"));
        tfPhoneNumberPanel.setOpaque(false);
        this.tfPhoneNumber = new JTextField();
        tfPhoneNumberPanel.add(this.tfPhoneNumber);
        personPanel.add(tfPhoneNumberPanel);


        JPanel tfAddressPanel = new JPanel(new GridLayout());
        tfAddressPanel.setBorder(new TitledBorder("Address"));
        tfAddressPanel.setOpaque(false);
        this.tfAddress = new JTextField();
        tfAddressPanel.add(this.tfAddress);
        personPanel.add(tfAddressPanel);

        JPanel paymentPanel = new JPanel(new GridLayout(3, 1));
        paymentPanel.setBorder(new TitledBorder("Payment Information"));
        paymentPanel.setOpaque(false);

        JPanel tfCardOwnerPanel = new JPanel(new GridLayout());
        tfCardOwnerPanel.setBorder(new TitledBorder("Credit Card Owner"));
        tfCardOwnerPanel.setOpaque(false);
        this.tfCardOwner = new JTextField();
        tfCardOwnerPanel.add(this.tfCardOwner);
        paymentPanel.add(tfCardOwnerPanel);

        JPanel tfCardAccountPanel = new JPanel(new GridLayout(1, 4));
        tfCardAccountPanel.setBorder(new TitledBorder("Credit Card Account"));
        tfCardAccountPanel.setOpaque(false);
        for ( int i=0; i<this.tfCardAccount.length; ++i ) {
            this.tfCardAccount[i] = new JTextField();
            tfCardAccountPanel.add(this.tfCardAccount[i]);
        }
        paymentPanel.add(tfCardAccountPanel);
        
        JPanel tfCardValidTimePanel = new JPanel(new GridLayout(1, 2));
        tfCardValidTimePanel.setBorder(new TitledBorder("Credit Card Valid Through"));
        tfCardValidTimePanel.setOpaque(false);
        this.cBoxCardValidMonth = new JComboBox(this.monthOptions);
        tfCardValidTimePanel.add(this.cBoxCardValidMonth);
        this.cBoxCardValidYear = new JComboBox(this.yearOptions);
        tfCardValidTimePanel.add(this.cBoxCardValidYear);
        paymentPanel.add(tfCardValidTimePanel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        this.btnCancel = new JButton("Cancel");
        this.btnCancel.addActionListener(this);
        buttonPanel.add(this.btnCancel);
        this.btnModify = new JButton("Modify");
        this.btnModify.addActionListener(this);
        buttonPanel.add(this.btnModify);
        this.btnSave = new JButton(" Save ");
        this.btnSave.addActionListener(this);
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

    public void paintComponent(Graphics g) {
        this.img = this.accountControl.getBackGroundImage();
        g.drawImage(this.img, 0, 0, null);
    }

    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == this.btnChangePassword ) {
            System.out.println("change password triggered");

        } else if ( e.getSource() == this.btnLogout ) {
            System.out.println("logout triggered");
            this.accountControl.triggerLogout();
        } else if ( e.getSource() == this.btnCancel ) {
            System.out.println("cancel triggered");
            this.accountControl.triggerCancel();
        } else if ( e.getSource() == this.btnModify ) {
            System.out.println("modify triggered");
            this.accountControl.triggerModify();
        } else if ( e.getSource() == this.btnSave ) {
            System.out.println("save triggered");
            this.accountControl.triggerSave();
        }
    }

    public void setUserDisplay(User user) {
        this.labelUsername.setText("Hello, " + user.getUsername() + "!");
        if ( user.getUserType() == 0 ) {
            this.labelUserType.setText("Account Type: Hoster");
        } else if ( user.getUserType() == 1 ) {
            this.labelUserType.setText("Account Type: Normal User");
        } else {
            this.labelUserType.setText("Account Type: Who r u?!");
        }
    }

    public void setUserInfoDisplay(UserInfo userInfo) {
        this.userID = userInfo.getUserID();
        this.cBoxSex.setSelectedIndex(userInfo.getSex());
        this.tfPhoneNumber.setText(userInfo.getPhoneNumber());
        this.tfAddress.setText(userInfo.getAddress());
        this.tfCardOwner.setText(userInfo.getCardOwner());
        if ( userInfo.getCardAccount().length() == 16 ) {
            for ( int i=0; i<this.tfCardAccount.length; ++i ) {
                this.tfCardAccount[i].setText(userInfo.getCardAccount().substring(4 * i, 4 * (i + 1)));
            }
        }
        if ( userInfo.getCardValidTime().length() == 5 ) {
            int monthIndex = Integer.valueOf(userInfo.getCardValidTime().substring(0, 2));
            this.cBoxCardValidMonth.setSelectedIndex(monthIndex);
            int yearIndex = Integer.valueOf(userInfo.getCardValidTime().substring(3, 5)) - 19;
            this.cBoxCardValidYear.setSelectedIndex(yearIndex);
        }
    }

    public UserInfo getUserInfoFromEntry() {
        int sex = this.cBoxSex.getSelectedIndex();
        String cardAccount = "";
        for ( int i=0; i<this.tfCardAccount.length; ++i ) {
            cardAccount += this.tfCardAccount[i].getText();
        }
        String month = this.cBoxCardValidMonth.getSelectedItem().toString();
        String year = this.cBoxCardValidYear.getSelectedItem().toString();
        String cardValidTime = month + "/" + year;
        UserInfo userInfo = new UserInfo(this.userID, sex,
            this.tfPhoneNumber.getText(), this.tfAddress.getText(),
            this.tfCardOwner.getText(), cardAccount, cardValidTime);
        return userInfo;
    }

    public void createMessageDialog(String content, String title, String type) {
        int messageType = 0;
        if ( type.compareTo("INFORMATION_MESSAGE") == 0 ) {
            messageType = JOptionPane.INFORMATION_MESSAGE;
        } else if ( type.compareTo("ERROR_MESSAGE") == 0 ) {
            messageType = JOptionPane.ERROR_MESSAGE;
        }
        JOptionPane.showMessageDialog(null, content, title, messageType);
    }

    public void setEntryEnabled(boolean b) {
        this.cBoxSex.setEnabled(b);
        this.tfPhoneNumber.setEnabled(b);
        this.tfAddress.setEnabled(b);
        this.tfCardOwner.setEnabled(b);
        for ( int i=0; i<this.tfCardAccount.length; ++i ) {
            this.tfCardAccount[i].setEnabled(b);
        }
        this.cBoxCardValidMonth.setEnabled(b);
        this.cBoxCardValidYear.setEnabled(b);
        
        this.btnCancel.setEnabled(b);
        this.btnModify.setEnabled(!b);
        this.btnSave.setEnabled(b);
    }
}