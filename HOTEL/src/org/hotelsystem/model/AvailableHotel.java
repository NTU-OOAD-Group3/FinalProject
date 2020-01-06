package org.hotelsystem.model;
import java.util.*;

public class AvailableHotel implements Comparable{
    private int hotelID;
    private int hotelStar;
    private int minPrice;
    private String locality;
    private String streetAddress;
    private ArrayList<ArrayList<Integer>> roomCombination;
    private ArrayList<Integer> combinationPrice;
    
    public AvailableHotel(int hotelID, int hotelStar, String locality, String streetAddress,
                          ArrayList<ArrayList<Integer>> roomCombination,
                          ArrayList<Integer> combinationPrice){
        this.hotelID = hotelID;
        this.hotelStar = hotelStar;
        this.locality = locality;
        this.streetAddress = streetAddress;
        this.roomCombination = roomCombination;
        this.combinationPrice = combinationPrice;
        this.minPrice = -1;
    }

    @Override
    public int compareTo(Object obj){
        AvailableHotel availableHotel = (AvailableHotel) obj;
        if( this.minPrice != availableHotel.getMinPrice() ) 
            return this.getMinPrice() - availableHotel.getMinPrice();
        return availableHotel.getHotelStar() - this.hotelStar;
    }

    public int getHotelID() { return this.hotelID; }

    public int getHotelStar() { return this.hotelStar; }

    public int getMinPrice(){
        if( this.minPrice == -1 )
            this.minPrice = Collections.min(this.combinationPrice);
        return this.minPrice;
    }

    public String getLocality() { return this.locality; }

    public String getStreetAddress() { return this.streetAddress; }

    public ArrayList<ArrayList<Integer>> getRoomCombination(){
        return this.roomCombination;
    }

    public ArrayList<Integer> getCombinationPrice() {
        return this.combinationPrice;
    }

    public void setCombinationPrice(ArrayList<Integer> combinationPrice){
        this.combinationPrice = combinationPrice;
    }
}