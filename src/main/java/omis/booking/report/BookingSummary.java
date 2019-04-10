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
package omis.booking.report;

import java.util.Date;

/** Summary for booking.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 10, 2019)
 * @since OMIS 3.0 */
public class BookingSummary {
	private final Long id;
	private final Date date;
	private final String countyName;
	private final Integer bookingNumber;
	
	/** Constructor.
	 * @param id - id.
	 * @param date - date.
	 * @param countyName - county name.
	 * @param bookingNumber - booking number. */
	public BookingSummary(final Long id, final Date date, 
			final String countyName, final Integer bookingNumber) {
		this.id = id;
		this.date = date;
		this.countyName = countyName;
		this.bookingNumber = bookingNumber;
	}
	
	/** Gets id.
	 * @return id. */
	public Long getId() {
		return this.id;
	}
	
	/** Gets date.
	 * @return date. */
	public Date getDate() { 
		return this.date;
	}
	
	/** Gets county name.
	 * @return county name. */
	public String getCountyName() {
		return this.countyName;
	}
	
	/** Gets booking number.
	 * @return booking number. */
	public Integer getBookingNumber() {
		return this.bookingNumber;
	}
}
