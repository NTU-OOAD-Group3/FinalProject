# ESOS OOAD 2019
## FinalProject: Hotel System

### How to use
```
cd HOTEL

javac -cp src/:lib/json.jar:lib/mysql-connector-java-5.1.47.jar -d ./ src/InitDB.java
java -cp ./:lib/json.jar:lib/mysql-connector-java-5.1.47.jar InitDB

javac -cp src/:lib/json.jar:lib/mysql-connector-java-5.1.47.jar -d ./ src/main.java
java -cp ./:lib/json.jar:lib/mysql-connector-java-5.1.47.jar main
```

### Some development note
```
├─.vscode
└─HOTEL
    ├─data
    ├─lib
    ├─resources
    └─src
        └─org
            └─hotelsystem
                ├─control
                ├─model
                └─view
```

```SQL
CREATE TABLE Users (
    `UserID` INT NOT NULL,
    `UserType` INT NOT NULL, /* 0=hoster, 1=user */
    `UserName` VARCHAR(100) NOT NULL,
    `Password` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`UserID`)
);

CREATE TABLE UserInfo (
    `UserID` INT NOT NULL,
    `Sex` INT NOT NULL, /* 1 = Male, 2 = Female, 0 = Secret */
    `PhoneNumber` CHAR(16),
    `Address` VARCHAR(128),
    `CardOwner` VARCHAR(32),
    `CardAccount` CHAR(16),
    `CardValidTime` CHAR(5),
    PRIMARY KEY (`UserID`),
    FOREIGN KEY (`UserID`) REFERENCES Users (`UserID`)
);

CREATE TABLE Hotels (
    `HotelID` INT NOT NULL,
    `HotelStar` INT NOT NULL,
    `LOCALITY` CHAR(16) NOT NULL,
    `ADDRESS` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`HotelID`)
);

CREATE TABLE Rooms (
	/* set first digit as roomtype, 4 random digits following as unique room id */
    `RoomID` INT NOT NULL,
    `HotelID` INT NOT NULL,
    `RoomPrice` INT NOT NULL,
    PRIMARY KEY (`RoomID`, `HotelID`),
    FOREIGN KEY (`HotelID`) REFERENCES Hotels (`HotelID`)
);

CREATE TABLE Orders (
    `OrderID` INT NOT NULL,
    `UserID` INT NOT NULL,
    `HotelID` INT NOT NULL,
    `RoomID` INT NOT NULL,
    `CheckIn` DATE NOT NULL,
    `CheckOut` DATE NOT NULL,
    PRIMARY KEY (`OrderID`),
    FOREIGN KEY (`UserID`) REFERENCES Users (`UserID`),
    FOREIGN KEY (`HotelID`) REFERENCES Hotels (`HotelID`),
    FOREIGN KEY (`RoomID`) REFERENCES Rooms (`RoomID`)
);

CREATE TABLE Reviews (
    `HotelID` INT NOT NULL,
    `UserID` INT NOT NULL,
    `OrderID` INT NOT NULL,
    `Rating` INT NOT NULL,
    `Review` VARCHAR(256),
    PRIMARY KEY (`HotelID`, `UserID`, `OrderID`),
    FOREIGN KEY (`HotelID`) REFERENCES Hotels (`HotelID`),
    FOREIGN KEY (`UserID`) REFERENCES Users (`UserID`)
);

user U, time A ~ time B, Hotel C, Room 1, 2, 5
(OID-1, U, A, B, C, 1)
(OID-2, U, A, B, C, 2)
(OID-3, U, A, B, C, 5)
```

```
(maybe)
ORDERROOM
	INT ORDERID
	INT ROOMID
```
