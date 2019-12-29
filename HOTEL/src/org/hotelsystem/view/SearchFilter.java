package org.hotelsystem.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SearchFilter extends JPanel implements ActionListener {
    private JLabel labelTitle;
    private JLabel labelPriceLower;
    private JTextField tfPriceLower;
    private JLabel labelPriceUpper;
    private JTextField tfPriceUpper;
    private JLabel labelStar;
    private JRadioButton[] rbtnStars = new JRadioButton[6];
    private ButtonGroup btnGroupStars;

    public SearchFilter() {
        initUI();
    }

    private void initUI() {
        LineBorder line = new LineBorder(Color.BLACK);
        EmptyBorder empty = new EmptyBorder(20, 20, 20, 20);
        this.setBorder(new CompoundBorder(line, empty));
        this.setLayout(new GridBagLayout());

        this.labelTitle = new JLabel("Search Filter");
        this.labelTitle.setHorizontalAlignment(JLabel.CENTER);
        this.addWithConstraints(this, this.labelTitle, 0, 0, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        this.labelPriceLower = new JLabel("Price Lower Bound");
        this.addWithConstraints(this, this.labelPriceLower, 0, 1, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        this.tfPriceLower = new JTextField();
        this.addWithConstraints(this, this.tfPriceLower, 0, 2, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        this.labelPriceUpper = new JLabel("Price Upper Bound");
        this.addWithConstraints(this, this.labelPriceUpper, 0, 3, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        this.tfPriceUpper = new JTextField();
        this.addWithConstraints(this, this.tfPriceUpper, 0, 4, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        this.labelStar = new JLabel("Select Stars");
        this.addWithConstraints(this, this.labelStar, 0, 5, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        this.btnGroupStars = new ButtonGroup();
        this.rbtnStars[0] = new JRadioButton("ALL");
        this.rbtnStars[0].setSelected(true);
        this.btnGroupStars.add(rbtnStars[0]);
        this.addWithConstraints(this, this.rbtnStars[0], 0, 6, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        for ( int i=5; i>0; --i ) {
            this.rbtnStars[i] = new JRadioButton(String.valueOf(i));
            this.rbtnStars[i].addActionListener(this);
            this.btnGroupStars.add(rbtnStars[i]);
            this.addWithConstraints(this, this.rbtnStars[i], 0, 12-i, 1, 1, 1, 1,
                GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        }
    }

    public void actionPerformed(ActionEvent e){
        for( int i=1;i<6;++i ){
            if( e.getSource() == this.rbtnStars[i] ){
                System.out.printf("Refresh, star = %d\n", i);
            }
        }
        if( e.getSource() == this.rbtnStars[0] ){
            System.out.printf("Refresh, all\n");
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
		// gbc.insets = new Insets(5, 5, 5, 5);
		p.add(c, gbc);
    }
}