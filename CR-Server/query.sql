CREATE TABLE CARRIER_DETAILS
(
	CITY			VARCHAR2(30),
	CARRIER_NAME	VARCHAR2(30),
	CARRIER_RATING	NUMBER(10,2),
	CAPACITY		NUMBER
);

CREATE TABLE ORDER_DETAILS
(
	ORDER_NMBR	VARCHAR2(20),
	CUST_NAME	VARCHAR2(50),
	CITY		VARCHAR2(30),
	CUST_TYPE	VARCHAR2(10),
	CARRIER		VARCHAR2(30),
	STATUS		VARCHAR2(20) -- CREATED, IN-TRANSIT, DELIVERED
);

CREATE TABLE XPO_DETAILS
(
	CITY			VARCHAR2(30),
	NO_OF_VEHICALS	NUMBER,
	CAPACITY		NUMBER
);

CREATE SEQUENCE ORDER_NUMBER
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 20;