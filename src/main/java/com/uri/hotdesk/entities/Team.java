package com.uri.hotdesk.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team {

	@Id
	@Column(name="team_id")
	private String teamId;
	private String teamName;
	private Date createdDate;
	private String description;
	private String modifiedBy;
	
	@OneToMany(mappedBy = "team")
    private Set<EmployeeTeam> registrations;
	@ManyToOne
	private Location location;
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Set<EmployeeTeam> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(Set<EmployeeTeam> registrations) {
		this.registrations = registrations;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
