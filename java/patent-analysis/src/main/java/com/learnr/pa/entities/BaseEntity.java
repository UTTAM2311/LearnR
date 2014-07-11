package com.learnr.pa.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.learnr.pa.persistence.IEntity;
import com.learnr.pa.persistence.PesistenceListener;

/**
 * Base definition for persistable objects/entities. Extend this class to make
 * an object persistable.
 */
@MappedSuperclass
@EntityListeners(value = { PesistenceListener.class })
public class BaseEntity implements IEntity, Serializable {

	private Integer pkey;

	@JsonIgnore
	private Date createdOn;
	@JsonIgnore
	private String createdBy;

	@JsonIgnore
	private Date lastUpdatedOn;
	@JsonIgnore
	private String lastUpdatedBy;

	@JsonIgnore
	private Integer version;
	@JsonIgnore
	private Boolean deleted = false;
	
	
	private String metadata;
	
	/* Getters and Setters */

	@Id
	@GeneratedValue
	@Column(name = "pkey")
	public Integer getPkey() {
		return pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	/**
	 * Returns when the object is created
	 * 
	 * @return the created on date
	 */
	@Column(name = "created_on")
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * Sets the created date
	 * 
	 * <p>
	 * <b>Note :</b> This is supposed to be set by the persistent listener. The
	 * values set may possibly get overridden by the {@link PesistenceListener}.
	 * </p>
	 * 
	 * @param createdOn
	 *            the created on date
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Returns the user by whom the object is created
	 * 
	 * @return the username
	 */
	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the name of the user creating the object
	 * 
	 * <p>
	 * <b>Note :</b> This is supposed to be set by the persistent listener. The
	 * values set may possibly get overridden by the {@link PesistenceListener}.
	 * </p>
	 * 
	 * @param createdBy
	 *            username of the user
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Returns when the object is last updated
	 * 
	 * @return the last updated on date
	 */
	@Column(name = "last_updated_on")
	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	/**
	 * Sets the last updated date
	 * 
	 * <p>
	 * <b>Note :</b> This is supposed to be set by the persistent listener. The
	 * values set may possibly get overridden by the {@link PesistenceListener}.
	 * </p>
	 * 
	 * @param lastUpdatedOn
	 *            the last updated on date
	 */
	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	/**
	 * Returns the user by whom the object is last updated
	 * 
	 * @return the username
	 */
	@Column(name = "last_updated_by")
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	/**
	 * Sets the name of the user updating the object
	 * 
	 * <p>
	 * <b>Note :</b> This is supposed to be set by the persistent listener. The
	 * values set may possibly get overridden by the {@link PesistenceListener}.
	 * </p>
	 * 
	 * @param lastUpdatedBy
	 *            username of the user
	 */
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	/**
	 * Returns the current version/revision of the object.
	 * 
	 * @return the verison number
	 */
	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return version;
	}

	/**
	 * Sets the current version/revision of the object.
	 * <p>
	 * <b>Note :</b> This is supposed to be set by the Entity Manager in the
	 * persistence context. The values set may possibly disturb the version
	 * management.
	 * </p>
	 * 
	 * @param the
	 *            version of the object
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "deleted")
	public Boolean getDeleted() {
		if (deleted == null) {
			deleted = false;
		}
		return deleted;
	}

	public void setDeleted(Boolean isDeleted) {
		this.deleted = isDeleted;
	}

	@Lob
	@Column(name = "metadata", columnDefinition = "TEXT")
	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

}
