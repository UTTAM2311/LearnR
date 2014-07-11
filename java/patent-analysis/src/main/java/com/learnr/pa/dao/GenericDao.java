package com.learnr.pa.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learnr.pa.persistence.IDao;
import com.learnr.pa.persistence.IEntity;

/**
 * Data Access Object for all the Entities implementing {@link IEntity}.
 * 
 * <p> This Data Access Object(DAO) provides the methods like persist, update,
 * find/search .. to manipulate these objects. </p>
 * 
 * <p> This implementation makes use of the JPA ( Javax Persistence API ) for its
 * persistence context and {@link EntityManager}. </p>
 * 
 * <p> Be vary of transaction management while using this object. </p>
 * 
 * @param E extends {@link IEntity}
 * @param K extends {@link Serializable}
 * 
 * @see IDao
 * @see IEntity
 */
public class GenericDao<E extends IEntity, K extends Serializable> implements IDao<E, K> {

	private static final Logger logger = LoggerFactory.getLogger(GenericDao.class);

	protected EntityManager entityManager;

	/* Getters and Setters */

	/**
	 * Returns the entity manager
	 * 
	 * @return the entitymanager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the entity manager
	 * 
	 * @param entityManager
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/* Basic CRUD methods */

	@Override
	public void save(E inEntity) {
		if (inEntity != null) {
			entityManager.persist(inEntity);
			entityManager.flush();
		}
	}

	@Override
	public E update(E inEntity) {
		if (inEntity != null) {
			return entityManager.merge(inEntity);
		}
		return null;
	}

	@Override
	public void delete(E inEntity) {
		if (inEntity != null) {
			inEntity.setDeleted(true);
			entityManager.merge(inEntity);
			entityManager.flush();
		}
	}

	@Override
	public void delete(Class<E> inEntityClass, List<Integer> pkeys) {
		String entityClsName = inEntityClass.getSimpleName();
		String sQuery = "UPDATE " + entityClsName + " instance SET instance.deleted = " + true
				+ " WHERE instance.pkey in (:pkeys)";

		logger.debug("Query : " + sQuery);

		Query query = entityManager.createQuery(sQuery);
		query.setParameter("pkeys", pkeys);
		query.executeUpdate();
	}

	@Override
	public void restore(E inEntity) {
		if (inEntity != null) {
			inEntity.setDeleted(false);
			entityManager.merge(inEntity);
			entityManager.flush();
		}
	}

	@Override
	public void restore(Class<E> inEntityClass, List<Integer> pkeys) {
		String entityClsName = inEntityClass.getSimpleName();
		String sQuery = "UPDATE " + entityClsName + " instance SET instance.deleted = " + false
				+ " WHERE instance.pkey in (:pkeys)";

		logger.debug("Query : " + sQuery);

		Query query = entityManager.createQuery(sQuery);
		query.setParameter("pkeys", pkeys);
		query.executeUpdate();
	}

	@Override
	public void purge(E inEntity) {
		if (inEntity != null) {
			entityManager.remove(inEntity);
		}
	}

	@Override
	public void purge(Class<E> inEntityClass, List<Integer> pkeys) {
		E entity = null;
		for (Integer pkey : pkeys) {
			entity = find(inEntityClass, pkey);
			this.purge(entity);
		}
	}

	@Override
	public <E> E find(Class<E> inEntityClass, Integer inPkey) {
		return entityManager.find(inEntityClass, inPkey);
	}

	@Override
	public <E> List<E> find(Class<E> inEntityClass, Boolean isDeleted) {
		return this.find(inEntityClass, isDeleted, null);
	}

