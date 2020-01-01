package org.hotelsystem.model;

public class HotelReview {
    private int hotelID;
    private int userID;
    private int rating;
    private String review;
    
    public HotelReview(int hotelID, int userID, int rating, String review){
        this.hotelID = hotelID;
        this.userID = userID;
        this.rating = rating;
        this.review = review;
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

    public String getReview(){
        return this.review;
    }
}