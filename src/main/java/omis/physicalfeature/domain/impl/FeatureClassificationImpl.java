package omis.physicalfeature.domain.impl;

import omis.physicalfeature.domain.FeatureClassification;

/**
 * Feature classification implementation.
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public class FeatureClassificationImpl implements FeatureClassification {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private String descripiton;
	
	private Integer sortOrder;
	
	private Boolean valid;
	
	/**
	 * Instantiates a default instance of feature classification implementation.
	 */
	public FeatureClassificationImpl() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescripiton() {
		return this.descripiton;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescripiton(final String descripiton) {
		this.descripiton = descripiton;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public void setSortOrder(final Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof FeatureClassification)) {
			return false;
		}
		
		FeatureClassification that = (FeatureClassification) o;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getName().hashCode();
		
		return hashCode;
	}
}