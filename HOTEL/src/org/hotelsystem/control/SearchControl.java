package org.hotelsystem.control;

import org.hotelsystem.model.AvailableHotel;
import org.hotelsystem.model.Hotel;
import org.hotelsystem.model.DBUtil;
import org.hotelsystem.view.SearchUI;

import java.util.ArrayList;

public class SearchControl {
    private ArrayList<Hotel> hotels = new ArrayList<Hotel>(0);
    private SearchUI searchUI;
    //
    // private DBUtil dbutils = new DBUtil("140.112.21.82", "ooad", "ooad", "HOTEL");
    //
	public SearchControl(){
    }

    public void setUI(SearchUI searchUI){
        this.searchUI = searchUI;
    }

    public ArrayList<AvailableHotel> searchAvailableHotel(String locality, int checkin, int checkout, int room, int people){
        // this.hotels = dbutils.getHotels(locality, checkin, checkout);

        return null;   
    }
    
}