	@Override
	public <E> List<E> find(Class<E> inEntityClass, Boolean isDeleted, String orderby) {

		Entity entity = inEntityClass.getAnnotation(Entity.class);
		String entityClsName = inEntityClass.getSimpleName();
		if (entity != null) {
			String eName = entity.name();
			if ((eName != null) && (eName.length() > 0)) {
				entityClsName = eName;
			}
		}

		String sQuery = "SELECT instance FROM " + entityClsName + " instance WHERE instance.deleted";
		if (isDeleted) {
			sQuery += " IS true";
		} else {
			sQuery += " IS NOT true";
		}

		if (orderby == null) {
			sQuery += " ORDER BY instance.createdOn DESC";
		} else {
			sQuery += " ORDER BY instance." + orderby;
		}

		logger.debug("Query : " + sQuery);

		Query query = entityManager.createQuery(sQuery);
		return query.getResultList();
	}

	@Override
	public <E> List<E> find(Class<E> inEntityClass, Boolean isDeleted, String orderby, Integer pageNumber, Integer pageSize) {
		
		Entity entity = inEntityClass.getAnnotation(Entity.class);
		String entityClsName = inEntityClass.getSimpleName();
		if (entity != null) {
			String eName = entity.name();
			if ((eName != null) && (eName.length() > 0)) {
				entityClsName = eName;
			}
		}

		String sQuery = "SELECT instance FROM " + entityClsName + " instance WHERE instance.deleted";
		if (isDeleted) {
			sQuery += " IS true";
		} else {
			sQuery += " IS NOT true";
		}

		if (orderby == null) {
			sQuery += " ORDER BY instance.createdOn DESC";
		} else {
			sQuery += " ORDER BY instance." + orderby;
		}

		Query query = entityManager.createQuery(sQuery);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public <E> Integer count(Class<E> inEntityClass) {
		String entityClsName = inEntityClass.getSimpleName();
		String sQuery = "SELECT count(instance) from " + entityClsName + " instance";

		logger.debug("Query : " + sQuery);

		Query query = entityManager.createQuery(sQuery);
		Long count = (Long) query.getSingleResult();
		return Integer.valueOf(count.toString());
	}

	@Override
	public <E> Integer count(Class<E> inEntityClass, Boolean isDeleted) {
		String entityClsName = inEntityClass.getSimpleName();
		String sQuery = "SELECT count(instance) from " + entityClsName + " instance WHERE instance.deleted";
		if (isDeleted) {
			sQuery += " IS true";
		} else {
			sQuery += " IS NOT true";
		}

		logger.debug("Query : " + sQuery);

		Query query = entityManager.createQuery(sQuery);
		Long count = (Long) query.getSingleResult();
		return Integer.valueOf(count.toString());
	}

	@Override
	public <E> Integer count(Class<E> inEntityClass, Hashtable<String, Object> criteria) {

		String entityClsName = inEntityClass.getSimpleName();

		StringBuffer sQuery = new StringBuffer();
		sQuery.append("SELECT count(instance) from ").append(entityClsName.toString()).append(" instance ");

		if ((criteria != null) && !criteria.isEmpty()) {
			sQuery.append("where ");
		}

		Iterator<Entry<String, Object>> it = criteria.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> pairs = it.next();
			sQuery.append("instance.").append(pairs.getKey()).append("=:").append(pairs.getKey());
			if (it.hasNext()) {
				sQuery.append(" AND ");
			}
		}

		logger.debug("Query : " + sQuery);

		Query query = entityManager.createQuery(sQuery.toString());

		Enumeration<String> keys = criteria.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			query.setParameter(key, criteria.get(key));
		}

