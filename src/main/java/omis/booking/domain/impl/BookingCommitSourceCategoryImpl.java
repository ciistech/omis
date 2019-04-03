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

import omis.booking.domain.BookingCommitSourceCategory;

/** Implementation of Booking commit source category.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 2, 2019)
 * @since OMIS 3.0*/
public class BookingCommitSourceCategoryImpl implements BookingCommitSourceCategory {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Boolean valid;
	
	/** Default constructor of booking commit source category. */
	public BookingCommitSourceCategoryImpl() { }
	
	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {	
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result;
		
		if (this == obj) {
			result = true;
		} else {
			if (!(obj instanceof BookingCommitSourceCategory)) {
				result = false;
			} else {
				BookingCommitSourceCategory that = 
						(BookingCommitSourceCategory) obj;
				this.checkState();
				
				result = (this.getName().equals(that.getName())
						&& this.getValid().equals(that.getValid()) ? true : 
							false);
			}
		}
		return result;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 14;
		this.checkState();
		hashCode = 29 * hashCode + this.getName().hashCode();
		hashCode = 29 * hashCode + this.getValid().hashCode();
		return hashCode;
	}
	
	/* Checks state. */
	private void checkState() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		
		if (this.getValid() == null) {
			throw new IllegalStateException("Valid required.");
		}
	}
}
