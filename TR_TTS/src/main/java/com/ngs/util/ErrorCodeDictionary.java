package com.ngs.util;

public class ErrorCodeDictionary {
	
	public static final int GET_USER_DETAILS_ERROR 	= 101;
	public static final int READ_MASK_ERROR 		= 102;
	public static final int CREDIT_MODIFY_ERROR 	= 103;
	public static final int CHECK_BLACKLIST_ERROR 	= 104;
	
	public static final int INVALID_PRIORITY 		= 201;
	public static final int EMPTY_USERNAME 			= 202;
	public static final int EMPTY_MESSAGE 			= 203;
	public static final int EMPTY_MASK 				= 204;
	public static final int EMPTY_PASSWORD 			= 205;
	public static final int EMPTY_DESTINATION		= 206;
	public static final int EMPTY_VALID_DESTINATIONS= 207;
	
	public static final int API_SERVICE_NOT_PROVISIONED = 301;
	public static final int INCORRECT_CREDENTIALS 		= 302;
	public static final int INCORRECT_MASK 				= 303;
	public static final int INSUFFICIENT_CREDIT			= 304;
	
	public static final int INTERNAL_COMMUNICATION_ERROR		= 401;
	
	public static final int SUCCESS		= 1;
}
