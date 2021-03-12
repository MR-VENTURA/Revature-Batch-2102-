package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class RejectForm {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reject_id")
	private Integer id;
	//@ManyToOne
	@Column(name="pitch_id")
	private Integer pitchID; //change name to pitch here and in column?
	private String reason;
	
	public RejectForm() {
		id = 0;
		pitchID = 0;
		reason = "Reason for rejection";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPitchID() {
		return pitchID;
	}

	public void setPitchID(Integer pitchID) {
		this.pitchID = pitchID;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pitchID == null) ? 0 : pitchID.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
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
		RejectForm other = (RejectForm) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pitchID == null) {
			if (other.pitchID != null)
				return false;
		} else if (!pitchID.equals(other.pitchID))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RejectForm [id=" + id + ", pitchID=" + pitchID + ", reason=" + reason + "]";
	}
}
