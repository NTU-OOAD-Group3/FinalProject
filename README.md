# FinalProject

```bash
#!/bin/bash

#File: tree-md

tree=$(tree -tf --noreport -I '*~' --charset ascii $1 |
       sed -e 's/| \+/  /g' -e 's/[|`]-\+/ */g' -e 's:\(* \)\(\(.*/\)\([^/]\+\)\):\1[\4](\2):g')

printf "# Project tree\n\n${tree}"
```

```SQL
ROOM TABLE
	INT ROOMID
	INT HOTELID
	ENUM ROOMTYPE = {1, 2, 4}
	INT ROOMPRICE
```
```SQL
ORDER TABLE
	INT ORDERID
	INT USERID
	DATE CHECKIN
	DATE CHECKOUT
	INT HOTELID
	INT ROOMID
	
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