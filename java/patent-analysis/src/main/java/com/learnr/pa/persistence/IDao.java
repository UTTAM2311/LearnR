package com.learnr.pa.persistence;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.NoResultException;

/**
 * Data Access Object for all the Entities implementing {@link IEntity}.
 * 
 * <p>This Data Access Object(DAO) provides the methods like persist, update,
 * find/search .. to manipulate these objects.</p>
 * 
 * <p>Be vary of transaction management while using this object.</p>
 * 
 * @param E extends {@link IEntity}
 * @param K extends {@link Serializable}
 * 
 * @see GenericAuditDao
 * @see IEntity
 */
public interface IDao<E extends IEntity, K extends Serializable> {
	
	
	/* Basic CRUD methods */
	
	/**
	 * Saves the Entity.
	 * 
	 * @param inEntity The entity to be saved.
	 */
	void save(E inEntity);
	
	/**
	 * Updates/Merges with the existing Entity.
	 * 
	 * @param inEntity
	 *            The entity to be merged/updated.
	 * @return The updated Entity.
	 */
	E update(E inEntity);

	/**
	 * Marks the entity as deleted. Note that its not deleted physically. To
	 * delete it completely refer to {@link #purge(IEntity)}
	 * 
	 * <p>Also see - {@link #restore(IEntity)}</p>
	 * 
	 * @param inEntity
	 *            The entity to be deleted
	 */
	void delete(E inEntity);
	
	/**
	 * Marks the list of entities as deleted. Takes in the Entity type and a
	 * List of Unique Identifiers. Note that these not deleted physically. To
	 * delete it completely refer to {@link #purge(Class, List)}
	 * Also see - {@link #restore(Class, List)}
	 * 
	 * @param inEntity
	 *            Entity Class name
	 * @param pkeys
	 *            List of Unique Identifiers
	 */
	void delete(Class<E> inEntityClass, List<Integer> pkeys);
	
	
	/**
	 * Restores the entity that is marked as deleted (soft delete). 
	 * 
	 * <p>Also see - {@link #delete(IEntity)}</p>
	 * 
	 * @param inEntity
	 *            The entity to be restored
	 */
	void restore(E inEntity);
	
	/**
	 * Restores the list of entities that are marked as deleted (soft delete). 
	 * Accepts the entity class name and a list of Identifiers (pKeys)
	 * 
	 * <p>Also see - {@link #delete(Class, List)}</p>
	 * 
	 * @param inEntity
	 *            Entity Class name
	 * @param pkeys
	 *            List of Unique Identifiers
	 */
	void restore(Class<E> inEntityClass, List<Integer> pkeys);
	
	/**
	 * Purges the entity (hard delete). 
	 * 
	 * <p>Also see - {@link #delete(IEntity)}</p>
	 * 
	 * @param inEntity
	 *            The entity to be restored
	 */
	void purge(E inEntity);
	
	/**
	 * Purges the list of entities (hard delete). 
	 * Accepts the entity class name and a list of Identifiers (pKeys)
	 * 
	 * <p>Also see - {@link #delete(Class, List)}</p>
	 * 
	 * @param inEntity
	 *            Entity Class name
	 * @param pkeys
	 *            List of Unique Identifiers
	 */
	void purge(Class<E> inEntityClass, List<Integer> pkeys);
    
	/**
	 * Fetches the entity with the passed identifier and type.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param inPkey
	 *            entity identifier
	 * @return The entity object with the passed identifier
	 */
	<E> E find(Class<E> inEntityClass, Integer inPkey);
	
	/**
	 * Fetches a list entities with the passed deleted state and type.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param isDeleted
	 *            soft delete state
	 * @return A list of entity objects
	 */
	<E> List<E> find(Class<E> inEntityClass, Boolean isDeleted);
    
	/**
	 * Fetches a list entities with the passed deleted state and type, ordered
	 * by the give parameter.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param isDeleted
	 *            soft delete state
	 * @param orderby
	 *            parameter name to order the objects
	 * @return A list of entity objects
	 */
	<E> List<E> find(Class<E> inEntityClass, Boolean isDeleted, String orderby);
    
