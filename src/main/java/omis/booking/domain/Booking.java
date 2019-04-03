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
import omis.offender.domain.OffenderAssociable;
import omis.region.domain.County;
import omis.staff.domain.StaffAssignment;
import omis.supervision.domain.CorrectionalStatus;

/** Booking
 * @author Ryan Johns
 * @version 0.1.0 (Apr 2, 2019)
 * @since OMIS 3.0 */
public interface Booking extends Creatable, Updatable, OffenderAssociable {
	/** Sets ID of booking.
	 * @param id - id of booking.*/
	void setId(Long id);
	
	/** Returns ID of booking.
	 * @return booking id. */
	Long getId();
	
	/** Sets booking number of booking.
	 * @param bookingNumber - number of booking. */
	void setBookingNumber(Integer bookingNumber);
	
	/** Returns booking number of booking.
	 * @return booking number. */
	Integer getBookingNumber();
	
	/** Sets date of booking.
	 * @param date - date of booking. */
	void setDate(Date date);
	
	/** Returns date of booking.
	 * @return date of booking. */
	Date getDate();
	
	/** Sets category of booking.
	 * @param category - booking admission category. */
	void setCategory(BookingAdmissionCategory category);
	
	/** Gets category of booking.
	 * @return booking category. */
	BookingAdmissionCategory getCategory();
	
	/** Sets commit source of booking.
	 * @param commitSource booking commit source. */
	void setCommitSource(BookingCommitSourceCategory commitSource);
	
	/** Gets commit source of booking.
	 * @return booking commit source. */
	BookingCommitSourceCategory getCommitSource();
	
	/** Sets transport officer of booking.
	 * @param transportOfficer booking transport officer. */
	void setTransportOfficer(StaffAssignment transportOfficer);
	
	/** Gets transport officer of booking.
	 * @return booking transport officer. */
	StaffAssignment getTransportOfficer();
	
	/** Sets correctional status of booking.
	 * @param correctionalStatus correctional status of booking. */
	void setCorrectionalStatus(CorrectionalStatus correctionalStatus);
	
	/** Gets correctional status of booking.
	 * @return booking correctional status. */
	CorrectionalStatus getCorrectionalStatus();
	
	/** sets county of booking.
	 * @param county booking county. */
	void setCounty(County county);
	
	/** Gets county.
	 * @return booking county. */
	County getCounty();
}
