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
package omis.patsearch.dao;

import java.util.Date;
import java.util.List;

import omis.booking.domain.Booking;
import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.patsearch.domain.PatSearch;
import omis.staff.domain.StaffAssignment;

/** Data access object for pat search.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 4, 2019)
 * @since OMIS 3.0 */
public interface PatSearchDao extends GenericDao<PatSearch> {
	/** Find by Offender Date and officer.
	 * @param offender - offender.
	 * @param date - date.
	 * @param officer - officer
	 * @return pat search. */
	PatSearch findByOffenderDateAndOfficer(Offender offender, Date date, 
			StaffAssignment officer);
	
	/** Finds pat searches by booking.
	 * @param booking - booking.
	 * @return pat searches. */
	List<PatSearch> findByBooking(Booking booking);
}
