package org.hotelsystem.view;

import org.hotelsystem.control.SearchControl;
import org.hotelsystem.model.AvailableHotel;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;

public class SearchUI extends JPanel {
	private SearchControl searchControl;
	private SearchBar searchBar;
	private SearchFilter searchFilter;
	private SearchResults searchResult;
	private JFrame parent;
	public SearchUI(JFrame parent, SearchControl searchControl) {
		this.setLayout(new GridBagLayout());
		this.parent = parent;
		this.searchControl = searchControl;
		initUI();
	}
	
	private void initUI() {
		this.searchBar = new SearchBar(this.parent, this);
		LineBorder line = new LineBorder(Color.BLACK);
		EmptyBorder empty = new EmptyBorder(20, 50, 20, 50);
		this.searchBar.setBorder(new CompoundBorder(line, empty));
		this.addWithConstraints(this.searchBar, 0, 0, 7, 1, 7, 1,
				GridBagConstraints.BOTH, GridBagConstraints.CENTER);

		this.searchFilter = new SearchFilter(this);
		this.addWithConstraints(this.searchFilter, 0, 1, 1, 6, 1, 6,
				GridBagConstraints.BOTH, GridBagConstraints.CENTER);
		
		this.searchResult = new SearchResults(this.parent, this.searchControl);
		this.searchResult.setBorder(new LineBorder(Color.BLACK));
		this.addWithConstraints(this.searchResult, 1, 1, 6, 6, 6, 6,
				GridBagConstraints.BOTH, GridBagConstraints.CENTER);
	}
	
	public void triggerSearch(String locality, int checkin, int checkout, int room, int people){
		ArrayList<AvailableHotel> tmp = this.searchControl.searchAvailableHotel(locality, checkin, checkout, room, people);
		System.out.printf("get %d available hotels\n", tmp.size());
		this.searchResult.setAvailableHotel(tmp);
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