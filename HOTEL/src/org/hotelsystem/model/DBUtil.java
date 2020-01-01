package org.hotelsystem.model;

import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class DBUtil {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet result = null;

    /**
     * Constructor of class DBUtil.
     * Create connection instance to MySQL with given arguments.
     * @param host The IP address to MySQL server.
     * @param user The username to login.
     * @param password The password to login.
     * @param DBName The database name to use.
     */
    public DBUtil(String host, String user, String password, String DBName) {
        // build connection to MySQL
        try {
            System.out.print("Connecting to MySQL ...");
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://" + host +
                "/?user=" + user + "&password=" + password +
                "&useUnicode=true&characterEncoding=utf8");
            System.out.println("Connected to MySQL successfully.");

            System.out.print("Using database " + DBName + " ...");
            String cmd = String.format("USE `%s`;", DBName);
            this.stmt = conn.createStatement();
            this.stmt.execute(cmd);
            System.out.println("successfully.");

            this.stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
            System.exit(1);
        }
    }

    public ArrayList<Hotel> getHotels(String locality, int checkin, int checkout) {
        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        String cmd = null;
        
        try {
            this.stmt = this.conn.createStatement();
            cmd = "SELECT * FROM Hotels as H, Rooms as R " +
                "WHERE H.HotelID = R.HotelID " +
                // "AND H.Locality = \"" + locality + "\" " +
                "ORDER BY H.HotelID ASC, R.RoomID ASC;";
            this.result = this.stmt.executeQuery(cmd);
            if ( !this.result.isBeforeFirst() ) {
                // System.err.println("EMPTY_QUERY");
            }
            
            int lastHotelID = -1;
            Hotel hotel = null;
            ArrayList<Room> singleRooms = null;
            ArrayList<Room> doubleRooms = null;
            ArrayList<Room> quadRooms = null;
            while ( this.result.next() ) {
                int hotelID = this.result.getInt("HotelID");
                int hotelStar = this.result.getInt("HotelStar");
                String address = this.result.getString("Address");
                int roomID = this.result.getInt("RoomID");
                int roomType = roomID / 10000;
                int roomPrice = this.result.getInt("RoomPrice");
                
                if ( hotelID != lastHotelID ) {
                    singleRooms = new ArrayList<Room>();
                    doubleRooms = new ArrayList<Room>();
                    quadRooms = new ArrayList<Room>();
                    hotel = new Hotel(hotelID, hotelStar, locality, address, singleRooms, doubleRooms, quadRooms);
                    hotels.add(hotel);
                    lastHotelID = hotelID;
                }

                if ( roomType == 1 ) {
                    singleRooms.add(new Room(roomID, roomPrice));
                } else if ( roomType == 2 ) {
                    doubleRooms.add(new Room(roomID, roomPrice));
                } else if ( roomType == 4 ) {
                    quadRooms.add(new Room(roomID, roomPrice));
                } else {
                    System.err.println("Error on roomType " + String.valueOf(roomType));
                    System.exit(1);
                }
            }

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyymmdd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-mm-dd");
            String checkinStr = sdf2.format(sdf1.parse(String.valueOf(checkin)));
            String checkoutStr = sdf2.format(sdf1.parse(String.valueOf(checkout)));
            for ( int i=0; i<hotels.size(); ++i ) {
                hotel = hotels.get(i);
                cmd = "SELECT * FROM Orders " +
                    "WHERE HotelID = " + String.valueOf(hotel.getHotelID()) + " " +
                    "AND CheckOut > \"" + checkinStr + "\" " +
                    "AND CheckIn < \"" + checkoutStr + "\";";
                // System.out.println(cmd);
                this.result = this.stmt.executeQuery(cmd);
                if ( !this.result.isBeforeFirst() ) {
                    // System.err.println("EMPTY_QUERY");
                }
                while ( this.result.next() ) {
                    int roomID = this.result.getInt("RoomID");
                    hotel.setOccupied(roomID, true);
                }
            }

            this.stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
            System.exit(1);
        }

        return hotels;
    }

    public ArrayList<HotelReview> getHotelReviews(int hotelID) {
        ArrayList<HotelReview> hotelReviews = new ArrayList<HotelReview>();
        String cmd = null;

        try {
            this.stmt = this.conn.createStatement();
            cmd = "SELECT * FROM Reviews " +
                "WHERE HotelID = " + String.valueOf(hotelID) + " " +
                "ORDER BY ReviewTime DESC;";
            this.result = this.stmt.executeQuery(cmd);
            if ( !this.result.isBeforeFirst() ) {
                System.err.println("EMPTY_QUERY");
            }

            while ( this.result.next() ) {
                int userID = this.result.getInt("UserID");
                int rating = this.result.getInt("Rating");
                String review = this.result.getString("Review");
                hotelReviews.add(new HotelReview(hotelID, userID, rating, review));
            }

            this.stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
            System.exit(1);
        }

        return hotelReviews;
    }

    public static void main(String[] args) {
        DBUtil dbutil = new DBUtil("140.112.21.82", "ooad", "ooad", "HOTEL");
        ArrayList<Hotel> hotels = dbutil.getHotels("台北", 20191229, 20191231);
        System.out.println(hotels.size());
        for ( int i=0; i<hotels.size(); ++i ) {
            System.out.println(hotels.get(i).toString());
        }
    }
}