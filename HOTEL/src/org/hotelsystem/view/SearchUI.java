package org.hotelsystem.view;

import org.hotelsystem.control.SearchControl;
import org.hotelsystem.model.AvailableHotel;
import org.hotelsystem.model.HotelReview;

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
	private ReviewDialogs reviewDialogs;
	public SearchUI(JFrame parent, SearchControl searchControl) {
		this.setLayout(new GridBagLayout());
		this.parent = parent;
		this.searchControl = searchControl;
		reviewDialogs = new ReviewDialogs(0, this.parent, "", null, 0, 0, this.searchControl);
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
		this.searchControl.searchAvailableHotel(locality, checkin, checkout, room, people);
	}

	public void setAvailableHotels(ArrayList<AvailableHotel> availableHotels, int page, int totalPage){
		this.searchResult.setAvailableHotel(availableHotels, page, totalPage);
	}

	public void showReview(int hotelID, String dialogName, ArrayList<HotelReview> hotelReview, int page, int totalPage){
		this.reviewDialogs.refresh(hotelID, hotelReview, page, totalPage);
		this.reviewDialogs.setVisible(true);
	}

	public ArrayList<String> getLocality(){
		return this.searchControl.getLocality();
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