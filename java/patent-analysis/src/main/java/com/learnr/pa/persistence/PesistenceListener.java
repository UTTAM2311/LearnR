package com.learnr.pa.persistence;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learnr.pa.entities.BaseEntity;

/**
 * Its an interceptor to intercept the persistence layer actions. Once
 * intercepted, some of the fields in the object/entity are set/updated as
 * needed by the action.
 * 
 * <p>
 * Intercept actions are as follows.
 * <li><b>Persist</b> : sets in the entity values for createdOn and createdBy</li>
 * <li><b>Update</b> : sets in the entity values for updatedOn and updatedBy</li>
 * <p>
 */
public class PesistenceListener {

	private static final Logger logger = LoggerFactory.getLogger(PesistenceListener.class);

	private static final String ANONYMOUS_USER = "anonymous";
	
	/**
	 * This intercept method is executed during the object persist.
	 * 
	 * @param entity
	 *            the entity being persisted
	 */
	@PrePersist
	void onPreCreate(Object entity) {

		BaseEntity baseEntity = (BaseEntity) entity;
		baseEntity.setCreatedOn(new Date());
		baseEntity.setLastUpdatedOn(null);
		baseEntity.setLastUpdatedBy("");
		baseEntity.setCreatedBy(ANONYMOUS_USER);

		logger.debug("Pre-persist base entity : " + baseEntity);
	}

	/**
	 * This intercept method is executed during the object update.
	 * 
	 * @param entity
	 *            the entity being updated
	 */
	@PreUpdate
	void onPreUpdate(Object entity) {

		BaseEntity baseEntity = (BaseEntity) entity;
		baseEntity.setLastUpdatedOn(new Date());
		baseEntity.setLastUpdatedBy(ANONYMOUS_USER);

		logger.debug("Pre-update base entity : " + baseEntity);
	}

}
