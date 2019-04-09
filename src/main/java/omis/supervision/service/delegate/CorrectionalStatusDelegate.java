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
package omis.supervision.service.delegate;

import java.util.List;

import omis.instance.factory.InstanceFactory;
import omis.supervision.dao.CorrectionalStatusDao;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.exception.CorrectionalStatusExistsException;

/**
 * Delegate for correctional statuses.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 20, 2016)
 * @since OMIS 3.0
 */
public class CorrectionalStatusDelegate {
	
	private final InstanceFactory<CorrectionalStatus>
		correctionalStatusInstanceFactory;
	
	private final CorrectionalStatusDao correctionalStatusDao;

	/**
	 * Instantiates delegate for correctional statuses.
	 * 
	 * @param correctionalStatusInstanceFactory instance factory for
	 * correctional statuses
	 * @param correctionalStatusDao data access object for correctional statuses
	 */
	public CorrectionalStatusDelegate(
			final InstanceFactory<CorrectionalStatus>
				correctionalStatusInstanceFactory,
			final CorrectionalStatusDao correctionalStatusDao) {
		this.correctionalStatusInstanceFactory
			= correctionalStatusInstanceFactory;
		this.correctionalStatusDao = correctionalStatusDao;
	}
	
	/**
	 * Returns correctional status.
	 * 
	 * <p>If correctional status does not exists, creates it.
	 * 
	 * @param name name
	 * @param abbreviation abbreviation
	 * @param locationRequired whether location is required
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return correctional status
	 */
	public CorrectionalStatus findOrCreate(
			final String name, final String abbreviation,
			final Boolean locationRequired, final Short sortOrder,
			final Boolean valid) {
		CorrectionalStatus correctionalStatus
			= this.correctionalStatusDao.find(name);
		if (correctionalStatus != null) {
			return correctionalStatus;
		} else {
			return this.createImpl(
					name, abbreviation, locationRequired, sortOrder, valid);
		}
	}

	/**
	 * Creates correctional status.
	 * 
	 * @param name name
	 * @param abbreviation abbreviation
	 * @param locationRequired whether location is required
	 * @param sortOrder sort order
	 * @param valid whether valid
	 * @return correctional status
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 */
	public CorrectionalStatus create(final String name,
			final String abbreviation, final Boolean locationRequired,
			final Short sortOrder, final Boolean valid)
					throws CorrectionalStatusExistsException {
		if (this.correctionalStatusDao.find(name) != null) {
			throw new CorrectionalStatusExistsException(
					"Correctional status exists");
		}
		return this.createImpl(
				name, abbreviation, locationRequired, sortOrder, valid);
	}
	
	/** Finds all correctional statuses.
	 * @return correctional status. */
	public List<CorrectionalStatus> findAllCorrectionalStatuses() {
		return this.correctionalStatusDao.findAll();
	}
	
	// Implementation to create correctional status
	private CorrectionalStatus createImpl(final String name,
			final String abbreviation, final Boolean locationRequired,
			final Short sortOrder, final Boolean valid) {
		CorrectionalStatus correctionalStatus
				= this.correctionalStatusInstanceFactory
					.createInstance();
		correctionalStatus.setName(name);
		correctionalStatus.setAbbreviation(abbreviation);
		correctionalStatus.setLocationRequired(locationRequired);
		correctionalStatus.setSortOrder(sortOrder);
		correctionalStatus.setValid(valid);
		return this.correctionalStatusDao.makePersistent(correctionalStatus);
	}
	
}