	/**
	 * Fetches a list entities with the passed deleted state and type, ordered
	 * by the give parameter. The results of this method are paginated
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param isDeleted
	 *            soft delete state
	 * @param orderby
	 *            parameter name to order the objects
	 * @param pageNumber
	 *            number of the page
	 * @param pageSize
	 *            Size of the page
	 * @return A list of entity objects
	 */
	<E> List<E> find(Class<E> inEntityClass, Boolean isDeleted, String orderby, Integer pageNumber, Integer pageSize);
    
	/**
	 * Gets the total count of a given entity entries.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @return total entity count.
	 */
	<E> Integer count(Class<E> inEntityClass);
    
	/**
	 * Gets the total count of a given entity entries with the passed entity
	 * type.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param isDeleted
	 *            soft delete state
	 * @return total entity count.
	 */
    <E> Integer count(Class<E> inEntityClass, Boolean isDeleted);
    
	/**
	 * Gets the total count of a given entity entries with the passed entity
	 * select criteria.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param criteria
	 *            entity select criteria
	 * @return total entity count.
	 */
    <E> Integer count(Class<E> inEntityClass, Hashtable<String, Object> criteria);
    
    
    /* Other Methods */
    
	/**
	 * Executes a query to fetch/select a single object/entity.
	 * 
	 * @param queryName
	 *            Name of the Query(Named Query) to be executed
	 * @param criteria
	 *            entity select criteria
	 * @return the selected entity
	 * @throws NoResultException
	 *             when no matching result is found
	 */
    Object getResult(String queryName, Hashtable<String, Object> criteria);
    
    /**
	 * Executes a query to fetch/select a single object/entity.
	 * 
	 * @param query
	 *            The Query(Named Query) to be executed
	 * @param criteria
	 *            entity select criteria
	 * @return the selected entity
	 * @throws NoResultException
	 *             when no matching result is found
	 */
    Object getResult(StringBuffer query, Hashtable<String, Object> criteria);
    
    /**
	 * Executes a query to fetch/select a list of matching objects/entities.
	 * 
	 * @param queryName
	 *            Name of the Query(Named Query) to be executed
	 * @param criteria
	 *            entity select criteria
	 * @return the list of entitles selected
	 * @throws NoResultException
	 *             when no matching result is found
	 */
    List<Object> getResults(String queryName, Hashtable<String, Object> criteria);
    
    /**
	 * Executes a query to fetch/select a list of matching objects/entities.
	 * 
	 * @param query
	 *            The Query(Named Query) to be executed
	 * @param criteria
	 *            entity select criteria
	 * @return the selected entity
	 * @throws NoResultException
	 *             when no matching result is found
	 */
    List<Object> getResults(StringBuffer query, Hashtable<String, Object> criteria);
    
	/**
	 * TODO Beleive me even I have no idea why I wrote this. Wait for the
	 * description as well as the implementation, till the day I figure this out
	 * 
	 * @param inEntityClass
	 * @param entityField
	 * @return
	 */
	List<Object> getResultsByEntityField(Class<E> inEntityClass, String entityField);
    
	/**
	 * Fetches/selects an entity matching with the given criteria.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param queryName
	 *            Name of the Query(Named Query) to be executed
	 * @param criteria
	 *            entity select criteria
	 * @return the selected entity
	 * @throws NoResultException
	 *             when no matching result is found
	 */
	<E, obj extends Object> E getEntity(Class<E> inEntityClass, String queryName, Hashtable<String, obj> criteria);

	/**
	 * Fetches/selects a List of entities matching with the given criteria.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param queryName
	 *            Name of the Query(Named Query) to be executed
	 * @param criteria
	 *            entity select criteria
	 * @return the selected list of entities
	 * @throws NoResultException
	 *             when no matching results are found
	 */
    <E, obj extends Object> List<E> getEntities(Class<E> inEntityClass, String queryName, Hashtable<String, obj> criteria);
    
    /**
	 * Fetches/selects a List of entities matching with the given criteria.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param query
	 *            The Query(Named Query) to be executed
	 * @param criteria
	 *            entity select criteria
	 * @return the selected list of entities
	 * @throws NoResultException
	 *             when no matching results are found
	 */
    <E, obj extends Object> List<E> getEntities(Class<E> inEntityClass, StringBuffer query, Hashtable<String, obj> criteria);
    
