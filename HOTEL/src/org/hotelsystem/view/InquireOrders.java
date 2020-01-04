package org.hotelsystem.view;
import org.hotelsystem.model.Order;
import org.hotelsystem.control.InquireControl;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class InquireOrders extends JPanel{
    private JPanel listPanel;
    private InquireOrderUnit[] arrs = new InquireOrderUnit[10];

    public InquireOrders(ArrayList<Order> orders, InquireControl inquireControl, JFrame parent) {
        this.setLayout(new BorderLayout());

        this.listPanel = new JPanel();
        this.listPanel.setLayout(new GridBagLayout());
        JScrollPane listPanelScroll = new JScrollPane(listPanel);

        for ( int i=0; i<orders.size(); i++ ) {
            arrs[i] = new InquireOrderUnit(orders.get(i), inquireControl, parent);
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