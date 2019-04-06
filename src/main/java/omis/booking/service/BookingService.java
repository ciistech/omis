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
import omis.ncic.domain.NcicCheck;
import omis.ncic.domain.NcicCheckResult;
import omis.ncic.exception.NcicCheckExistsException;
import omis.offender.domain.Offender;
import omis.patsearch.domain.PatSearch;
import omis.patsearch.exception.PatSearchExistsException;
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
	
	/** Creates pat search.
	 * @param offender - offender.
	 * @param date - date.
	 * @param officer - officer.
	 * @param booking - booking.
	 * @return pat search.
	 * @throws PatSearchExistsException - when pat search exists. */
	PatSearch createPatSearch(Offender offender, Date date, 
			StaffAssignment officer, Booking booking) 
		throws PatSearchExistsException;
	
	/** Updates pat search.
	 * @param patSearch - pat search.
	 * @param date - date.
	 * @param officer - staff assignment.
	 * @return pat search.
	 * @throws PatSearchExistsException - when pat search exists. */
	PatSearch updatePatSearch(PatSearch patSearch, Date date,
			StaffAssignment officer) throws PatSearchExistsException;
	
	/** removes pat search.
	 * @param patSearch - pat search. */
	void removePatSearch(PatSearch patSearch);
	
	/** Creates NCIC Check.
	 * @param booking - booking.
	 * @param date - date.
	 * @param offender - offender.
	 * @param result - ncic check result.
	 * @return ncic check.
	 * @throws NcicCheckExistsException - when ncic check exists. */
	NcicCheck createNcicCheck(Booking booking, Date date,
			Offender offender, NcicCheckResult result) 
					throws NcicCheckExistsException;
	
	/** Updates NCIC Check.
	 * @param ncicCheck - ncic check.
	 * @param date - date.
	 * @param result - ncic check result.
	 * @return ncic check.
	 * @throws NcicCheckExistsException - when ncic check exists. */
	NcicCheck updateNcicCheck(NcicCheck ncicCheck, Date date,
			NcicCheckResult ncicCheckResult) throws NcicCheckExistsException;
	
	/** Removes NCIC Check.
	 * @param ncic check - ncic check. */
	void removeNcicCheck(NcicCheck ncicCheck);
	
	/** Finds pat search by booking.
	 * @param booking - booking
	 * @return pat searches. */
	List<PatSearch> findPatSearchByBooking(Booking booking);
	
	/** Finds booking admission categories.
	 * @return booking admission categories. */
	List<BookingAdmissionCategory> findBookingAdmissionCategories();
	
	/** Find booking commit source category.
	 * @return booking commit source categories. */
	List<BookingCommitSourceCategory> findBookingCommitSourceCategories();
	
	/** Find ncic check results.
	 * @return ncic check results. */
	List<NcicCheckResult> findNcicCheckResults();
	
	/** Find ncic checks by booking.
	 * @param booking - booking.
	 * @return ncic checks. */
	List<NcicCheck> findNcicChecksByBooking(Booking booking);
}
