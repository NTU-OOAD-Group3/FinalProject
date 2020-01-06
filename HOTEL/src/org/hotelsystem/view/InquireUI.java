package org.hotelsystem.view;
import org.hotelsystem.model.Order;
import org.hotelsystem.model.User;
import org.hotelsystem.control.InquireControl;
import org.hotelsystem.model.Review;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.*;
import java.text.*;


public class InquireUI extends JPanel implements ActionListener{
  private InquireControl inquireControl;
  private JFrame parent;

  private JPanel userInfo;
  private JPanel searchOrder;
  private JPanel showGeneralOrder;
  private JButton searchOrderButton;
  private JTextField searchOrderTextField;
  private JLabel userNameLabel;
  private JLabel orderSumLabel;
  private JLabel orderQuickView;
  private InquireReviewDialog inquireReviewDialog;

  
  private User user;
  private ArrayList<Order> orders;
  private User emptyUser;
  private ArrayList<Order> emptyOrders = new ArrayList<Order>();
  
  private Image img;

  
  public InquireUI(JFrame parent, InquireControl inquireControl) {
    this.setLayout(new GridBagLayout());
    this.inquireControl = inquireControl;
    this.parent = parent;
    //mock Data
    ArrayList<Order> orders = new ArrayList<Order>();
    User user = new User(0,0,"Unknown","");
    this.emptyUser = user;
    this.emptyOrders = orders;
    this.user = this.emptyUser;
    this.orders = this.emptyOrders;
    initUI();
  }

  public void refreshUI(ArrayList<Order> orders, User user) {
    if (user!=null){
      this.orders = orders;
      this.user = user;
      this.searchOrderTextField.setText("");
      this.searchOrderTextField.setEditable(true);
      this.searchOrderButton.setEnabled(true);
    }
    else{
      this.orders = this.emptyOrders;
      this.user = this.emptyUser;
      this.searchOrderTextField.setText("Please login!");
      this.searchOrderTextField.setEditable(false);
      this.searchOrderButton.setEnabled(false);
    }
    this.userNameLabel.setText(" User Name: " + this.user.getUsername());
    this.orderSumLabel.setText(" Total Order: " + this.orders.size());
    this.orderQuickView.setText(" Order ID: " + orderIDsToString());
    this.remove(this.showGeneralOrder);
    this.showGeneralOrder = new InquireOrders(this.orders, this.inquireControl, this.parent);
    this.showGeneralOrder.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
    this.showGeneralOrder.setOpaque(false);
    this.addWithConstraints(this.showGeneralOrder, 1, 0, 4, 5, 30, 2,
        GridBagConstraints.BOTH, GridBagConstraints.CENTER);    
  }
    
