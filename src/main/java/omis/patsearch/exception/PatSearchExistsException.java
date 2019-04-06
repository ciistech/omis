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
package omis.patsearch.exception;

import omis.exception.DuplicateEntityFoundException;
import omis.patsearch.domain.PatSearch;

/** Thrown when pat search exists.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 4, 2019)
 * @since OMIS 3.0 */
public class PatSearchExistsException extends DuplicateEntityFoundException {
	public static final long serialVersionUID = 1L;
	private final PatSearch patSearch;
	
	/** Constructor.
	 * @param patSearch - pat search. */
	public PatSearchExistsException(final PatSearch patSearch) {
		this.patSearch = patSearch;
	}
	
	/** Constructor.
	 * @param message - message.
	 * @param cause - cause.
	 * @param patSearch - pat search. */
	public PatSearchExistsException(final String message, 
			final Throwable cause, final PatSearch patSearch) {
		super(message, cause);
		this.patSearch = patSearch;
	}
	
	/** Constructor.
	 * @param message - message. */
	public PatSearchExistsException(final String message) {
		super(message);
		this.patSearch = null;
	}
	
	/** Constructor.
	 * @param cause - cause. */
	public PatSearchExistsException(final Throwable cause) {
		super(cause);
		this.patSearch = null;
	}
	
	/** Gets pat search.
	 * @return pat search. */
	public PatSearch getPatSearch() {
		return this.patSearch; 
	}
}
