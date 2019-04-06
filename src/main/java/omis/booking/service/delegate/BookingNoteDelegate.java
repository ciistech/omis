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
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.booking.dao.BookingNoteDao;
import omis.booking.domain.Booking;
import omis.booking.domain.BookingNote;
import omis.booking.exception.BookingNoteExistsException;
import omis.instance.factory.InstanceFactory;

/** Delegate for booking note related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 4, 2019)
 * @since OMIS 3.0 */
public class BookingNoteDelegate {
	private static final String 
		BOOKING_NOTE_EXISTS_EXCEPTION_MESSAGE 
			= "Booking note with booking, date, and description exists.";
	
	private final InstanceFactory<BookingNote> bookingNoteInstanceFactory;
	private final BookingNoteDao bookingNoteDao;
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Constructor.
	 * @param bookingNoteInstanceFactory - booking note instance factory.
	 * @param bookingNoteDao - booking note dao.
	 * @param auditComponentRetriever - audit component retriever. */
	public BookingNoteDelegate(
			final InstanceFactory<BookingNote> bookingNoteInstanceFactory,
			final BookingNoteDao bookingNoteDao, 
			final AuditComponentRetriever auditComponentRetriever) {
		this.bookingNoteDao = bookingNoteDao;
		this.bookingNoteInstanceFactory = bookingNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** Create booking note.
	 * @param bookingNote - booking note.
	 * @param booking - booking.
	 * @param date - date.
	 * @param description - description. 
	 * @return booking note. 
	 * @throws BookingNoteExistsException when booking note exists.*/
	public BookingNote createBookingNote(final Booking booking, 
			final Date date , final String description) 
					throws BookingNoteExistsException {
		final BookingNote bookingNote;
		if (this.bookingNoteDao.findByBookingDateAndDescription(booking, date, 
				description) != null) {
			throw new BookingNoteExistsException(BOOKING_NOTE_EXISTS_EXCEPTION_MESSAGE);
		} else {
			bookingNote = this.populateBookingNote(
					this.bookingNoteInstanceFactory.createInstance(), booking, 
					date, description);
			
			bookingNote.setCreationSignature(new CreationSignature(
					this.auditComponentRetriever.retrieveUserAccount(), 
					this.auditComponentRetriever.retrieveDate()));
			
			bookingNote.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(), 
					this.auditComponentRetriever.retrieveDate()));
			
			return this.bookingNoteDao.makePersistent(bookingNote);
		}
	}
	
	/** Updated booking note.
	 * @param bookingNote - booking note.
	 * @param date - date.
	 * @param description - description.
	 * @return booking note.
	 * @throws BookingNoteExistsException - when booking note exists. */
	public BookingNote updateBookingNote(final BookingNote bookingNote, 
			final Date date, final String description) 
					throws BookingNoteExistsException {
		if (this.bookingNoteDao.findByBookingDateAndDescriptionExcluding(
				bookingNote.getBooking(), date, description,
				bookingNote) != null) {
			throw new BookingNoteExistsException(
					BOOKING_NOTE_EXISTS_EXCEPTION_MESSAGE);
		} else {
			this.populateBookingNote(bookingNote, bookingNote.getBooking(), 
					date, description);
			
			bookingNote.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(), 
					this.auditComponentRetriever.retrieveDate()));
			
			return this.bookingNoteDao.makePersistent(bookingNote);
		}
	}
	
	/** Removes booking note.
	 * @param bookingNote - booking note. */
	public void removeBookingNote(final BookingNote bookingNote) {
		this.bookingNoteDao.makeTransient(bookingNote);
	}
	
	/** Finds booking notes by booking.
	 * @param booking - booking.
	 * @return booking notes. */
	public List<BookingNote> findByBooking(final Booking booking) {
		return this.bookingNoteDao.findByBooking(booking);
	}
	
	/* Populate booking note. */
	private BookingNote populateBookingNote(final BookingNote bookingNote, final Booking booking, final Date date, final String description) {
		bookingNote.setBooking(booking);
		bookingNote.setDate(date);
		bookingNote.setDescription(description);
		
		return bookingNote;
	}
	
}
