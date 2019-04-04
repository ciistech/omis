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
package omis.ncic.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.booking.domain.Booking;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.ncic.dao.NcicCheckDao;
import omis.ncic.domain.NcicCheck;
import omis.offender.domain.Offender;

/** Hibernate implementation of ncic check dao.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 4, 2019)
 * @since OMIS 3.0 */
public class NcicCheckDaoHibernateImpl 
	extends GenericHibernateDaoImpl<NcicCheck> implements NcicCheckDao {
	private static final String FIND_BY_OFFENDER_AND_DATE_QUERY_NAME = 
			"findNCICCheckByOffenderDate";
	private static final String FIND_BY_BOOKING = "findNCICCheckByBooking";
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String DATE_PARAM_NAME = "date";
	private static final String BOOKING_PARAM_NAME = "booking";
	/** Constructor.
	 * @param sessionFactory - session factory.
	 * @param entityName - entity name. */
	public NcicCheckDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public NcicCheck findByOffenderAndDate(final Offender offender, final Date date) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_OFFENDER_AND_DATE_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setTimestamp(DATE_PARAM_NAME, date);
		return (NcicCheck) q.uniqueResult();
	}
	
	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<NcicCheck> findByBooking(final Booking booking) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_BOOKING);
		q.setEntity(BOOKING_PARAM_NAME, booking);
		return (List<NcicCheck>) q.list();
	}
}
