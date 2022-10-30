CREATE TABLE CARS_AUD (
                           EVENT_ID INT(11) NOT NULL AUTO_INCREMENT,
                           EVENT_DATE DATETIME NOT NULL,
                           EVENT_TYPE VARCHAR(10) DEFAULT NULL,
                           CAR_ID INT(11) NOT NULL,
                           OLD_MODEL_ID INT(11),
                           NEW_MODEL_ID INT(11),
                           OLD_CAR_STATUS VARCHAR(255),
                           NEW_CAR_STATUS VARCHAR(255),
                           OLD_LICENSE_NUMBER VARCHAR(255),
                           NEW_LICENSE_NUMBER VARCHAR(255),
                           OLD_PRICE DOUBLE(9,2),
                           NEW_PRICE DOUBLE(9,2),
                           PRIMARY KEY (EVENT_ID)
);

DELIMITER $$
CREATE TRIGGER CARS_INSERT AFTER INSERT ON cars
    FOR EACH ROW
BEGIN
    INSERT INTO CARS_AUD (EVENT_DATE, EVENT_TYPE, CAR_ID, NEW_MODEL_ID, NEW_CAR_STATUS, NEW_LICENSE_NUMBER, NEW_PRICE)
        VALUE(CURTIME(), 'INSERT', NEW.CAR_ID, NEW.MODEL, NEW.CAR_STATUS, NEW.LICENSE_NUMBER, NEW.PRICE);
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER CARS_DELETE AFTER DELETE ON cars
    FOR EACH ROW
BEGIN
    INSERT INTO CARS_AUD (EVENT_DATE, EVENT_TYPE, CAR_ID)
        VALUE(CURTIME(), 'DELETE', OLD.car_id);
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER CARS_UPDATE AFTER UPDATE ON cars
    FOR EACH ROW
BEGIN
    INSERT INTO CARS_AUD (EVENT_DATE, EVENT_TYPE, CAR_ID, OLD_MODEL_ID, NEW_MODEL_ID,
                OLD_CAR_STATUS, NEW_CAR_STATUS, OLD_LICENSE_NUMBER, NEW_LICENSE_NUMBER, OLD_PRICE, NEW_PRICE)
        VALUE(CURTIME(), 'UPDATE', OLD.car_id, OLD.MODEL, NEW.MODEL, OLD.CAR_STATUS, NEW.CAR_STATUS,
              OLD.LICENSE_NUMBER, NEW.LICENSE_NUMBER, OLD.PRICE, NEW.PRICE);
END $$
DELIMITER ;