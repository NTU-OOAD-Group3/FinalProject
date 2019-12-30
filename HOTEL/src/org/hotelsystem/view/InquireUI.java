package org.hotelsystem.view;
import org.hotelsystem.model.Order;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InquireUI extends JPanel implements ActionListener{
  private JPanel userInfo;
  private JPanel searchOrder;
  private JPanel showGeneralOrder;
  private JButton searchOrderButton;
  private JTextField searchOrderTextField;
  private MainFrame mainFrame;

  private String userName;
  private ArrayList<Order> orders = new ArrayList<Order>();

  
  public InquireUI(MainFrame mainFrame) {
    this.setLayout(new GridBagLayout());
    this.mainFrame = mainFrame;
    initUI();
  }
    
  private void initUI() {
    //mock Data
    for (int i=0;i<10;i++){
      ArrayList<Integer> a= new ArrayList<Integer>();
      a.add(12);
      a.add(15);
      orders.add(new Order(i, 0, 0, a, 1250, 1350, 666*i));
    }
    this.userName = "Default";

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
        
    this.searchOrderTextField = new JTextField();
    addWithConstraints(this.searchOrder, this.searchOrderTextField, 0, 1, 1, 1, 1, 1,
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
        
    JLabel orderSumLabel = new JLabel(" Total Order: " + this.orders.size());
    this.addWithConstraints(this.userInfo, orderSumLabel, 0, 2, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);

    JLabel orderQuickView = new JLabel(" Order ID: " + orderIDsToString());
    this.addWithConstraints(this.userInfo, orderQuickView, 0, 3, 1, 1, 1, 2,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);
        

    
    
    this.showGeneralOrder = new InquireOrders(this.orders);
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
    for (int i =0; i<this.orders.size();i++){
      buf = buf + this.orders.get(i).getOrderID();
      if (i!=this.orders.size()-1) buf = buf + ", "; 
    }
    return buf;
  }

  private String intToString(ArrayList<Integer> intArr){
    String buf = "";
    for (int i = 0;i<intArr.size();i++){
      buf = buf + intArr.get(i);
      if (i != intArr.size()-1) buf = buf + ",";
    }
    return buf;
  }

  public void actionPerformed(ActionEvent e){  
    if( e.getSource() == this.searchOrderButton){
        try{
          int orderID = Integer.valueOf(this.searchOrderTextField.getText());
          for (int i=0;i<this.orders.size();i++){
            if (this.orders.get(i).getOrderID() == orderID) {
              String showMessage = "";
              showMessage += "Order ID: " + orderID + "\n";
              showMessage += "Hotel name: " + orders.get(orderID).getHotelID() + "\n";
              showMessage += "Checkin/Checkout: " + orders.get(orderID).getCheckinTime() + "~" + orders.get(orderID).getCheckoutTime() + "\n";
              showMessage += "Rooms: " + intToString(orders.get(orderID).getRoomIDs()) + "\n";
              showMessage += "Price: " + orders.get(orderID).getPrice() + "\n" + "\n";
              showMessage += "Do you want to modify order?";

              int modify =  JOptionPane.showConfirmDialog(this, showMessage, "Order ID: " + orderID, JOptionPane.YES_NO_OPTION);
              if (modify == JOptionPane.YES_OPTION) {
                this.mainFrame.switchPanal(3);
              } else if (modify == JOptionPane.NO_OPTION) {
                System.out.println("Cancel");
              }
              return;
            }
          }
          JOptionPane.showMessageDialog(this, "No such order!", "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (NumberFormatException f){
          JOptionPane.showMessageDialog(this, "Input is not a number!", "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
  }  
	
}