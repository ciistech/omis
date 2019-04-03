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
package omis.booking.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.booking.domain.Booking;
import omis.booking.domain.BookingNote;

/** Implementation of booking note.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 2, 2019)
 * @since OMIS 3.0 */
public class BookingNoteImpl implements BookingNote {
	private static final long serialVersionUID = 1L;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	private Long id;
	private Booking booking;
	private String description;
	private Date date;
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setBooking(final Booking booking) {
		this.booking = booking;
	}

	/** {@inheritDoc} */
	@Override
	public Booking getBooking() {
		return this.booking;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result;
		if (this == obj) {
			result = true;
		} else {
			if (!(obj instanceof BookingNote)) {
				result = false;
			} else {
				BookingNote that = (BookingNote) obj;
				this.checkState();
				
				result = (this.getBooking().equals(that.getBooking())
						&& this.getDate().equals(that.getDate())
						&& this.getDescription().equals(that.getDescription())
						? true : false);
			}
		}
		return result;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 14;
		this.checkState();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getBooking().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		return hashCode;
	}
	
	/* Checks state. */
	public void checkState() {
		if (this.getBooking() == null) {
			throw new IllegalStateException("Booking required.");
		}
		
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required.");
		}
		
		if (this.getDate() == null) {
			throw new IllegalStateException("Date requried.");
		}
	}
}
