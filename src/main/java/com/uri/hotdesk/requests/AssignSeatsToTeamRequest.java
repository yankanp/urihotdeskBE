package com.uri.hotdesk.requests;

import java.util.List;

public class AssignSeatsToTeamRequest {

	private List<TeamSeatCreateRequest> teamSeatCreateRequest;

	public List<TeamSeatCreateRequest> getTeamSeatCreateRequest() {
		return teamSeatCreateRequest;
	}

	public void setTeamSeatCreateRequest(List<TeamSeatCreateRequest> teamSeatCreateRequest) {
		this.teamSeatCreateRequest = teamSeatCreateRequest;
	}
	
}
