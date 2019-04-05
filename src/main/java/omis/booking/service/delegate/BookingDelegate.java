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
package omis.booking.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.booking.dao.BookingDao;
import omis.booking.domain.Booking;
import omis.booking.domain.BookingAdmissionCategory;
import omis.booking.domain.BookingCommitSourceCategory;
import omis.booking.exception.BookingExistsException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.region.domain.County;
import omis.staff.domain.StaffAssignment;
import omis.supervision.domain.CorrectionalStatus;

/** Delegate for booking related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 5, 2019)
 * @since OMIS 3.0 */
public class BookingDelegate {
	private static final String BOOKING_EXISTS_EXCEPTION_MESSAGE 
		= "Booking exists with offender date and booking number.";
	
	private final InstanceFactory<Booking> bookingInstanceFactory;
	private final BookingDao bookingDao;
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Constructor.
	 * @param bookingInstanceFactory - booking instance factory.
	 * @param bookingDao - booking dao.
	 * @param auditComponentRetriever - audit component retriever. */
	public BookingDelegate(
			final InstanceFactory<Booking> bookingInstanceFactory,
			final BookingDao bookingDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.bookingInstanceFactory = bookingInstanceFactory;
		this.bookingDao = bookingDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** Creates booking.
	 * @param offender - offender.
	 * @param date - date.
	 * @param bookingNumber - booking number.
	 * @param category - booking admission category.
	 * @param commitSource - booking commit source category.
	 * @param correctionalStatus - correctional status.
	 * @param county - county.
	 * @param transportOfficer - transport staff.
	 * @return booking.
	 * @throws BookingExistsException - when booking exists. */
	 public Booking createBooking(final Offender offender, final Date date, 
			 final Integer bookingNumber, 
			 final BookingAdmissionCategory category,
			 final BookingCommitSourceCategory commitSource,
			 final CorrectionalStatus correctionalStatus, final County county,
			 final StaffAssignment transportOfficer) 
					 throws BookingExistsException {
		 final Booking result;
		 if (this.bookingDao.findByOffenderDateAndBookingNumber(offender, 
				 date, bookingNumber) != null) {
			 throw new BookingExistsException(BOOKING_EXISTS_EXCEPTION_MESSAGE);
		 } else {
			 result = this.populateBooking(
					 this.bookingInstanceFactory.createInstance(), offender, 
					 date, bookingNumber, category, commitSource, 
					 correctionalStatus, county, transportOfficer);
			 
			 result.setCreationSignature(new CreationSignature(
					 this.auditComponentRetriever.retrieveUserAccount(), 
					 this.auditComponentRetriever.retrieveDate()));
			 
			 result.setUpdateSignature(new UpdateSignature(
					 this.auditComponentRetriever.retrieveUserAccount(), 
					 this.auditComponentRetriever.retrieveDate()));
			 
			 return this.bookingDao.makePersistent(result);

		 }
	 }
	 
	 /** removes booking.
	  * @param booking - booking. */
	 public void removeBooking(final Booking booking) {
		 this.bookingDao.makeTransient(booking);
	 }
	 
	 /** Updates booking.
	  * @param booking - booking.
	  * @param date - date.
	  * @param bookingNumber - booking number.
	  * @param category - booking admission category.
	  * @param commitSource - booking commit source category.
	  * @param correctionalStatus - correctional status.
	  * @param county - county.
	  * @param transportOfficer - transport staff assignment.
	  * @return booking.
	  * @throws BookingExistsException - when booking exists. */
	 public Booking updateBooking(final Booking booking, final Date date,
			 final Integer bookingNumber, 
			 final BookingAdmissionCategory category,
			 final BookingCommitSourceCategory commitSource, 
			 final CorrectionalStatus correctionalStatus, final County county,
			 final StaffAssignment transportOfficer) 
					 throws BookingExistsException {
		 if (this.bookingDao.findByOffenderDateAndBookingNumberExcluding(
				 booking.getOffender(), date, bookingNumber, 
				 booking) != null) {
			 throw new BookingExistsException(
					 BOOKING_EXISTS_EXCEPTION_MESSAGE);
		 } else {
			 this.populateBooking(booking, booking.getOffender(), date, 
					 bookingNumber, category, commitSource, 
					 correctionalStatus, county, transportOfficer);
			 
			 booking.setUpdateSignature(new UpdateSignature(
					 this.auditComponentRetriever.retrieveUserAccount(), 
					 this.auditComponentRetriever.retrieveDate()));
			 
			 return this.bookingDao.makePersistent(booking);
		 }
	 }
	 
	/* Populates booking. */
	private Booking populateBooking(final Booking booking, 
			final Offender offender, final Date date, 
			final Integer bookingNumber, 
			final BookingAdmissionCategory category, 
			final BookingCommitSourceCategory commitSource, 
			final CorrectionalStatus correctionalStatus,
			final County county, final StaffAssignment transportOfficer) {
		booking.setBookingNumber(bookingNumber);
		booking.setCategory(category);
		booking.setCommitSource(commitSource);
		booking.setCorrectionalStatus(correctionalStatus);
		booking.setCounty(county);
		booking.setDate(date);
		booking.setOffender(offender);
		booking.setTransportOfficer(transportOfficer);
		
		return booking;
	}
}
