package omis.presentenceinvestigation.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * JailAdjustmentSectionSummaryNote.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 24, 2017)
 *@since OMIS 3.0
 *
 */
public interface JailAdjustmentSectionSummaryNote extends Creatable, Updatable {
	/**
	 * Returns the ID of the JailAdjustmentSectionSummaryNote
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the JailAdjustmentSectionSummaryNote
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the description of the JailAdjustmentSectionSummaryNote
	 * @return description - string
	 */
	public String getDescription();
	
	/**
	 * Sets the description of the JailAdjustmentSectionSummaryNote
	 * @param description - String
	 */
	public void setDescription(String description);
	
	/**
	 * Returns the date of the JailAdjustmentSectionSummaryNote
	 * @return date - Date
	 */
	public Date getDate();
	
	/**
	 * Sets the date of the JailAdjustmentSectionSummaryNote
	 * @param date - Date
	 */
	public void setDate(Date date);
	
	/**
	 * Returns the jailAdjustmentSectionSummary of the
	 * JailAdjustmentSectionSummaryNote
	 * @return jailAdjustmentSectionSummary - JailAdjustmentSectionSummary
	 */
	public JailAdjustmentSectionSummary getJailAdjustmentSectionSummary();
	
	/**
	 * Sets the jailAdjustmentSectionSummary of the
	 * JailAdjustmentSectionSummaryNote
	 * @param jailAdjustmentSectionSummary - JailAdjustmentSectionSummary
	 */
	public void setJailAdjustmentSectionSummary(
			JailAdjustmentSectionSummary jailAdjustmentSectionSummary);
	
	/** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
}
