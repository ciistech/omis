package omis.offender.report.delegate.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.offender.report.ActivitySummary;

/**
 * Compliance Activity Summary Report Delegate.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 7, 2017)
 *@since OMIS 3.0
 *
 */
public class ComplianceActivitySummaryReportDelegate {

	private static final String FIND_BY_OFFENDER_QUERY_NAME =
			"findComplianceActivitySummariesByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory - Session Factory
	 */
	public ComplianceActivitySummaryReportDelegate(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Returns a list of Compliance Activity Summaries found by the
	 * specified Offender.
	 * 
	 * @param offender - Offender
	 * @return List of Compliance Activity Summaries found by the
	 * specified Offender
	 */
	public List<ActivitySummary> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<ActivitySummary> summaries = this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		
		return summaries;
	}
}
