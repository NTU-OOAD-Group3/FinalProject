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
    private int page = 0;
    private int totalPage = 0;
    private ArrayList<AvailableHotel> availableHotels =  new ArrayList<AvailableHotel>(4); ;

    public SearchResults() {
        initUI();
    }

    private void initUI() {
        for(int i=0;i<11;i++)
            availableHotels.add(new AvailableHotel(i, 1, "test", "test", null));
        this.setLayout(new BorderLayout());

        this.listPanel = new JPanel();
        this.listPanel.setLayout(new GridBagLayout());
        JScrollPane listPanelScroll = new JScrollPane(listPanel);
        this.totalPage = this.availableHotels.size() / 10;
        for ( int i=0; i<10 && i<this.availableHotels.size(); ++i ) {
            resultArray[i] = new SearchResult(this.availableHotels.get(i));
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

    private void refreshUI(){
        int round = 10, base = 10 * this.page;
        if( this.page == this.totalPage ){
            round = this.availableHotels.size() % 10;
            for( int i=round;i<10;++i )
                this.resultArray[i].setVisible(false);
        }
        for( int i=0;i<round;++i ){
            this.resultArray[i].setVisible(true);
            this.resultArray[i].refreshUI(this.availableHotels.get(base + i));
        }
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