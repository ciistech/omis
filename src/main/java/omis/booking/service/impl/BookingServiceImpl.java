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
package omis.booking.service.impl;

import java.util.Date;
import java.util.List;

import omis.booking.domain.Booking;
import omis.booking.domain.BookingAdmissionCategory;
import omis.booking.domain.BookingCommitSourceCategory;
import omis.booking.domain.BookingNote;
import omis.booking.exception.BookingExistsException;
import omis.booking.exception.BookingNoteExistsException;
import omis.booking.service.BookingService;
import omis.booking.service.delegate.BookingAdmissionCategoryDelegate;
import omis.booking.service.delegate.BookingCommitSourceCategoryDelegate;
import omis.booking.service.delegate.BookingDelegate;
import omis.booking.service.delegate.BookingNoteDelegate;
import omis.ncic.domain.NcicCheck;
import omis.ncic.domain.NcicCheckResult;
import omis.ncic.exception.NcicCheckExistsException;
import omis.ncic.service.delegate.NcicCheckDelegate;
import omis.offender.domain.Offender;
import omis.patsearch.domain.PatSearch;
import omis.patsearch.exception.PatSearchExistsException;
import omis.patsearch.service.delegate.PatSearchDelegate;
import omis.region.domain.County;
import omis.region.domain.State;
import omis.region.service.delegate.CountyDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.staff.domain.StaffAssignment;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.service.delegate.CorrectionalStatusDelegate;

/** Implementation of booking service.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 5, 2019)
 * @since OMIS 3.0 */
public class BookingServiceImpl implements BookingService {
	private final BookingDelegate bookingDelegate;
	private final BookingNoteDelegate bookingNoteDelegate;
	private final PatSearchDelegate patSearchDelegate;
	private final NcicCheckDelegate ncicCheckDelegate;
	private final BookingAdmissionCategoryDelegate 
		bookingAdmissionCategoryDelegate;
	private final BookingCommitSourceCategoryDelegate
		bookingCommitSourceCategoryDelegate;
	private final CorrectionalStatusDelegate 
		correctionalStatusDelegate;
	private final StateDelegate stateDelegate;
	private final CountyDelegate countyDelegate;
	
	/** Constructor.
	 * @param bookingDelegate - booking delegate.
	 * @param bookingNoteDelegate - booking note delegate.
	 * @param patSearchDelegate - pat search delegate. 
	 * @param ncicCheckDelegate - ncic check delegate. 
	 * @param bookingAdmissionCategoryDelegate - 
	 * booking admission category delegate. 
	 * @param bookingCommitSourceCategoryDelegate -
	 * booking commit source category delegate. 
	 * @param correctionalStatusDelegate - correctional status delegate. 
	 * @param stateDelegate - state delegate.
	 * @param countyDelegate - county delegate. */
	public BookingServiceImpl(final BookingDelegate bookingDelegate,
			final BookingNoteDelegate bookingNoteDelegate,
			final PatSearchDelegate patSearchDelegate,
			final NcicCheckDelegate ncicCheckDelegate,
			final BookingAdmissionCategoryDelegate 
				bookingAdmissionCategoryDelegate,
			final BookingCommitSourceCategoryDelegate
				bookingCommitSourceCategoryDelegate, 
			final CorrectionalStatusDelegate correctionalStatusDelegate,
			final StateDelegate stateDelegate, 
			final CountyDelegate countyDelegate) {
		this.bookingDelegate = bookingDelegate;
		this.bookingNoteDelegate = bookingNoteDelegate;
		this.patSearchDelegate = patSearchDelegate;
		this.ncicCheckDelegate = ncicCheckDelegate;
		this.bookingAdmissionCategoryDelegate 
			= bookingAdmissionCategoryDelegate;
		this.bookingCommitSourceCategoryDelegate 
			= bookingCommitSourceCategoryDelegate;
		this.correctionalStatusDelegate = correctionalStatusDelegate;
		this.stateDelegate = stateDelegate;
		this.countyDelegate = countyDelegate;
	}
	
	
	/** {@inheritDoc} */
	@Override
	public Booking createBooking(final Integer bookingNumber, 
			final BookingAdmissionCategory category, final Offender offender, 
			final Date date, final County county, 
			final CorrectionalStatus correctionalStatus, 
			final BookingCommitSourceCategory commitSource,
			final StaffAssignment transportStaff) throws BookingExistsException {
		return this.bookingDelegate.createBooking(offender, date, bookingNumber, category, commitSource, correctionalStatus, county, transportStaff);
	}

