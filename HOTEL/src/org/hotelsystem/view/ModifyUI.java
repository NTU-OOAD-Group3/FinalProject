package org.hotelsystem.view;
import org.hotelsystem.model.Order;
import org.hotelsystem.control.ModifyControl;
import org.hotelsystem.model.User;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.*;
import java.text.*;



public class ModifyUI extends JPanel implements ActionListener{
    private ModifyControl modifyControl;
    private int haveSetOrderID;
    private User user;

    private JPanel orderIDbar;
	private JPanel originalOrderbar;
    private JPanel modifiedOrderbar;
    private JTextField tfOrderID;
    private JButton btnQueryOrder;
    private JButton btnCheckAndSend;

    private JTextField tfOriHotelName;
    private JTextField tfOriCheckInTime;
    private JTextField tfOriCheckOutTime;
    private JTextField tfOriSingleNum;
    private JTextField tfOriDoubleNum;
    private JTextField tfOriQuadNum;
    private JTextField tfOriPrice;

    private JTextField tfModHotelName;
    private JTextField tfModCheckInTime;
    private JTextField tfModCheckOutTime;
    private JTextField tfModSingleNum;
    private JTextField tfModDoubleNum;
    private JTextField tfModQuadNum;

	
	public ModifyUI(ModifyControl modifyControl) {
        this.setLayout(new GridBagLayout());
        this.modifyControl = modifyControl;
		initUI();
	}
	
