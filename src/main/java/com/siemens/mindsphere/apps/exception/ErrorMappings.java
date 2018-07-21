package com.siemens.mindsphere.apps.exception;

public interface ErrorMappings {

    public static final long USER_NOT_FOUND_CODE = 1000;
    public static final String USER_NOT_FOUND_MESSAGE = "User not found in Database, Please check whether it is an existing user or not";
    public static final long ALREADY_EXISTING_USER_CODE = 1001;
    public static final String ALREADY_EXISTING_USER_MESSAGE = "User is already in Database, Please try with another username";


    public static final long PRODUCT_NOT_FOUND_CODE = 2000;
    public static final String PRODUCT_NOT_FOUND_MESSAGE = "Product not found in Database, Please check whether it is an existing product or not";
    public static final long ALREADY_EXISTING_PRODUCT_CODE = 2001;
    public static final String ALREADY_EXISTING_PRODUCT_MESSAGE = "Product is already in Database, Please check the existing product Ids";

    public static final long MAIL_NOT_SENT_CODE = 3000;
    public static final String MAIL_NOT_SENT_MASSAGE = "Mail not sent, Please check the configuration";

    public static final long TOKEN_EXPIRED_CODE = 4000;
    public static final String TOKEN_EXPIRED_MASSAGE = "Mail not sent, Please check the configuration";
}