		Long count = (Long) query.getSingleResult();
		return Integer.valueOf(count.toString());
	}

	/* Other Methods */

	@Override
	public Object getResult(String queryName, Hashtable<String, Object> criteria) {

		Query qry = entityManager.createNamedQuery(queryName);
		this.prepareQueryWithCriteria(criteria, qry);
		Object result;
		try {
			result = qry.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
		return result;

	}

	@Override
	public Object getResult(StringBuffer query, Hashtable<String, Object> criteria) {

		Query qry = entityManager.createQuery(query.toString());
		prepareQueryWithCriteria(criteria, qry);
		Object result;
		try {
			result = qry.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
		return result;

	}

	@Override
	public List<Object> getResults(String queryName, Hashtable<String, Object> criteria) {

		Query qry = entityManager.createNamedQuery(queryName);
		prepareQueryWithCriteria(criteria, qry);
		List<Object> result;
		try {
			result = qry.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		return result;

	}

	@Override
	public List<Object> getResults(StringBuffer query, Hashtable<String, Object> criteria) {

		Query qry = entityManager.createQuery(query.toString());
		prepareQueryWithCriteria(criteria, qry);
		List<Object> result;
		try {
			result = qry.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		return result;

	}

	@Override
	public List<Object> getResultsByEntityField(Class<E> inEntityClass, String entityField) {
		return null;
	}

	@Override
	public <E, obj extends Object> E getEntity(Class<E> inEntityClass, String queryName, Hashtable<String, obj> criteria) {

		Query qry = entityManager.createNamedQuery(queryName);
		prepareQueryWithCriteria(criteria, qry);
		Object result;
		try {
			result = qry.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
		return (E) result;

	}

	@Override
	public <E, obj extends Object> List<E> getEntities(Class<E> inEntityClass, String queryName,
			Hashtable<String, obj> criteria) {

		Query qry = entityManager.createNamedQuery(queryName);
		prepareQueryWithCriteria(criteria, qry);
		Object result;
		try {
			result = qry.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		return (List<E>) result;
	}

	@Override
	public <E, obj extends Object> List<E> getEntities(Class<E> inEntityClass, StringBuffer query,
			Hashtable<String, obj> criteria) {

		Query qry = entityManager.createQuery(query.toString());
		prepareQueryWithCriteria(criteria, qry);
		Object result;
		try {
			result = qry.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		return (List<E>) result;
	}

	@Override
	public <E, obj extends Object> List<E> getEntities(Class<E> inEntityClass, String queryName,
			Hashtable<String, obj> criteria, Integer pageNumber, Integer pageSize) {

		Query qry = entityManager.createNamedQuery(queryName);
		prepareQueryWithCriteria(criteria, qry);
		Object result;
		try {
			qry = qry.setFirstResult(pageSize * (pageNumber - 1));
			qry.setMaxResults(pageSize);
			result = qry.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		return (List<E>) result;

	}

	@Override
	public <E, obj extends Object> List<E> getEntities(Class<E> inEntityClass, StringBuffer query,
			Hashtable<String, obj> criteria, Integer pageNumber, Integer pageSize) {

		Query qry = entityManager.createQuery(query.toString());
		prepareQueryWithCriteria(criteria, qry);
		Object result;
		try {
			qry = qry.setFirstResult(pageSize * (pageNumber - 1));
			qry.setMaxResults(pageSize);
			result = qry.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		return (List<E>) result;
	}

	@Override
	public List<E> getEntities(Class<E> inEntityClass, StringBuffer query) {

		Query qry = entityManager.createQuery(query.toString());
		Object result;
		try {
			result = qry.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		return (List<E>) result;
	}

	/* Table Columns */

	@Override
	public List<String> getTableColumns(Class<E> inEntityClass) {
		Field[] fields = inEntityClass.getDeclaredFields();
		Field[] superClassFields = inEntityClass.getSuperclass().getDeclaredFields();
		List<String> allColumns = new ArrayList<String>();
		for (int i = 0; i < fields.length; i++) {
			if (!Collection.class.isAssignableFrom(fields[i].getType())) {
				allColumns.add(fields[i].getName());
			}
		}
		for (int i = 0; i < superClassFields.length; i++) {
			if (!Collection.class.isAssignableFrom(superClassFields[i].getType())) {
				allColumns.add(superClassFields[i].getName());
			}
		}
		return allColumns;
	}

	@Override
	public List<String> getTableColumns(Class<E> inEntityClass, List<String> excludeColumns) {
		List<String> allColumns = getTableColumns(inEntityClass);
		allColumns.removeAll(excludeColumns);
		return allColumns;
	}

	/* Search Related */

	@Override
	public List<E> searchEntityByField(Class<E> inEntityClass, String entityField, String searchString) {

		searchString = "%" + searchString + "%";

		String entityClsName = inEntityClass.getSimpleName();
		String sQuery = "SELECT instance from " + entityClsName + " instance WHERE instance." + entityField
				+ " like :searchString";

		logger.debug("Query : " + sQuery);

		Query query = entityManager.createQuery(sQuery);
		query.setParameter("searchString", searchString);

		return query.getResultList();
	}

	@Override
	public List<E> searchEntityByField(Class<E> inEntityClass, String entityField, String searchString,
			Integer pageNum, Integer pageSize) {

		searchString = "%" + searchString + "%";

		String entityClsName = inEntityClass.getSimpleName();
		String sQuery = "SELECT instance from " + entityClsName + " instance WHERE instance." + entityField
				+ " like :searchString";

		logger.debug("Query : " + sQuery);

		StringBuffer queryStrBuffer = new StringBuffer(sQuery);
		Hashtable<String, Object> criteria = new Hashtable<String, Object>();
		criteria.put("searchString", searchString);

		return this.getEntities(inEntityClass, queryStrBuffer, criteria, pageNum, pageSize);
	}

	@Override
	public List<E> searchEntityByField(Class<E> inEntityClass, List<String> projections, String entityField,
			String searchString) {

		searchString = "%" + searchString + "%";

		String projectionStr = "";
		for (int i = 0; i < projections.size(); i++) {
			String projection = projections.get(i);
			if ((projection != null) && (projection != "")) {
				projectionStr = projectionStr + "instance." + projection;
			}

			if ((i + 1) != projections.size()) {
				projectionStr = projectionStr + ",";
			}
		}

		String entityClsName = inEntityClass.getSimpleName();
		String sQuery = "SELECT " + projectionStr + " from " + entityClsName + " instance WHERE instance."
				+ entityField + " like :searchString";

		logger.debug("Query : " + sQuery);

		Query query = entityManager.createQuery(sQuery);
		query.setParameter("searchString", searchString);

		return query.getResultList();

	}

	@Override
	public List<E> searchEntityByField(Class<E> inEntityClass, List<String> projections, String entityField,
			String searchString, Integer pageNum, Integer pageSize) {

		searchString = "%" + searchString + "%";

		String projectionStr = "";
		for (int i = 0; i < projections.size(); i++) {
			String projection = projections.get(i);
			if ((projection != null) && (projection != "")) {
				projectionStr = projectionStr + "instance." + projection;
			}

			if ((i + 1) != projections.size()) {
				projectionStr = projectionStr + ",";
			}
		}

		String entityClsName = inEntityClass.getSimpleName();
		String sQuery = "SELECT " + projectionStr + " from " + entityClsName + " instance WHERE instance."
				+ entityField + " like :searchString";

		logger.debug("Query : " + sQuery);

		StringBuffer strBuffer = new StringBuffer(sQuery);

		Hashtable<String, Object> criteria = new Hashtable<String, Object>();
		criteria.put("searchString", searchString);

		return getEntities(inEntityClass, strBuffer, criteria, pageNum, pageSize);

	}

	@Override
	public Integer fetchSearchResultsCount(Class<E> inEntityClass, String entityField, String searchString) {

		searchString = "%" + searchString + "%";

		String entityClsName = inEntityClass.getSimpleName();
		String sQuery = "SELECT count(instance) from " + entityClsName + " instance WHERE instance." + entityField
				+ " like :searchString";

		logger.debug("Query : " + sQuery);

		Query query = entityManager.createQuery(sQuery);
		query.setParameter("searchString", searchString);

		Long count = (Long) query.getSingleResult();
		return Integer.valueOf(count.toString());
	}

	/* Private Supporting Methods */

	/**
	 * Prepares the named query with the provided criteria.
	 * 
	 * @param criteria
	 *            entity select criteria
	 * @param qry
	 *            The Query(Named Query) to be executed
	 * 
	 */
	private <obj extends Object> void prepareQueryWithCriteria(Hashtable<String, obj> criteria, Query qry) {
		Enumeration<String> keys = criteria.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			qry.setParameter(key, criteria.get(key));
		}
	}

}
