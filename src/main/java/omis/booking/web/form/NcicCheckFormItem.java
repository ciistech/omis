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

import omis.ncic.domain.NcicCheckResult;

/** Form for ncic check items.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 10, 2019)
 * @since OMIS 3.0 */
public class NcicCheckFormItem {
	private NcicCheckFormItemOperation itemOperation;
	private Date date;
	private NcicCheckResult result;
	
	/** Constructor. */
	public NcicCheckFormItem() {}
	
	/** Gets item operation.
	 * @return item operation. */
	public NcicCheckFormItemOperation getItemOperation() {
		return this.itemOperation;
	}
	
	/** Sets item operation.
	 * @param itemOperation - ncic check form item operation. */
	public void setItemOperation(final NcicCheckFormItemOperation itemOperation) {
		this.itemOperation = itemOperation;
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
	
	
	/** Gets ncic check result.
	 * @return result. */
	public NcicCheckResult getResult() {
		return this.result;
	}
	
	/** Sets ncic check result.
	 * @param result - ncic check result. */
	public void setResult(final NcicCheckResult result) {
		this.result = result;
	}
}
