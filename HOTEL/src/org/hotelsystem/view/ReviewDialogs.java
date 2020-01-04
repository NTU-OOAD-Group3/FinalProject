package org.hotelsystem.view;

import org.hotelsystem.model.Review;
import org.hotelsystem.control.SearchControl;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReviewDialogs extends JDialog implements ActionListener{
    private int hotelID;
    private int page = 0;
    private int totalPage = 0;
    private ReviewPanel[] ReviewArray = new ReviewPanel[10];
    private ArrayList<Review> reviews = new ArrayList<Review>(0) ;
    private JPanel listPanel;
    private JPanel bottomPanel;
    private JButton btnPrevPage;
    private JButton btnNextPage;
    private JLabel labelPageNum;
    private SearchControl searchControl;

    public ReviewDialogs(int hotelID, JFrame parent, String name, ArrayList<Review> reviews, int page, int totalPage, SearchControl searchControl){
        super(parent, name, true);
        this.reviews = reviews;
        this.page = page;
        this.totalPage = totalPage;
        this.searchControl = searchControl;
        initUI();
    }

    private void initUI(){
        JPanel pan = new JPanel();
        pan.setLayout(new BorderLayout());
        
        this.listPanel = new JPanel();
        this.listPanel.setLayout(new GridBagLayout());
        JScrollPane listPanelScroll = new JScrollPane(listPanel);
        
        for ( int i=0; i<10 ; ++i ) {
            ReviewArray[i] = new ReviewPanel();    
            ReviewArray[i].setVisible(false);
            this.addWithConstraints(listPanel, ReviewArray[i],
                0, i, 1, 1, 1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
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

        // this.add(listPanel);
        this.add(listPanelScroll, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    
        this.setSize(800, 600);
    }

    public void refresh(int hotelID, ArrayList<Review> reviews, int page, int totalPage){
        for ( int i=0; i<10 && i<reviews.size(); ++i ) {
            this.ReviewArray[i].setReview(reviews.get(i));
            this.ReviewArray[i].setVisible(true);
        }
        for( int i=reviews.size(); i<10; ++i){
            this.ReviewArray[i].setVisible(false);
        }
        this.labelPageNum.setText(String.format("%d/%d", page + 1, totalPage + 1));
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
            this.searchControl.setReviewPage(-1);
        }

        else if( e.getSource() == this.btnNextPage ){
            System.out.println("Next page triggered.");
            this.searchControl.setReviewPage(1);
        }
    }
}