package com.uri.hotdesk.exceptions;

public class SeatAlreadyExsistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "SEAT_ALREADY_EXSIST";
	}

	
}
