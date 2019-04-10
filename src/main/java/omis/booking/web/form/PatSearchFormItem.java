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

import omis.staff.domain.StaffAssignment;

/** Form for pat searches.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 9, 2019)
 * @since OMIS 3.0 */
public class PatSearchFormItem {
	private PatSearchItemOperation itemOperation;
	private StaffAssignment officer;
	private Date date;
	
	/** Gets item operation.
	 * @return Item operation. */
	public PatSearchItemOperation getItemOperation() {
		return this.itemOperation;
	}
	
	/** Sets item operation.
	 * @param itemOperation - Pat search item operation. */
	public void setItemOperation(final PatSearchItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	/** Gets officer.
	 * @return Officer. */
	public StaffAssignment getOfficer() {
		return this.officer;
	}
	
	/** Sets officer.
	 * @param officer - staff assignment. */
	public void setOfficer(final StaffAssignment officer) {
		this.officer = officer;
	}
	
	/** Gets date.
	 * @return Date. */
	public Date getDate() {
		return this.date; 
	}
	
	/** Sets date.
	 * @param date - date. */
	public void setDate(final Date date) {
		this.date = date;
	}
	
	
}
