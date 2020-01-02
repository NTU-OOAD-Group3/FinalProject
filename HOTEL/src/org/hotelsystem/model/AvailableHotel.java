package org.hotelsystem.model;
import java.util.ArrayList;

public class AvailableHotel {
    private int hotelID;
    private int hotelStar;
    private String locality;
    private String streetAddress;
    private ArrayList<ArrayList<Integer>> roomCombination;
    private ArrayList<Integer> combinationPrice;
    
    public AvailableHotel(int hotelID, int hotelStar, String locality, String streetAddress,
                          ArrayList<ArrayList<Integer>> roomCombination,
                          ArrayList<Integer> combinationPrice) {
        this.hotelID = hotelID;
        this.hotelStar = hotelStar;
        this.locality = locality;
        this.streetAddress = streetAddress;
        this.roomCombination = roomCombination;
        this.combinationPrice = combinationPrice;
    }

    public int getHotelID() { return this.hotelID; }

    public int getHotelStar() { return this.hotelStar; }

    public String getLocality() { return this.locality; }

    public String getStreetAddress() { return this.streetAddress; }

    public ArrayList<ArrayList<Integer>> getRoomCombination(){
        return this.roomCombination;
    }

    public ArrayList<Integer> getCombinationPrice() {
        return this.combinationPrice;
    }
}