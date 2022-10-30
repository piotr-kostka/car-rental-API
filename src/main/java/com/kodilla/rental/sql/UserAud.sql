CREATE TABLE USERS_AUD (
                          EVENT_ID INT(11) NOT NULL AUTO_INCREMENT,
                          EVENT_DATE DATETIME NOT NULL,
                          EVENT_TYPE VARCHAR(10) DEFAULT NULL,
                          USER_ID INT(11) NOT NULL,
                          OLD_ADDRESS VARCHAR(255),
                          NEW_ADDRESS VARCHAR(255),
                          OLD_CREDIT_CARD_NUMBER VARCHAR(255),
                          NEW_CREDIT_CARD_NUMBER VARCHAR(255),
                          OLD_FIRSTNAME VARCHAR(255),
                          NEW_FIRSTNAME VARCHAR(255),
                          OLD_LASTNAME VARCHAR(255),
                          NEW_LASTNAME VARCHAR(255),
                          OLD_MAIL VARCHAR(255),
                          NEW_MAIL VARCHAR(255),
                          OLD_ACCOUNT_STATUS BOOLEAN,
                          NEW_ACCOUNT_STATUS BOOLEAN,
                          OLD_PASSWORD VARCHAR(255),
                          NEW_PASSWORD VARCHAR(255),
                          NEW_SIGNUP_DATE DATETIME,
                          OLD_SIGNUP_DATE DATETIME,
                          NEW_AMOUNT_TO_PAY DOUBLE(9,2),
                          OLD_AMOUNT_TO_PAY DOUBLE(9,2),
                          PRIMARY KEY (EVENT_ID)
);

DELIMITER $$
CREATE TRIGGER USERS_INSERT AFTER INSERT ON users
    FOR EACH ROW
BEGIN
    INSERT INTO USERS_AUD (EVENT_DATE, EVENT_TYPE, USER_ID, NEW_ADDRESS, NEW_CREDIT_CARD_NUMBER, NEW_FIRSTNAME, NEW_LASTNAME, NEW_MAIL,
                           NEW_ACCOUNT_STATUS, NEW_PASSWORD, NEW_SIGNUP_DATE, NEW_AMOUNT_TO_PAY)
        VALUE(CURTIME(), 'INSERT', NEW.USER_ID, NEW.ADDRESS, NEW.CREDIT_CARD_NUMBER, NEW.first_name, NEW.last_name, NEW.MAIL,
              NEW.ACCOUNT_STATUS, NEW.PASSWORD, NEW.SIGNUP_DATE, NEW.AMOUNT_TO_PAY);
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER USERS_DELETE AFTER DELETE ON users
    FOR EACH ROW
BEGIN
    INSERT INTO USERS_AUD (EVENT_DATE, EVENT_TYPE, USER_ID)
        VALUE(CURTIME(), 'DELETE', OLD.user_id);
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER USERS_UPDATE AFTER UPDATE ON users
    FOR EACH ROW
BEGIN
    INSERT INTO USERS_AUD (EVENT_DATE, EVENT_TYPE, USER_ID, NEW_ADDRESS, OLD_ADDRESS, NEW_CREDIT_CARD_NUMBER, OLD_CREDIT_CARD_NUMBER, NEW_FIRSTNAME, OLD_FIRSTNAME,
                           NEW_LASTNAME, OLD_LASTNAME, NEW_MAIL, OLD_MAIL, NEW_ACCOUNT_STATUS, OLD_ACCOUNT_STATUS, NEW_PASSWORD, OLD_PASSWORD,
                           NEW_SIGNUP_DATE, OLD_SIGNUP_DATE, NEW_AMOUNT_TO_PAY, OLD_AMOUNT_TO_PAY)
        VALUE(CURTIME(), 'UPDATE', OLD.USER_ID, NEW.ADDRESS, OLD.ADDRESS, NEW.CREDIT_CARD_NUMBER, OLD.CREDIT_CARD_NUMBER, NEW.first_name, OLD.first_name,
              NEW.last_name, OLD.last_name, NEW.MAIL, OLD.MAIL, NEW.ACCOUNT_STATUS, OLD.ACCOUNT_STATUS, NEW.PASSWORD, OLD.PASSWORD,
              NEW.SIGNUP_DATE, OLD.SIGNUP_DATE, NEW.AMOUNT_TO_PAY, OLD.AMOUNT_TO_PAY);
END $$
DELIMITER ;