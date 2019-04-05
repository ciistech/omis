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
package omis.booking.exception;

import omis.booking.domain.BookingNote;
import omis.exception.DuplicateEntityFoundException;

/** Thrown when booking note exists.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 4, 2019)
 * @since OMIS 3.0 */
public class BookingNoteExistsException extends DuplicateEntityFoundException {
	public static final long serialVersionUID = 1L;
	private final BookingNote bookingNote;
	
	/** Constructor. */
	public BookingNoteExistsException() {
		this.bookingNote = null;
	}
	
	/** Constructor.
	 * @param message - message.
	 * @param cause - cause.
	 * @param bookingNote - booking note. */
	public BookingNoteExistsException(final String message, final Throwable cause, final BookingNote bookingNote) {
		super(message, cause);
		this.bookingNote = bookingNote;
	}
	
	/** Constructor.
	 * @param message - message. */
	public BookingNoteExistsException(final String message) {
		super(message);
		this.bookingNote = null;
	}
	
	/** Constructor.
	 * @param cause - cause. */
	public BookingNoteExistsException(final Throwable cause) {
		super(cause);
		this.bookingNote = null;
	}
	
	/** Constructor.
	 * @param bookingNote - booking note. */
	public BookingNoteExistsException(final BookingNote bookingNote) {
		this.bookingNote = bookingNote;
	}
	
	/** Gets booking note.
	 * @return booking note. */
	public BookingNote getBookingNote() {
		return this.bookingNote;
	}
}
