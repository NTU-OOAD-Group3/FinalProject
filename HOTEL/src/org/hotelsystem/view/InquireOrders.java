package org.hotelsystem.view;

import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;

public class InquireOrders extends JPanel{
    private JPanel listPanel;
    private InquireOrderUnit[] arrs = new InquireOrderUnit[10];

    public InquireOrders() {
        initUI();
    }

    private void initUI() {
        this.setLayout(new BorderLayout());

        this.listPanel = new JPanel();
        this.listPanel.setLayout(new GridBagLayout());
        JScrollPane listPanelScroll = new JScrollPane(listPanel);

        for ( int i=0; i<10; ++i ) {
            arrs[i] = new InquireOrderUnit();
            this.addWithConstraints(listPanel, arrs[i],
                0, i, 1, 1, 1, 1,
                GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        }

        this.add(listPanelScroll, BorderLayout.CENTER);
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