package org.hotelsystem.view;

import java.util.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SearchBar extends JPanel implements ActionListener{
    private SearchUI searchUI;
    private JLabel labelLocality;
    private JLabel labelCheckin;
    private JLabel labelCheckout;
    private JLabel labelTotalNight;
    private JLabel labelRoom;
    private JLabel labelPeople;
    private JTextField tfCheckin;
    private JTextField tfCheckout;
    private JTextField tfTotalNight;
    private JTextField tfRoom;
    private JTextField tfPeople;
    private JButton btnSearch;
    private JButton btnCheckinDate;
    private JButton btnCheckoutDate;
    private JFrame parent;
    private JComboBox jcmbLocality;
    
    public SearchBar(JFrame parent, SearchUI searchUI) {
        this.parent = parent;
        this.searchUI = searchUI;
        this.setOpaque(false);
        initUI();
    }

    private void initUI() {
        this.setLayout(new GridBagLayout());

        this.labelLocality = new JLabel("Destination");
        // this.labelLocality.setBorder(new LineBorder(Color.RED));
        this.addWithConstraints(labelLocality, 0, 0, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.labelCheckin = new JLabel("Checkin");
        // this.labelCheckin.setBorder(new LineBorder(Color.RED));
        this.addWithConstraints(labelCheckin, 1, 0, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.labelCheckout = new JLabel("Checkout");
        // this.labelCheckout.setBorder(new LineBorder(Color.RED));
        this.addWithConstraints(labelCheckout, 2, 0, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.labelTotalNight = new JLabel("Nights");
        // this.labelTotalNight.setBorder(new LineBorder(Color.RED));
        this.addWithConstraints(labelTotalNight, 3, 0, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);
        
        this.labelRoom = new JLabel("Rooms");
        // this.labelRoom.setBorder(new LineBorder(Color.RED));
        this.addWithConstraints(labelRoom, 4, 0, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.labelPeople = new JLabel("People");
        // this.labelPeople.setBorder(new LineBorder(Color.RED));
        this.addWithConstraints(labelPeople, 5, 0, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        // String Locality[]={"台北","Taichung","Kaohsiung"};
        ArrayList<String> tmpLocality = this.searchUI.getLocality();
        String locality[] = new String[tmpLocality.size()];
        for(int i=0; i<tmpLocality.size(); ++i)
            locality[i] = tmpLocality.get(i);

        this.jcmbLocality = new JComboBox(locality);
        this.addWithConstraints(jcmbLocality, 0, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.tfCheckin = new JTextField(8);
        this.tfCheckin.setEditable(false);
        this.addWithConstraints(tfCheckin, 1, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);
        
        ImageIcon iconCalendar = new ImageIcon("resources/calendar.png");
        iconCalendar.setImage(iconCalendar.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
        
        this.btnCheckinDate = new JButton(iconCalendar);
        this.btnCheckinDate.addActionListener(this);
        this.addWithConstraints(btnCheckinDate, 1, 2, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.tfCheckout = new JTextField(8);
        this.tfCheckout.setEditable(false);
        this.addWithConstraints(tfCheckout, 2, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.btnCheckoutDate = new JButton(iconCalendar);
        this.btnCheckoutDate.addActionListener(this);
        this.addWithConstraints(btnCheckoutDate, 2, 2, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.tfTotalNight = new JTextField(8);
        this.tfTotalNight.setEditable(false);
        this.addWithConstraints(tfTotalNight, 3, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.tfRoom = new JTextField(8);
        this.addWithConstraints(tfRoom, 4, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.tfPeople = new JTextField(8);
        this.addWithConstraints(tfPeople, 5, 1, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.CENTER);

        this.btnSearch = new JButton("Search");
        this.btnSearch.addActionListener(this);
        this.addWithConstraints(btnSearch, 5, 2, 1, 1, 1, 1,
            GridBagConstraints.NONE, GridBagConstraints.NORTH);
    }

    private void addWithConstraints(JComponent c, int gridx, int gridy,
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
		// gbc.insets = new Insets(3, 3, 3, 3);
		this.add(c, gbc);
    }
    
    public void actionPerformed(ActionEvent e){ 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        if( e.getSource() == this.btnSearch ){
            System.out.println("Search triggered.");
            try{
                Calendar checkinDate = Calendar.getInstance();
                Calendar checkoutDate = Calendar.getInstance();
                checkinDate.setTime(sdf.parse(this.tfCheckin.getText()));
                checkoutDate.setTime(sdf.parse(this.tfCheckout.getText()));
                
                int checkin = Integer.valueOf(String.format("%4d%02d%02d", checkinDate.get(Calendar.YEAR), checkinDate.get(Calendar.MONTH) + 1, checkinDate.get(Calendar.DAY_OF_MONTH)));
                int checkout = Integer.valueOf(String.format("%4d%02d%02d", checkoutDate.get(Calendar.YEAR), checkoutDate.get(Calendar.MONTH) + 1, checkoutDate.get(Calendar.DAY_OF_MONTH)));
                
                if( (this.tfTotalNight.getText().isEmpty()) || (this.tfRoom.getText().isEmpty()) || (this.tfPeople.getText().isEmpty())){
                    JOptionPane.showMessageDialog(this, "Please select dates, room number, people number before searching", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                else if( Integer.valueOf(this.tfRoom.getText()) == 0 || Integer.valueOf(this.tfPeople.getText()) == 0 ){
                    JOptionPane.showMessageDialog(new JFrame(), "Number of room and people must be greater than zero.", "Zero number error", JOptionPane.ERROR_MESSAGE);
                    this.tfRoom.setText("");
                    this.tfPeople.setText("");
                }
                else{
                    this.searchUI.triggerSearch((String)this.jcmbLocality.getSelectedItem(), checkin, checkout, Integer.valueOf(tfRoom.getText()), Integer.valueOf(tfPeople.getText()));
                    System.out.println("Search Comlete.");
                }
            }
            catch(Exception error){
                JOptionPane.showMessageDialog(this, "Please select dates, room number, people number before searching.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            // searchAvailableHotel(this.jcmbLocality.getText(), checkin, checkout, Integer.valueOf(this.tfRoom), Integer.valueOf(this.tfPeople));
        }
        else if( e.getSource() == this.btnCheckinDate ){
            DatePickerDialog datePickDlg = new DatePickerDialog(this.parent, "Select Date");
            datePickDlg.setVisible(true);
            if (datePickDlg.isPicked()) {
                System.out.println("select checkin date.");
            	this.tfCheckin.setText(datePickDlg.getPickedDate());
            }
        }
        else if( e.getSource() == this.btnCheckoutDate ){
            DatePickerDialog datePickDlg = new DatePickerDialog(this.parent, "Select Date");
            datePickDlg.setVisible(true);
            if (datePickDlg.isPicked()) {
                System.out.println("select checkout date.");
            	this.tfCheckout.setText(datePickDlg.getPickedDate());
            }
        }
        try{
            if( !(this.tfCheckin.getText().equals("") || this.tfCheckout.getText().equals("")) ){  
                Date checkinDate = sdf.parse(this.tfCheckin.getText());
                Date checkoutDate = sdf.parse(this.tfCheckout.getText());
                long diffInMillies = checkoutDate.getTime() - checkinDate.getTime();
                if( diffInMillies < 1){
                    JOptionPane.showMessageDialog(this, "Checkout time must be later than checkin time.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    this.tfTotalNight.setText("");
                    this.tfCheckout.setText("");
                }
                else{
                    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    this.tfTotalNight.setText(String.valueOf(diff));
                }
            }
        }
        catch(Exception error){
            
        }
    }  

}