	private void initUI() {
        this.orderIDbar = new JPanel();
        this.orderIDbar.setLayout(new GridBagLayout());
		this.addWithConstraints(this, this.orderIDbar, 0, 0, 7, 2, 7, 2,
				GridBagConstraints.BOTH, GridBagConstraints.WEST, 0, 20, 20, 20);

        JLabel lbOrderID = new JLabel("Order ID");
        lbOrderID.setFont(new Font("Serif", Font.BOLD, 18));
        lbOrderID.setHorizontalAlignment(JLabel.CENTER);
        this.addWithConstraints(this.orderIDbar, lbOrderID, 0, 0, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH, 10, 10, 0, 0);
        
        this.tfOrderID = new JTextField(20);
        this.tfOrderID.setHorizontalAlignment(JTextField.CENTER);
        this.addWithConstraints(this.orderIDbar, this.tfOrderID, 0, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 10, 10, 0, 0);

        this.btnQueryOrder = new JButton("Query");
        this.btnQueryOrder.addActionListener(this);
        this.addWithConstraints(this.orderIDbar, this.btnQueryOrder, 0, 2, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST, 0, 0, 1000, 50);

        JLabel lbOriginalOrder = new JLabel("Original order");
        lbOriginalOrder.setFont(new Font("Serif", Font.BOLD, 18));
        lbOriginalOrder.setHorizontalAlignment(JLabel.CENTER);
        this.addWithConstraints(this, lbOriginalOrder, 0, 0, 7, 2, 7, 2,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        this.originalOrderbar = new JPanel();
        this.originalOrderbar.setLayout(new GridBagLayout());
		this.addWithConstraints(this, this.originalOrderbar, 0, 2, 7, 4, 7, 4,
				GridBagConstraints.BOTH, GridBagConstraints.WEST, 0, 20, 20, 20);
        
        JLabel lbOriHotelName = new JLabel("Hotel");
        this.addWithConstraints(this.originalOrderbar, lbOriHotelName, 0, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 10, 10);
        
        this.tfOriHotelName = new JTextField(15);
        this.tfOriHotelName.setEditable(false);
        this.tfOriHotelName.setHorizontalAlignment(JTextField.CENTER);
        this.addWithConstraints(this.originalOrderbar, tfOriHotelName, 0, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbOriCheckInTime = new JLabel("Check in");
        this.addWithConstraints(this.originalOrderbar, lbOriCheckInTime, 1, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        this.tfOriCheckInTime = new JTextField(8);
        this.tfOriCheckInTime.setEditable(false);
        this.tfOriCheckInTime.setHorizontalAlignment(JTextField.CENTER);
        this.addWithConstraints(this.originalOrderbar, this.tfOriCheckInTime, 1, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbOriCheckOutTime = new JLabel("Check out");
        this.addWithConstraints(this.originalOrderbar, lbOriCheckOutTime, 2, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);
        
        this.tfOriCheckOutTime = new JTextField(8);
        this.tfOriCheckOutTime.setEditable(false);
        this.tfOriCheckOutTime.setHorizontalAlignment(JTextField.CENTER);
        this.addWithConstraints(this.originalOrderbar, this.tfOriCheckOutTime, 2, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbOriSingleNum = new JLabel("Single ");
        this.addWithConstraints(this.originalOrderbar, lbOriSingleNum, 3, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        this.tfOriSingleNum = new JTextField(8);
        this.tfOriSingleNum.setEditable(false);
        this.tfOriSingleNum.setHorizontalAlignment(JTextField.CENTER);
        this.addWithConstraints(this.originalOrderbar, this.tfOriSingleNum, 3, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbOriDoubleNum = new JLabel("Double ");
        this.addWithConstraints(this.originalOrderbar, lbOriDoubleNum, 4, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        this.tfOriDoubleNum = new JTextField(8);
        this.tfOriDoubleNum.setEditable(false);
        this.tfOriDoubleNum.setHorizontalAlignment(JTextField.CENTER);
        this.addWithConstraints(this.originalOrderbar, this.tfOriDoubleNum, 4, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbOriQuadNum = new JLabel("Quad");
        this.addWithConstraints(this.originalOrderbar, lbOriQuadNum, 5, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);
        
        this.tfOriQuadNum = new JTextField(8);
        this.tfOriQuadNum.setEditable(false);
        this.tfOriQuadNum.setHorizontalAlignment(JTextField.CENTER);
        this.addWithConstraints(this.originalOrderbar, this.tfOriQuadNum, 5, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbOriPrice = new JLabel("Price");
        this.addWithConstraints(this.originalOrderbar, lbOriPrice, 6, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);
        
        this.tfOriPrice = new JTextField(8);
        this.tfOriPrice.setEditable(false);
        this.tfOriPrice.setHorizontalAlignment(JTextField.CENTER);
        this.addWithConstraints(this.originalOrderbar, tfOriPrice, 6, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbModifiedOrder = new JLabel("Modified order");
        lbModifiedOrder.setFont(new Font("Serif", Font.BOLD, 18));
        lbModifiedOrder.setHorizontalAlignment(JLabel.CENTER);
        this.addWithConstraints(this, lbModifiedOrder, 0, 2, 7, 2, 7, 2,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        this.modifiedOrderbar = new JPanel();
        this.modifiedOrderbar.setLayout(new GridBagLayout());
		this.addWithConstraints(this, this.modifiedOrderbar, 0, 6, 7, 4, 7, 4,
                GridBagConstraints.BOTH, GridBagConstraints.WEST, 0, 20, 20, 20);   

        JLabel lbModHotelName = new JLabel("Hotel");
        this.addWithConstraints(this.modifiedOrderbar, lbModHotelName, 0, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 10, 10);
        
        this.tfModHotelName = new JTextField(15);
        this.tfModHotelName.setHorizontalAlignment(JTextField.CENTER);
        this.tfModHotelName.setEditable(false);
        this.addWithConstraints(this.modifiedOrderbar, this.tfModHotelName, 0, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbModCheckInTime = new JLabel("Check in");
        this.addWithConstraints(this.modifiedOrderbar, lbModCheckInTime, 1, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        this.tfModCheckInTime = new JTextField(8);
        this.tfModCheckInTime.setHorizontalAlignment(JTextField.CENTER);
        this.addWithConstraints(this.modifiedOrderbar, this.tfModCheckInTime, 1, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbModCheckOutTime = new JLabel("Check out");
        this.addWithConstraints(this.modifiedOrderbar, lbModCheckOutTime, 2, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);
        
        this.tfModCheckOutTime = new JTextField(8);
        this.tfModCheckOutTime.setHorizontalAlignment(JTextField.CENTER);
        this.addWithConstraints(this.modifiedOrderbar, this.tfModCheckOutTime, 2, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbModSingleNum = new JLabel("Single ");
        this.addWithConstraints(this.modifiedOrderbar, lbModSingleNum, 3, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        this.tfModSingleNum = new JTextField(8);
        this.tfModSingleNum.setHorizontalAlignment(JTextField.CENTER);
        this.addWithConstraints(this.modifiedOrderbar, this.tfModSingleNum, 3, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbModDoubleNum = new JLabel("Double ");
        this.addWithConstraints(this.modifiedOrderbar, lbModDoubleNum, 4, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        this.tfModDoubleNum = new JTextField(8);
        this.tfModDoubleNum.setHorizontalAlignment(JTextField.CENTER);
        this.addWithConstraints(this.modifiedOrderbar, this.tfModDoubleNum, 4, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbModQuadNum = new JLabel("Quad");
        this.addWithConstraints(this.modifiedOrderbar, lbModQuadNum, 5, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);
        
        this.tfModQuadNum = new JTextField(8);
        this.tfModQuadNum.setHorizontalAlignment(JTextField.CENTER);
        this.addWithConstraints(this.modifiedOrderbar, this.tfModQuadNum, 5, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);
        
        this.btnCheckAndSend = new JButton("Check");
        this.btnCheckAndSend.addActionListener(this);
        this.addWithConstraints(this, this.btnCheckAndSend, 0, 10, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH);

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

    public int dateToInt(String date){
        String tmp = date.substring(0,4) + date.substring(5, 7) + date.substring(8, 10);
        return Integer.valueOf(tmp);
    }

    public void setUser(User user){
        this.user = user;
    }


    public void setOrder(Order order){
        ArrayList<Integer> roomNums = order.getRoomsNum();
        this.tfOrderID.setText(String.valueOf(order.getOrderID()));
        this.tfOriHotelName.setText(String.valueOf(order.getHotelID()));
        this.tfOriCheckInTime.setText(dateToString(order.getCheckinTime()));
        this.tfOriCheckOutTime.setText(dateToString(order.getCheckoutTime()));
        this.tfOriSingleNum.setText(String.valueOf(roomNums.get(0)));
        this.tfOriDoubleNum.setText(String.valueOf(roomNums.get(1)));
        this.tfOriQuadNum.setText(String.valueOf(roomNums.get(2)));
        this.tfOriPrice.setText(String.valueOf(order.getPrice()));
        

        this.tfModHotelName.setText(String.valueOf(order.getHotelID()));
        this.tfModCheckInTime.setText(dateToString(order.getCheckinTime()));
        this.tfModCheckOutTime.setText(dateToString(order.getCheckoutTime()));
        this.tfModSingleNum.setText(String.valueOf(roomNums.get(0)));
        this.tfModDoubleNum.setText(String.valueOf(roomNums.get(1)));
        this.tfModQuadNum.setText(String.valueOf(roomNums.get(2)));

        this.haveSetOrderID = order.getOrderID();
    }

    public void resetOrder(){
        this.tfOrderID.setText("");
        this.tfOriHotelName.setText("");
        this.tfOriCheckInTime.setText("");
        this.tfOriCheckOutTime.setText("");
        this.tfOriSingleNum.setText("");
        this.tfOriDoubleNum.setText("");
        this.tfOriQuadNum.setText("");
        this.tfOriPrice.setText("");
        

        this.tfModHotelName.setText("");
        this.tfModCheckInTime.setText("");
        this.tfModCheckOutTime.setText("");
        this.tfModSingleNum.setText("");
        this.tfModDoubleNum.setText("");
        this.tfModQuadNum.setText("");

        this.haveSetOrderID = -1;
    }
	
	private void addWithConstraints(JComponent mother, JComponent c, int gridx, int gridy,
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
		gbc.insets = new Insets(20, 20, 20, 20);
		mother.add(c, gbc);
    }

    private void addWithConstraints(JComponent mother, JComponent c, int gridx, int gridy,
            int gridwidth, int gridheight, int weightx, int weighty,
            int fill, int anchor, int insetup, int insetdown, int insetleft, int insetright){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.fill = fill;
		gbc.anchor = anchor;
        gbc.insets = new Insets(insetup, insetleft, insetdown, insetright);
        mother.add(c, gbc);
    }

    public void actionPerformed(ActionEvent e){
        if (this.user != null){
            if (e.getSource() == this.btnQueryOrder){
                try{
                    Order order = this.modifyControl.getOrder(Integer.valueOf(this.tfOrderID.getText()));
                    if (order == null){
                        this.resetOrder();
                        JOptionPane.showMessageDialog(this, "No such order!", "Error", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    else if(order.getUserID() != this.user.getUserID()){
                        this.resetOrder();
                        JOptionPane.showMessageDialog(this, "Not your order!", "Error", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    else{
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        if (java.sql.Date.valueOf(dateToString(order.getCheckinTime())).after(java.sql.Date.valueOf(df.format(new Date())))){
                            this.setOrder(order);
                        }
                        else{
                            this.resetOrder();
                            JOptionPane.showMessageDialog(this, "Cannot modify order equal or after checkin date!", "Error", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    }
                }
                catch(NumberFormatException f){
                    System.out.println(f);
                    JOptionPane.showMessageDialog(this, "Input is not a number!", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if (e.getSource() == this.btnCheckAndSend){
                try{
                    if (this.haveSetOrderID != Integer.valueOf(this.tfOrderID.getText())){
                        this.resetOrder();
                        JOptionPane.showMessageDialog(this, "Order ID unmatched please first query!", "Error", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    boolean simpleCheckPass = true;
                    if (Integer.valueOf(this.tfModSingleNum.getText())>Integer.valueOf(this.tfOriSingleNum.getText())) simpleCheckPass = false;
                    if (Integer.valueOf(this.tfModDoubleNum.getText())>Integer.valueOf(this.tfOriDoubleNum.getText())) simpleCheckPass = false;
                    if (Integer.valueOf(this.tfModQuadNum.getText())>Integer.valueOf(this.tfOriQuadNum.getText())) simpleCheckPass = false;
                    if (simpleCheckPass == false){
                        JOptionPane.showMessageDialog(this, "Modify can only reduce room!", "Error", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (java.sql.Date.valueOf(this.tfModCheckInTime.getText()).before(java.sql.Date.valueOf(this.tfOriCheckInTime.getText()))) simpleCheckPass =false;
                    if (java.sql.Date.valueOf(this.tfModCheckOutTime.getText()).after(java.sql.Date.valueOf(this.tfOriCheckOutTime.getText()))) simpleCheckPass =false;
                    if (simpleCheckPass == false){
                        JOptionPane.showMessageDialog(this, "Modified date out of original bound!", "Error", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (!java.sql.Date.valueOf(this.tfModCheckOutTime.getText()).after(java.sql.Date.valueOf(this.tfModCheckInTime.getText()))) {
                        JOptionPane.showMessageDialog(this, "Modified date error!", "Error", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    int confirmModify =  JOptionPane.showConfirmDialog(this, "Do you want to modify?", "Check modify!", JOptionPane.YES_NO_OPTION);
                    if (confirmModify == JOptionPane.YES_OPTION){
                        boolean success = modifyControl.modifyOrder(
                        this.modifyControl.getOrder(Integer.valueOf(this.tfOrderID.getText())), 
                        Integer.valueOf(this.tfModSingleNum.getText()).intValue(), 
                        Integer.valueOf(this.tfModDoubleNum.getText()).intValue(), 
                        Integer.valueOf(this.tfModQuadNum.getText()).intValue(), 
                        this.dateToInt(this.tfModCheckInTime.getText()), 
                        this.dateToInt(this.tfModCheckOutTime.getText()));
                        if (success) {
                            Order order = this.modifyControl.getOrder(Integer.valueOf(this.tfOrderID.getText()));
                            if (order == null){
                                this.resetOrder();
                                JOptionPane.showMessageDialog(this, "Order has been deleted!", "Notify", JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                            else{
                                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                if (java.sql.Date.valueOf(dateToString(order.getCheckinTime())).after(java.sql.Date.valueOf(df.format(new Date())))){
                                    this.setOrder(order);
                                    JOptionPane.showMessageDialog(this, "Modify succeed!", "Notify", JOptionPane.INFORMATION_MESSAGE);
                                }
                                else{
                                    this.resetOrder();
                                    JOptionPane.showMessageDialog(this, "Modify error!", "Error", JOptionPane.INFORMATION_MESSAGE);
                                    return;
                                }
                            }
                        }
                        else System.out.println("Modify fail");
                    }
                    return;                    
                }
                catch(NumberFormatException f){
                    System.out.println(f);
                    JOptionPane.showMessageDialog(this, "Input is not a number!", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                catch(Exception a){
                    System.out.println(a);
                    JOptionPane.showMessageDialog(this, "Input wrong date format!", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please login!", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}