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
            cmd = "SELECT * FROM Hotels AS H, Rooms AS R " +
                "WHERE H.HotelID = R.HotelID " +
                "AND H.Locality = \"" + locality + "\" " +
                "ORDER BY H.HotelID ASC, R.RoomID ASC;";
            // System.out.println(cmd);
            this.result = this.stmt.executeQuery(cmd);
            if ( !this.result.isBeforeFirst() ) {
                // System.err.println("EMPTY_QUERY");
            }
            int lastHotelID = -1, lastHotelStar = 0;
            String lastLocality = null, lastAddress = null;
            ArrayList<Room> singleRooms = new ArrayList<Room>();;
            ArrayList<Room> doubleRooms = new ArrayList<Room>();;
            ArrayList<Room> quadRooms = new ArrayList<Room>();;
            while ( this.result.next() ) {
                int rowHotelID = this.result.getInt("HotelID");
                int rowHotelStar = this.result.getInt("HotelStar");
                String rowLocality = this.result.getString("Locality");
                String rowAddress = this.result.getString("Address");
                int rowRoomID = this.result.getInt("RoomID");
                int rowRoomType = rowRoomID / 10000;
                int rowRoomPrice = this.result.getInt("RoomPrice");
                
                if ( lastHotelID != rowHotelID ) {
                    if ( lastHotelID != -1 ) {
                        hotels.add(new Hotel(lastHotelID, lastHotelStar, lastLocality, lastAddress,
                                             singleRooms, doubleRooms, quadRooms));
                    }
                    lastHotelID = rowHotelID;
                    lastHotelStar = rowHotelStar;
                    lastLocality = rowLocality;
                    lastAddress = rowAddress;
                    singleRooms = new ArrayList<Room>();
                    doubleRooms = new ArrayList<Room>();
                    quadRooms = new ArrayList<Room>();
                }

                if ( rowRoomType == 1 ) {
                    singleRooms.add(new Room(rowRoomID, rowRoomPrice));
                } else if ( rowRoomType == 2 ) {
                    doubleRooms.add(new Room(rowRoomID, rowRoomPrice));
                } else if ( rowRoomType == 4 ) {
                    quadRooms.add(new Room(rowRoomID, rowRoomPrice));
                } else {
                    System.err.println("Error on roomType " + String.valueOf(rowRoomType));
                    System.exit(1);
                }
            }

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyymmdd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-mm-dd");
            String checkinStr = sdf2.format(sdf1.parse(String.valueOf(checkin)));
            String checkoutStr = sdf2.format(sdf1.parse(String.valueOf(checkout)));
            cmd = "SELECT H.HotelID, O.RoomID " +
                "FROM Hotels AS H, Orders AS O " +
                "WHERE H.HotelID = O.HotelID " +
                "AND H.Locality = \"" + locality + "\" " +
                "AND O.CheckOut > \"" + checkinStr + "\" " +
                "AND O.CheckIn < \"" + checkoutStr + "\" " +
                "ORDER BY H.HotelID ASC, O.RoomID ASC;";
            // System.out.println(cmd);
            this.result = this.stmt.executeQuery(cmd);
            if ( this.result.isBeforeFirst() ) {
                int hotels_ptr = 0;
                while ( this.result.next() ) {
                    int rowHotelID = this.result.getInt("HotelID");
                    int rowRoomID = this.result.getInt("RoomID");
                    while ( hotels.get(hotels_ptr).getHotelID() != rowHotelID ) {
                        ++hotels_ptr;
                    }
                    hotels.get(hotels_ptr).setOccupied(rowRoomID, true);
                }
            } else {
                // System.err.println("EMPTY_QUERY");
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
            cmd = "SELECT UserID, Rating, Review " +
                "FROM Reviews " +
                "WHERE HotelID = " + String.valueOf(hotelID) + " " +
                "ORDER BY ReviewTime DESC;";
            this.result = this.stmt.executeQuery(cmd);
            if ( !this.result.isBeforeFirst() ) {
                // System.err.println("EMPTY_QUERY");
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
        System.out.printf("get %d reviews\n", hotelReviews.size());
        return hotelReviews;
    }

    public ArrayList<String> getLocality() {
        ArrayList<String> locality = new ArrayList<String>();
        String cmd = null;
        try{
            this.stmt = this.conn.createStatement();
            cmd = "SELECT DISTINCT Locality FROM Hotels;";
            this.result = this.stmt.executeQuery(cmd);
            while ( this.result.next() ) {
                locality.add(result.getString("Locality"));
            }
            this.stmt.close();
        }
        catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
            System.exit(1);
        }
        return locality;
    }

    public static void main(String[] args) {
        DBUtil dbutil = new DBUtil("140.112.21.82", "ooad", "ooad", "HOTEL");
        long time1 = System.currentTimeMillis();
        ArrayList<Hotel> hotels = dbutil.getHotels("台北", 20200103, 20200105);
        long time2 = System.currentTimeMillis();
        System.out.println((time2 - time1));
        System.out.println(hotels.size());
        // for ( int i=0; i<hotels.size(); ++i ) {
        //     System.out.println(hotels.get(i));
        // }
    }
}