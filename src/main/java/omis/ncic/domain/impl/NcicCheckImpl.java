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
package omis.ncic.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.booking.domain.Booking;
import omis.ncic.domain.NcicCheck;
import omis.ncic.domain.NcicCheckResult;
import omis.offender.domain.Offender;

/** Implementation of ncic check.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 4, 2019)
 * @since OMIS 3.0 */
public class NcicCheckImpl implements NcicCheck {
	public static final long serialVersionUID = 1L;
	private Long id;
	private Offender offender;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	private Booking booking;
	private Date date;
	private NcicCheckResult result;
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public Booking getBooking() {
		return this.booking;
	}

	/** {@inheritDoc} */
	@Override
	public void setBooking(final Booking booking) {
		this.booking = booking;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public NcicCheckResult getResult() {
		return this.result;
	}

	/** {@inheritDoc} */
	@Override
	public void setResult(NcicCheckResult result) {
		this.result = result;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result;
		if (this == obj) {
			result = true;
		} else {
			if (!(obj instanceof NcicCheck)) {
				result = false;
			} else {
				NcicCheck that = (NcicCheck) obj;
				this.checkState();
				
				result = (this.getOffender().equals(that.getOffender())
						&& this.getDate().equals(that.getDate())
						&& this.getResult().equals(that.getResult()) ? true
						: false);
			}
		}
		return result;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 14;
		this.checkState();
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getResult().hashCode();
		
		return hashCode;
	}
	
	/* Checks state. */
	private void checkState() {
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		
		if (this.getResult() == null) {
			throw new IllegalStateException("Result required");
		}
	}
}
