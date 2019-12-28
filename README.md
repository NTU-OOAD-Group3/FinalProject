# FinalProject

```
├─.vscode
└─HOTEL
    └─src
        └─org
            └─hotelsystem
                ├─control
                ├─model
                └─view
```

```SQL
ROOM TABLE
	INT HOTELID NOT NULL,
	INT ROOMID NOT NULL,/*(Publish key: HETELID,ROOMID)*/
	 /*set first digit as roomtype, 4 random digits following as unique room id */
	INT HOTELID NOT NULL
	/*ENUM ROOMTYPE = {1, 2, 4}*/
	INT ROOMPRICE NOT NULL,

ORDER TABLE
	INT ORDERID NOT NULL,(PUBLIC KEY)
	INT USERID NOT NULL,(FOREIGN KEY)
	DATE CHECKIN NOT NULL,
	DATE CHECKOUT NOT NULL,
	INT HOTELID NOT NULL,
	INT ROOMID NOT NULL

USER TABLE
	INT USERID (PUBLIC KEY)
	INT USERTYPE /*1=user, 0=hoster*/
	VARCHAR USERNAME
	VARCHAR PASSWORD

HOTEL TABLE
	INT HOTELID ,(PUBLIC KEY)
	INT HOTELSTAR,
	CHAR LOCALITY,
	VARCHAR ADDRESS	



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
