package com.uri.hotdesk.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_roles")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "userRoleSeqGen")
	@SequenceGenerator(name = "userRoleSeqGen", sequenceName = "users_roles_id_seq", allocationSize = 1)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;

	private String username;

	private Boolean isCurrent;

	private Date createdDate;

	private Date updatedDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Boolean isCurrent() {
		return isCurrent;
	}

	public void setCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
