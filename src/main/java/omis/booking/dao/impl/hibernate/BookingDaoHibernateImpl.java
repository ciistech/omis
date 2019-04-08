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
package omis.booking.dao.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.booking.dao.BookingDao;
import omis.booking.domain.Booking;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;

/** Hibernate implementation of booking dao.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 2, 2019)
 * @since OMIS 3.0 */
public class BookingDaoHibernateImpl extends GenericHibernateDaoImpl<Booking> 
	implements BookingDao {
	private static final String 
		FIND_BY_OFFENDER_DATE_AND_BOOKING_NUMBER_QUERY_NAME 
		= "findBookingByOffenderDateAndBookingNumber";
	private static final String
		FIND_BY_OFFENDER_DATE_AND_BOOKING_NUMBER_EXCLUDING_QUERY_NAME
		= "findBookingByOffenderDateAndBookingNumberExcluding";
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String DATE_PARAM_NAME = "date";
	private static final String BOOKING_NUMBER_PARAM_NAME = "bookingNumber";
	private static final String EXCLUDING_PARAM_NAME = "excluding";
	
	/** Constructor.
	 * @param sessionFactory session factory.
	 * @param entityName entity name. */
	public BookingDaoHibernateImpl(final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}
	

	/** {@inheritDoc} */
	@Override
	public Booking findByOffenderDateAndBookingNumber(final Offender offender,
			final Date date, final Integer bookingNumber) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_OFFENDER_DATE_AND_BOOKING_NUMBER_QUERY_NAME);
		
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setTimestamp(DATE_PARAM_NAME, date);
		q.setInteger(BOOKING_NUMBER_PARAM_NAME, bookingNumber);
		return (Booking) q.uniqueResult();
	}
	
	/** {@inheritDoc} */
	@Override
	public Booking findByOffenderDateAndBookingNumberExcluding(
			final Offender offender, final Date date, 
			final Integer bookingNumber, final Booking excluding) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_OFFENDER_DATE_AND_BOOKING_NUMBER_EXCLUDING_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setTimestamp(DATE_PARAM_NAME, date);
		q.setInteger(BOOKING_NUMBER_PARAM_NAME, bookingNumber);
		q.setEntity(EXCLUDING_PARAM_NAME, excluding);
		return (Booking) q.uniqueResult();
	}
}
