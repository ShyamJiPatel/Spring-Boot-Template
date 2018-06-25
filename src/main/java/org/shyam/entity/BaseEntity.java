package org.shyam.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
	protected Long id;

	@Column(name = "created_at", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date createdAt;

	@Size(max = 20)
	@Column(name = "created_by", length = 20, updatable = false)
	@JsonIgnore
	private String createdBy;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date updatedAt;

	@Size(max = 20)
	@Column(name = "updated_by", length = 20)
	@JsonIgnore
	private String updatedBy;

	@Column(name = "archived")
	@JsonIgnore
	private boolean archived;

	public Long getId() {
		return id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * Sets createdAt before insert
	 */
	@PrePersist
	public void setCreationDate() {
		this.createdAt = new Date();
	}

	/**
	 * Sets updatedAt before update
	 */
	@PreUpdate
	public void setChangeDate() {
		this.updatedAt = new Date();
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;
		BaseEntity that = (BaseEntity) object;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return 3 * Objects.hash(id);
	}

	public static String getCurrentUser() {
		return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
	}

}