create or replace PROCEDURE carr_rtg_proc_inst
	(v_custName IN order_details.cust_name%TYPE,
	v_city IN order_details.city%TYPE,
	v_custType IN order_details.cust_type%TYPE,
  o_orderId OUT order_details.order_nmbr%TYPE)
IS
	v_defCarr	order_details.carrier%TYPE := 'XPOLogistics';
	v_carrier	order_details.carrier%TYPE;
	v_stat		order_details.status%TYPE;
  v_tmp0		number(5) := 0;
	v_tmp1		number(5) := 0;
  v_tmp2		number(5) := 0;
	
BEGIN
  SELECT COUNT(capacity) INTO v_tmp0 FROM carrier_details WHERE
	city = v_city AND carrier_name = v_defCarr;
  
  IF (v_tmp0 > 0) THEN
    SELECT capacity INTO v_tmp1 FROM carrier_details WHERE
    city = v_city AND carrier_name = v_defCarr;
  END IF;
  
	IF (v_tmp1 > 0) THEN
		v_carrier := v_defCarr;
		v_stat := 'IN-TRANSIT';
    v_tmp2 := 1;
	ELSE
		IF v_custType = 'Premium' THEN
        SELECT COUNT(carrier_name) INTO v_tmp2 FROM carrier_details WHERE
        city = v_city AND carrier_rating = 
        (SELECT MAX(carrier_rating) FROM carrier_details WHERE
        city = v_city AND capacity > 0);
        
        IF(v_tmp2 > 0) THEN
          SELECT carrier_name INTO v_carrier FROM carrier_details WHERE
          city = v_city AND carrier_rating = 
          (SELECT MAX(carrier_rating) FROM carrier_details WHERE
          city = v_city AND capacity > 0);
          v_stat := 'CREATED';
        ELSE
          Raise_Application_Error (-20002, 'Unable to place order. No available capacity');
        END IF;
		ELSE
        SELECT COUNT(carrier_name) INTO v_tmp2 FROM carrier_details WHERE
        city = v_city AND carrier_rating > 3.5 AND capacity = 
        (SELECT MAX(capacity) FROM carrier_details WHERE
        city = v_city AND carrier_rating > 3.5 AND capacity > 0) AND rownum = 1;
        
        IF(v_tmp2 > 0) THEN
          SELECT carrier_name INTO v_carrier FROM carrier_details WHERE
          city = v_city AND carrier_rating > 3.5 AND capacity = 
          (SELECT MAX(capacity) FROM carrier_details WHERE
          city = v_city AND carrier_rating > 3.5 AND capacity > 0) AND rownum = 1;
          v_stat := 'CREATED';
        ELSE
          Raise_Application_Error (-20002, 'Unable to place order. No available capacity');
        END IF;
		END IF;
	END IF;
  
  IF(v_tmp2 > 0) THEN
    o_orderId := CONCAT(CONCAT('OR',TO_CHAR(sysdate,'DDMMYY')),LPAD(ordernum_seq.nextval,4,'0'));
    INSERT INTO order_details (ORDER_NMBR, CUST_NAME, CITY, CUST_TYPE, CARRIER, STATUS) VALUES(
    o_orderId,v_custName,v_city,v_custType,v_carrier,v_stat);
  
    UPDATE carrier_details SET capacity = capacity - 1 WHERE 
    city = v_city AND carrier_name = v_carrier;
  END IF;
END;