  public void initUI() {
    
    LineBorder line = new LineBorder(Color.BLACK);
    EmptyBorder empty = new EmptyBorder(10, 10, 10, 10);

    //Search Order Part
    this.searchOrder = new JPanel();
    this.searchOrder.setBorder(new CompoundBorder(line, new EmptyBorder(10, 20, 10, 20)));
    this.searchOrder.setOpaque(false);
    this.searchOrder.setLayout(new GridBagLayout());
    this.addWithConstraints(this.searchOrder, 0, 0, 1, 2, 2, 1,
        GridBagConstraints.BOTH, GridBagConstraints.CENTER);

    JLabel searchOrderLabel = new JLabel("PLease input order ID! ");
    searchOrderLabel.setHorizontalAlignment(JLabel.CENTER);
    this.addWithConstraints(this.searchOrder, searchOrderLabel, 0, 0, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);
        
    this.searchOrderTextField = new JTextField();
    this.searchOrderTextField.setHorizontalAlignment(JTextField.CENTER);
    this.searchOrderTextField.setText("Please login!");
    this.searchOrderTextField.setEditable(false);
    addWithConstraints(this.searchOrder, this.searchOrderTextField, 0, 1, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

    this.searchOrderButton = new JButton("Send query!");
    this.searchOrderButton.setEnabled(false);
    this.searchOrderButton.addActionListener(this);
    this.addWithConstraints(this.searchOrder, this.searchOrderButton, 0, 2, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);

    //User Overview Part
    this.userInfo = new JPanel();
    this.userInfo.setBorder(new CompoundBorder(line, empty));
    this.userInfo.setOpaque(false);
    this.userInfo.setLayout(new GridBagLayout());
    this.addWithConstraints(this.userInfo, 0, 2, 1, 3, 2, 2,
        GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        
    JLabel userOverview = new JLabel("User Overview");
    userOverview.setHorizontalAlignment(JLabel.CENTER);
    this.addWithConstraints(this.userInfo, userOverview, 0, 0, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

    this.userNameLabel = new JLabel(" User Name: " + this.user.getUsername());
    this.addWithConstraints(this.userInfo, this.userNameLabel, 0, 1, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);
        
    this.orderSumLabel = new JLabel(" Total Order: " + this.orders.size());
    this.addWithConstraints(this.userInfo, this.orderSumLabel, 0, 2, 1, 1, 1, 1,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);

    this.orderQuickView = new JLabel(" Order ID: " + orderIDsToString());
    this.addWithConstraints(this.userInfo, this.orderQuickView, 0, 3, 1, 1, 1, 2,
        GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);

    this.showGeneralOrder = new InquireOrders(this.orders, this.inquireControl, this.parent);
    this.showGeneralOrder.setBorder(new CompoundBorder(line, empty));
    this.showGeneralOrder.setOpaque(false);
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

  private String intArrToString(ArrayList<Integer> intArr){
    String buf = "";
    for (int i = 0;i<intArr.size();i++){
      buf = buf + intArr.get(i);
      if (i != intArr.size()-1) buf = buf + ",";
    }
    return buf;
  }

  public String dateToString(int date){
    String tmp = "";    
    tmp += String.valueOf(date/10000);
    tmp +="-";
    if ((date%10000)/100<10) tmp+="0";
    tmp += String.valueOf((date%10000)/100);
    tmp +="-";
    if (date%100<10) tmp+="0";
    tmp += String.valueOf(date%100);
    return tmp;
  }

  public String showOrder(Order order){
    String showMessage = "";
    showMessage += "Order ID: " + order.getOrderID() + "\n";
    showMessage += "Hotel name: " + order.getHotelID() + "\n";
    showMessage += "Checkin/Checkout: " + order.getCheckinTime() + "~" + order.getCheckoutTime() + "\n";
    showMessage += "Rooms: " + intArrToString(order.getRoomIDs()) + "\n";
    showMessage += "Price: " + order.getPrice() + "\n" + "\n";
    return showMessage;
  }

  public void paintComponent(Graphics g) {
    this.img = this.inquireControl.getBackGroundImage();
    g.drawImage(this.img, 0, 0, null);
  }

  public void actionPerformed(ActionEvent e){  
    if( e.getSource() == this.searchOrderButton){
        try{
          int orderID = Integer.valueOf(this.searchOrderTextField.getText());
          for (int i=0;i<this.orders.size();i++){
            if (this.orders.get(i).getOrderID() == orderID) {
              DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
              if (java.sql.Date.valueOf(dateToString(this.orders.get(i).getCheckinTime())).after(java.sql.Date.valueOf(df.format(new Date())))){
                int modify =  JOptionPane.showConfirmDialog(this, showOrder(orders.get(i)) + "Do you want to modify order?", "Order " + orderID, JOptionPane.YES_NO_OPTION);
                if (modify == JOptionPane.YES_OPTION) {
                  this.inquireControl.switchToModify(this.orders.get(i));
                  System.out.println("Modify!!!");
                }
              }
              else{
                int review =  JOptionPane.showConfirmDialog(this, showOrder(orders.get(i)) + "Do you want to leave a review?", "Order " + orderID, JOptionPane.YES_NO_OPTION);
                if (review == JOptionPane.YES_OPTION) {
                  this.inquireReviewDialog = new InquireReviewDialog(this.parent, this.inquireControl, this.orders.get(i), this.inquireControl.getReview(this.orders.get(i)));
                  this.inquireReviewDialog.setLocationRelativeTo(this);
                  this.inquireReviewDialog.setVisible(true);
                }
              }
              return;
            }
          }
          JOptionPane.showMessageDialog(this, "No such order!", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (NumberFormatException f){
          JOptionPane.showMessageDialog(this, "Input is not a number!", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
  }  
	
}