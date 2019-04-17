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
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.booking.dao.BookingNoteDao;
import omis.booking.domain.Booking;
import omis.booking.domain.BookingNote;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/** Hibernate implementation of booking note dao.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 4, 2019)
 * @since OMIS 3.0 */
public class BookingNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<BookingNote> implements BookingNoteDao {
	private static final String FIND_BY_BOOKING_QUERY_NAME = 
			"findBookingNotesByBooking";
	private static final String FIND_BY_BOOKING_DATE_DESCRIPTION_QUERY_NAME
		= "findBookingNoteByBookingDateAndDescription";
	private static final String 
		FIND_BY_BOOKING_DATE_DESCRIPTION_EXCLUDING_QUERY_NAME
		= "findBookingNoteByBookingDateAndDescriptionExcluding";
	private static final String BOOKING_PARAM_NAME = "booking";
	private static final String DATE_PARAM_NAME = "date";
	private static final String DESCRIPTION_PARAM_NAME = "description";
	private static final String EXCLUDING_PARAM_NAME = "excluding";
	
	/** Constructor.
	 * @param sessionFactory - session factory.
	 * @param entityName - entity name. */
	public BookingNoteDaoHibernateImpl(final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}
	

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<BookingNote> findByBooking(final Booking booking) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_BOOKING_QUERY_NAME);
		q.setEntity(BOOKING_PARAM_NAME, booking);
		return (List<BookingNote>) q.list();
	}
	
	/** {@inheritDoc} */
	@Override
	public BookingNote findByBookingDateAndDescription(final Booking booking, 
			final Date date, final String description) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_BOOKING_DATE_DESCRIPTION_QUERY_NAME);
		q.setTimestamp(DATE_PARAM_NAME, date);
		q.setEntity(BOOKING_PARAM_NAME, booking);
		q.setString(DESCRIPTION_PARAM_NAME, description);
		return (BookingNote) q.uniqueResult();
	}
	
	/** {@inheritDoc} */
	@Override
	public BookingNote findByBookingDateAndDescriptionExcluding(
			final Booking booking, final Date date, final String description, 
			final BookingNote excluding) {
		Query q = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_BY_BOOKING_DATE_DESCRIPTION_EXCLUDING_QUERY_NAME);
		q.setEntity(BOOKING_PARAM_NAME, booking);
		q.setTimestamp(DATE_PARAM_NAME, date);
		q.setString(DESCRIPTION_PARAM_NAME, description);
		q.setEntity(EXCLUDING_PARAM_NAME, excluding);
		return (BookingNote) q.uniqueResult();
	}
}
