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
package omis.ncic.exception;

import omis.exception.DuplicateEntityFoundException;
import omis.ncic.domain.NcicCheck;

/** Thrown when ncic check exists.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 4, 2019)
 * @since OMIS 3.0 */
public class NcicCheckExistsException extends DuplicateEntityFoundException {
	public static final long serialVersionUID = 1L;
	private final NcicCheck ncicCheck;
	
	/** Constructor.
	 * @param ncicCheck - ncic check. */
	public NcicCheckExistsException(final NcicCheck ncicCheck) {
		this.ncicCheck = ncicCheck;
	}
	
	/** Constructor.
	 * @param message - message.
	 * @param cause - cause.
	 * @param ncicCheck - ncic check. */
	public NcicCheckExistsException(final String message,
			final Throwable cause, final NcicCheck ncicCheck) {
		super(message, cause);
		this.ncicCheck = ncicCheck;
	}
	
	/** Constructor.
	 * @param message - message. */
	public NcicCheckExistsException(final String message) {
		super(message);
		this.ncicCheck = null;
	}
	
	/** Constructor.
	 * @param cause - cause. */
	public NcicCheckExistsException(final Throwable cause) {
		super(cause);
		this.ncicCheck = null;
	}
	
	/** Gets ncic search.
	 * @return ncic search. */
	public NcicCheck getNcicCheck() {
		return this.ncicCheck;
	}
}
