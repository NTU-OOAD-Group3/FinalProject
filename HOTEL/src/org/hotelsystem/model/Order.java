package org.hotelsystem.model;
import java.util.ArrayList;

public class Order {
    private int orderID;
    private int userID;
    private int hotelID;
    private ArrayList<Integer> roomIDs;
    private int checkinTime;
    private int checkoutTime;
    private int price;

    public Order(int orderID, int userID, int hotelID, ArrayList<Integer> roomIDs, int checkinTime, int checkoutTime, int price){
        this.orderID = orderID;
        this.userID = userID;
        this.hotelID = hotelID;
        this.roomIDs = roomIDs;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.price = price;
    }

    public int getOrderID(){
        return this.orderID;
    }
    
    public int getUserID(){
        return this.userID;
    }

    public int getHotelID(){
        return this.hotelID;
    }

    public ArrayList<Integer> getRoomIDs(){
        return this.roomIDs;
    } 

    public int getCheckinTime(){
        return this.checkinTime;
    }

    public int getCheckoutTime(){
        return this.checkoutTime;
    }

    public int getPrice(){
        return this.price;
    }
    
}