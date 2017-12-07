CREATE TABLE CARRIER_DETAILS
(
	CITY		VARCHAR2(30),
	CARRIER_NAME	VARCHAR2(30),
	CARRIER_RATING	NUMBER(5,2),
	TOT_CAP		NUMBER(5),
	AVAIL_CAP	NUMBER(5)
);

CREATE TABLE ORDER_DETAILS
(
	ORDER_NMBR	VARCHAR2(20),
	CUST_NAME	VARCHAR2(50),
	CITY		VARCHAR2(30),
	CUST_TYPE	VARCHAR2(10),
	CARRIER		VARCHAR2(30),
	STATUS		VARCHAR2(10)
);

CREATE OR REPLACE SEQUENCE ordernum_seq
START WITH     0
INCREMENT BY   1
NOCACHE
NOCYCLE;