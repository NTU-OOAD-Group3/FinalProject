package org.hotelsystem.view;
import org.hotelsystem.model.Order;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.*;
import java.text.*;


public class ModifyUI extends JPanel implements ActionListener{
    private JPanel orderIDbar;
	private JPanel originalOrderbar;
    private JPanel modifiedOrderbar;
    private JTextField tfOrderID;
    private JButton btnQueryOrder;
    private JButton btnCheckAndSend;

    JTextField tfOriHotelName;
    JTextField tfOriCheckInTime;
    JTextField tfOriCheckOutTime;
    JTextField tfOriSingleNum;
    JTextField tfOriDoubleNum;
    JTextField tfOriQuadNum;
    JTextField tfOriPrice;

    JTextField tfModHotelName;
    JTextField tfModCheckInTime;
    JTextField tfModCheckOutTime;
    JTextField tfModSingleNum;
    JTextField tfModDoubleNum;
    JTextField tfModQuadNum;

	
	public ModifyUI() {
		this.setLayout(new GridBagLayout());
		initUI();
	}
	
	private void initUI() {
        this.orderIDbar = new JPanel();
        this.orderIDbar.setLayout(new GridBagLayout());
		this.orderIDbar.setBorder(new LineBorder(Color.RED));
		this.addWithConstraints(this, this.orderIDbar, 0, 0, 7, 2, 7, 2,
				GridBagConstraints.BOTH, GridBagConstraints.WEST, 0, 20, 20, 20);

        JLabel lbOrderID = new JLabel("Order ID");
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
        lbOriginalOrder.setHorizontalAlignment(JLabel.CENTER);
        this.addWithConstraints(this, lbOriginalOrder, 0, 0, 7, 2, 7, 2,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        this.originalOrderbar = new JPanel();
        this.originalOrderbar.setLayout(new GridBagLayout());
		this.originalOrderbar.setBorder(new LineBorder(Color.GREEN));
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
        lbModifiedOrder.setHorizontalAlignment(JLabel.CENTER);
        this.addWithConstraints(this, lbModifiedOrder, 0, 2, 7, 2, 7, 2,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        this.modifiedOrderbar = new JPanel();
        this.modifiedOrderbar.setLayout(new GridBagLayout());
		this.modifiedOrderbar.setBorder(new LineBorder(Color.BLUE));
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
        tmp += String.valueOf((date%10000)/100);
        tmp +="-";
        tmp += String.valueOf(date%100);
        return tmp;
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
        if (e.getSource() == this.btnQueryOrder){
            //Mock Data
            ArrayList<Integer> a= new ArrayList<Integer>();
            a.add(1);
            Order order = new Order(1, 0, 0, a, 1250, 1350, 666);
            this.tfOriHotelName.setText(String.valueOf(order.getHotelID()));
            //Todo
            System.out.println("Perform Query");
        }
        else if (e.getSource() == this.btnCheckAndSend){
            try{
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
                    JOptionPane.showMessageDialog(this, "Modify date out of original bound!", "Error", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                //Todo
                System.out.println("Perform modify");
            }
            catch(NumberFormatException f){
                JOptionPane.showMessageDialog(this, "Input is not a number!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception a){
                JOptionPane.showMessageDialog(this, "Input wrong date format!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}