package omis.presentenceinvestigation.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummary;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummaryNote;

/**
 * Evaluation Recommendation Section Summary Note Impl
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2017)
 *@since OMIS 3.0
 *
 */
public class EvaluationRecommendationSectionSummaryNoteImpl
		implements EvaluationRecommendationSectionSummaryNote {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String description;
	
	private Date date;
	
	private EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**{@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/**{@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/**{@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/**{@inheritDoc} */
	@Override
	public EvaluationRecommendationSectionSummary
		getEvaluationRecommendationSectionSummary() {
		return this.evaluationRecommendationSectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public void setEvaluationRecommendationSectionSummary(
			final EvaluationRecommendationSectionSummary
					evaluationRecommendationSectionSummary) {
		this.evaluationRecommendationSectionSummary =
				evaluationRecommendationSectionSummary;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof EvaluationRecommendationSectionSummaryNote)){
			return false;
		}
		
		EvaluationRecommendationSectionSummaryNote that =
				(EvaluationRecommendationSectionSummaryNote) obj;
		
		if(this.getEvaluationRecommendationSectionSummary() == null){
			throw new IllegalStateException(
					"Evaluation Recommendation Section Summary required.");
		}
		if(this.getDescription() == null){
			throw new IllegalStateException(
					"Description required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException(
					"Date required.");
		}
		
		if(!this.getEvaluationRecommendationSectionSummary().equals(
				that.getEvaluationRecommendationSectionSummary())){
			return false;
		}if(!this.getDescription().equals(
				that.getDescription())){
			return false;
		}if(!this.getDate().equals(
				that.getDate())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getEvaluationRecommendationSectionSummary() == null){
			throw new IllegalStateException(
					"Evaluation Recommendation Section Summary required.");
		}
		if(this.getDescription() == null){
			throw new IllegalStateException(
					"Description required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException(
					"Date required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode +
				this.getEvaluationRecommendationSectionSummary().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		
		return hashCode;
	}
	
}
