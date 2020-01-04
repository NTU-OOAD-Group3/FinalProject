package org.hotelsystem.view;

import org.hotelsystem.model.HotelReview;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ReviewPanel extends JPanel{
    private JLabel labelUserID;
    private JLabel labelRating;
    private JTextArea taReview;

    public ReviewPanel(){
        initUI();
    }

    private void initUI(){
        this.setBorder(new LineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        
        labelUserID = new JLabel(String.format("User: %d", 0));
        this.addWithConstraints(this, labelUserID, 0, 0, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.WEST);
        
        labelRating = new JLabel(String.format("Rating: %d ", 5));
        this.addWithConstraints(this, labelRating, 1, 0, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.WEST);
        
        taReview = new JTextArea("Comment: \n    " + "this is a review.");
        taReview.setBackground(new Color(238, 238, 238));
        taReview.setEditable(false);
        this.addWithConstraints(this, taReview, 0, 1, 1, 2, 1, 2,
            GridBagConstraints.NONE, GridBagConstraints.WEST);
    }

    public void setReview(HotelReview hotelReview){
        this.labelUserID.setText(String.format("User: %d", hotelReview.getUserID()));
        this.labelRating.setText(String.format("Rating: %d", hotelReview.getRating()));
        this.taReview.setText(String.format("comment: \n    " + hotelReview.getReview()));
    }

    private void addWithConstraints(JComponent parent, JComponent c, int gridx, int gridy,
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
		parent.add(c, gbc);
    }
}