package omis.ncic.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.booking.domain.Booking;
import omis.offender.domain.OffenderAssociable;

/** NCIC check.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 4, 2019)
 * @since OMIS 3.0 */
public interface NcicCheck extends OffenderAssociable, Creatable, Updatable {
	/** Gets id.
	 * @return id. */
	Long getId();
	
	/** Sets id.
	 * @param id - id. */
	void setId(Long id);
	
	/** Gets booking.
	 * @return booking. */
	Booking getBooking();
	
	/** Sets booking.
	 * @param booking - booking. */
	void setBooking(Booking booking);
	
	/** Gets date.
	 * @return date. */
	Date getDate();
	
	/** Sets date.
	 * @param date - date. */
	void setDate(Date date);
	
	/** Gets ncic check result.
	 * @return ncic check result. */
	NcicCheckResult getResult();
	
	/** Sets ncic check result.
	 * @param result - ncic check result. */
	void setResult(NcicCheckResult result);
	
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
