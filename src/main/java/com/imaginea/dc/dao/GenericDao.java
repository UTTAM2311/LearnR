package com.imaginea.dc.dao;

import java.io.Serializable;
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
import org.springframework.dao.DataAccessException;

import com.imaginea.dc.api.IDao;
import com.imaginea.dc.api.IEntity;

public class GenericDao implements IDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericDao.class);
	
	protected EntityManager entityManager;
	
	/* Getters and Setters */
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	/* Basic CRUD methods --- Save Find Update Delete */
	
	public <E extends IEntity> void save(E inEntity) throws DataAccessException {
		if(inEntity != null)
			entityManager.persist(inEntity);
		
	}

	public <E extends IEntity,K extends Serializable> E find(Class<E> inEntityClass, K inPkey)	throws DataAccessException {
		return entityManager.find(inEntityClass, inPkey);
	}

	public <E extends IEntity> E update(E inEntity) throws DataAccessException {
		if(inEntity != null)
			return entityManager.merge(inEntity);
		
		return null;
	}

	public <E extends IEntity> void delete(E inEntity) throws DataAccessException {
		if(inEntity != null)
			entityManager.remove(inEntity);
	}
	
	/* Other Methods */

	public <E extends IEntity> List<E> findAllEntities(Class<E> inEntityClass) {
		return findAllEntities(inEntityClass, null);
	}

	@SuppressWarnings("unchecked")
	public <E extends IEntity> List<E> findAllEntities(Class<E> inEntityClass, String orderby) {
		
		String entityClsName = inEntityClass.getSimpleName();
		
		Entity entity = inEntityClass.getAnnotation(Entity.class);
		if (entity != null) {
			String eName = entity.name();
			if (eName != null && eName.length() > 0) {
				entityClsName = eName;
			}
		}

		String sQuery = "SELECT instance FROM " + entityClsName + " instance";
		if (orderby == null) {
			sQuery = sQuery + " order by instance.createdOn ASC";
		} else {
			sQuery = sQuery + " order by instance." + orderby;
		}
			
		Query query = entityManager.createQuery(sQuery);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public <E extends IEntity> List<E> findAllEntities(Class<E> inEntityClass, String orderby,  Integer pageNumber, Integer pageSize) {
		
		String entityClsName = inEntityClass.getSimpleName();
		
		Entity entity = inEntityClass.getAnnotation(Entity.class);
		if (entity != null) {
			String eName = entity.name();
			if (eName != null && eName.length() > 0) {
				entityClsName = eName;
			}
		}

		String sQuery = "SELECT instance FROM " + entityClsName + " instance";
		if (orderby == null) {
			sQuery = sQuery + " order by instance.createdOn ASC";
		} else {
			sQuery = sQuery + " order by instance." + orderby;
		}

		Query query = entityManager.createQuery(sQuery);
		
		query = query.setFirstResult(pageSize * (pageNumber - 1));
		query.setMaxResults(pageSize);
		
		return (List<E>) query.getResultList();
		
	}

	
	@SuppressWarnings("unchecked")
	public <E extends IEntity> List<Object> getResultsByFieldName(Class<E> inEntityClass, String fieldName) throws DataAccessException {
		
		String entityClsName = inEntityClass.getSimpleName();
		
		Entity entity = inEntityClass.getAnnotation(Entity.class);
		if (entity != null) {
			String eName = entity.name();
			if (eName != null && eName.length() > 0) {
				entityClsName = eName;
			}
		}
		
		String sQuery = "";
		if(fieldName != null) {
			sQuery = "SELECT instance." + fieldName + " FROM " + entityClsName + " instance";
		}
		
		Query query = entityManager.createQuery(sQuery);
		
		return query.getResultList();
	}
	
	
	
	public Object getResult(String queryName, Hashtable<String, Object> criteria) throws DataAccessException {
		
		Query qry = entityManager.createNamedQuery(queryName);
        Enumeration<String> keys = criteria.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            qry.setParameter(key, criteria.get(key));
        }
        Object result;
        try {
            result = qry.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
        return result;
	}

	public Object getResult(StringBuffer query,	Hashtable<String, Object> criteria) throws DataAccessException {
		
		Query qry = entityManager.createQuery(query.toString());
		Enumeration<String> keys = criteria.keys();
		while (keys.hasMoreElements()) {
		    String key = keys.nextElement();
		    qry.setParameter(key, criteria.get(key));
		}
		Object result;
		try {
		    result = qry.getSingleResult();
		} catch (NoResultException nre) {
		    return null;
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public List getResults(String queryName, Hashtable<String, Object> criteria) throws DataAccessException {
		
		Query qry = entityManager.createNamedQuery(queryName);
		Enumeration<String> keys = criteria.keys();
		while (keys.hasMoreElements()) {
		    String key = keys.nextElement();
		    qry.setParameter(key, criteria.get(key));
		}
		List result;
		try {
		    result = qry.getResultList();
		} catch (NoResultException nre) {
		    return null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public <E extends IEntity, obj extends Object> E getEntity(Class<E> inElementClass, String queryName, Hashtable<String, obj> criteria)	throws DataAccessException {
		Query qry = entityManager.createNamedQuery(queryName);
		Enumeration<String> keys = criteria.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			qry.setParameter(key, criteria.get(key));
		}
		Object result;
		try {
			result = qry.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
		return (E) result;
	}

	@SuppressWarnings("unchecked")
	public <E extends IEntity, obj extends Object> List<E> getEntities(Class<E> inElementClass, String queryName, Hashtable<String, obj> criteria) throws DataAccessException {
		Query qry = entityManager.createNamedQuery(queryName);
		Enumeration<String> keys = criteria.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			qry.setParameter(key, criteria.get(key));
		}
		Object result;
		try {
			result = qry.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		return (List<E>) result;
	}
	
	
	@SuppressWarnings("unchecked")
	public <E extends IEntity, obj extends Object> List<E> getEntities(Class<E> inElementClass, String queryName, 
					Hashtable<String, obj> criteria, Integer pageNumber, Integer pageSize) throws DataAccessException {
		Query qry = entityManager.createNamedQuery(queryName);
		Enumeration<String> keys = criteria.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			qry.setParameter(key, criteria.get(key));
		}
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


	@SuppressWarnings("unchecked")
	public <E extends IEntity, obj extends Object> List<E> getEntities(Class<E> inElementClass, StringBuffer query, Hashtable<String, obj> criteria) {
		Query qry = entityManager.createQuery(query.toString());
		Enumeration<String> keys = criteria.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			qry.setParameter(key, criteria.get(key));
		}
		Object result;
		try {
			result = qry.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		return (List<E>) result;
	}

	@SuppressWarnings("unchecked")
	public <E extends IEntity, obj extends Object> List<E> getEntities(Class<E> inElementClass, StringBuffer query, Hashtable<String, obj> criteria, Integer pageNumber, Integer pageSize) {
		Query qry = entityManager.createQuery(query.toString());
		Enumeration<String> keys = criteria.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			qry.setParameter(key, criteria.get(key));
		}
		Object result;
		try {
			if(pageNumber != -1 && pageSize != -1) {
				qry = qry.setFirstResult(pageSize * (pageNumber - 1));
				qry.setMaxResults(pageSize);
			}
			result = qry.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		return (List<E>) result;
	}

	@SuppressWarnings("unchecked")
	public <E extends IEntity> List<E> getEntities(Class<E> inElementClass, StringBuffer query) throws DataAccessException {
		Query qry = entityManager.createQuery(query.toString());
		Object result;
		try {
			result = qry.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		return (List<E>) result;
	}
	
    /* Counts */
	
	public <E extends IEntity> Integer fetchEntitiesCount( Class<E> inEntityClass ) {
		String entityClsName = inEntityClass.getSimpleName();
		String sQuery = "SELECT count(instance) from " + entityClsName + " instance";
		
		Query query = entityManager.createQuery(sQuery);
		Long count = (Long) query.getSingleResult();
		return Integer.valueOf(count.toString());
	}

	public <E extends IEntity> Integer fetchEntitiesCount( Class<E> inEntityClass, Hashtable<String, Object> criteria ) {
		
		String entityClsName = inEntityClass.getSimpleName();
		
		StringBuffer sQuery = new StringBuffer();
		sQuery.append("SELECT count(instance) from ").append(entityClsName.toString()).append(" instance ");
		
		if (criteria!=null && !criteria.isEmpty()) {
			sQuery.append("where ");
		}
		
		Iterator<Entry<String, Object>> it = criteria.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<String, Object> pairs = it.next();
	        sQuery.append("instance.").append(pairs.getKey()).append("=:").append(pairs.getKey());
//	        it.remove();
	        
	        if(it.hasNext())
	        	sQuery.append(" AND ");
	    }
		
		Query query = entityManager.createQuery(sQuery.toString());
		
		Enumeration<String> keys = criteria.keys();
	    while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			query.setParameter(key, criteria.get(key));
		}
		
		Long count = (Long) query.getSingleResult();
		return Integer.valueOf(count.toString());
		
	}

	public <E extends IEntity> Integer fetchSearchResultsCount( Class<E> inEntityClass, String entityField, String searchString ) {
		
		searchString = "%" + searchString + "%";
		
		String entityClsName = inEntityClass.getSimpleName();
		String sQuery = "SELECT count(instance) from " + entityClsName + " instance WHERE instance." + entityField + " like :searchString";
		
		Query query = entityManager.createQuery(sQuery);
		query.setParameter("searchString", searchString);
				
		Long count = (Long) query.getSingleResult();
		return Integer.valueOf(count.toString());
	}

	/* Search Related */
	
	@SuppressWarnings("unchecked")
	public <E extends IEntity> List<E> searchEntityByField(Class<E> inEntityClass, String entityField, String searchString) {
		
		searchString = "%" + searchString + "%";
		
		String entityClsName = inEntityClass.getSimpleName();
		String sQuery = "SELECT instance from " + entityClsName + " instance WHERE instance." + entityField + " like :searchString" ;
		
		Query query = entityManager.createQuery(sQuery);
		query.setParameter("searchString", searchString);
		
		return (List<E>)query.getResultList();
		
	}

	public <E extends IEntity> List<E> searchEntityByField(Class<E> inEntityClass, String entityField, String searchString, 
			Integer pageNum, Integer pageSize) {
		
		searchString = "%" + searchString + "%";
		
		String entityClsName = inEntityClass.getSimpleName();
		String sQuery = "SELECT instance from " + entityClsName + " instance WHERE instance." + entityField + " like :searchString" ;
		
		StringBuffer queryStrBuffer = new StringBuffer(sQuery);
		
		Hashtable<String, Object> criteria = new Hashtable<String, Object>();
		criteria.put("searchString", searchString);
		
		return this.getEntities(inEntityClass, queryStrBuffer, criteria, pageNum, pageSize);
		
	}
	
	@SuppressWarnings("unchecked")
	public <E extends IEntity> List<E> searchEntityByField(Class<E> inEntityClass, List<String> projections, String entityField, String searchString) {
		
		searchString = "%" + searchString + "%";
		
		String projectionStr = "";
		for (int i = 0; i < projections.size(); i++) {
			String projection = projections.get(i);
			if( projection != null && projection != "") {
				projectionStr = projectionStr + "instance." + projection;
			}
			
			if (i+1 != projections.size()) {
				projectionStr = projectionStr + ",";
			}			
		}
		
		String entityClsName = inEntityClass.getSimpleName();
		String sQuery = "SELECT " + projectionStr + " from " + entityClsName + " instance WHERE instance." + entityField + " like :searchString" ;
		
		Query query = entityManager.createQuery(sQuery);
		query.setParameter("searchString", searchString);
		
		return query.getResultList();
	}

	public <E extends IEntity> List<E> searchEntityByField(Class<E> inEntityClass, List<String> projections, String entityField, String searchString,
			Integer pageNum, Integer pageSize) {
		
		searchString = "%" + searchString + "%";
		
		String projectionStr = "";
		for (int i = 0; i < projections.size(); i++) {
			String projection = projections.get(i);
			if( projection != null && projection != "") {
				projectionStr = projectionStr + "instance." + projection;
			}
			
			if (i+1 != projections.size()){
				projectionStr = projectionStr + ",";
			}
		}
		
		String entityClsName = inEntityClass.getSimpleName();
		String sQuery = "SELECT " + projectionStr + " from " + entityClsName + " instance WHERE instance." + entityField + " like :searchString" ;
		
		
		StringBuffer strBuffer = new StringBuffer(sQuery);
		
		Hashtable<String, Object> criteria = new Hashtable<String, Object>();
		criteria.put("searchString", searchString);
		
		return getEntities(inEntityClass, strBuffer, criteria, pageNum, pageSize);
	}
	
}
