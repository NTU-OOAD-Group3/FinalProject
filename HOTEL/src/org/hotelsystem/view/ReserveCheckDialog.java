package org.hotelsystem.view;

import org.hotelsystem.model.Order;
import org.hotelsystem.model.AvailableHotel;
import org.hotelsystem.control.SearchControl;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ReserveCheckDialog extends JDialog implements ActionListener{
    private JFrame parent;
    private JPanel buttonPanel;
    private JLabel labelCombination;
    private JLabel labelErrorMessage;
    private JButton btnReserve;
    private JButton btnCancel;
    private JButton btnErrorOK;
    private JDialog errorDialog;
    private SearchControl searchControl;
    private String combinationInfo;
    private AvailableHotel availableHotel;
    private ArrayList<Integer> combination;
    private int price;

    ReserveCheckDialog(JFrame parent, String name, AvailableHotel availableHotel, int index, SearchControl searchControl){
        super(parent, name, true);
        this.parent = parent;
        this.searchControl = searchControl;
        this.availableHotel = availableHotel;
        this.combination = this.availableHotel.getRoomCombination().get(index);
        this.price = this.availableHotel.getCombinationPrice().get(index);
        this.combinationInfo = String.format("Single: %d, Double: %d, Quad: %d, Price: %d", combination.get(0), combination.get(1), combination.get(2), this.price);
        initUI();
    }

    private void initUI(){
        this.labelCombination = new JLabel(this.combinationInfo);
        this.labelCombination.setHorizontalAlignment(SwingConstants.CENTER);
        this.buttonPanel = new JPanel(new FlowLayout());
        this.btnReserve = new JButton("Reserve!");
        this.btnReserve.addActionListener(this);
        this.btnCancel = new JButton("Cancel");
        this.btnCancel.addActionListener(this);
        this.buttonPanel.add(this.btnCancel);
        this.buttonPanel.add(this.btnReserve);
        this.add(this.labelCombination, BorderLayout.CENTER);
        this.add(this.buttonPanel, BorderLayout.SOUTH);
        this.setSize(300, 90);
    }

    public void actionPerformed(ActionEvent e){
        if( e.getSource() == this.btnReserve ){
            System.out.println("RESERVE !!!!!");
            this.dispose();
            Order order = new Order(-1, this.searchControl.getUserID(), this.availableHotel.getHotelID(), null, this.searchControl.getCheckin(), this.searchControl.getCheckout(), this.price);
            if( this.searchControl.insertOrder(order, this.combination.get(0), this.combination.get(1), this.combination.get(2)) ){
            }
            else{
                this.errorDialog = new JDialog(this.parent, "Reserve fail", true);
                this.labelErrorMessage = new JLabel("<html>Someone just ordered while you're considering.<br/>Please select another one, or Search again.</html>");

                this.btnErrorOK = new JButton("OK");
                this.btnErrorOK.addActionListener(this);
                this.errorDialog.setSize(300,100);

                this.errorDialog.add(labelErrorMessage, BorderLayout.CENTER);
                this.errorDialog.add(btnErrorOK, BorderLayout.SOUTH);
                this.errorDialog.setLocationRelativeTo(null);
                this.errorDialog.setVisible(true);
            }
        }
        else if( e.getSource() == this.btnCancel ){
            dispose();
        }
        else{
            this.errorDialog.dispose();
        }
    }
}