package org.hotelsystem.model;

public class HotelComment {
    private int hotelID;
    private int userID;
    private int rating;
    private String comment;
    
    public HotelComment(int hotelID, int userID, int rating, String comment){
        this.hotelID = hotelID;
        this.userID = userID;
        this.rating = rating;
        this.comment = comment;
    }

    public int getHotelID(){
        return this.hotelID;
    }

    public int getUserID(){
        return this.userID;
    }

    public int getRating(){
        return this.rating;
    }

    public String getComment(){
        return this.comment;
    }
}