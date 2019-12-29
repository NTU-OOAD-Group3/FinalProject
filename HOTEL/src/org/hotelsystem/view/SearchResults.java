package org.hotelsystem.view;

import org.hotelsystem.model.AvailableHotel;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;

public class SearchResults extends JPanel implements ActionListener{
    private JPanel listPanel, bottomPanel;
    private SearchResult[] resultArray = new SearchResult[10];
    private JButton btnPrevPage, btnNextPage;
    private JLabel labelPageNum;

    public SearchResults() {
        initUI();
    }

    private void initUI() {
        this.setLayout(new BorderLayout());

        this.listPanel = new JPanel();
        this.listPanel.setLayout(new GridBagLayout());
        JScrollPane listPanelScroll = new JScrollPane(listPanel);
        AvailableHotel availablehotel = new AvailableHotel(0, 1, "test", "test", null);
        for ( int i=0; i<10; ++i ) {
            resultArray[i] = new SearchResult(availablehotel);
            this.addWithConstraints(listPanel, resultArray[i],
                0, i, 1, 1, 1, 1,
                GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        }

        this.bottomPanel = new JPanel();
        this.bottomPanel.setLayout(new GridLayout(1, 3));

        this.btnPrevPage = new JButton("<< Previous");
        this.btnPrevPage.addActionListener(this);
        this.bottomPanel.add(this.btnPrevPage);

        this.labelPageNum = new JLabel("0/0", JLabel.CENTER);
        this.bottomPanel.add(this.labelPageNum);
        
        this.btnNextPage = new JButton("Next >>");
        this.btnNextPage.addActionListener(this);
        this.bottomPanel.add(this.btnNextPage);

        this.add(listPanelScroll, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
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
        }  
        else if( e.getSource() == this.btnNextPage ){
            System.out.println("Next page triggered.");
        }
    }

    public static void main(String[] args) {
        JFrame testFrame = new JFrame("SearchResults testFrame");
        testFrame.setSize(600, 600);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel SearchResults = new SearchResults();
        testFrame.add(SearchResults);

        testFrame.setVisible(true);
    }
}