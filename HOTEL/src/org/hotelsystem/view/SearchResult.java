package org.hotelsystem.view;

import org.hotelsystem.model.AvailableHotel;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SearchResult extends JPanel implements ActionListener{
    private JLabel labelHotelImage;
    private JLabel labelHotelName;
    private JLabel labelHotelLocality;
    private JLabel labelHotelAddress;
    private JLabel labelHotelStar;
    private JButton btnReview;
    private JLabel labelPriceTitle;
    private JLabel labelPrice;
    private JLabel labelSummary;
    private JButton btnReserve;

    private ArrayList<ArrayList<Integer>> roomCombination;

    public SearchResult(AvailableHotel availableHotel) {
        initUI(availableHotel);
    }

    private void initUI(AvailableHotel availableHotel) {
        LineBorder line = new LineBorder(Color.GRAY);
        EmptyBorder empty = new EmptyBorder(5, 5, 5, 5);
        this.roomCombination = availableHotel.getRoomCombination();

        this.setBorder(new CompoundBorder(line, empty));
        this.setLayout(new GridBagLayout());

        this.labelHotelImage = new JLabel("Hotel Image");
        this.labelHotelImage.setHorizontalAlignment(JLabel.CENTER);
        this.labelHotelImage.setBorder(new LineBorder(Color.BLACK));
        this.addWithConstraints(labelHotelImage, 0, 0, 4, 4, 4, 4,
            GridBagConstraints.BOTH, GridBagConstraints.CENTER);

        this.labelHotelName = new JLabel(Integer.toString(availableHotel.getHotelID()));
        this.addWithConstraints(labelHotelName, 4, 0, 4, 1, 4, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        
        this.labelHotelLocality = new JLabel(availableHotel.getLocality());
        this.addWithConstraints(labelHotelLocality, 4, 1, 8, 1, 8, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        this.labelHotelAddress = new JLabel(availableHotel.getStreetAddress());
        this.addWithConstraints(labelHotelAddress, 4, 2, 8, 1, 8, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        this.labelHotelStar = new JLabel(Integer.toString(availableHotel.getHotelStar()));
        this.addWithConstraints(labelHotelStar, 4, 3, 8, 1, 8, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        this.btnReview = new JButton("Reviews");
        this.btnReview.addActionListener(this);
        this.addWithConstraints(btnReview, 8, 0, 4, 1, 4, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.labelPriceTitle = new JLabel("Min Price");
        this.labelPriceTitle.setHorizontalAlignment(JLabel.LEFT);
        this.addWithConstraints(labelPriceTitle, 12, 0, 4, 1, 4, 1,
            GridBagConstraints.NONE, GridBagConstraints.EAST);

        this.labelPrice = new JLabel("NTD 168,000");
        this.addWithConstraints(labelPrice, 12, 1, 4, 1, 4, 1,
            GridBagConstraints.NONE, GridBagConstraints.EAST);

        this.labelSummary = new JLabel("(2 people, 3 nights, 3 rooms)");
        this.addWithConstraints(labelSummary, 12, 2, 4, 1, 4, 1,
            GridBagConstraints.NONE, GridBagConstraints.EAST);

        this.btnReserve = new JButton("Reserve It!");
        this.btnReserve.addActionListener(this);
        this.addWithConstraints(btnReserve, 12, 3, 4, 1, 4, 1,
            GridBagConstraints.NONE, GridBagConstraints.EAST);

    }

    public void refreshUI(AvailableHotel availableHotel){
        this.labelHotelName.setText(Integer.toString(availableHotel.getHotelID()));
        this.labelHotelStar.setText(Integer.toString(availableHotel.getHotelStar()));
        this.labelHotelLocality.setText(availableHotel.getLocality());
        this.labelHotelAddress.setText(availableHotel.getStreetAddress());
        this.roomCombination = availableHotel.getRoomCombination();
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
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(c, gbc);
    }

    public void actionPerformed(ActionEvent e){  
        if( e.getSource() == this.btnReview){
            System.out.println("Review triggered.");
        } 
        else if( e.getSource() == this.btnReserve){
            System.out.println("Reserve triggered.");
        }
    }  

    // public static void main(String[] args) {
    //     JFrame testFrame = new JFrame("SearchResult testFrame");
    //     testFrame.setSize(600, 150);
    //     testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //     JPanel SearchResult = new SearchResult(availableHotel);
    //     testFrame.add(SearchResult);

    //     testFrame.setVisible(true);
    // }
}