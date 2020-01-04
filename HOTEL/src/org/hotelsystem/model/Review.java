package org.hotelsystem.model;

public class Review {
    private int orderID;
    private int hotelID;
    private int userID;
    private int rating;
    private String review;
    
    public Review(int orderID, int hotelID, int userID, int rating, String review) {
        this.orderID = orderID;
        this.hotelID = hotelID;
        this.userID = userID;
        this.rating = rating;
        this.review = review;
    }

    public int getOrderID() { return this.orderID; }
    public int getHotelID() { return this.hotelID; }
    public int getUserID() { return this.userID; }
    public int getRating() { return this.rating; }
    public String getReview() { return this.review; }

    public String toString() {
        String s = "OrderID: " + String.valueOf(this.orderID) + ", " +
            "UserID: " + String.valueOf(this.userID) + ", " +
            "HotelID: " + String.valueOf(this.hotelID) + ", " +
            "Rating: " + String.valueOf(this.rating) + ",\n" +
            "Review:\n" + this.review;
        return s;
    }
}