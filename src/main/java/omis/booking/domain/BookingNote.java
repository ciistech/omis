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
package omis.booking.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/** Booking note.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 2, 2019)
 * @since OMIS 3.0 */
public interface BookingNote extends Creatable, Updatable {
	/** Sets ID of booking note.
	 * @param id - id of booking note. */
	void setId(Long id);
	
	/** Gets ID of booking note.
	 * @return booking note id. */
	Long getId();
	
	/** Sets booking of booking note.
	 * @param bookingNote - booking note. */
	void setBooking(Booking booking);
	
	/** Gets booking of booking note.
	 * @return booking note. */
	Booking getBooking();
	
	/** Sets description of note.
	 * @param description - description of note. */
	void setDescription(String description);
	
	/** Gets description of note.
	 * @return description. */
	String getDescription();
	
	/** Sets date of note.
	 * @param date - date of note. */
	void setDate(Date date);
	
	/** Gets date of note.
	 * @return date. */
	Date getDate();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}
