package org.hotelsystem.view;

import org.hotelsystem.model.HotelComment;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class CommentPanel extends JPanel{
    private JLabel labelUserID;
    private JLabel labelRaring;
    private JTextArea taComment;
    public CommentPanel (HotelComment hotelComment){
        initUI(hotelComment);
    }

    private void initUI(HotelComment hotelComment){
        this.setBorder(new LineBorder(Color.RED));
        this.setLayout(new GridBagLayout());
        
        labelUserID = new JLabel(String.valueOf(hotelComment.getUserID()));
        this.addWithConstraints(this, labelUserID, 0, 0, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);
        
        labelRaring = new JLabel(String.valueOf(hotelComment.getRating()));
        this.addWithConstraints(this, labelRaring, 1, 0, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);
        
        taComment = new JTextArea(hotelComment.getComment());
        taComment.setEditable(false);
        this.addWithConstraints(this, taComment, 0, 1, 1, 2, 1, 2,
            GridBagConstraints.NONE, GridBagConstraints.WEST);

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