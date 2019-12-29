package org.hotelsystem.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class InquireUI extends JPanel implements ActionListener{
  private JPanel userInfo;
  private JPanel searchOrder;
  private JPanel showGeneralOrder;
  private JButton searchOrderButton;

  private String userName;
  private int orderSum;
  private int[] orderIDs;
  
  public InquireUI() {
    this.setLayout(new GridBagLayout());
    initUI();
  }
    
  private void initUI() {
    this.userName = "Default";
    this.orderSum = 10;
    int[] buf = {1,2,3,4,5};
    this.orderIDs = buf;

    LineBorder line = new LineBorder(Color.BLACK);
    EmptyBorder empty = new EmptyBorder(10, 10, 10, 10);

    //Search Order Part
    this.searchOrder = new JPanel();
    this.searchOrder.setBorder(new CompoundBorder(line, new EmptyBorder(10, 20, 10, 20)));
    this.searchOrder.setLayout(new GridBagLayout());
    this.addWithConstraints(this.searchOrder, 0, 0, 1, 2, 2, 1,
        GridBagConstraints.BOTH, GridBagConstraints.CENTER);

    JLabel searchOrderLabel = new JLabel("PLease input order ID! ");
    searchOrderLabel.setHorizontalAlignment(JLabel.CENTER);
    this.addWithConstraints(this.searchOrder, searchOrderLabel, 0, 0, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);
        
    JTextField searchOrderTextField = new JTextField();
    addWithConstraints(this.searchOrder, searchOrderTextField, 0, 1, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

    this.searchOrderButton = new JButton("Send query!");
    this.searchOrderButton.addActionListener(this);
    this.addWithConstraints(this.searchOrder, this.searchOrderButton, 0, 2, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);

    //User Overview Part
    this.userInfo = new JPanel();
    this.userInfo.setBorder(new CompoundBorder(line, empty));
    this.userInfo.setLayout(new GridBagLayout());
    this.addWithConstraints(this.userInfo, 0, 2, 1, 3, 2, 2,
        GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        
    JLabel userOverview = new JLabel("User Overview");
    userOverview.setHorizontalAlignment(JLabel.CENTER);
    this.addWithConstraints(this.userInfo, userOverview, 0, 0, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

    JLabel userNameLabel = new JLabel(" User Name: " + userName);
    this.addWithConstraints(this.userInfo, userNameLabel, 0, 1, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);
        
    JLabel orderSumLabel = new JLabel(" Total Order: " + orderSum);
    this.addWithConstraints(this.userInfo, orderSumLabel, 0, 2, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);

    JLabel orderQuickView = new JLabel(" Order ID: " + orderIDsToString());
    this.addWithConstraints(this.userInfo, orderQuickView, 0, 3, 1, 1, 1, 2,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);
        

    
    
    this.showGeneralOrder = new InquireOrders();
    this.showGeneralOrder.setBorder(new CompoundBorder(line, empty));
    this.addWithConstraints(this.showGeneralOrder, 1, 0, 4, 5, 30, 2,
        GridBagConstraints.BOTH, GridBagConstraints.CENTER);
    
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
		gbc.insets = new Insets(3, 3, 3, 3);
		this.add(c, gbc);
  }

  private void addWithConstraints(JPanel p, JComponent c,
        int gridx, int gridy, int gridwidth, int gridheight,
        int weightx, int weighty, int fill, int anchor) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.fill = fill;
		gbc.anchor = anchor;
		// gbc.insets = new Insets(5, 5, 5, 5);
		p.add(c, gbc);
  }

  private String orderIDsToString(){
    String buf = "";
    for (int i =0; i<this.orderIDs.length;i++){
      buf = buf + this.orderIDs[i];
      if (i!=this.orderIDs.length-1) buf = buf + ", "; 
    }
    return buf;
  }

  public void actionPerformed(ActionEvent e){  
    if( e.getSource() == this.searchOrderButton){
        System.out.println("Search triggered.");
    }
  }  
	
}