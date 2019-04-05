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
package omis.patsearch.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.booking.domain.Booking;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.patsearch.dao.PatSearchDao;
import omis.patsearch.domain.PatSearch;
import omis.patsearch.exception.PatSearchExistsException;
import omis.staff.domain.StaffAssignment;

/** Delegate for pat search related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 4, 2019)
 * @since OMIS 3.0 */
public class PatSearchDelegate {
	private static final String PAT_SEARCH_EXISTS_EXCEPTION_MESSAGE 
		= "Pat search exists with give offender date and officer.";
	
	private final PatSearchDao patSearchDao;
	private final InstanceFactory<PatSearch> patSearchInstanceFactory;
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Constructor.
	 * @param patSearchDao - pat search dao. 
	 * @param patSearchInstanceFactory - pat search instance factory. 
	 * @param auditComponenetRetreiver - audit component retriever. */
	public PatSearchDelegate(final PatSearchDao patSearchDao,
			final InstanceFactory<PatSearch> patSearchInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.patSearchDao = patSearchDao;
		this.patSearchInstanceFactory = patSearchInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** Create pat search. 
	 * @param offender - offender.
	 * @param date - date.
	 * @param officer - staff assignment.
	 * @param booking - booking.
	 * @throws PatSearchExistsException - when pat search exists. */
	public PatSearch createPatSearch(final Offender offender, final Date date, 
			final StaffAssignment officer, final Booking booking) 
			throws PatSearchExistsException {
		final PatSearch result;	
		if (this.patSearchDao.findByOffenderDateAndOfficer(offender, date, 
				officer) != null) {
			throw new PatSearchExistsException(PAT_SEARCH_EXISTS_EXCEPTION_MESSAGE);
		} else {
			result = this.populatePatSearch(
					this.patSearchInstanceFactory.createInstance(), booking, date, 
					offender, officer);
			
			result.setCreationSignature(new CreationSignature(
					this.auditComponentRetriever.retrieveUserAccount(), 
					this.auditComponentRetriever.retrieveDate()));
			
			result.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
		
			return this.patSearchDao.makePersistent(result);
		}
	}

	/** Update pat search.
	 * @param patSearch - pat search.
	 * @param date - date.
	 * @param officer - staff assignment.
	 * @throws PatSearchExistsException - when pat search exists. */
	public PatSearch updatePatSearch(final PatSearch patSearch, final Date date,
			final StaffAssignment officer) throws PatSearchExistsException {
		if (this.patSearchDao.findByOffenderDateAndOfficerExcluding(
				patSearch.getOffender(), date, officer, patSearch) != null) {
			throw new PatSearchExistsException(PAT_SEARCH_EXISTS_EXCEPTION_MESSAGE);
		} else {
			this.populatePatSearch(patSearch, patSearch.getBooking(), date, 
					patSearch.getOffender(), officer);
			
			patSearch.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(), 
					this.auditComponentRetriever.retrieveDate()));
		
			return this.patSearchDao.makePersistent(patSearch);
		}
	}
	
	
	/** Removes pat search.
	 * @param patSearch - pat search. */
	public void removePatSearch(final PatSearch patSearch) {
		this.patSearchDao.makeTransient(patSearch);
	}
	
	/** Finds pat searches by booking.
	 * @param booking - booking
	 * @return list of pat searches. */
	public List<PatSearch> findByBooking(final Booking booking) {
		return this.patSearchDao.findByBooking(booking);
	}

	/* Populate pat search. */
	private PatSearch populatePatSearch(final PatSearch patSearch, 
			final Booking booking, final Date date, final Offender offender,
			final StaffAssignment officer) {
		patSearch.setBooking(booking);
		patSearch.setDate(date);
		patSearch.setOffender(offender);
		patSearch.setOfficer(officer);
		return patSearch;
	}
}
