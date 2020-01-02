package org.hotelsystem.view;

import org.hotelsystem.model.AvailableHotel;
import org.hotelsystem.control.SearchControl;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;

public class SearchResults extends JPanel implements ActionListener{
    private JPanel listPanel, bottomPanel;
    private SearchResult[] resultArray = new SearchResult[10];
    private JButton btnPrevPage, btnNextPage;
    private JLabel labelPageNum;
    private JFrame parent;
    private int page = 0;
    private int totalPage = 0;
    private SearchControl searchControl;

    public SearchResults(JFrame parent, SearchControl searchControl) {
        this.parent = parent;
        this.searchControl = searchControl;
        initUI();
    }

    private void initUI() {
        this.setLayout(new BorderLayout());
        this.listPanel = new JPanel();
        this.listPanel.setLayout(new GridBagLayout());
        JScrollPane listPanelScroll = new JScrollPane(listPanel);

        this.totalPage = 0;
        for ( int i=0; i<10 ; ++i ){
            resultArray[i] = new SearchResult(this.parent, new AvailableHotel(i, i, "test", "test", null, null), searchControl);
            // resultArray[i].setVisible(false);
            this.addWithConstraints(listPanel, resultArray[i],
                0, i, 1, 1, 1, 1,
                GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        }

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

        this.add(listPanelScroll, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void refreshUI(ArrayList<AvailableHotel> availableHotels){
        int round = 10;
        if( this.page == this.totalPage ){
            round = availableHotels.size() % 10;
            for( int i=round; i<10; ++i )
                this.resultArray[i].setVisible(false);
        }
        for( int i=0; i<10; ++i ){
            this.resultArray[i].setVisible(true);
            this.resultArray[i].refreshUI(availableHotels.get(i));
        }
    }

    public void setAvailableHotel(ArrayList<AvailableHotel> availableHotels, int page, int totalPage){
        this.page = page;
        this.totalPage = totalPage;
        this.labelPageNum.setText(String.format("%d/%d", this.page + 1, this.totalPage + 1));
        this.refreshUI(availableHotels);
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
            this.searchControl.setSearchResultsPage(-1);
        }

        else if( e.getSource() == this.btnNextPage ){
            System.out.println("Next page triggered.");
            this.searchControl.setSearchResultsPage(1);
        }

        this.labelPageNum.setText(String.format("%d/%d", this.page + 1, this.totalPage + 1));
    }

}