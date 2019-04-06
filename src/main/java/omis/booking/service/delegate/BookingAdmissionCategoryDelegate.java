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

import java.util.List;

import omis.booking.dao.BookingAdmissionCategoryDao;
import omis.booking.domain.BookingAdmissionCategory;

/** Delegate for booking admission category operations.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 5, 2019)
 * @since OMIS 3.0 */
public class BookingAdmissionCategoryDelegate {
	private final BookingAdmissionCategoryDao bookingAdmissionCategoryDao;
	
	/** Constructor.
	 * @param bookingAdmissionCategoryDao - booking admission category doa. */
	public BookingAdmissionCategoryDelegate(
			final BookingAdmissionCategoryDao bookingAdmissionCategoryDao) {
		this.bookingAdmissionCategoryDao = bookingAdmissionCategoryDao;
	}
	
	/** Finds booking admission categories.
	 * @return list of booking admission categories. */
	public List<BookingAdmissionCategory> findAll() {
		return this.bookingAdmissionCategoryDao.findAll();
	}
}
