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
package omis.patsearch.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.booking.domain.Booking;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.patsearch.dao.PatSearchDao;
import omis.patsearch.domain.PatSearch;
import omis.staff.domain.StaffAssignment;

/** Hibernate implementation of pat search dao.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 4, 2019)
 * @since OMIS 3.0 */
public class PatSearchDaoHibernateImpl 
	extends GenericHibernateDaoImpl<PatSearch> implements PatSearchDao {
	private static final String 
		FIND_BY_OFFENDER_DATE_AND_STAFF_ASSIGNMENT_QUERY_NAME 
			= "findPatSearchByOffenderDateAndStaffAssignment";
	private static final String FIND_BY_BOOKING_QUERY_NAME 
		= "findPatSearchByBooking";
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String OFFICER_PARAM_NAME = "officer";
	private static final String DATE_PARAM_NAME = "date";
	private static final String BOOKING_PARAM_NAME = "booking";
	
	/** Constructor.
	 * @param sessionFactory - session factory.
	 * @param entityName - entity name. */
	public PatSearchDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inerhitDoc} */
	@Override
	public PatSearch findByOffenderDateAndOfficer(final Offender offender, 
			final Date date, final StaffAssignment officer) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_OFFENDER_DATE_AND_STAFF_ASSIGNMENT_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setTimestamp(DATE_PARAM_NAME, date);
		q.setEntity(OFFICER_PARAM_NAME, officer);
		return (PatSearch) q.uniqueResult();
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<PatSearch> findByBooking(final Booking booking) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_BOOKING_QUERY_NAME);
		q.setEntity(BOOKING_PARAM_NAME, booking);
		return (List<PatSearch>) q.list();
	}
}
