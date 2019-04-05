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
package omis.booking.dao;

import java.util.Date;

import omis.booking.domain.Booking;
import omis.dao.GenericDao;
import omis.offender.domain.Offender;

/** Data access object for Booking.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 2, 2019)
 * @since OMIS 3.0 */
public interface BookingDao extends GenericDao<Booking> {
	
	/** Finds booking by offender, date and booking number.
	 * @param offender - offender.
	 * @param date - date.
	 * @param bookingNumber - booking number.
	 * @return booking. */
	Booking findByOffenderDateAndBookingNumber(Offender offender, Date date,
			Integer bookingNumber);
	
	/** Finds booking by offender, date and booking number excluding.
	 * @param offender - offender.
	 * @param date - date.
	 * @param bookingNumber - booking number.
	 * @param excluding - excluding booking.
	 * @return booking. */
	Booking findByOffenderDateAndBookingNumberExcluding(Offender offender, 
			Date date, Integer bookingNumber, Booking excluding);
}
