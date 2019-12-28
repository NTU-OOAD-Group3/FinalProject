package org.hotelsystem.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class InquireUI extends JPanel{
    private JPanel userInfo;
    private JPanel seachOrder;
    private JPanel showGeneralOrder;

    public InquireUI() {
		this.setLayout(new GridBagLayout());
		initUI();
    }
    
    private void initUI() {
        this.userInfo = new JPanel();
        this.userInfo.setBorder(new LineBorder(Color.RED));
        this.addWithConstraints(this.userInfo, 0, 0, 1, 2, 2, 1,
                                GridBagConstraints.BOTH, GridBagConstraints.WEST);

        this.seachOrder = new JPanel();
        this.seachOrder.setBorder(new LineBorder(Color.GREEN));
        this.addWithConstraints(this.seachOrder, 0, 2, 1, 3, 2, 2,
                                GridBagConstraints.BOTH, GridBagConstraints.WEST);

        this.showGeneralOrder = new JPanel();
        this.showGeneralOrder.setBorder(new LineBorder(Color.BLUE));
        this.addWithConstraints(this.showGeneralOrder, 1, 0, 4, 5, 7, 2,
                                GridBagConstraints.BOTH, GridBagConstraints.WEST);


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
		gbc.insets = new Insets(3, 3, 3, 3);
		this.add(c, gbc);
	}
	
}