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
public class StoryPitch {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pitch_id")
	private Integer id;
	
	@Column(name="user_id")
	private Integer userID;
	private String title;
	@Column(name="completion")
	private String completionDate;
	@Column(name="story_type")
	private String type;
	@Column(name="genre")
	private String genre;
	@Column(name="tag_line")
	private String tagline;
	private String description;
	@Column(name="attached_documents")
	private String attachedDocs;
	@Column(name="date_submission")
	private String submissionDate;
	private Integer priority;
	@Column(name="assistant_approve")
	private Boolean assistantApprove;
	@Column(name="general_approve")
	private Boolean generalApprove;
	@Column(name="senior_approve")
	private Boolean seniorApprove;
	@Column(name="final_changes")
	private Boolean finalChanges;
	@Column(name="final_approve")
	private Boolean finalApprove;
	private Integer status;
	
	public StoryPitch() {
		id = 0;
		userID = 0;
		title = null;
		completionDate = null;
		type = null;
		genre = null;
		tagline = null;
		description = null;
		attachedDocs = null;
		submissionDate = "2020-11-11"; //datestamp
		priority = 1;
		assistantApprove = false;
		generalApprove = false;
		seniorApprove = false;
		finalChanges = false;
		finalApprove = false;
		status = 1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAttachedDocs() {
		return attachedDocs;
	}

	public void setAttachedDocs(String attachedDocs) {
		this.attachedDocs = attachedDocs;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getAssistantApprove() {
		return assistantApprove;
	}

	public void setAssistantApprove(Boolean assistantApprove) {
		this.assistantApprove = assistantApprove;
	}

	public Boolean getGeneralApprove() {
		return generalApprove;
	}

	public void setGeneralApprove(Boolean generalApprove) {
		this.generalApprove = generalApprove;
	}

	public Boolean getSeniorApprove() {
		return seniorApprove;
	}

	public void setSeniorApprove(Boolean seniorApprove) {
		this.seniorApprove = seniorApprove;
	}

	public Boolean getFinalChanges() {
		return finalChanges;
	}

	public void setFinalChanges(Boolean finalChanges) {
		this.finalChanges = finalChanges;
	}

	public Boolean getFinalApprove() {
		return finalApprove;
	}

	public void setFinalApprove(Boolean finalApprove) {
		this.finalApprove = finalApprove;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assistantApprove == null) ? 0 : assistantApprove.hashCode());
		result = prime * result + ((attachedDocs == null) ? 0 : attachedDocs.hashCode());
		result = prime * result + ((completionDate == null) ? 0 : completionDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((finalApprove == null) ? 0 : finalApprove.hashCode());
		result = prime * result + ((finalChanges == null) ? 0 : finalChanges.hashCode());
		result = prime * result + ((generalApprove == null) ? 0 : generalApprove.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((seniorApprove == null) ? 0 : seniorApprove.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submissionDate == null) ? 0 : submissionDate.hashCode());
		result = prime * result + ((tagline == null) ? 0 : tagline.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
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
		StoryPitch other = (StoryPitch) obj;
		if (assistantApprove == null) {
			if (other.assistantApprove != null)
				return false;
		} else if (!assistantApprove.equals(other.assistantApprove))
			return false;
		if (attachedDocs == null) {
			if (other.attachedDocs != null)
				return false;
		} else if (!attachedDocs.equals(other.attachedDocs))
			return false;
		if (completionDate == null) {
			if (other.completionDate != null)
				return false;
		} else if (!completionDate.equals(other.completionDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (finalApprove == null) {
			if (other.finalApprove != null)
				return false;
		} else if (!finalApprove.equals(other.finalApprove))
			return false;
		if (finalChanges == null) {
			if (other.finalChanges != null)
				return false;
		} else if (!finalChanges.equals(other.finalChanges))
			return false;
		if (generalApprove == null) {
			if (other.generalApprove != null)
				return false;
		} else if (!generalApprove.equals(other.generalApprove))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (seniorApprove == null) {
			if (other.seniorApprove != null)
				return false;
		} else if (!seniorApprove.equals(other.seniorApprove))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submissionDate == null) {
			if (other.submissionDate != null)
				return false;
		} else if (!submissionDate.equals(other.submissionDate))
			return false;
		if (tagline == null) {
			if (other.tagline != null)
				return false;
		} else if (!tagline.equals(other.tagline))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StoryPitch [id=" + id + ", userID=" + userID + ", title=" + title + ", completionDate=" + completionDate
				+ ", type=" + type + ", genre=" + genre + ", tagline=" + tagline + ", description=" + description
				+ ", attachedDocs=" + attachedDocs + ", submissionDate=" + submissionDate + ", priority=" + priority
				+ ", assistantApprove=" + assistantApprove + ", generalApprove=" + generalApprove + ", seniorApprove="
				+ seniorApprove + ", finalChanges=" + finalChanges + ", finalApprove=" + finalApprove + ", status="
				+ status + "]";
	}
}
