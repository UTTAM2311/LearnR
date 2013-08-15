package com.imaginea.dc.api;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface IDao{
	
	/* Basic CRUD methods --- Save Find Update Delete */
	
	<E extends IEntity> void save(E inEntity) throws DataAccessException;

	<E extends IEntity,K extends Serializable>  E find(Class<E> inEntityClass, K inPkey) throws DataAccessException;
	
	<E extends IEntity> E update(E inEntity) throws DataAccessException;

	<E extends IEntity> void delete(E inEntity) throws DataAccessException;
    
    /* Other Methods */
    
    <E extends IEntity> List<E> findAllEntities(Class<E> inEntityClass);
    
    <E extends IEntity> List<E> findAllEntities(Class<E> inEntityClass, String orderby);
    
    <E extends IEntity> List<E> findAllEntities(Class<E> inEntityClass, String orderby,  Integer pageNumber, Integer pageSize);
    
    
    <E extends IEntity> List<Object> getResultsByFieldName(Class<E> inEntityClass, String fieldName) throws DataAccessException;
    
    
    Object getResult(String queryName, Hashtable<String, Object> criteria) throws DataAccessException;
    
    Object getResult(StringBuffer query, Hashtable<String, Object> criteria) throws DataAccessException;
    
    @SuppressWarnings("rawtypes")
	List getResults(String queryName, Hashtable<String, Object> criteria) throws DataAccessException;
    
    <E extends IEntity, obj extends Object> E getEntity(Class<E> inElementClass, String queryName, Hashtable<String, obj> criteria) throws DataAccessException;

    <E extends IEntity, obj extends Object> List<E> getEntities(Class<E> inElementClass, String queryName, Hashtable<String, obj> criteria, Integer pageNumber, Integer pageSize) throws DataAccessException;
    
    <E extends IEntity, obj extends Object> List<E> getEntities(Class<E> inElementClass, String queryName, Hashtable<String, obj> criteria) throws DataAccessException;
    
    <E extends IEntity, obj extends Object> List<E> getEntities(Class<E> inElementClass, StringBuffer query, Hashtable<String, obj> criteria);
    
    <E extends IEntity, obj extends Object> List<E> getEntities(Class<E> inElementClass, StringBuffer query, Hashtable<String, obj> criteria, Integer pageNumber, Integer pageSize);
    
    <E extends IEntity> List<E> getEntities(Class<E> inElementClass, StringBuffer query) throws DataAccessException;
    
    
    /* Counts */
    
    <E extends IEntity> Integer fetchEntitiesCount(Class<E> inEntityClass);
    
    <E extends IEntity> Integer fetchEntitiesCount(Class<E> inEntityClass, Hashtable<String, Object> criteria);
	
    <E extends IEntity> Integer fetchSearchResultsCount(Class<E> inEntityClass,	String entityField, String searchString);
	
    
	/* Search Related */
	
	<E extends IEntity> List<E> searchEntityByField(Class<E> inEntityClass, String entityField, String searchString);
	
	<E extends IEntity> List<E> searchEntityByField(Class<E> inEntityClass, String entityField, String searchString, Integer pageNum, Integer pageSize);
	
	<E extends IEntity> List<E> searchEntityByField(Class<E> inEntityClass, List<String> projections, String entityField, String searchString);
	
	<E extends IEntity> List<E> searchEntityByField(Class<E> inEntityClass, List<String> projections, String entityField, String searchString, Integer pageNum, Integer pageSize);
	
	public Object executeResult(String queryName, Hashtable<String, Object> criteria)throws DataAccessException;
}
