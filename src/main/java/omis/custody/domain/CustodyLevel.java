package omis.custody.domain;

import java.io.Serializable;

/**
 * Custody Level.
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 07 2013)
 * @since OMIS 3.0
 */
public interface CustodyLevel extends Serializable {

	/**
	 * Returns the id of the custody level.
	 * @return the id
	 */
	Long getId();

	/**
	 * Sets the id of the custody level.
	 * @param id the id to set
	 */
	void setId(final Long id);

	/**
	 * Returns the name of the custody level.
	 * @return the name
	 */
	String getName();

	/**
	 * Sets the name of the custody level.
	 * @param name the name to set
	 */
	void setName(final String name);
	
	/**
	 * Returns the valid value of the custody level.
	 * @return the valid
	 */
	Boolean getValid();

	/**
	 * Sets the valid value of the custody level.
	 * @param valid the valid to set
	 */
	void setValid(final Boolean valid);
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();	
}