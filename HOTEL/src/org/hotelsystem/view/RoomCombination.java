package org.hotelsystem.view;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class RoomCombination extends JPanel{
    private JRadioButton rbtnCombination;
    public RoomCombination (ArrayList<Integer> combination){
        initUI(combination);
    }

    private void initUI(ArrayList<Integer> combination){
        this.setBorder(new LineBorder(Color.RED));
        this.setLayout(new GridBagLayout());
        
        rbtnCombination = new JRadioButton(String.format("Single: %d, Double: %d, Quad: %d, Price: %d", combination.get(0), combination.get(1), combination.get(2), 7777));
        this.addWithConstraints(this, rbtnCombination, 0, 0, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);
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