	/**
	 * Fetches/selects a List of entities matching with the given criteria
	 * paginated.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param queryName
	 *            Name of the Query(Named Query) to be executed
	 * @param criteria
	 *            entity select criteria
	 * @param pageNumber
	 *            number of the page
	 * @param pageSize
	 *            Size of the page
	 * @return the selected list of entities
	 * @throws NoResultException
	 *             when no matching results are found
	 */
	<E, obj extends Object> List<E> getEntities(Class<E> inEntityClass, String queryName,
			Hashtable<String, obj> criteria, Integer pageNumber, Integer pageSize);
    
    /**
	 * Fetches/selects a List of entities matching with the given criteria
	 * paginated.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param query
	 *            The Query(Named Query) to be executed
	 * @param criteria
	 *            entity select criteria
	 * @param pageNumber
	 *            number of the page
	 * @param pageSize
	 *            Size of the page
	 * @return the selected list of entities
	 * @throws NoResultException
	 *             when no matching results are found
	 */
	<E, obj extends Object> List<E> getEntities(Class<E> inEntityClass, StringBuffer query,
			Hashtable<String, obj> criteria, Integer pageNumber, Integer pageSize);
    
    /**
	 * Fetches/selects a List of entities with the given query.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param query
	 *            The Query(Named Query) to be executed
	 * @return the selected list of entities
	 * @throws NoResultException
	 *             when no matching results are found
	 */
    List<E> getEntities(Class<E> inEntityClass, StringBuffer query);
    
    
    /* Table Columns */
    
	/**
	 * Fetches the physical table column names of the current entity.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @return a List of strings containing the entity table column names
	 */
	List<String> getTableColumns(Class<E> inEntityClass);
    
	/**
	 * Fetches the physical table column names of the current entity. You can
	 * exclude the unwanted by passing a list of column names as argument.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param excludeColumns
	 *            column names to exclude in the result
	 * @return a List of strings containing the entity table column names
	 */
    List<String> getTableColumns(Class<E> inEntityClass, List<String> excludeColumns);
    
    
	/* Search Related */
	
	/**
	 * Search a particular entity by its field. The search, results in all the
	 * entities with their field like the passed search string.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param entityField
	 *            the entity field to search on
	 * @param searchString
	 *            the string to search for
	 * @return a list of entity objects
	 */
	List<E> searchEntityByField(Class<E> inEntityClass, String entityField, String searchString);
	
	/**
	 * Search a particular entity by its field. The search, results in the
	 * entities paginated with their field like the passed search string. 
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param entityField
	 *            the entity field to search on
	 * @param searchString
	 *            the string to search for
	 * @param pageNumber
	 *            number of the page
	 * @param pageSize
	 *            Size of the page
	 * @return a list of entity objects
	 */
	List<E> searchEntityByField(Class<E> inEntityClass, String entityField, String searchString, Integer pageNum, Integer pageSize);
	
	/**
	 * Search a particular entity by its field. The search, results in all the
	 * entities with their field like the passed search string. Allows to select
	 * only a few fields of the entity by passing them as projections.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param projections
	 *            fields of the entity to be selected.
	 * @param entityField
	 *            the entity field to search on
	 * @param searchString
	 *            the string to search for
	 * @return a list of entity objects
	 */
	List<E> searchEntityByField(Class<E> inEntityClass, List<String> projections, String entityField, String searchString);
	
	/**
	 * Search a particular entity by its field. The search, results in the
	 * entities paginated with their field like the passed search string. Allows
	 * to select only a few fields of the entity by passing them as projections.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param projections
	 *            fields of the entity to be selected.
	 * @param entityField
	 *            the entity field to search on
	 * @param searchString
	 *            the string to search for
	 * @param pageNumber
	 *            number of the page
	 * @param pageSize
	 *            Size of the page
	 * @return a list of entity objects
	 */
	List<E> searchEntityByField(Class<E> inEntityClass, List<String> projections, String entityField,
			String searchString, Integer pageNum, Integer pageSize);
	
	
	/**
	 * Search a particular entity by its field. The search, results in the count of the all matching 
	 * entities with their field like the passed search string.
	 * 
	 * @param inEntityClass
	 *            the entity type
	 * @param entityField
	 *            the entity field to search on
	 * @param searchString
	 *            the string to search for
	 * @return the count of the search results
	 */
	Integer fetchSearchResultsCount(Class<E> inEntityClass,	String entityField, String searchString);
	
}
