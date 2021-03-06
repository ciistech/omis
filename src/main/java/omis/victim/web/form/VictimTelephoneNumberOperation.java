package omis.victim.web.form;

/**
 * Victim telephone number operation.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public enum VictimTelephoneNumberOperation {

	/** Create. */
	CREATE,
	
	/** Update. */
	UPDATE,
	
	/** Remove. */
	REMOVE;
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}