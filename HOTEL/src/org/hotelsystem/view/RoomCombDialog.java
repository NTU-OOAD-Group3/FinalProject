package org.hotelsystem.view;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoomCombDialog extends JDialog implements ActionListener{
    private int hotelID;
    private int page = 0;
    private int totalPage = 0;
    private RoomCombination[] combinationArray = new RoomCombination[10];
    private ArrayList<ArrayList<Integer>> roomCombination = new ArrayList<ArrayList<Integer>>(0) ;
    private JPanel listPanel;
    private JPanel bottomPanel;
    private JButton btnReserve;
    private JButton btnPrevPage;
    private JButton btnNextPage;
    private JLabel labelPageNum;

    public RoomCombDialog(ArrayList<ArrayList<Integer>> roomCombination, JFrame parent, String name){
        super(parent, name, true);
        // this.roomCombination = roomCombination;
        initUI();
    }

    private void initUI(){
        roomCombination.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
        this.listPanel = new JPanel();
        this.listPanel.setLayout(new GridBagLayout());
        JScrollPane listPanelScroll = new JScrollPane(listPanel);
        this.totalPage = this.roomCombination.size() / 10;

        for ( int i=0; i<10 && i<this.roomCombination.size(); ++i ) {
            combinationArray[i] = new RoomCombination(this.roomCombination.get(i));
            // commentArray[i].setVisible(false);
            this.addWithConstraints(listPanel, combinationArray[i],
                0, i, 1, 1, 1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        }

        this.bottomPanel = new JPanel();
        this.bottomPanel.setLayout(new GridLayout(1, 3));

        this.btnReserve = new JButton("Reserve");
        this.btnReserve.addActionListener(this);
        this.add(btnReserve, BorderLayout.CENTER);

        this.btnPrevPage = new JButton("<< Previous");
        this.btnPrevPage.addActionListener(this);
        this.bottomPanel.add(this.btnPrevPage);

        this.labelPageNum = new JLabel("0/0", JLabel.CENTER);
        this.bottomPanel.add(this.labelPageNum);
        
        this.btnNextPage = new JButton("Next >>");
        this.btnNextPage.addActionListener(this);
        this.bottomPanel.add(this.btnNextPage);

        // this.add(listPanel);
        this.add(listPanelScroll, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    
        this.setSize(300, 800);
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
    public void actionPerformed(ActionEvent e){  
        if( e.getSource() == this.btnPrevPage ){
            System.out.println("Previous page triggered.");
            if( this.page > 0 ){
                --this.page;
                // refreshUI();
            }
            else{ 
                System.out.println("Already the first page.");
            }
        }

        else if( e.getSource() == this.btnNextPage ){
            System.out.println("Next page triggered.");
            if( this.page < this.totalPage ){
                ++this.page;
                // refreshUI();
            }
            else{
                System.out.println("Already the final page.");
            }
        }
    }
}