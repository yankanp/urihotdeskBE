package com.uri.hotdesk.exceptions;

public class SeatDoesNotBelongToTeamException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "SEAT_DOES_NOT_BELONG_TO_TEAM";
	}

	
}
