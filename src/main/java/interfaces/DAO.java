 package interfaces;

import java.io.Serializable;
import java.util.List;

public interface DAO<E, ID extends Serializable> {
	/**
	 * Persist an entity.
	 *
	 * @param entity the entity to persist
	 * @return the persisted entity or null if duplicate key
	 */
	public E persist(E entity);

	/**
	 * Update entity given its id and an object with new values.
	 *
	 * @param id the id of the entity to update
	 * @param newEntityValues an object with the new values to update the entity 
	 * @return the updated entity or null if the entity id does not exist
	 */
	
	public E findById(ID id);

	/**
	 * Find all entities.
	 *
	 * @return the list of entities
	 */
	public List<E> findAll();
	
}
