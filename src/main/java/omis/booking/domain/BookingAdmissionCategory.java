package omis.booking.domain;

import java.io.Serializable;

/** Booking admission category.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 2, 2019)
 * @since OMIS 3.0 */
public interface BookingAdmissionCategory extends Serializable {
	/** Sets the ID of booking admission category.
	 * @param id booking admission category. */
	void setId(Long id);
	
	/** Returns the ID of the booking admission category.
	 * @return booking admission category. */
	Long getId();
	
	/** Sets the name of booking admission category.
	 * @param name booking admission category name. */
	void setName(String name);
	
	/** Returns the name of booking admission category.
	 * @return booking admission category name. */
	String getName();
	
	/** Sets validity of booking admission category.
	 *  @param valid valid flag. */
	void setValid(Boolean valid);
	
	/** Gets validity of booking admission category.
	 * @return valid flag of booking admission category. */
	Boolean getValid();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
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
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}
