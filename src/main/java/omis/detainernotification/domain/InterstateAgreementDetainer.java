package omis.detainernotification.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Interstate agreement detainer.
 *
 * @author Sheronda Vaughn
 * @author Annie Jacques
 * @version 0.1.1 (Mar 22, 2017)
 * @since OMIS 3.0
 */
public interface InterstateAgreementDetainer extends Creatable, Updatable {

	/**
	 * Sets the id of the interstate agreement detainer.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the id of the interstate  agreement detainer.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the detainer of the interstate agreement detainer.
	 * 
	 * @param detainer detainer
	 */
	void setDetainer(Detainer detainer);
	
	/**
	 * Returns the detainer of the interstate agreement detainer.
	 * 
	 * @return detainer
	 */
	Detainer getDetainer();
	
	
	/**
	 * Sets the prosecutor received date of the interstate agreement detainer.
	 * 
	 * @param prosecutorreceivedDate prosecutor received date
	 */
	void setProsecutorReceivedDate(Date prosecutorReceivedDate);
	
	/**
	 * Returns the prosecutor received date of the interstate 
	 * agreement detainer.
	 * 
	 * @return prosecutor received date
	 */
	Date getProsecutorReceivedDate();
	
	
	/**
	 * Sets the initiated by of the interstate agreement detainer.
	 * 
	 * @param initiatedBy initiated
	 */
	void setInitiatedBy(InterstateAgreementInitiatedByCategory initiatedBy);
	
	/**
	 * Returns the initiated by of the interstate agreement detainer.
	 * 
	 * @return initiated by
	 */
	InterstateAgreementInitiatedByCategory getInitiatedBy();
	
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