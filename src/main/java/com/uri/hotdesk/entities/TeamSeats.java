package com.uri.hotdesk.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "team_seats")
public class TeamSeats {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teamSeatId;
	@ManyToOne(fetch = FetchType.LAZY)
	private Team team;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private String type;
	@ManyToOne(fetch = FetchType.LAZY)
	private Seat seat;
	@Transient
	private int seatId;
	@Transient
	private String teamId;
	public Long getTeamSeatId() {
		return teamSeatId;
	}
	public void setTeamSeatId(Long teamSeatId) {
		this.teamSeatId = teamSeatId;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	@Override
	public String toString() {
		return "TeamSeats{" +
				"teamSeatId=" + teamSeatId +
				", team=" + team +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", type='" + type + '\'' +
				", seat=" + seat +
				'}';
	}
}
