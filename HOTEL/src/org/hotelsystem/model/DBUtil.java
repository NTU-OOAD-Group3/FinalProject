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

    /**
     * Get User instance from DB with corresponding username & password.
     * @param username The username to login.
     * @param password The password to login.
     * @return A User object if retrieve successfully, null if failed.
     */
    public User getUser(String username, String password) {
        User user = null;
        try {
            this.stmt = this.conn.createStatement();
            this.result = this.stmt.executeQuery(
                "SELECT UserID, UserType FROM Users " +
                "WHERE Username = \"" + username + "\" " +
                "AND Password = \"" + password + "\";"
            );
            if ( this.result.isBeforeFirst() ) {
                this.result.next();
                int userID = this.result.getInt("UserID");
                int userType = this.result.getInt("UserType");
                user = new User(userID, userType, username, password);
            }
            this.stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
            System.exit(1);
        }
        return user;
    }

    /**
     * The Method for sign-up with given arguments & insert a row into DB.
     * @param userType An integer in {0, 1} represents the type of user.
     * @param username The username to sign-up.
     * @param password The password to sign-up.
     * @return True if sign-up successfully, False if failed.
     */
    public boolean insertUser(int userType, String username, String password) {
        try {
            // Check username available
            this.stmt = this.conn.createStatement();
            String cmd = "INSERT INTO Users (UserType, Username, Password) " +
                "VALUES (" + String.valueOf(userType) + ", " +
                      "\"" + String.valueOf(username) + "\", " +
                      "\"" + String.valueOf(password) + "\");";
            System.out.println(cmd);
            int rowCount = this.stmt.executeUpdate(cmd);
            this.stmt.close();
            if ( rowCount != 1 ) { return false; }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
            return false;
        }
        return true;
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

    public ArrayList<Review> getReviews(int hotelID) {
        ArrayList<Review> reviews = new ArrayList<Review>();
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
                int orderID = this.result.getInt("OrderID");
                int userID = this.result.getInt("UserID");
                int rating = this.result.getInt("Rating");
                String reviewStr = this.result.getString("Review");
                reviews.add(new Review(orderID, hotelID, userID, rating, reviewStr));
            }
            this.stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
            System.exit(1);
        }
        System.out.printf("get %d reviews\n", reviews.size());
        return reviews;
    }

    public Review getOrderReview(int orderID) {
        Review review = null;
        try {
            this.stmt = this.conn.createStatement();
            String cmd = "SELECT * FROM Reviews " +
                "WHERE OrderID = " + String.valueOf(orderID) + ";";
            this.result = this.stmt.executeQuery(cmd);
            if ( this.result.isBeforeFirst() ) {
                this.result.next();
                int hotelID = this.result.getInt("HotelID");
                int userID = this.result.getInt("UserID");
                int rating = this.result.getInt("Rating");
                String reviewStr = this.result.getString("Review");
                review = new Review(orderID, hotelID, userID, rating, reviewStr);
            }
            this.stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
            System.exit(1);
        }
        return review;
    }

    public boolean insertReview(Review review) {
        try {
            this.stmt = this.conn.createStatement();
            String cmd = "INSERT INTO Reviews " +
                "(HotelID, UserID, OrderID, Rating, Review) VALUES (" +
                String.valueOf(review.getHotelID()) + ", " +
                String.valueOf(review.getUserID()) + ", " +
                String.valueOf(review.getOrderID()) + ", " +
                String.valueOf(review.getRating()) + ", " +
                "\"" + review.getReview() + "\");";
            // System.out.println(cmd);
            int rowCount = this.stmt.executeUpdate(cmd);
            this.stmt.close();
            if ( rowCount != 1 ) { return false; }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
            return false;
        }
        return true;
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

    /**
     * Insert order with given arguments into DB.
     * @param order The order instance to insert into DB. (orderID should be -1, roomIDs should be null)
     * @param singleNum The target number of 1-person rooms.
     * @param doubleNum The target number of 2-person rooms.
     * @param quadNum The target number of 4-person rooms.
     * @return True if the insertion was successful, False if some errors occured.
     */
    public boolean insertOrder(Order order, int singleNum, int doubleNum, int quadNum) {
        // Assign vacant rooms
        Hotel hotel = this.getHotel(order.getHotelID(), order.getCheckinTime(), order.getCheckoutTime());
        ArrayList<Integer> roomIDs = hotel.assignRooms(singleNum, doubleNum, quadNum);
        order.setRoomIDs(roomIDs);
        if ( order.getRoomIDs().size() != singleNum + doubleNum + quadNum ) { return false; }
        // Insert order
        String cmd = "INSERT INTO Orders " +
            String.format("SELECT ?, %d, %d, ?, \"%s\", \"%s\" FROM dual ",
                order.getUserID(), order.getHotelID(),
                this.dateIntToString(order.getCheckinTime()),
                this.dateIntToString(order.getCheckoutTime())) +
            "WHERE NOT EXISTS (SELECT OrderID FROM Orders " +
            String.format("WHERE HotelID = %d AND RoomID = ? ", order.getHotelID()) +
            String.format("AND CheckIn < \"%s\" AND CheckOut > \"%s\");",
                this.dateIntToString(order.getCheckoutTime()),
                this.dateIntToString(order.getCheckinTime()));
        try {
            this.conn.setAutoCommit(false);
            // Set incremental orderID
            this.stmt = this.conn.createStatement();
            this.result = this.stmt.executeQuery("SELECT MAX(OrderID) AS MaxOrderID FROM Orders FOR UPDATE;");
            while ( this.result.next() ) { order.setOrderID(this.result.getInt("MaxOrderID") + 1); }
            this.stmt.close();
            // Insert rows to table
            PreparedStatement ps = this.conn.prepareStatement(cmd);
            for ( int i=0; i<order.getRoomIDs().size(); ++i ) {
                System.out.println(order.getRoomIDs());
                ps.setInt(1, order.getOrderID());
                ps.setInt(2, order.getRoomIDs().get(i));
                ps.setInt(3, order.getRoomIDs().get(i));
                int rowCount = ps.executeUpdate();
                if ( rowCount == 0 ) {
                    System.err.println("Rollback!");
                    this.conn.rollback();
                    this.conn.setAutoCommit(true);
                    return false;
                }
            }
            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
            System.exit(1);
        }
        return true;
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

    /**
     * Modify order with given arguments & apply changes to database.
     * @param order The original order instance to modify.
     * @param newSN The target number of 1-person rooms.
     * @param newDN The target number of 2-person rooms.
     * @param newQN The target number of 4-person rooms.
     * @param newCheckin The target checkin time.
     * @param newCheckout The target checkout time.
     * @return True if the modification was successful, False if some errors occured.
     */
    public boolean modifyOrder(Order order, int newSN, int newDN, int newQN, int newCheckin, int newCheckout) {
        // Check new numbers of rooms are less than the original ones
        int oriSN = 0, oriDN = 0, oriQN = 0;
        for ( int i=0; i<order.getRoomIDs().size(); ++i ) {
            int roomType = order.getRoomIDs().get(i) / 10000;
            if ( roomType == 1 ) { ++oriSN; }
            if ( roomType == 2 ) { ++oriDN; }
            if ( roomType == 4 ) { ++oriQN; }
        }
        if ( newSN > oriSN || newDN > oriDN || newQN > oriQN ) { System.err.println("Rooms"); return false; }
        // Check the new date interval is in the original one
        if ( newCheckin < order.getCheckinTime() ) { System.err.println("Checkin"); return false; }
        if ( newCheckout > order.getCheckoutTime() ) { System.err.println("Checkout"); return false; }
        // Retrieve roomIDs to cancel (if any)
        ArrayList<Integer> cancelRoomIDs = new ArrayList<Integer>();
        int start = 0;
        for ( int i=start+newSN; i<start+oriSN; ++i ) { cancelRoomIDs.add(order.getRoomIDs().get(i)); }
        start += oriSN;
        for ( int i=start+newDN; i<start+oriDN; ++i ) { cancelRoomIDs.add(order.getRoomIDs().get(i)); }
        start += oriDN;
        for ( int i=start+newQN; i<start+oriQN; ++i ) { cancelRoomIDs.add(order.getRoomIDs().get(i)); }
        String cancelRoomIDStr = cancelRoomIDs.toString();
        cancelRoomIDStr = cancelRoomIDStr.substring(1, cancelRoomIDStr.length()-1);
        // Update the database
        try {
            this.stmt = this.conn.createStatement();
            // Delete cancelled rooms
            if ( cancelRoomIDs.size() > 0 ) {
                String cmd = "DELETE FROM Orders " +
                    "WHERE OrderID = " + String.valueOf(order.getOrderID()) + " " +
                    "AND UserID = " + String.valueOf(order.getUserID()) + " " +
                    "AND HotelID = " + String.valueOf(order.getHotelID()) + " " +
                    "AND RoomID IN (" + cancelRoomIDStr + ");";
                System.out.println(cmd);
                this.stmt.executeUpdate(cmd);
            }
            // Update checkin, checkout
            String cmd = "UPDATE Orders " +
                "SET CheckIn = \"" + this.dateIntToString(newCheckin) + "\", " +
                    "CheckOut = \"" + this.dateIntToString(newCheckout) + "\" " +
                "WHERE OrderID = " + String.valueOf(order.getOrderID()) + " " +
                "AND UserID = " + String.valueOf(order.getUserID()) + " " +
                "AND HotelID = " + String.valueOf(order.getHotelID()) + ";";
            System.out.println(cmd);
            this.stmt.executeUpdate(cmd);
            this.stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        DBUtil dbutil = new DBUtil("140.112.21.82", "ooad", "ooad", "HOTEL");
        long time1 = System.currentTimeMillis();
        // ArrayList<Hotel> hotels = dbutil.getHotels("台北", 20200103, 20200105);
        // ArrayList<Order> orders = dbutil.getOrders(-1, 0, -1);
        // ArrayList<Integer> roomIDs = new ArrayList<Integer>();
        // Order order = new Order(-1, 0, 0, null, 20200103, 20200106, 0);
        // dbutil.insertOrder(order, 3, 2, 2);
        // boolean rtn = dbutil.modifyOrder(order, 2, 1, 1, 20200103, 20200105);
        // System.out.println(rtn);
        // User user = dbutil.getUser("Howard", "123");
        // System.out.println(user);
        // Order order = new Order(11, 0, 148, null, 202001015, 20200124, 0);
        // Review review = dbutil.getOrderReview(11);
        // System.out.println(review);
        // if ( review == null ) {
        // review = new Review(11, 148, 0, 5, "Awesome!");
        // boolean rtn = dbutil.insertReview(review);
        // System.out.println(rtn);
        // }
        boolean rtn = dbutil.insertUser(0, "Howard", "456");
        System.out.println(rtn);
        long time2 = System.currentTimeMillis();
        System.out.println("Time cost: " + String.valueOf(time2 - time1) + " ms.");
        // System.out.println(orders.size());
        // for ( int i=0; i<orders.size(); ++i ) {
        //     System.out.println(orders.get(i));
        // }
    }
}