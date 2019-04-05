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
package omis.ncic.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.booking.domain.Booking;
import omis.instance.factory.InstanceFactory;
import omis.ncic.dao.NcicCheckDao;
import omis.ncic.dao.NcicCheckResultDao;
import omis.ncic.domain.NcicCheck;
import omis.ncic.domain.NcicCheckResult;
import omis.ncic.exception.NcicCheckExistsException;
import omis.offender.domain.Offender;

/** Delegate for NCIC search related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 4, 2019) */
public class NcicCheckDelegate {
	private static final String NCIC_CHECK_EXISTS_EXCEPTION_MESSAGE 
		= "NCIC check exists with given offender and date.";
	
	private final InstanceFactory<NcicCheck> ncicCheckInstanceFactory;
	private final NcicCheckDao ncicCheckDao;
	private final NcicCheckResultDao ncicCheckResultDao;
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Constructor.
	 * @param ncicCheckInstanceFactory - ncic check instance factory.
	 * @param ncicCheckDao - ncic check dao.
	 * @param ncicCheckResultDao - ncic check result dao.
	 * @param auditComponentRetriever - audiComponentRetriever; */
	public NcicCheckDelegate(final InstanceFactory<NcicCheck> ncicCheckInstanceFactory,
			final NcicCheckDao ncicCheckDao, 
			final NcicCheckResultDao ncicCheckResultDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.ncicCheckInstanceFactory = ncicCheckInstanceFactory;
		this.ncicCheckDao = ncicCheckDao;
		this.ncicCheckResultDao = ncicCheckResultDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** Creates NCIC Check.
	 * @param booking - booking.
	 * @param date - date.
	 * @param offender - offender.
	 * @param result - ncic check result.
	 * @return ncic check
	 * @throws NcicCheckExistsException - when ncic check exists. */
	public NcicCheck createNcicCheck(final Booking booking, final Date date,
			final Offender offender, final NcicCheckResult result) 
					throws NcicCheckExistsException {
		final NcicCheck ncicCheck;
		if (this.ncicCheckDao.findByOffenderAndDate(offender, date) != null) {
			throw new NcicCheckExistsException(NCIC_CHECK_EXISTS_EXCEPTION_MESSAGE);
		} else {
			ncicCheck = this.populateNcicCheck(this.ncicCheckInstanceFactory.createInstance(), 
					booking, date, offender, result);
			
			ncicCheck.setCreationSignature(new CreationSignature(
					this.auditComponentRetriever.retrieveUserAccount(), 
					this.auditComponentRetriever.retrieveDate()));
			
			ncicCheck.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
			
			return this.ncicCheckDao.makePersistent(ncicCheck);
		}
	}
	
	/** Update NCIC Check.
	 * @param ncicCheck - ncic check.
	 * @param date - date.
	 * @param result - ncic check result.
	 * @return ncic check.
	 * @throws NcicCheckExistsException - when ncic check exists. */
	public NcicCheck updateNcicCheck(final NcicCheck ncicCheck, 
			final Date date, final NcicCheckResult result) 
					throws NcicCheckExistsException {
		if (this.ncicCheckDao.findByOffenderAndDateExcluding(
				ncicCheck.getOffender(), date, ncicCheck) != null) {
			throw new NcicCheckExistsException(NCIC_CHECK_EXISTS_EXCEPTION_MESSAGE);
		} else {
			this.populateNcicCheck(ncicCheck, ncicCheck.getBooking(), date, 
					ncicCheck.getOffender(), result);
			
			ncicCheck.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
			
			this.ncicCheckDao.makePersistent(ncicCheck);
		}
		
		return ncicCheck;
	}
	
	/** Remove ncic checl.
	 * @param ncicCheck - ncic check. */
	public void removeNcicCheck(final NcicCheck ncicCheck) {
		this.ncicCheckDao.makeTransient(ncicCheck);
	}
	
	/** find ncic checks by booking.
	 * @param booking - booking
	 * @return ncic checks. */
	public List<NcicCheck> findByBooking(final Booking booking) {
		return this.ncicCheckDao.findByBooking(booking);
	}
	
	/** find ncic check results.
	 * @return ncic check results. */
	public List<NcicCheckResult> findNcicCheckResults() {
		return this.ncicCheckResultDao.findAll();
	}
	
	/* Populate NCIC Check. */
	private NcicCheck populateNcicCheck(final NcicCheck ncicCheck, 
			final Booking booking, final Date date, final Offender offender,
			final NcicCheckResult result) {
		ncicCheck.setBooking(booking);
		ncicCheck.setDate(date);
		ncicCheck.setOffender(offender);
		ncicCheck.setResult(result);
		
		return ncicCheck;
	}
}
