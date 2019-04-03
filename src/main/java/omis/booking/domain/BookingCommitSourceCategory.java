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

import java.io.Serializable;

/** Booking Commitment source.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 2, 2019)
 * @since OMIS 3.0 */
public interface BookingCommitSourceCategory extends Serializable {
	/** Sets Id of booking commit source category.
	 * @param id booking commit source category id. */
	void setId(Long id);
	
	/** Gets id of booking commit source category.
	 * @return booking commit source id. */
	Long getId();
	
	/** Sets name of booking commit source category.
	 * @param name booking commit source category name. */
	void setName(String name);
	
	/** Gets name of booking commit source category.
	 * @return booking commit source name. */
	String getName();
	
	/** Sets valid of booking commit source category.
	 * @param valid booking commit source category valid. */
	void setValid(Boolean valid);
	
	/** Gets valid of booking commit source category.
	 * @return booking commit source valid. */
	Boolean getValid();
}
