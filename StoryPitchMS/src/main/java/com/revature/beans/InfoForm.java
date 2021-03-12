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
public class InfoForm {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="info_id")
	private Integer id;
	//@ManyToOne
	@Column(name="pitch_id")
	private Integer pitchID; //change name to pitch here and in column?
	private String request; //change these names? 
	private String response;
	
	public InfoForm() {
		id = 0;
		pitchID = 0;
		request = "Editor request here";
		response = "Response here";
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

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pitchID == null) ? 0 : pitchID.hashCode());
		result = prime * result + ((request == null) ? 0 : request.hashCode());
		result = prime * result + ((response == null) ? 0 : response.hashCode());
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
		InfoForm other = (InfoForm) obj;
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
		if (request == null) {
			if (other.request != null)
				return false;
		} else if (!request.equals(other.request))
			return false;
		if (response == null) {
			if (other.response != null)
				return false;
		} else if (!response.equals(other.response))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InfoForm [id=" + id + ", pitchID=" + pitchID + ", request=" + request + ", response=" + response + "]";
	}
}
