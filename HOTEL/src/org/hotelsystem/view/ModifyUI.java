package org.hotelsystem.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class ModifyUI extends JPanel{
    private JPanel orderIDbar;
	private JPanel originalOrderbar;
	private JPanel modifiedOrderbar;
	
	public ModifyUI() {
		this.setLayout(new GridBagLayout());
		initUI();
	}
	
	private void initUI() {
        this.orderIDbar = new JPanel();
        this.orderIDbar.setLayout(new GridBagLayout());
		this.orderIDbar.setBorder(new LineBorder(Color.RED));
		this.addWithConstraints(this, this.orderIDbar, 0, 0, 7, 2, 7, 2,
				GridBagConstraints.BOTH, GridBagConstraints.WEST, 0, 20, 20, 20);

        JLabel lbOrderID = new JLabel("Order ID");
        lbOrderID.setHorizontalAlignment(JLabel.CENTER);
        this.addWithConstraints(this.orderIDbar, lbOrderID, 0, 0, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH, 10, 10, 0, 0);
        
        JTextField tfOrderID = new JTextField(20);
        tfOrderID.setHorizontalAlignment(JTextField.CENTER);
        this.addWithConstraints(this.orderIDbar, tfOrderID, 0, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 10, 10, 0, 0);

        JButton btnQueryOrder = new JButton("Query");
        this.addWithConstraints(this.orderIDbar, btnQueryOrder, 0, 2, 1, 1, 1, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST, 0, 0, 1000, 50);

        JLabel lbOriginalOrder = new JLabel("Original order");
        lbOriginalOrder.setHorizontalAlignment(JLabel.CENTER);
        this.addWithConstraints(this, lbOriginalOrder, 0, 0, 7, 2, 7, 2,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        this.originalOrderbar = new JPanel();
        this.originalOrderbar.setLayout(new GridBagLayout());
		this.originalOrderbar.setBorder(new LineBorder(Color.GREEN));
		this.addWithConstraints(this, this.originalOrderbar, 0, 2, 7, 4, 7, 4,
				GridBagConstraints.BOTH, GridBagConstraints.WEST, 0, 20, 20, 20);
        
        JLabel lbOriHotelName = new JLabel("Hotel");
        this.addWithConstraints(this.originalOrderbar, lbOriHotelName, 0, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 10, 10);
        
        JTextField tfOriHotelName = new JTextField(15);
        this.addWithConstraints(this.originalOrderbar, tfOriHotelName, 0, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbOriCheckInTime = new JLabel("Check in");
        this.addWithConstraints(this.originalOrderbar, lbOriCheckInTime, 1, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        JTextField tfOriCheckInTime = new JTextField(8);
        this.addWithConstraints(this.originalOrderbar, tfOriCheckInTime, 1, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbOriCheckOutTime = new JLabel("Check out");
        this.addWithConstraints(this.originalOrderbar, lbOriCheckOutTime, 2, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);
        
        JTextField tfOriCheckOutTime = new JTextField(8);
        this.addWithConstraints(this.originalOrderbar, tfOriCheckOutTime, 2, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbOriSingleNum = new JLabel("Single ");
        this.addWithConstraints(this.originalOrderbar, lbOriSingleNum, 3, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        JTextField tfOriSingleNum = new JTextField(8);
        this.addWithConstraints(this.originalOrderbar, tfOriSingleNum, 3, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbOriDoubleNum = new JLabel("Double ");
        this.addWithConstraints(this.originalOrderbar, lbOriDoubleNum, 4, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        JTextField tfOriDoubleNum = new JTextField(8);
        this.addWithConstraints(this.originalOrderbar, tfOriDoubleNum, 4, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbOriQuadNum = new JLabel("Quad");
        this.addWithConstraints(this.originalOrderbar, lbOriQuadNum, 5, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);
        
        JTextField tfOriQuadNum = new JTextField(8);
        this.addWithConstraints(this.originalOrderbar, tfOriQuadNum, 5, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbOriPrice = new JLabel("Price");
        this.addWithConstraints(this.originalOrderbar, lbOriPrice, 6, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);
        
        JTextField tfOriPrice = new JTextField(8);
        this.addWithConstraints(this.originalOrderbar, tfOriPrice, 6, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbModifiedOrder = new JLabel("Modified order");
        lbModifiedOrder.setHorizontalAlignment(JLabel.CENTER);
        this.addWithConstraints(this, lbModifiedOrder, 0, 2, 7, 2, 7, 2,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        this.modifiedOrderbar = new JPanel();
        this.modifiedOrderbar.setLayout(new GridBagLayout());
		this.modifiedOrderbar.setBorder(new LineBorder(Color.BLUE));
		this.addWithConstraints(this, this.modifiedOrderbar, 0, 6, 7, 4, 7, 4,
                GridBagConstraints.BOTH, GridBagConstraints.WEST, 0, 20, 20, 20);   

        JLabel lbModHotelName = new JLabel("Hotel");
        this.addWithConstraints(this.modifiedOrderbar, lbModHotelName, 0, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 10, 10);
        
        JTextField tfModHotelName = new JTextField(15);
        this.addWithConstraints(this.modifiedOrderbar, tfModHotelName, 0, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbModCheckInTime = new JLabel("Check in");
        this.addWithConstraints(this.modifiedOrderbar, lbModCheckInTime, 1, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        JTextField tfModCheckInTime = new JTextField(8);
        this.addWithConstraints(this.modifiedOrderbar, tfModCheckInTime, 1, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbModCheckOutTime = new JLabel("Check out");
        this.addWithConstraints(this.modifiedOrderbar, lbModCheckOutTime, 2, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);
        
        JTextField tfModCheckOutTime = new JTextField(8);
        this.addWithConstraints(this.modifiedOrderbar, tfModCheckOutTime, 2, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbModSingleNum = new JLabel("Single ");
        this.addWithConstraints(this.modifiedOrderbar, lbModSingleNum, 3, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        JTextField tfModSingleNum = new JTextField(8);
        this.addWithConstraints(this.modifiedOrderbar, tfModSingleNum, 3, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbModDoubleNum = new JLabel("Double ");
        this.addWithConstraints(this.modifiedOrderbar, lbModDoubleNum, 4, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);

        JTextField tfModDoubleNum = new JTextField(8);
        this.addWithConstraints(this.modifiedOrderbar, tfModDoubleNum, 4, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbModQuadNum = new JLabel("Quad");
        this.addWithConstraints(this.modifiedOrderbar, lbModQuadNum, 5, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);
        
        JTextField tfModQuadNum = new JTextField(8);
        this.addWithConstraints(this.modifiedOrderbar, tfModQuadNum, 5, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);

        JLabel lbModPrice = new JLabel("Price");
        this.addWithConstraints(this.modifiedOrderbar, lbModPrice, 6, 0, 1, 1, 1, 1,
                GridBagConstraints.NONE, GridBagConstraints.SOUTH, 0, 0, 0, 0);
        
        JTextField tfModPrice = new JTextField(8);
        this.addWithConstraints(this.modifiedOrderbar, tfModPrice, 6, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH, 0, 0, 0, 0);
        
        JButton btnCheckAndSend = new JButton("Check");
        this.addWithConstraints(this, btnCheckAndSend, 0, 10, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH);

    }
	
	private void addWithConstraints(JComponent mother, JComponent c, int gridx, int gridy,
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
		gbc.insets = new Insets(20, 20, 20, 20);
		mother.add(c, gbc);
    }

    private void addWithConstraints(JComponent mother, JComponent c, int gridx, int gridy,
            int gridwidth, int gridheight, int weightx, int weighty,
            int fill, int anchor, int insetup, int insetdown, int insetleft, int insetright){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.fill = fill;
		gbc.anchor = anchor;
        gbc.insets = new Insets(insetup, insetleft, insetdown, insetright);
        mother.add(c, gbc);
    }
}