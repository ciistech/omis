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
package omis.booking.service;

import java.util.Date;
import java.util.List;

import omis.booking.domain.Booking;
import omis.booking.domain.BookingAdmissionCategory;
import omis.booking.domain.BookingCommitSourceCategory;
import omis.booking.domain.BookingNote;
import omis.booking.exception.BookingExistsException;
import omis.booking.exception.BookingNoteExistsException;
import omis.offender.domain.Offender;
import omis.region.domain.County;
import omis.staff.domain.StaffAssignment;
import omis.supervision.domain.CorrectionalStatus;

/** Service for booking operations.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 2, 2019)
 * @since OMIS 3.0 */
public interface BookingService {
	
	/** Creates booking.
	 * @param bookingNumber booking number.
	 * @param category booking admission category.
	 * @param offender offender.
	 * @param date date.
	 * @param county county.
	 * @param correctionalStatus correctional status.
	 * @param commitSource booking commit source category.
	 * @param transportStaff transport staff assignment.
	 * @throws BookingExistsException when duplicate booking exists.
	 * @return booking. */
	Booking createBooking(Integer bookingNumber, 
			BookingAdmissionCategory category, Offender offender, Date date, 
			County county, CorrectionalStatus correctionalStatus, 
			BookingCommitSourceCategory commitSource, 
			StaffAssignment transportStaff) throws BookingExistsException;
	
	/** Updates booking.
	 * @param booking booking.
	 * @param bookingNumber booking number.
	 * @param category booking admission category.
	 * @param date date.
	 * @param county county.
	 * @param correctionalStatus correctional status.
	 * @param commitSource booking commit source category.
	 * @param transportStaff transport staff assignment.
	 * @throws BookingExistsException when duplicate booking exists.
	 * @return booking. */
	Booking updateBooking(Booking booking, Integer bookingNumber, 
			BookingAdmissionCategory category, Date date, County county,
			CorrectionalStatus correctionalStatus, 
			BookingCommitSourceCategory commitSource, 
			StaffAssignment transportStaff) throws BookingExistsException;
	
	/** Removes booking.
	 * @param booking booking. */
	void removeBooking(Booking booking);
	
	/** Creates booking note.
	 * @param booking - booking.
	 * @param description - description.
	 * @param date - date. 
	 * @return booking note
	 * @throws BookingNoteExistsException - when booking note exists. */
	BookingNote createBookingNote(Booking booking, String description, 
			Date date) throws BookingNoteExistsException;
	
	
	/** Updates booking note.
	 * @param bookingNote - booking note.
	 * @param description - description.
	 * @param date - date.
	 * @return booking note.
	 * @throws BookingNoteExistsException - when booking note exists. */
	BookingNote updateBookingNote(BookingNote bookingNote, String description, 
			Date date) throws BookingNoteExistsException;
	
	/** Removes booking note.
	 * @param bookingNote - booking note. */
	void removeBookingNote(BookingNote bookingNote);
	
	/** Finds booking admission categories.
	 * @return booking admission categories. */
	List<BookingAdmissionCategory> findBookingAdmissionCategories();
	
	/** Find booking commit source category.
	 * @return booking commit source categories. */
	List<BookingCommitSourceCategory> findBookingCommitSourceCategories();
}
