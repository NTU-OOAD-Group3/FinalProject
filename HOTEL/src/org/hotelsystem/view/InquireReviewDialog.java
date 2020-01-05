package org.hotelsystem.view;

import org.hotelsystem.control.InquireControl;
import org.hotelsystem.model.Order;
import org.hotelsystem.model.Review;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class InquireReviewDialog extends JDialog implements ActionListener{
    private JPanel orderPanel;
    private JPanel reviewPanal;
    private Order order;
    private InquireControl inquireControl;
    private JComboBox reviewStar;
    private JTextArea reviewText;
    private JButton btnSendReview;
    private Review review;
    public InquireReviewDialog(JFrame parent, InquireControl inquireControl, Order order, Review review){
        super(parent, "Review", true);
        this.inquireControl = inquireControl;
        this.order = order;
        this.review = review;
        this.initUI();
    }
    public void initUI(){
        this.orderPanel = new JPanel();
        this.orderPanel.setLayout(new GridBagLayout());
        this.orderPanel.setBorder(new LineBorder(Color.black));
        this.add(this.orderPanel, BorderLayout.NORTH);

        JLabel orderID = new JLabel("Order ID: " + this.order.getOrderID());
        this.addWithConstraints(this.orderPanel, orderID, 0, 0, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.WEST);
        JLabel hotelName = new JLabel("Hotel name: " + order.getHotelID());
        this.addWithConstraints(this.orderPanel, hotelName, 0, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.WEST);
        JLabel date = new JLabel("Checkin/Checkout: " + order.getCheckinTime() + "~" + order.getCheckoutTime());
        this.addWithConstraints(this.orderPanel, date, 0, 2, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.WEST);
        JLabel rooms = new JLabel("Rooms: " + intArrToString(order.getRoomIDs()) + "\n");
        this.addWithConstraints(this.orderPanel, rooms, 0, 3, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.WEST);
        JLabel price = new JLabel("Price: " + order.getPrice());
        this.addWithConstraints(this.orderPanel, price, 0, 4, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.WEST);

        this.reviewPanal = new JPanel();
        this.reviewPanal.setLayout(new GridBagLayout());
        this.reviewPanal.setBorder(new LineBorder(Color.black));
        this.add(this.reviewPanal, BorderLayout.CENTER);

        

        if (this.review != null) {
            JLabel ranting = new JLabel("Rating: " + this.review.getRating()+" star");
            this.addWithConstraints(this.reviewPanal, ranting, 0, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.WEST);

            JLabel review = new JLabel("review: " + this.review.getReview());
            this.addWithConstraints(this.reviewPanal, review, 0, 1, 1, 2, 1, 2,
                GridBagConstraints.NONE, GridBagConstraints.WEST);

        }
        else{
            String ranting[]  = new String[5];
            for (int i=0;i<5;i++){
                ranting[i] = String.valueOf(5-i) + " star";
            }
            this.reviewStar = new JComboBox(ranting);
            this.addWithConstraints(this.reviewPanal, this.reviewStar, 0, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.CENTER);

            this.reviewText = new JTextArea();
            this.reviewText.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Comment", 0, 0, Font.getFont("Times New Roman"), Color.BLUE));
            this.addWithConstraints(this.reviewPanal, this.reviewText, 0, 1, 1, 2, 1, 2,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);
        }

        String tmp = "Send review!";
        if (this.review != null) tmp = "Close";
        this.btnSendReview = new JButton(tmp);
        this.btnSendReview.addActionListener(this);
        this.addWithConstraints(this.reviewPanal, this.btnSendReview, 0, 2, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.SOUTH);

        this.setSize(320, 320);

        
        
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
        if( e.getSource() == this.btnSendReview){
            if (this.review != null) {
                this.setVisible(false);
                return;
            }
            else{
                this.setVisible(false);
                this.inquireControl.setReview(this.order, (String)this.reviewStar.getSelectedItem(), this.reviewText.getText());
            }
        }
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
		gbc.insets = new Insets(1, 1, 1, 1);
		p.add(c, gbc);
    }
}
