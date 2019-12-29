package org.hotelsystem.view;
import org.hotelsystem.model.Order;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class InquireOrderUnit extends JPanel{
    private JLabel labelHotelImage;
    private JLabel labelHotelName;
    private JLabel labelRoomType;
    private JLabel checkInAndOut;
    private JLabel labelOrderID;
    private JLabel labelPrice;

    public InquireOrderUnit(Order order) {
        LineBorder line = new LineBorder(Color.GRAY);
        EmptyBorder empty = new EmptyBorder(5, 5, 5, 5);
        this.setBorder(new CompoundBorder(line, empty));
        this.setLayout(new GridBagLayout());

        this.labelHotelImage = new JLabel("Hotel Image");
        this.labelHotelImage.setHorizontalAlignment(JLabel.CENTER);
        this.labelHotelImage.setBorder(new LineBorder(Color.BLACK));
        this.addWithConstraints(labelHotelImage, 0, 0, 4, 4, 4, 4,
            GridBagConstraints.BOTH, GridBagConstraints.CENTER);

        this.labelHotelName = new JLabel("Hotel Name: " + order.getHotelID());
        this.addWithConstraints(labelHotelName, 8, 1, 4, 1, 4, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        this.labelRoomType = new JLabel("Rooms: " + roomIDToString(order));
        this.addWithConstraints(labelRoomType, 8, 3, 8, 1, 8, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        this.checkInAndOut = new JLabel("Check in/out: " + order.getCheckinTime() + " ~ " + order.getCheckoutTime());
        this.addWithConstraints(checkInAndOut, 8, 2, 8, 1, 8, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);


        this.labelOrderID = new JLabel("Order ID: " + order.getOrderID());
        this.addWithConstraints(labelOrderID, 4, 0, 4, 1, 4, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);


        this.labelPrice = new JLabel("NTD " + order.getPrice());
        this.addWithConstraints(labelPrice, 12, 1, 4, 1, 12, 1,
            GridBagConstraints.CENTER, GridBagConstraints.EAST);
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

}