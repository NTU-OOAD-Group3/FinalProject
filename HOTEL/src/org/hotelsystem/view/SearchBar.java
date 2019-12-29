package org.hotelsystem.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SearchBar extends JPanel implements ActionListener{
    private JLabel labelLocality;
    private JLabel labelCheckin;
    private JLabel labelCheckout;
    private JLabel labelTotalNight;
    private JLabel labelRoom;
    private JLabel labelPeople;
    private JTextField tfLocality;
    private JTextField tfCheckin;
    private JTextField tfCheckout;
    private JTextField tfTotalNight;
    private JTextField tfRoom;
    private JTextField tfPeople;
    private JButton btnSearch;

    public SearchBar() {
        initUI();
    }

    private void initUI() {
        this.setLayout(new GridBagLayout());

        this.labelLocality = new JLabel("Destination");
        // this.labelLocality.setBorder(new LineBorder(Color.RED));
        this.addWithConstraints(labelLocality, 0, 0, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);

        this.labelCheckin = new JLabel("Checkin");
        // this.labelCheckin.setBorder(new LineBorder(Color.RED));
        this.addWithConstraints(labelCheckin, 1, 0, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);

        this.labelCheckout = new JLabel("Checkout");
        // this.labelCheckout.setBorder(new LineBorder(Color.RED));
        this.addWithConstraints(labelCheckout, 2, 0, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);

        this.labelTotalNight = new JLabel("Nights");
        // this.labelTotalNight.setBorder(new LineBorder(Color.RED));
        this.addWithConstraints(labelTotalNight, 3, 0, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);
        
        this.labelRoom = new JLabel("Rooms");
        // this.labelRoom.setBorder(new LineBorder(Color.RED));
        this.addWithConstraints(labelRoom, 4, 0, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);

        this.labelPeople = new JLabel("People");
        // this.labelPeople.setBorder(new LineBorder(Color.RED));
        this.addWithConstraints(labelPeople, 5, 0, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH);

        this.tfLocality = new JTextField();
        this.addWithConstraints(tfLocality, 0, 1, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);

        this.tfCheckin = new JTextField();
        this.addWithConstraints(tfCheckin, 1, 1, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);

        this.tfCheckout = new JTextField();
        this.addWithConstraints(tfCheckout, 2, 1, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);

        this.tfTotalNight = new JTextField();
        this.addWithConstraints(tfTotalNight, 3, 1, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);

        this.tfRoom = new JTextField();
        this.addWithConstraints(tfRoom, 4, 1, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);

        this.tfPeople = new JTextField();
        this.addWithConstraints(tfPeople, 5, 1, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);

        this.btnSearch = new JButton("Search");
        this.btnSearch.addActionListener(this);
        this.addWithConstraints(btnSearch, 5, 2, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH);
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
		// gbc.insets = new Insets(3, 3, 3, 3);
		this.add(c, gbc);
    }
    
    public void actionPerformed(ActionEvent e){  
        System.out.println("Search triggered.");  
    }  

    public static void main(String[] args) {
        JFrame testFrame = new JFrame("SearchBar testFrame");
        testFrame.setSize(600, 150);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel searchBar = new SearchBar();
        testFrame.add(searchBar);

        testFrame.setVisible(true);
    }
}