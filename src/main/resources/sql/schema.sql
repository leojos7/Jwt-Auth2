CREATE TABLE USER (
  USER_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  USERNAME VARCHAR(50),
  PASSWORD VARCHAR(500),
  FULL_NAME VARCHAR(100),
  STATUS BOOLEAN DEFAULT FALSE,
  MOBILE_NUMBER VARCHAR(13),
  OTP VARCHAR(50) DEFAULT NULL,
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL
);

CREATE TABLE AUTHORITY (
  AUTHORITY_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(50),
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL
);

CREATE TABLE USER_AUTHORITY_MAP (
    USER_ID INT NOT NULL,
    AUTHORITY_ID INT NOT NULL,
	 CREATE_DATE TIMESTAMP NOT NULL,
    MODIFIED_DATE TIMESTAMP NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES USER (USER_ID),
    FOREIGN KEY (AUTHORITY_ID) REFERENCES AUTHORITY (AUTHORITY_ID)
);

CREATE TABLE oauth_access_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token BLOB,
  authentication_id VARCHAR(256) DEFAULT NULL,
  user_name VARCHAR(256) DEFAULT NULL,
  client_id VARCHAR(256) DEFAULT NULL,
  authentication BLOB,
  refresh_token VARCHAR(256) DEFAULT NULL
);

CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token BLOB,
  authentication BLOB
);

CREATE TABLE USER_PARAM (
  USER_PARAM_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(50),
  DESCRIPTION VARCHAR(500),
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL
);

CREATE TABLE USER_PARAM_MAP (
  LOGIN_ID INT,
  USER_PARAM_ID INT,
  VALUE VARCHAR(100),
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL,
  FOREIGN KEY (LOGIN_ID) REFERENCES USER (USER_ID),
  FOREIGN KEY (USER_PARAM_ID) REFERENCES USER_PARAM (USER_PARAM_ID)
);


CREATE TABLE PRODUCT_PARAM (
  PRODUCT_PARAM_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(50),
  DESCRIPTION VARCHAR(500),
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL
);

CREATE TABLE PRODUCT (
  PRODUCT_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(50),
  DESCRIPTION VARCHAR(500),
  ADDED_BY INT,
  CODE VARCHAR(50),
  STATUS BOOLEAN DEFAULT FALSE,
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL,
  FOREIGN KEY (ADDED_BY) REFERENCES USER (USER_ID)
);

CREATE TABLE PARAM_DETAIL (
  PARAM_DETAIL_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  PRODUCT_PARAM_ID INT,
  VALUE VARCHAR(100),
  CODE VARCHAR(100),
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL,
  FOREIGN KEY (PRODUCT_PARAM_ID) REFERENCES PRODUCT_PARAM (PRODUCT_PARAM_ID)
);

CREATE TABLE PRODUCT_PARAM_MAP (
  PRODUCT_ID INT,
  PARAM_DETAIL_ID INT,
  VALUE VARCHAR(100),
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL,
  FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID),
  FOREIGN KEY (PARAM_DETAIL_ID) REFERENCES PARAM_DETAIL (PARAM_DETAIL_ID)
);

CREATE TABLE PRODUCT_VISIT (
  PRODUCT_VISIT_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  PRODUCT_ID INT,
  LOGIN_ID INT,
  DESCRIPTION VARCHAR(500),
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL,
  FOREIGN KEY (LOGIN_ID) REFERENCES USER (USER_ID)
);

CREATE TABLE LOCATION (
  LOCATION_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(50),
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL
);

CREATE TABLE LOCATION_PARAM (
  LOCATION_PARAM_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(50),
  DESCRIPTION VARCHAR(500),
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL
);


    CREATE TABLE LOCATION_PARAM_MAP (
      LOCATION_ID INT,
      LOCATION_PARAM_ID INT,
      CREATE_DATE TIMESTAMP NOT NULL,
      MODIFIED_DATE TIMESTAMP NOT NULL,
      FOREIGN KEY (LOCATION_ID) REFERENCES LOCATION (LOCATION_ID),
      FOREIGN KEY (LOCATION_PARAM_ID) REFERENCES LOCATION_PARAM (LOCATION_PARAM_ID)
    );


CREATE TABLE CART (
  CART_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  LOGIN_ID INT,
  PRODUCT_ID INT,
  QUANTITY INT,
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL,
  FOREIGN KEY (LOGIN_ID) REFERENCES USER (USER_ID),
  FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
);


CREATE TABLE ORDER_STATUS (
  ORDER_STATUS_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(50),
  DESCRIPTION VARCHAR(500),
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL
);


CREATE TABLE ORDER_DETAIL (
  ORDER_DETAIL_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  LOGIN_ID INT,
  LOCATION_ID INT,
  ORDER_STATUS_ID INT,
  UPDATED_BY INT,
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL,
  FOREIGN KEY (LOGIN_ID) REFERENCES USER (USER_ID),
  FOREIGN KEY (LOCATION_ID) REFERENCES LOCATION (LOCATION_ID),
  FOREIGN KEY (ORDER_STATUS_ID) REFERENCES ORDER_STATUS (ORDER_STATUS_ID),
  FOREIGN KEY (UPDATED_BY) REFERENCES USER (USER_ID)
);


CREATE TABLE ORDER_PARAM (
  ORDER_PARAM_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(50),
  DESCRIPTION VARCHAR(500),
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL
);

CREATE TABLE ORDER_PARAM_MAP (
  ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ORDER_DETAIL_ID INT,
  ORDER_PARAM_ID INT,
  VALUE VARCHAR(100),
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL,
  FOREIGN KEY (ORDER_DETAIL_ID) REFERENCES ORDER_DETAIL (ORDER_DETAIL_ID),
  FOREIGN KEY (ORDER_PARAM_ID) REFERENCES ORDER_PARAM (ORDER_PARAM_ID)
);

CREATE TABLE ORDER_PRODUCT_MAP (
  ORDER_DETAIL_ID INT,
  PRODUCT_ID INT,
  QUANTITY INT,
  CREATE_DATE TIMESTAMP NOT NULL,
  MODIFIED_DATE TIMESTAMP NOT NULL,
  FOREIGN KEY (ORDER_DETAIL_ID) REFERENCES ORDER_DETAIL (ORDER_DETAIL_ID),
  FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
);
