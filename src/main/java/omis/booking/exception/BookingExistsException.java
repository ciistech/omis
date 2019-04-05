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

import omis.booking.domain.Booking;
import omis.exception.DuplicateEntityFoundException;

/** Thrown when booking exists.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 2, 2019)
 * @since OMIS 3.0 */
public class BookingExistsException extends DuplicateEntityFoundException {
	private static final long serialVersionUID = 1L;
	private final Booking booking;
	
	/** Constructor. */
	public BookingExistsException() {
		this.booking = null;
	}
	
	/** Constructor.
	 * @param message message.
	 * @param cause cause.
	 * @param booking booking. */
	public BookingExistsException(
			final String message, final Throwable cause, final Booking booking) {
		super(message, cause);
		this.booking = booking;
	}
	
	/** Constructor.
	 * @param message message. */
	public BookingExistsException(final String message) {
		super(message);
		this.booking = null;
	}
	
	/** Constructor.
	 * @param cause cause. */
	public BookingExistsException(final Throwable cause) {
		super(cause);
		this.booking = null;
	}
	
	/** Constructor.
	 * @param booking booking. */
	public BookingExistsException(final Booking booking) {
		this.booking = booking;
	}
	
	/** Gets booking.
	 * @return booking. */
	public Booking getBooking() {
		return this.booking;
	}
}
