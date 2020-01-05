package org.hotelsystem.view;

import org.hotelsystem.model.AvailableHotel;
import org.hotelsystem.control.SearchControl;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class RoomCombDialog extends JDialog implements ActionListener{
    private int hotelID;
    private int page = 0;
    private int totalPage = 0;
    private ArrayList<ArrayList<Integer>> roomCombination = new ArrayList<ArrayList<Integer>>(0);
    private ArrayList<Integer> combinationPrice = new ArrayList<Integer>(0);
    private JFrame parent;
    private JPanel hotelPanel;
    private JPanel listPanel;
    private JPanel bottomPanel;
    private JButton btnCancel;
    private JButton btnPrevPage;
    private JButton btnNextPage;
    private JLabel labelHotelID;
    private JLabel labelHotelStar;
    private JLabel labelLocality;
    private JLabel labelAddress;
    private JButton[] btnCombination = new JButton[10];
    private JLabel labelPageNum;
    private SearchControl searchControl;
    private AvailableHotel availableHotel;

    public RoomCombDialog(AvailableHotel availableHotel, JFrame parent, String name, SearchControl searchControl){
        super(parent, name, true);
        this.parent = parent;
        this.searchControl = searchControl;
        this.availableHotel = availableHotel;
        initUI(availableHotel);
    }

    private void initUI(AvailableHotel availableHotel){
        this.hotelPanel = new JPanel();
        this.hotelPanel.setLayout(new GridBagLayout());
        this.hotelPanel.setBorder(new LineBorder(Color.black));
        this.listPanel = new JPanel();
        this.listPanel.setLayout(new GridBagLayout());

        JScrollPane listPanelScroll = new JScrollPane(listPanel);
        listPanelScroll.setBorder(new LineBorder(Color.black));
        
        this.roomCombination = availableHotel.getRoomCombination();
        this.combinationPrice = availableHotel.getCombinationPrice();
        this.totalPage = (this.roomCombination.size() - 1) / 10;
        this.labelHotelID = new JLabel(String.format("Hotel: %d", availableHotel.getHotelID()));
        this.addWithConstraints(hotelPanel, labelHotelID, 0, 0, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.labelHotelStar = new JLabel(String.format("Star: %d", availableHotel.getHotelStar()));
        this.addWithConstraints(hotelPanel, labelHotelStar, 1, 0, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.labelLocality = new JLabel(String.format("Locality: %s", availableHotel.getLocality()));
        this.addWithConstraints(hotelPanel, labelLocality, 0, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.labelAddress = new JLabel(String.format("Address: %s", availableHotel.getStreetAddress()));
        this.addWithConstraints(hotelPanel, labelAddress, 1, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        for ( int i=0; i<10 && i < this.roomCombination.size(); ++i ) {
            ArrayList<Integer> combination = this.roomCombination.get(i);
            btnCombination[i] = new JButton(String.format("Single: %d, Double: %d, Quad: %d, Price: %d", combination.get(0), combination.get(1), combination.get(2), this.combinationPrice.get(i)));
            btnCombination[i].addActionListener(this);
            // commentArray[i].setVisible(false);
            this.addWithConstraints(listPanel, btnCombination[i],
                0, i+2, 1, 1, 1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        }
        
        this.btnCancel = new JButton("Cancel");
        this.btnCancel.addActionListener(this);
        this.addWithConstraints(listPanel, btnCancel,
            0, Math.min(10, this.roomCombination.size()) + 2, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.SOUTH);
        
        this.bottomPanel = new JPanel();
        this.bottomPanel.setLayout(new GridLayout(1, 3));

        this.btnPrevPage = new JButton("<< Previous");
        this.btnPrevPage.addActionListener(this);
        this.bottomPanel.add(this.btnPrevPage);

        this.labelPageNum = new JLabel(String.format("%d/%d", this.page + 1, this.totalPage + 1), JLabel.CENTER);
        this.bottomPanel.add(this.labelPageNum);
        
        this.btnNextPage = new JButton("Next >>");
        this.btnNextPage.addActionListener(this);
        this.bottomPanel.add(this.btnNextPage);

        this.add(hotelPanel, BorderLayout.NORTH);
        this.add(listPanelScroll, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    
        this.setSize(320, 800);
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

    public void setAvailableHotel(AvailableHotel availableHotel){
        this.labelHotelID.setText(String.format("Hotel: %d", availableHotel.getHotelID()));
        this.labelHotelStar.setText(String.format("Star: %d", availableHotel.getHotelStar()));
        this.labelLocality.setText(String.format("Locality: %s", availableHotel.getLocality()));
        this.labelAddress.setText(String.format("Address: %s", availableHotel.getStreetAddress()));
        this.roomCombination = availableHotel.getRoomCombination();
        this.page = 0;
        this.totalPage = ( this.roomCombination.size() - 1) / 10;
        System.out.println(this.totalPage);
        this.refreshUI();
    }

    private void refreshUI(){
        int round = 10, base = 10 * this.page;
        if( this.page == this.totalPage ){
            round = this.roomCombination.size() % 10;
            for( int i=round;i<10;++i )
                this.btnCombination[i].setVisible(false);
        }
        for( int i=0;i<round;++i ){
            ArrayList<Integer> combination = this.roomCombination.get(base + i);
            this.btnCombination[i].setVisible(true);
            this.btnCombination[i].setText(String.format("Single: %d, Double: %d, Quad: %d, Price: %d", combination.get(0), combination.get(1), combination.get(2), this.combinationPrice.get(i)));
        }
    }

    public void actionPerformed(ActionEvent e){  
        if( e.getSource() == this.btnPrevPage ){
            System.out.println("Previous page triggered.");
            if( this.page > 0 ){
                --this.page;
                refreshUI();
            }
            else{ 
                System.out.println("Already the first page.");
            }
        }

        else if( e.getSource() == this.btnNextPage ){
            System.out.println("Next page triggered.");
            if( this.page < this.totalPage ){
                ++this.page;
                refreshUI();
            }
            else{
                System.out.println("Already the final page.");
            }
        }

        else if( e.getSource() == this.btnCancel ){
            dispose();
        }

        else{
            for(int i=0; i<10; ++i){
                if( e.getSource() == this.btnCombination[i] ){
                    ReserveCheckDialog reserveCheckDialog = new ReserveCheckDialog(this.parent, "Are you sure to reserve?", this.availableHotel, 10 * this.page +i, this.searchControl);
                    reserveCheckDialog.setLocationRelativeTo(this);
                    reserveCheckDialog.setVisible(true);
                }
            }
        }

        this.labelPageNum.setText(String.format("%d/%d", this.page + 1, this.totalPage + 1));
    }
}