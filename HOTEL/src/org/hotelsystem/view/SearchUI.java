package org.hotelsystem.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class SearchUI extends JPanel {
	private JPanel searchBar;
	private JPanel searchFilter;
	private JPanel searchResult;
	
	public SearchUI() {
		this.setLayout(new GridBagLayout());
		initUI();
	}
	
	private void initUI() {
		this.searchBar = new JPanel();
		this.searchBar.setBorder(new LineBorder(Color.RED));
		this.addWithConstraints(this.searchBar, 0, 0, 7, 2, 7, 2,
				GridBagConstraints.BOTH, GridBagConstraints.WEST);

		this.searchFilter = new JPanel();
		this.searchFilter.setBorder(new LineBorder(Color.GREEN));
		this.addWithConstraints(this.searchFilter, 0, 2, 2, 5, 2, 5,
				GridBagConstraints.BOTH, GridBagConstraints.WEST);
		
		this.searchResult = new JPanel();
		this.searchResult.setBorder(new LineBorder(Color.BLUE));
		this.addWithConstraints(this.searchResult, 2, 2, 5, 5, 5, 5,
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