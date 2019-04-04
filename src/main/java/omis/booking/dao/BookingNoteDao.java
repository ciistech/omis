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
package omis.booking.dao;

import java.util.Date;
import java.util.List;

import omis.booking.domain.Booking;
import omis.booking.domain.BookingNote;
import omis.dao.GenericDao;

/** Data access object for booking note.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 4, 2019)
 * @since OMIS 3.0 */
public interface BookingNoteDao extends GenericDao<BookingNote> {
	/** Finds booking notes by booking.
	 * @param booking notes. */
	List<BookingNote> findByBooking(Booking booking);
	
	/** Finds booking note by booking, date, and description.
	 * @param booking - booking.
	 * @param date - date.
	 * @param description - description.
	 * @return booking note. */
	BookingNote findByBookingDateAndDescription(Booking booking, Date date, 
			String Description);
}
