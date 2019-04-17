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
package omis.booking.web.form;

import java.util.Date;
import java.util.List;

import omis.booking.domain.BookingAdmissionCategory;
import omis.booking.domain.BookingCommitSourceCategory;
import omis.offender.domain.Offender;
import omis.region.domain.County;
import omis.staff.domain.StaffAssignment;
import omis.supervision.domain.CorrectionalStatus;

/** Form for booking record.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 8, 2019)
 * @since OMIS 3.0 */
public class BookingForm {
	private Integer bookingNumber;
	private Offender offender;
	private Date date;
	private BookingAdmissionCategory category;
	private BookingCommitSourceCategory commitSource;
	private StaffAssignment transportOfficer;
	private CorrectionalStatus correctionalStatus;
	private County county;
	private List<BookingNoteFormItem> bookingNoteItems;
	
	/** Constructor. */
	public BookingForm() { }
	
	/** Gets booking number.
	 * @return booking number. */
	public Integer getBookingNumber() {
		return this.bookingNumber;
	}
	
	/** Sets booking number.
	 * @param bookingNumber - booking number. */
	public void setBookingNumber(final Integer bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	/** Gets offender.
	 * @return offender. */
	public Offender getOffender() {
		return this.offender;
	}
	
	/** Sets offender.
	 * @param offender - offender. */
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}
	
	/** Gets date.
	 * @return date. */
	public Date getDate() {
		return this.date;
	}
	
	/** Sets date.
	 * @param date - date. */
	public void setDate(final Date date) {
		this.date = date;
	}

	/** Gets category.
	 * @return booking admission category. */
	public BookingAdmissionCategory getCategory() {
		return this.category;
	}
	
	/** Sets category.
	 * @param category - booking admission category. */
	public void setCategory(final BookingAdmissionCategory category) {
		this.category = category;
	}
	

	/** Gets commit source.
	 * @return booking commit source category. */
	public BookingCommitSourceCategory getCommitSource() {
		return this.commitSource;
	}
	
	/** Sets commit source.
	 * @param commitSource - booking commit source category. */
	public void setCommitSource(
			final BookingCommitSourceCategory commitSource) {
		this.commitSource = commitSource;
	}

	/** Gets transport officer.
	 * @return transport staff assignment. */
	public StaffAssignment getTransportOfficer() {
		return this.transportOfficer;
	}
	
	/** Sets transport officer.
	 * @param transportOfficer - transport staff assignment. */
	public void setTransportOfficer(
			final StaffAssignment transportOfficer) {
		this.transportOfficer = transportOfficer;
	}

	/** Gets Correctional status.
	 * @return correctional status. */
	public CorrectionalStatus getCorrectionalStatus() {
		return this.correctionalStatus;
	}
	
	/** Sets correctional status.
	 * @param correctionalStatus - correctional status. */
	public void setCorrectionalStatus(
			final CorrectionalStatus correctionalStatus) {
		this.correctionalStatus = correctionalStatus;
	}
	
	/** Gets county.
	 * @return county. */
	public County getCounty() {
		return this.county;
	}
	
	/** Sets County.
	 * @param county - county. */
	public void setCounty(final County county) {
		this.county = county;
	}
	
	/** Sets booking note form items.
	 * @param bookingNotes - booking note form items. */
	public void setBookingNoteItems(
			final List<BookingNoteFormItem> bookingNotesItems) {
		this.bookingNoteItems = bookingNotesItems;
	}
	
	/** Gets booking note form items.
	 * @return booking note form items. */
	public List<BookingNoteFormItem> getBookingNoteItems() {
		return this.bookingNoteItems;
	}
}
