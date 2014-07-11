package com.learnr.pa.persistence;

/**
 * Definition of any Entitiy that can be persisted.
 * 
 * <p> All the implementations/entities of this definition will provide the below
 * information. </p>
 * 
 * <ul>
 * <li>pKey - A unique identifier of the object.</li>
 * <li>deleted - A boolean providing the current status of the object.</li>
 * </ul>
 * 
 * <p> Just remember the deleted above is a soft delete. </p>
 */
public interface IEntity {

	/**
	 * Returns the Unique Identifier of the object.
	 * 
	 * @return the unique identifier of the object ( primary key )
	 */
	public Integer getPkey();

	/**
	 * Sets the Unique Identifier of the object.
	 * 
	 * @param pkey
	 *            the unique identifier of the object ( primary key )
	 */
	public void setPkey(Integer pkey);

	/* Soft Delete */

	/**
	 * Returns true if the object is marked as deleted. ( Soft delete )
	 * 
	 * @return isDeleted A boolean value. True if the object is marked as
	 *         deleted.
	 */
	public Boolean getDeleted();

	/**
	 * Updates the deleted status of the current object ( Soft delete )
	 * 
	 * @param isDeleted
	 *            True to mark the object as deleted
	 */
	public void setDeleted(Boolean isDeleted);

}
