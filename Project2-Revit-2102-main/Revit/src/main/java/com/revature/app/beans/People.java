package com.revature.app.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class People {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer peopleId;
	private String username;
	@Column(name="userpass")
	private String userPass;
	@ManyToOne
	@JoinColumn(name="account_statuses")
	private Status accountStatuses;
	@ManyToOne
	@JoinColumn(name="account_roles")
	private Roles accountRoles;
	
	public People() {
		peopleId =0;
		accountRoles = new Roles();
		accountStatuses = new Status();
		username = "";
		userPass = "";
		
	}
	public Integer getPeopleId() {
		return peopleId;
	}
	public void setPeopleId(Integer peopleId) {
		this.peopleId = peopleId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public Status getAccountStatuses() {
		return accountStatuses;
	}
	public void setAccountStatuses(Status accountStatuses) {
		this.accountStatuses = accountStatuses;
	}
	public Roles getAccountRoles() {
		return accountRoles;
	}
	public void setAccountRoles(Roles accountRoles) {
		this.accountRoles = accountRoles;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountRoles == null) ? 0 : accountRoles.hashCode());
		result = prime * result + ((accountStatuses == null) ? 0 : accountStatuses.hashCode());
		result = prime * result + ((peopleId == null) ? 0 : peopleId.hashCode());
		result = prime * result + ((userPass == null) ? 0 : userPass.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		People other = (People) obj;
		if (accountRoles == null) {
			if (other.accountRoles != null)
				return false;
		} else if (!accountRoles.equals(other.accountRoles))
			return false;
		if (accountStatuses == null) {
			if (other.accountStatuses != null)
				return false;
		} else if (!accountStatuses.equals(other.accountStatuses))
			return false;
		if (peopleId == null) {
			if (other.peopleId != null)
				return false;
		} else if (!peopleId.equals(other.peopleId))
			return false;
		if (userPass == null) {
			if (other.userPass != null)
				return false;
		} else if (!userPass.equals(other.userPass))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "People [peopleId=" + peopleId + ", username=" + username + ", userPass=" + userPass
				+ ", accountStatuses=" + accountStatuses + ", accountRoles=" + accountRoles + "]";
	}
	
	
}
