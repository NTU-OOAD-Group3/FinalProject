package org.hotelsystem.view;

import org.hotelsystem.control.SearchControl;

import java.util.Collections;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SearchFilter extends JPanel implements ActionListener {
    private SearchControl searchControl;
    private JLabel labelTitle;
    private JLabel labelPriceLower;
    private JTextField tfPriceLower;
    private JLabel labelPriceUpper;
    private JTextField tfPriceUpper;
    private JLabel labelStar;
    private JLabel labelPrice;
    private JRadioButton[] rbtnStars = new JRadioButton[6];
    private ButtonGroup btnGroupStars;
    private JButton btnApply;
    private ButtonGroup btnGroupPrice;
    private JRadioButton rbtnPriceL2H;
    private JRadioButton rbtnPriceH2L;

    public SearchFilter(SearchControl searchControl) {
        this.searchControl = searchControl;
        this.setOpaque(false);
        initUI();
    }

    private void initUI() {
        LineBorder line = new LineBorder(Color.BLACK);
        EmptyBorder empty = new EmptyBorder(20, 20, 20, 20);
        this.setBorder(new CompoundBorder(line, empty));
        this.setLayout(new GridBagLayout());

        this.labelTitle = new JLabel("Search Filter");
        this.labelTitle.setHorizontalAlignment(JLabel.CENTER);
        this.labelTitle.setFont(new Font("Serif", Font.BOLD, 18));
        this.addWithConstraints(this, this.labelTitle, 0, 0, 2, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        this.labelPriceLower = new JLabel("Price Lower Bound");
        this.addWithConstraints(this, this.labelPriceLower, 0, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.labelPriceUpper = new JLabel("Price Upper Bound");
        this.addWithConstraints(this, this.labelPriceUpper, 1, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.tfPriceLower = new JTextField(10);
        this.addWithConstraints(this, this.tfPriceLower, 0, 2, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.tfPriceUpper = new JTextField(10);
        this.addWithConstraints(this, this.tfPriceUpper, 1, 2, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.labelStar = new JLabel("Select Stars");
        this.addWithConstraints(this, this.labelStar, 0, 3, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.labelPrice = new JLabel("Price order");
        this.addWithConstraints(this, this.labelPrice, 1, 3, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);
        
        this.rbtnPriceL2H = new JRadioButton("Low to High");
        this.rbtnPriceL2H.setSelected(true);
        this.rbtnPriceL2H.setOpaque(false);
        this.addWithConstraints(this, this.rbtnPriceL2H, 1, 6, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.rbtnPriceH2L = new JRadioButton("High to Low");
        this.rbtnPriceH2L.setOpaque(false);
        this.addWithConstraints(this, this.rbtnPriceH2L, 1, 8, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.btnGroupPrice = new ButtonGroup();
        this.btnGroupPrice.add(this.rbtnPriceL2H);
        this.btnGroupPrice.add(this.rbtnPriceH2L);

        this.btnGroupStars = new ButtonGroup();
        this.rbtnStars[0] = new JRadioButton("ALL");
        this.rbtnStars[0].setOpaque(false);
        this.rbtnStars[0].setSelected(true);
        this.btnGroupStars.add(rbtnStars[0]);
        this.addWithConstraints(this, this.rbtnStars[0], 0, 4, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        for ( int i=5; i>0; --i ) {
            this.rbtnStars[i] = new JRadioButton(String.valueOf(i));
            this.rbtnStars[i].setOpaque(false);
            this.btnGroupStars.add(rbtnStars[i]);
            this.addWithConstraints(this, this.rbtnStars[i], 0, 10-i, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.CENTER);
        }

        this.btnApply = new JButton("Apply filter");
        this.btnApply.addActionListener(this);
        this.addWithConstraints(this, this.btnApply, 0, 10, 2, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);
    }

    public void actionPerformed(ActionEvent e){
        try{
            int priceLower = 0;
            int priceUpper = Integer.MAX_VALUE;
            int selectedStarIdx = 0;
            int selectedPriceIdx = 0;
            if( !this.tfPriceLower.getText().equals("") )
                priceLower = Integer.valueOf(this.tfPriceLower.getText());
                
            if( !this.tfPriceUpper.getText().equals("") )
                priceUpper = Integer.valueOf(this.tfPriceUpper.getText());

            for(int i=1;i<6;i++)
                if( this.rbtnStars[i].isSelected() ){
                    selectedStarIdx = i;
                }
            if( this.rbtnPriceH2L.isSelected() )
                selectedPriceIdx = 1;
            System.out.printf("Apply filter triggered.\n lower: %d, upper: %d, Star: %d, price order: %d\n", priceLower, priceUpper, selectedStarIdx, selectedPriceIdx);
            this.searchControl.applySearchFilter(priceLower, priceUpper, selectedStarIdx, selectedPriceIdx);
        }
        catch(Exception error){
            JOptionPane.showMessageDialog(new JFrame(), "Please input integer into blockes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refresh(){
        this.tfPriceLower.setText("");
        this.tfPriceUpper.setText("");
        this.rbtnStars[0].setSelected(true);
        this.rbtnPriceL2H.setSelected(true);
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
		// gbc.insets = new Insets(5, 5, 5, 5);
		p.add(c, gbc);
    }
}