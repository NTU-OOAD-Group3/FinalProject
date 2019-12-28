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
		this.searchBar = new SearchBar();
		LineBorder line = new LineBorder(Color.BLACK);
		EmptyBorder empty = new EmptyBorder(20, 50, 20, 50);
		this.searchBar.setBorder(new CompoundBorder(line, empty));
		this.addWithConstraints(this.searchBar, 0, 0, 7, 1, 7, 1,
				GridBagConstraints.BOTH, GridBagConstraints.CENTER);

		this.searchFilter = new SearchFilter();
		this.addWithConstraints(this.searchFilter, 0, 1, 2, 6, 2, 6,
				GridBagConstraints.BOTH, GridBagConstraints.CENTER);
		
		this.searchResult = new SearchResults();
		this.searchResult.setBorder(new LineBorder(Color.BLACK));
		this.addWithConstraints(this.searchResult, 2, 1, 5, 6, 5, 6,
				GridBagConstraints.BOTH, GridBagConstraints.CENTER);
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