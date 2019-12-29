import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.*;
import java.sql.*;
import org.json.*;

public class InitDB {
    private Connection conn = null;
    private Statement stmt = null;

    /**
     * Initialize class instance & build connection to MySQL server.
     * @param host The IP address to MySQL server.
     * @param user The username to login.
     * @param password The password to login.
     */
    public InitDB(String host, String user, String password) {
        // build connection to MySQL
		try {
			System.out.print("Connecting to MySQL...");
			Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://" + host +
                "/?user=" + user + "&password=" + password +
                "&useUnicode=true&characterEncoding=utf8");
            System.out.println("Connected to MySQL successfully.");
		} catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.getStackTrace();
		}
    }

    /**
     * Create database "HOTEL" with UTF-8 & use "HOTEL".
     */
    public void createDatabase() {
        String cmd = null;
        try {
            this.stmt = conn.createStatement();

            cmd = "CREATE DATABASE `HOTEL`;";
            stmt.execute(cmd);
            System.out.println(cmd);

            cmd = "ALTER DATABASE `HOTEL` CHARACTER SET utf8;";
            stmt.execute(cmd);
            System.out.println(cmd);

            cmd = "USE `HOTEL`;";
            stmt.execute(cmd);
            System.out.println(cmd);

            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.getStackTrace();
        }
    }

    /**
     * Create tables to database "HOTEL".
     */
    public void createTables() {
        String cmd = null;
        try {
            this.stmt = conn.createStatement();
            
            cmd = "CREATE TABLE Users (" +
                    "`UserID` INT NOT NULL," +
                    "`UserType` INT NOT NULL," +
                    "`UserName` VARCHAR(100) NOT NULL," +
                    "`Password` VARCHAR(100) NOT NULL," +
                    "PRIMARY KEY (`UserID`)" +
                  ");";
            stmt.execute(cmd);
            System.out.println("CREATE TABLE User");

            cmd = "CREATE TABLE Hotels (" +
                    "`HotelID` INT NOT NULL," +
                    "`HotelStar` INT NOT NULL," +
                    "`LOCALITY` CHAR(16) NOT NULL," +
                    "`ADDRESS` VARCHAR(100) NOT NULL," +
                    "PRIMARY KEY (`HotelID`)" +
                  ");";
            stmt.execute(cmd);
            System.out.println("CREATE TABLE Hotel");

            cmd = "CREATE TABLE Rooms (" +
                    "`RoomID` INT NOT NULL," +
                    "`HotelID` INT NOT NULL," +
                    "`RoomPrice` INT NOT NULL," +
                    "PRIMARY KEY (`RoomID`, `HotelID`)," +
                    "FOREIGN KEY (`HotelID`) REFERENCES Hotels (`HotelID`)" +
                  ");";
            stmt.execute(cmd);
            System.out.println("CREATE TABLE Room");

            cmd = "CREATE TABLE Orders (" +
                    "`OrderID` INT NOT NULL," +
                    "`UserID` INT NOT NULL," +
                    "`HotelID` INT NOT NULL," +
                    "`RoomID` INT NOT NULL," +
                    "`CheckIn` DATE NOT NULL," +
                    "`CheckOut` DATE NOT NULL," +
                    // "PRIMARY KEY (`OrderID`)," +
                    "FOREIGN KEY (`UserID`) REFERENCES Users (`UserID`)," +
                    "FOREIGN KEY (`HotelID`) REFERENCES Hotels (`HotelID`)," +
                    "FOREIGN KEY (`RoomID`) REFERENCES Rooms (`RoomID`)" +
                  ");";
            stmt.execute(cmd);
            System.out.println("CREATE TABLE Order");

            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();
        }
    }

    /**
     * Static method for reading lines of a file.
     * @param filePath The path to the file to read.
     * @return A string of all lines concated.
     */
    private static String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach(s -> sb.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
	}
    
    /**
     * Insert hotel data from JSON file to database tables.
     * @param filePath The path to the file of hotel data.
     */
    public void insertFromJSON(String filePath) {
        JSONArray hotelList = new JSONArray(readFile(filePath));
        String cmd = null;
        try {
			stmt = conn.createStatement();
			for ( int i=0; i<hotelList.length(); ++i ) {
				JSONObject hotel = hotelList.getJSONObject(i);
				int hotel_id = hotel.getInt("HotelID");
		        int star = hotel.getInt("HotelStar");
		        String locality = hotel.getString("Locality");
                String address = hotel.getString("Street-Address");
                int single_price = -1, double_price = -1, quad_price = -1;
                int single_number = 0, double_number = 0, quad_number = 0;

                JSONArray roomList = hotel.getJSONArray("Rooms");
		        for ( int j=0; j<roomList.length(); ++j ) {
		        	JSONObject room = roomList.getJSONObject(j);
		            String room_type_str = room.getString("RoomType");
		            int price = room.getInt("RoomPrice");
		            int number = room.getInt("Number");
		            
		            if (room_type_str.equals("Single")) {
                        single_price = price;
                        single_number = number;
                    } else if (room_type_str.equals("Double")) {
                        double_price = price;
                        double_number = number;
                    } else if (room_type_str.equals("Quad")) {
                        quad_price = price;
                        quad_number = number;
                    } else {
		            	System.err.println("Unexpected Room Type!");
		            	System.exit(0);
		            }
                }
            
                cmd = "INSERT INTO Hotels VALUES " +
                    String.format("(%d, %d, \"%s\", \"%s\");",
                        hotel_id, star, locality, address);
                stmt.execute(cmd);
                System.out.println(cmd);

                for ( int j=0; j<single_number; ++j ) {
                    cmd = "INSERT INTO Rooms VALUES " +
                        String.format("(%d, %d, %d);",
                            10000 + j + 1, hotel_id, single_price);
                    stmt.execute(cmd);
                    System.out.println(cmd);
                }

                for ( int j=0; j<double_number; ++j ) {
                    cmd = "INSERT INTO Rooms VALUES " +
                        String.format("(%d, %d, %d);",
                            20000 + j + 1, hotel_id, double_price);
                    stmt.execute(cmd);
                    System.out.println(cmd);
                }

                for ( int j=0; j<quad_number; ++j ) {
                    cmd = "INSERT INTO Rooms VALUES " +
                        String.format("(%d, %d, %d);",
                            40000 + j + 1, hotel_id, quad_price);
                    stmt.execute(cmd);
                    System.out.println(cmd);
                }
            }
            stmt.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void main(String[] args) {
        InitDB mysql = new InitDB("140.112.21.82", "ooad", "ooad");
        mysql.createTables();
        mysql.insertFromJSON("data/HotelList.json");
    }
}