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

import omis.booking.dao.BookingCommitSourceCategoryDao;
import omis.booking.domain.BookingCommitSourceCategory;

/** Delegate for booking commit source categories.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 5, 2019)
 * @since OMIS 3.0 */
public class BookingCommitSourceCategoryDelegate {
	private final BookingCommitSourceCategoryDao 
		bookingCommitSourceCategoryDao;
	
	/** Constructor.
	 * @param bookingCommitSourceCategoryDao - booking commit source 
	 * category. */
	public BookingCommitSourceCategoryDelegate(
			final BookingCommitSourceCategoryDao 
			bookingCommitSourceCategoryDao) {
		this.bookingCommitSourceCategoryDao = bookingCommitSourceCategoryDao;
	}
	
	/** Find all booking commit source categories.
	 * @return booking commit source categories. */
	public List<BookingCommitSourceCategory> findAll() {
		return this.bookingCommitSourceCategoryDao.findAll();
	}
}
