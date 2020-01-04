package org.hotelsystem.view;

import org.hotelsystem.control.InquireControl;
import org.hotelsystem.model.Order;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