	/** {@inheritDoc} */
	@Override
	public Booking updateBooking(final Booking booking, 
			final Integer bookingNumber, 
			final BookingAdmissionCategory category, final Date date,
			final County county, final CorrectionalStatus correctionalStatus, 
			final BookingCommitSourceCategory commitSource,
			final StaffAssignment transportStaff) 
					throws BookingExistsException {
		return this.bookingDelegate.updateBooking(booking, date, 
				bookingNumber, category, commitSource, 
				correctionalStatus, county, transportStaff);
	}

	/** {@inheritDoc} */
	@Override
	public void removeBooking(final Booking booking) {
		this.bookingDelegate.removeBooking(booking);
	}

	/** {@inheritDoc} */
	@Override
	public BookingNote createBookingNote(final Booking booking, 
			final String description, final Date date)
			throws BookingNoteExistsException {
		return this.bookingNoteDelegate.createBookingNote(booking, date, 
				description);
	}

	/** {@inheritDoc} */
	@Override
	public BookingNote updateBookingNote(final BookingNote bookingNote, 
			final String description, final Date date)
			throws BookingNoteExistsException {
		return this.bookingNoteDelegate.updateBookingNote(bookingNote, date, 
				description);
	}

	/** {@inheritDoc} */
	@Override
	public void removeBookingNote(final BookingNote bookingNote) {
		this.bookingNoteDelegate.removeBookingNote(bookingNote);
	}

	/** {@inheritDoc} */
	@Override
	public PatSearch createPatSearch(final Offender offender, final Date date, 
			final StaffAssignment officer, final Booking booking)
			throws PatSearchExistsException {
		return this.patSearchDelegate.createPatSearch(offender, date, officer, 
				booking);
	}

	/** {@inheritDoc} */
	@Override
	public PatSearch updatePatSearch(final PatSearch patSearch, 
			final Date date, final StaffAssignment officer)
			throws PatSearchExistsException {
		return this.patSearchDelegate.updatePatSearch(patSearch, date, 
				officer);
	}

	/** {@inheritDoc} */
	@Override
	public void removePatSearch(final PatSearch patSearch) {
		this.patSearchDelegate.removePatSearch(patSearch);
	}

	/** {@inheritDoc} */
	@Override
	public NcicCheck createNcicCheck(final Booking booking, final Date date, 
			final Offender offender, final NcicCheckResult result)
			throws NcicCheckExistsException {
		return this.ncicCheckDelegate.createNcicCheck(booking, date, offender, result);
	}

	/** {@inheritDoc} */
	@Override
	public NcicCheck updateNcicCheck(final NcicCheck ncicCheck, final Date date, 
			final NcicCheckResult ncicCheckResult)
			throws NcicCheckExistsException {
		return this.ncicCheckDelegate.updateNcicCheck(ncicCheck, date, 
				ncicCheckResult);
	}

	/** {@inheritDoc} */
	@Override
	public void removeNcicCheck(final NcicCheck ncicCheck) {
		this.ncicCheckDelegate.removeNcicCheck(ncicCheck);
	}

	/** {@inheritDoc} */
	@Override
	public List<PatSearch> findPatSearchByBooking(final Booking booking) {
		return this.patSearchDelegate.findByBooking(booking);
	}

	/** {@inheritDoc} */
	@Override
	public List<BookingNote> findBookingNotesByBooking(final Booking booking) {
		return this.bookingNoteDelegate.findByBooking(booking);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<BookingAdmissionCategory> findBookingAdmissionCategories() {
		return this.bookingAdmissionCategoryDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<BookingCommitSourceCategory> findBookingCommitSourceCategories() {
		return this.bookingCommitSourceCategoryDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<NcicCheckResult> findNcicCheckResults() {
		return this.ncicCheckDelegate.findNcicCheckResults();
	}

	/** {@inheritDoc} */
	@Override
	public List<NcicCheck> findNcicChecksByBooking(Booking booking) {
		return this.ncicCheckDelegate.findByBooking(booking);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CorrectionalStatus> findCorrectionalStatuses() {
		return this.correctionalStatusDelegate.findAllCorrectionalStatuses();
	}
	
	/** {@inheritDoc} */
	@Override
	public State findHomeState() {
		return this.stateDelegate.findHomeState();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<County> findAllCountiesByState(final State state) {
		return this.countyDelegate.findByState(state);
	}
}
