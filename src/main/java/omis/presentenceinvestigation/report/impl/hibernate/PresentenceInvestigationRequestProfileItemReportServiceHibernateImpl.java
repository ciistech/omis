/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.presentenceinvestigation.report.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.presentenceinvestigation.report.PresentenceInvestigationRequestProfileItemReportService;

/**
 * PresentenceInvestigationRequestProfileItemReportServiceHibernateImpl.java
 * 
 * @author Annie Wahl
 * @author Josh Divine 
 * @version 0.1.1 (Feb 14, 2018)
 * @since OMIS 3.0
 */
public class PresentenceInvestigationRequestProfileItemReportServiceHibernateImpl
		implements PresentenceInvestigationRequestProfileItemReportService {
	
	private static final String 
		FIND_PRESENTENCE_INVESTIGATION_REQUEST_COUNT_QUERY_NAME =
			"findPresentenceInvestigationRequestCount";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory
	 */
	public PresentenceInvestigationRequestProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**{@inheritDoc} */
	@Override
	public Integer findPresentenceInvestigationRequestCountByOffender(
			final Offender offender) {
		Integer count = ((Long) this.sessionFactory.getCurrentSession()
				.getNamedQuery(
						FIND_PRESENTENCE_INVESTIGATION_REQUEST_COUNT_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setReadOnly(true)
				.uniqueResult()).intValue();
		
		return count;
	}
}