package org.hotelsystem.model;

public class Room {
    private int roomID;
    private int roomPrice;
    private boolean isOccupied;

    public Room(int roomID, int roomPrice) {
        this.roomID = roomID;
        this.roomPrice = roomPrice;
        this.isOccupied = false;
    }

    public void setOccupied(boolean b) {
        this.isOccupied = b;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public int getRoomID() {
        return this.roomID;
    }

    public int getRoomPrice() {
        return this.roomPrice;
    }

    public String toString() {
        String s = "RoomID: " + String.valueOf(this.roomID) + " " +
            "RoomPrice: " + String.valueOf(this.roomPrice) + " " +
            "isOccupied: " + String.valueOf(this.isOccupied) + "\n";
        return s;
    }
}