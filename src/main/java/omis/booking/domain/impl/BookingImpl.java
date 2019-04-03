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

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.booking.domain.Booking;
import omis.booking.domain.BookingAdmissionCategory;
import omis.booking.domain.BookingCommitSourceCategory;
import omis.offender.domain.Offender;
import omis.region.domain.County;
import omis.staff.domain.StaffAssignment;
import omis.supervision.domain.CorrectionalStatus;

/** Implementation of booking.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 2, 2019)
 * @since OMIS 3.0 */
public class BookingImpl implements Booking {
	private static final long serialVersionUID = 1L;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	private Offender offender;
	private Long id;
	private Integer bookingNumber;
	private Date date;
	private BookingAdmissionCategory category;
	private BookingCommitSourceCategory commitSource;
	private StaffAssignment transportOfficer;
	private CorrectionalStatus correctionalStatus;
	private County county;
	
	/** Default constructor. */
	public BookingImpl() { }
	
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
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setBookingNumber(final Integer bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getBookingNumber() {
		return this.bookingNumber;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final BookingAdmissionCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public BookingAdmissionCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCommitSource(final BookingCommitSourceCategory commitSource) {
		this.commitSource = commitSource;
	}

	/** {@inheritDoc} */
	@Override
	public BookingCommitSourceCategory getCommitSource() {
		return this.commitSource;
	}

	/** {@inheritDoc} */
	@Override
	public void setTransportOfficer(final StaffAssignment transportOfficer) {
		this.transportOfficer = transportOfficer;
	}

	/** {@inheritDoc} */
	@Override
	public StaffAssignment getTransportOfficer() {
		return transportOfficer;
	}

	/** {@inheritDoc} */
	@Override
	public void setCorrectionalStatus(final CorrectionalStatus correctionalStatus) {
		this.correctionalStatus = correctionalStatus;
	}

	/** {@inheritDoc} */
	@Override
	public CorrectionalStatus getCorrectionalStatus() {
		return correctionalStatus;
	}

	/** {@inheritDoc} */
	@Override
	public void setCounty(final County county) {
		this.county = county;
	}

	/** {@inheritDoc} */
	@Override
	public County getCounty() {
		return this.county;
	}

}
