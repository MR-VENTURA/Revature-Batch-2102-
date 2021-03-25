package com.revature.app.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Posts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer postId;
	@ManyToOne
	@JoinColumn(name="author_id")
	private People authorId;
	@ManyToOne
	@JoinColumn(name="parent_post_id")
	private Posts parentPostId;
	@Column(name="flagged_for_review")
	private Boolean flaggedForReview;
	private Integer likes;
	private Integer dislikes;
	@Column(name="last_activity_date", insertable = false)
	private Long lastActivityDate;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="content_id")
	private Content contentId;
	
	public Posts() {
		this.authorId = new People();
		this.dislikes = 0;
		this.likes = 0;
		this.flaggedForReview = false;
		this.lastActivityDate = null;
		this.parentPostId = null;
		this.postId = 0;

	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public People getAuthorId() {
		return authorId;
	}
	public void setAuthorId(People authorId) {
		this.authorId = authorId;
	}
	public Posts getParentPostId() {
		return parentPostId;
	}
	public void setParentPostId(Posts parentPostId) {
		this.parentPostId = parentPostId;
	}
	public Boolean getFlaggedForReview() {
		return flaggedForReview;
	}
	public void setFlaggedForReview(Boolean flaggedForReview) {
		this.flaggedForReview = flaggedForReview;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public Integer getDislikes() {
		return dislikes;
	}
	public void setDislikes(Integer dislikes) {
		this.dislikes = dislikes;
	}
	public Long getLastActivityDate() {
		return lastActivityDate;
	}
	public void setLastActivityDate(Long lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}
	
	public Content getContentId() {
		return contentId;
	}
	public void setContentId(Content contentId) {
		this.contentId = contentId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((contentId == null) ? 0 : contentId.hashCode());
		result = prime * result + ((dislikes == null) ? 0 : dislikes.hashCode());
		result = prime * result + ((flaggedForReview == null) ? 0 : flaggedForReview.hashCode());
		result = prime * result + ((lastActivityDate == null) ? 0 : lastActivityDate.hashCode());
		result = prime * result + ((likes == null) ? 0 : likes.hashCode());
		result = prime * result + ((parentPostId == null) ? 0 : parentPostId.hashCode());
		result = prime * result + ((postId == null) ? 0 : postId.hashCode());
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
		Posts other = (Posts) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (contentId == null) {
			if (other.contentId != null)
				return false;
		} else if (!contentId.equals(other.contentId))
			return false;
		if (dislikes == null) {
			if (other.dislikes != null)
				return false;
		} else if (!dislikes.equals(other.dislikes))
			return false;
		if (flaggedForReview == null) {
			if (other.flaggedForReview != null)
				return false;
		} else if (!flaggedForReview.equals(other.flaggedForReview))
			return false;
		if (lastActivityDate == null) {
			if (other.lastActivityDate != null)
				return false;
		} else if (!lastActivityDate.equals(other.lastActivityDate))
			return false;
		if (likes == null) {
			if (other.likes != null)
				return false;
		} else if (!likes.equals(other.likes))
			return false;
		if (parentPostId == null) {
			if (other.parentPostId != null)
				return false;
		} else if (!parentPostId.equals(other.parentPostId))
			return false;
		if (postId == null) {
			if (other.postId != null)
				return false;
		} else if (!postId.equals(other.postId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Posts [postId=" + postId + ", authorId=" + authorId + ", parentPostId=" + parentPostId
				+ ", flaggedForReview=" + flaggedForReview + ", likes=" + likes + ", dislikes=" + dislikes
				+ ", lastActivityDate=" + lastActivityDate + ", contentId=" + contentId + "]";
	}
	
	
	
}
