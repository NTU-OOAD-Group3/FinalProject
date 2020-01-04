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
            if ( lastHotelID != -1 ) {
                hotels.add(new Hotel(lastHotelID, lastHotelStar, lastLocality, lastAddress,
                                     singleRooms, doubleRooms, quadRooms));
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
            System.out.println(cmd);
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

    public ArrayList<Order> getOrders(int orderID, int userID, int hotelID) {
        ArrayList<Order> orders = new ArrayList<Order>();
        String cmd = null;
        try {
            // Get all orders match the constraint
            cmd = "SELECT O.OrderID, O.UserID, O.HotelID, O.RoomID, O.CheckIn, O.CheckOut, R.RoomPrice " +
                "FROM Orders AS O, Rooms AS R ";
            if ( orderID != -1 ) { cmd += String.format("WHERE O.OrderID = %d ", orderID); }
            else if ( userID != -1 ) { cmd += String.format("WHERE O.UserID = %d ", userID); }
            else if ( orderID != -1 ) { cmd += String.format("WHERE O.HotelID = %d ", hotelID); }
            else {
                System.err.println("Invalid arguments!");
                System.exit(1);
            }
            cmd += "AND O.HotelID = R.HotelID AND O.RoomID = R.RoomID ";
            cmd += "ORDER BY OrderID DESC, RoomID ASC;";
            this.stmt = this.conn.createStatement();
            this.result = this.stmt.executeQuery(cmd);
            // Organize the result into Order objects
            int lastOrderID = -1, lastUserID = -1, lastHotelID = -1;
            int lastCheckinInt = -1, lastCheckoutInt = 0;
            int orderPrice = 0;
            ArrayList<Integer> roomIDs = new ArrayList<Integer>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");    
            while ( this.result.next() ) {
                int rowOrderID = this.result.getInt("OrderID");
                int rowUserID = this.result.getInt("UserID");
                int rowHotelID = this.result.getInt("HotelID");
                int rowRoomID = this.result.getInt("RoomID");
                java.sql.Date rowCheckin = this.result.getDate("CheckIn");
                int rowCheckinInt = Integer.valueOf(sdf.format(rowCheckin));
                java.sql.Date rowCheckout = this.result.getDate("CheckOut");
                int rowCheckoutInt = Integer.valueOf(sdf.format(rowCheckout));
                int rowRoomPrice = this.result.getInt("RoomPrice");
                if ( lastOrderID != rowOrderID ) {
                    if ( lastOrderID != -1 ) {
                        orders.add(new Order(lastOrderID, lastUserID, lastHotelID, roomIDs,
                                             lastCheckinInt, lastCheckoutInt, orderPrice));
                    }
                    lastOrderID = rowOrderID; lastUserID = rowUserID; lastHotelID = rowHotelID;
                    lastCheckinInt = rowCheckinInt; lastCheckoutInt = rowCheckoutInt;
                    orderPrice = 0;
                    roomIDs = new ArrayList<Integer>();
                }
                roomIDs.add(rowRoomID);
                orderPrice += rowRoomPrice;
            }
            if ( lastOrderID != -1 ) {
                orders.add(new Order(lastOrderID, lastUserID, lastHotelID, roomIDs,
                                     lastCheckinInt, lastCheckoutInt, orderPrice));
            }
            this.stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
            System.exit(1);
        }
        return orders;
    }
    
    public int getLastOrderID() {
        int lastOrderID = 0;
        try {
            this.stmt = this.conn.createStatement();
            this.result = this.stmt.executeQuery("SELECT MAX(OrderID) AS MaxOrderID FROM Orders;");
            while ( this.result.next() ) { lastOrderID = this.result.getInt("MaxOrderID"); }
            this.stmt.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
            System.exit(1);
        }
        return lastOrderID;
    }

    public Hotel getHotel(int hotelID, int checkin, int checkout) {
        Hotel hotel = null;
        String cmd = null;
        try {
            this.stmt = this.conn.createStatement();
            // Get attributes of Hotel class.
            cmd = "SELECT * FROM Hotels AS H, Rooms AS R " +
                "WHERE H.HotelID = " + String.valueOf(hotelID) + " " +
                "AND H.HotelID = R.HotelID " +
                "ORDER BY R.RoomID ASC;";
            this.result = this.stmt.executeQuery(cmd);
            int rowHotelID = -1, rowHotelStar = 0;
            String rowLocality = null, rowAddress = null;
            ArrayList<Room> singleRooms = new ArrayList<Room>();
            ArrayList<Room> doubleRooms = new ArrayList<Room>();
            ArrayList<Room> quadRooms = new ArrayList<Room>();
            while ( this.result.next() ) {
                rowHotelID = this.result.getInt("HotelID");
                rowHotelStar = this.result.getInt("HotelStar");
                rowLocality = this.result.getString("Locality");
                rowAddress = this.result.getString("Address");
                int rowRoomID = this.result.getInt("RoomID");
                int rowRoomType = rowRoomID / 10000;
                int rowRoomPrice = this.result.getInt("RoomPrice");
                if ( rowRoomType == 1 ) { singleRooms.add(new Room(rowRoomID, rowRoomPrice)); }
                if ( rowRoomType == 2 ) { doubleRooms.add(new Room(rowRoomID, rowRoomPrice)); }
                if ( rowRoomType == 4 ) { quadRooms.add(new Room(rowRoomID, rowRoomPrice)); }
            }
            hotel = new Hotel(rowHotelID, rowHotelStar, rowLocality, rowAddress,
                              singleRooms, doubleRooms, quadRooms);
            // Set occupied rooms
            cmd = "SELECT RoomID FROM Orders " +
                "WHERE HotelID = " + String.valueOf(hotelID) + " " +
                "AND CheckOut > \"" + this.dateIntToString(checkin) + "\" " +
                "AND CheckIn < \"" + this.dateIntToString(checkout) + "\";";
            this.result = this.stmt.executeQuery(cmd);
            while ( this.result.next() ) {
                int rowRoomID = this.result.getInt("RoomID");
                hotel.setOccupied(rowRoomID, true);
            }
            // Close statement
            this.stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
            System.exit(1);
        }
        return hotel;
    }

    public void insertOrder(Order order, int singleNum, int doubleNum, int quadNum) {
        // Set incremental orderID
        order.setOrderID(this.getLastOrderID() + 1);
        // Assign vacant rooms
        Hotel hotel = this.getHotel(order.getHotelID(), order.getCheckinTime(), order.getCheckoutTime());
        ArrayList<Integer> roomIDs = hotel.assignRooms(singleNum, doubleNum, quadNum);
        order.setRoomIDs(roomIDs);
        // Insert order
        String cmd = "INSERT INTO Orders " +
            String.format("SELECT %d, %d, %d, ?, \"%s\", \"%s\" FROM dual ",
                order.getOrderID(), order.getUserID(), order.getHotelID(),
                this.dateIntToString(order.getCheckinTime()),
                this.dateIntToString(order.getCheckoutTime())) +
            "WHERE NOT EXISTS (SELECT OrderID FROM Orders " +
            String.format("WHERE HotelID = %d AND RoomID = ? ", order.getHotelID()) +
            String.format("AND CheckIn < \"%s\" AND CheckOut > \"%s\");",
                this.dateIntToString(order.getCheckoutTime()),
                this.dateIntToString(order.getCheckinTime()));
        try {
            this.conn.setAutoCommit(false);
            PreparedStatement ps = this.conn.prepareStatement(cmd);
            for ( int i=0; i<order.getRoomIDs().size(); ++i ) {
                System.out.println(order.getRoomIDs());
                ps.setInt(1, order.getRoomIDs().get(i));
                ps.setInt(2, order.getRoomIDs().get(i));
                int rowCount = ps.executeUpdate();
                if ( rowCount == 0 ) {
                    System.err.println("Rollback!");
                    this.conn.rollback();
                    this.conn.setAutoCommit(true);
                    return ;
                }
            }
            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
            System.exit(1);
        }
    }

    private String dateIntToString(int date) {
        SimpleDateFormat originSDF = new SimpleDateFormat("yyyymmdd");
        SimpleDateFormat targetSDF = new SimpleDateFormat("yyyy-mm-dd");
        try {
            return targetSDF.format(originSDF.parse(String.valueOf(date)));
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        DBUtil dbutil = new DBUtil("140.112.21.82", "ooad", "ooad", "HOTEL");
        long time1 = System.currentTimeMillis();
        // ArrayList<Hotel> hotels = dbutil.getHotels("台北", 20200103, 20200105);
        // ArrayList<Order> orders = dbutil.getOrders(-1, 0, -1);
        // ArrayList<Integer> roomIDs = new ArrayList<Integer>();
        Order order = new Order(-1, 0, 0, null, 20200103, 20200105, 0);
        dbutil.insertOrder(order, 2, 1, 1);
        long time2 = System.currentTimeMillis();
        System.out.println("Time cost: " + String.valueOf(time2 - time1) + " ms.");
        // System.out.println(orders.size());
        // for ( int i=0; i<orders.size(); ++i ) {
        //     System.out.println(orders.get(i));
        // }
    }
}