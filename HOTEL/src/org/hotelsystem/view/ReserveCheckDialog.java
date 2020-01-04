package org.hotelsystem.view;

import org.hotelsystem.model.AvailableHotel;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ReserveCheckDialog extends JDialog implements ActionListener{
    private JLabel labelCombination;
    private JButton btnReserve;

    ReserveCheckDialog(JFrame parent, String name, String combinationInfo){
        super(parent, name, true);
        initUI(combinationInfo);
    }

    private void initUI(String combinationInfo){
        this.labelCombination = new JLabel(combinationInfo);
        this.btnReserve = new JButton("Reserve!");
        this.add(this.labelCombination, BorderLayout.NORTH);
        this.add(this.btnReserve, BorderLayout.SOUTH);
        this.setSize(300, 90);
    }

    public void actionPerformed(ActionEvent e){
        if( e.getSource() == this.btnReserve ){
            System.out.println("CHEKCJIEJP");
        }
    }
}