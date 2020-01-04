package org.hotelsystem.model;

import java.util.*;

public class Hotel {
    private int hotelID;
    private int hotelStar;
    private String locality;
    private String address;
    ArrayList<Room> singleRooms, doubleRooms, quadRooms;
    private int vacantSingleNum, vacantDoubleNum, vacantQuadNum;
    private int singleRoomPrice, doubleRoomPrice, quadRoomPrice;
    
    public Hotel(int hotelID, int hotelStar, String locality, String address,
                 ArrayList<Room> singleRooms,
                 ArrayList<Room> doubleRooms,
                 ArrayList<Room> quadRooms) {
        this.hotelID = hotelID;
        this.hotelStar = hotelStar;
        this.locality = locality;
        this.address = address;
        this.singleRooms = singleRooms;
        this.doubleRooms = doubleRooms;
        this.quadRooms = quadRooms;
    }

    private void calcVacantNum() {
        this.vacantSingleNum = 0;
        this.vacantDoubleNum = 0;
        this.vacantQuadNum = 0;
        for ( int i=0; i<this.singleRooms.size(); ++i ) {
            if (this.singleRooms.get(i).isOccupied() == false) {
                vacantSingleNum += 1;
            }
        }
        for ( int i=0; i<this.doubleRooms.size(); ++i ) {
            if (this.doubleRooms.get(i).isOccupied() == false) {
                vacantDoubleNum += 1;
            }
        }
        for ( int i=0; i<this.quadRooms.size(); ++i ) {
            if ( this.quadRooms.get(i).isOccupied() == false) {
                vacantQuadNum += 1;
            }
        }
    }

    private void retrieveRoomPrice() {
        this.singleRoomPrice = (this.singleRooms.isEmpty() ? -1 : this.singleRooms.get(0).getRoomPrice());
        this.doubleRoomPrice = (this.doubleRooms.isEmpty() ? -1 : this.doubleRooms.get(0).getRoomPrice());
        this.quadRoomPrice = (this.quadRooms.isEmpty() ? -1 : this.quadRooms.get(0).getRoomPrice());
    }

    public void setOccupied(int roomID, boolean b) {
        int roomType = roomID / 10000;
        int roomNumber = roomID % 10000;
        if ( roomType == 1 ) {
            singleRooms.get(roomNumber - 1).setOccupied(b);
        } else if ( roomType == 2 ) {
            doubleRooms.get(roomNumber - 1).setOccupied(b);
        } else if ( roomType == 4 ) {
            quadRooms.get(roomNumber - 1).setOccupied(b);
        } else {
            System.err.println("Error on roomType " + String.valueOf(roomType));
            System.exit(1);
        }
    }

    public int getHotelID() { return this.hotelID; }

    public AvailableHotel getAvailableHotel(int peopleNum, int roomNum) {
        this.calcVacantNum();
        this.retrieveRoomPrice();
        ArrayList<ArrayList<Integer>> roomCombination = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> combinationPrice = new ArrayList<Integer>();
        for ( int x=0; x<=Math.min(roomNum, vacantSingleNum); ++x ) {
            for ( int y=0; y<=Math.min(roomNum - x, vacantDoubleNum); ++y ) {
                int z = roomNum - x - y;
                if ( x + y + z == roomNum && x + 2*y + 4*z >= peopleNum ) {
                    ArrayList<Integer> comb = new ArrayList<Integer>();
                    comb.add(x); comb.add(y); comb.add(z);
                    roomCombination.add(comb);
                    combinationPrice.add(x * this.singleRoomPrice +
                                         y * this.doubleRoomPrice +
                                         z * this.quadRoomPrice);
                }
            }
        }
        keySort(combinationPrice, combinationPrice, roomCombination);
        AvailableHotel availableHotel = new AvailableHotel(
            this.hotelID, this.hotelStar, this.locality, this.address,
            roomCombination, combinationPrice);
        return availableHotel;
    }

    public boolean checkAvailable(int singleNum, int doubleNum, int quadNum) {
        this.calcVacantNum();
        if ( singleNum <= this.vacantSingleNum ) { return false; }
        if ( doubleNum <= this.vacantDoubleNum ) { return false; }
        if ( quadNum <= this.vacantQuadNum ) { return false; }
        return true;
    }

    public String toString() {
        String s = "HotelID: " + String.valueOf(this.hotelID) + "\n" +
            "HotelStar: " + String.valueOf(this.hotelStar) + "\n" +
            "Locality: " + this.locality + "\n" +
            "Address: " + this.address + "\n";
        for ( int i=0; i<this.singleRooms.size(); ++i ) {
            s = s + singleRooms.get(i).toString();
        }
        for ( int i=0; i<this.doubleRooms.size(); ++i ) {
            s = s + doubleRooms.get(i).toString();
        }
        for ( int i=0; i<this.quadRooms.size(); ++i ) {
            s = s + quadRooms.get(i).toString();
        }
        return s;
    }


    // Code from StackOverflow used to jointly sort two ArrayList.
    public static <T extends Comparable<T>> void keySort(
        final List<T> key, List<?>... lists){
        // Create a List of indices
        List<Integer> indices = new ArrayList<Integer>();
        for(int i = 0; i < key.size(); i++)
            indices.add(i);

        // Sort the indices list based on the key
        Collections.sort(indices, new Comparator<Integer>(){
            @Override public int compare(Integer i, Integer j) {
                return key.get(i).compareTo(key.get(j));
            }
        });

        // Create a mapping that allows sorting of the List by N swaps.
        Map<Integer,Integer> swapMap = new HashMap<Integer, Integer>(indices.size());

        // Only swaps can be used b/c we cannot create a new List of type <?>
        for(int i = 0; i < indices.size(); i++){
            int k = indices.get(i);
            while(swapMap.containsKey(k))
                k = swapMap.get(k);

            swapMap.put(i, k);
        }

        // for each list, swap elements to sort according to key list
        for(Map.Entry<Integer, Integer> e : swapMap.entrySet())
            for(List<?> list : lists)
                Collections.swap(list, e.getKey(), e.getValue());
    }
    
}