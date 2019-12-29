package org.hotelsystem.view;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class DatePickerDialog extends JDialog implements ActionListener {
    private int year = Calendar.getInstance().get(Calendar.YEAR);
    private int month = Calendar.getInstance().get(Calendar.MONTH);
    private int day = Calendar.getInstance().get(Calendar.DATE);
    private JPanel calendarPanel, bottomPanel;
    private JButton[] dayButtons;
    private JButton btnPrevious, btnNext;
    private JLabel lbCurrentMonth;
    private boolean picked = false;

    public DatePickerDialog(JFrame parent, String name) {
        super(parent, name, true);
        init();
    }
    
    private void init() {
    	calendarPanel = new JPanel(new GridLayout(7, 7));
        calendarPanel.setPreferredSize(new Dimension(430, 120));

        dayButtons = new JButton[49];
        String[] header = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
        for (int i = 0; i < dayButtons.length; i++) {
            dayButtons[i] = new JButton();
            if (i < 7) {
                // dayButtons[i].setEnabled(false);
                dayButtons[i].setText(header[i]);
                dayButtons[i].setOpaque(true);
                dayButtons[i].setForeground(Color.RED);
                dayButtons[i].setBackground(Color.GRAY);
            } else {
                dayButtons[i].addActionListener(this);
            }
            calendarPanel.add(dayButtons[i]);
        }

        bottomPanel = new JPanel(new GridLayout(1, 3));

        btnPrevious = new JButton("<< Previous");
        btnPrevious.addActionListener(this);
        bottomPanel.add(btnPrevious);

        lbCurrentMonth = new JLabel("", JLabel.CENTER);
        bottomPanel.add(lbCurrentMonth);

        btnNext = new JButton("Next >>");
        btnNext.addActionListener(this);
        bottomPanel.add(btnNext);

        add(calendarPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        pack();
//        setLocationRelativeTo(parent);
        refreshDisplay();
    }
    private void refreshDisplay() {
        for (int i = 7; i < dayButtons.length; i++) {
            dayButtons[i].setText("");
            dayButtons[i].setEnabled(false);
        }
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 6 + dayOfWeek, day = 1; day <= daysInMonth; i++, day++) {
            dayButtons[i].setText("" + day);
            dayButtons[i].setEnabled(true);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
        lbCurrentMonth.setText(sdf.format(cal.getTime()));
    }
    public boolean isPicked() {
        return picked;
    }
    public String getPickedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return sdf.format(cal.getTime());
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("<< Previous")) {
            if (month == 1) {
                year--;
                month = 12;
            } else {
                month--;
            }
            refreshDisplay();
        } else if (e.getActionCommand().equals("Next >>")) {
            if (month == 12) {
                year++;
                month = 1;
            } else {
                month++;
            }
            refreshDisplay();
        } else {
            day = Integer.valueOf(e.getActionCommand());
            picked = true;
            dispose();
        }
    }
}