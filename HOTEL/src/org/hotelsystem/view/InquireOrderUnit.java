package org.hotelsystem.view;
import org.hotelsystem.model.Order;
import org.hotelsystem.control.InquireControl;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class InquireOrderUnit extends JPanel implements ActionListener{
    private InquireControl inquireControl;
    private JLabel labelHotelImage;
    private JLabel labelHotelName;
    private JLabel labelRoomType;
    private JLabel checkInAndOut;
    private JLabel labelOrderID;
    private JLabel labelPrice;
    private boolean haveLived = true;
    private JButton btnModify;
    private Order order;
    private JFrame parent;
    


    public InquireOrderUnit(Order order, InquireControl inquireControl, JFrame parent) {
        this.inquireControl = inquireControl;
        this.order = order;
        this.parent = parent;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (java.sql.Date.valueOf(dateToString(order.getCheckinTime())).after(java.sql.Date.valueOf(df.format(new Date())))) this.haveLived = false;

        LineBorder line = new LineBorder(Color.GRAY);
        EmptyBorder empty = new EmptyBorder(5, 5, 5, 5);
        this.setBorder(new CompoundBorder(line, empty));
        this.setLayout(new GridBagLayout());


        this.labelHotelName = new JLabel("Hotel Name: " + order.getHotelID());
        this.addWithConstraints(labelHotelName, 8, 1, 4, 1, 4, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        this.labelRoomType = new JLabel("Rooms: " + roomIDToString(order));
        this.addWithConstraints(labelRoomType, 8, 3, 8, 1, 8, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        this.checkInAndOut = new JLabel("Check in/out: " + dateToString(order.getCheckinTime()) + " ~ " + dateToString(order.getCheckoutTime()));
        this.addWithConstraints(checkInAndOut, 8, 2, 8, 1, 8, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);


        this.labelOrderID = new JLabel("Order ID: " + order.getOrderID());
        this.addWithConstraints(labelOrderID, 4, 0, 4, 1, 4, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);


    
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");  
        long diffInMillies = 0;
        try{
            diffInMillies = dateFormatter.parse(Integer.toString(order.getCheckoutTime())).getTime() - dateFormatter.parse(Integer.toString(order.getCheckinTime())).getTime();
        }
        catch(Exception e){

        }
        int searchNight = (int)TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        this.labelPrice = new JLabel("NTD " + (order.getPrice() * searchNight));
        this.addWithConstraints(labelPrice, 12, 1, 4, 1, 12, 1,
            GridBagConstraints.CENTER, GridBagConstraints.EAST);

        if (this.haveLived) {
            this.btnModify = new JButton("Review");
        }
        else {
            this.btnModify = new JButton("Modify");
        }
        this.btnModify.addActionListener(this);
        this.addWithConstraints(btnModify, 12, 3, 4, 1, 8, 1,
            GridBagConstraints.NONE, GridBagConstraints.EAST);
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
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(c, gbc);
    }

    private String roomIDToString(Order order){
        String buf = "";
        for (int i =0; i<order.getRoomIDs().size();i++){
          buf = buf + order.getRoomIDs().get(i);
          if (i!=order.getRoomIDs().size()-1) buf = buf + ", "; 
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

    public void actionPerformed(ActionEvent e){  
        if( e.getSource() == this.btnModify){
            if (this.haveLived){
                InquireReviewDialog inquireReviewDialog = new InquireReviewDialog(this.parent, this.inquireControl, this.order, this.inquireControl.getReview(this.order));
                inquireReviewDialog.setLocationRelativeTo(this);
                inquireReviewDialog.setVisible(true);
                System.out.println("Review!!!");
            }
            else{
                this.inquireControl.switchToModify(this.order);
            }
            
        } 
    }  

}