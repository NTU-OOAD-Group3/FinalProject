package org.hotelsystem.view;

import org.hotelsystem.control.InquireControl;
import org.hotelsystem.model.Order;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class InquireReviewDialog extends JDialog implements ActionListener{
    private JFrame parent;
    private JPanel inquirePanel;
    private Order order;
    private InquireControl inquireControl;
    public InquireReviewDialog(JFrame parent, InquireControl inquireControl, Order order){
        super(parent, "Review", true);
        this.parent = parent;
        this.inquireControl = inquireControl;
        this.order = order;
    }
    public void initUI(){
        this.inquirePanel = new JPanel();
        this.inquirePanel.setLayout(new GridBagLayout());
        this.inquirePanel.setBorder(new LineBorder(Color.black));
        this.add(this.inquirePanel, BorderLayout.CENTER);
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

    private String intArrToString(ArrayList<Integer> intArr){
        String buf = "";
        for (int i = 0;i<intArr.size();i++){
          buf = buf + intArr.get(i);
          if (i != intArr.size()-1) buf = buf + ",";
        }
        return buf;
    }

    public void actionPerformed(ActionEvent e){
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
		gbc.insets = new Insets(5, 5, 5, 5);
		p.add(c, gbc);
    }
}
