create or replace PROCEDURE carr_rtg_proc_btch
IS

v_city		order_details.city%TYPE;
	v_carrier	order_details.carrier%TYPE;
	v_tmp1		number(5);
	v_stat		order_details.status%TYPE := 'DELIVERED';
	CURSOR carr_c1 IS 
		SELECT DISTINCT a.city FROM carrier_details a, order_details b WHERE
		a.city = b.city AND a.carrier_name = b.carrier AND b.status = 'IN-TRANSIT';

	CURSOR carr_c2 IS 
		SELECT DISTINCT a.carrier_name FROM carrier_details a, order_details b WHERE
		a.city = b.city AND a.carrier_name = b.carrier AND b.status = 'IN-TRANSIT';
BEGIN
	OPEN carr_c1;
	LOOP
		FETCH carr_c1 INTO v_city;
		OPEN carr_c2;
		LOOP
			FETCH carr_c2 INTO v_carrier;

			SELECT COUNT(*) INTO v_tmp1 FROM order_details WHERE
			city = v_city AND carrier = v_carrier AND status = 'IN-TRANSIT';
			
			UPDATE order_details SET status = v_stat WHERE 
			city = v_city AND carrier = v_carrier AND status = 'IN-TRANSIT';

			UPDATE carrier_details SET capacity = capacity + v_tmp1 WHERE
			city = v_city AND carrier_name = v_carrier;			

			EXIT WHEN carr_c2%NOTFOUND;
		END LOOP;
		CLOSE carr_c2;
		EXIT WHEN carr_c1%NOTFOUND;
	END LOOP;
	CLOSE carr_c1;
END;

/


create or replace PROCEDURE carr_rtg_proc_inst
	(v_custName IN order_details.cust_name%TYPE,
	v_city IN order_details.city%TYPE,
	v_custType IN order_details.cust_type%TYPE)
IS
	v_defCarr	order_details.carrier%TYPE := 'XPO Logistics';
	v_carrier	order_details.carrier%TYPE;
	v_stat		order_details.status%TYPE;
	v_tmp1		number(5);
	
BEGIN
	SELECT capacity INTO v_tmp1 FROM carrier_details WHERE
	city = v_city AND carrier_name = v_defCarr;

	IF v_tmp1 > 0 THEN
		v_carrier := v_defCarr;
		v_stat := 'IN-TRANSIT';
	ELSE
		IF v_custType = 'Premium' THEN
			SELECT carrier_name INTO v_carrier FROM carrier_details WHERE
			city = v_city AND carrier_rating = 
			(SELECT MAX(carrier_rating) FROM carrier_details WHERE
			city = v_city AND capacity > 0);
		ELSE
			SELECT carrier_name INTO v_carrier FROM carrier_details WHERE
			city = v_city AND carrier_rating > 3.5 AND capacity = 
			(SELECT MAX(capacity) FROM carrier_details WHERE
			city = v_city AND carrier_rating > 3.5) AND rownum = 1;
		END IF;
		v_stat := 'CREATED';
	END IF;

	INSERT INTO order_details (ORDER_NMBR, CUST_NAME, CITY, CUST_TYPE, CARRIER, STATUS) VALUES(
	CONCAT(CONCAT('OR',TO_CHAR(sysdate,'DDMMYY')),LPAD(ordernum_seq.nextval,4,'0')),
	v_custName,v_city,v_custType,v_carrier,v_stat);

	UPDATE carrier_details SET capacity = capacity - 1 WHERE 
	city = v_city AND carrier_name = v_carrier;
END;

/

create or replace PROCEDURE carr_rtg_proc_updt
	(v_order_nmbr IN order_details.order_nmbr%TYPE,
	v_city IN order_details.city%TYPE,
	v_prevCarrier	order_details.carrier%TYPE,
	v_carrier	order_details.carrier%TYPE)
IS	
	v_stat		order_details.status%TYPE := 'IN-TRANSIT';
	v_tmp1		number(5);
	
BEGIN
	SELECT capacity INTO v_tmp1 FROM carrier_details WHERE
	city = v_city AND carrier_name = v_carrier;

	IF v_tmp1 > 0 THEN
		UPDATE carrier_details SET capacity = capacity - 1 WHERE 
		city = v_city AND carrier_name = v_carrier;

		UPDATE carrier_details SET capacity = capacity + 1 WHERE 
		city = v_city AND carrier_name = v_prevCarrier;

		UPDATE order_details SET carrier = v_carrier, status = v_stat WHERE
		order_nmbr = v_order_nmbr;
	ELSE
		Raise_Application_Error (-20001, 'Selected carrier has no available capacity');
	END IF;
END;

/

BEGIN
    DBMS_SCHEDULER.CREATE_JOB (
         job_name             => 'ORDER_STAT_UPDT',
         job_type             => 'STORED_PROCEDURE',
         job_action           => 'carr_rtg_proc_btch',
         repeat_interval      => 'FREQ=MINUTELY;INTERVAL=5;',
         enabled              => TRUE);
END;