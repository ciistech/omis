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
package omis.supervision.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.supervision.domain.CorrectionalStatus;

/**
 * Data access object for correctional statuses.
 * 
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.1 (Apr 8, 2019)
 * @since OMIS 3.0
 */
public interface CorrectionalStatusDao
		extends GenericDao<CorrectionalStatus> {

	/**
	 * Returns correctional status.
	 * 
	 * @param name name
	 * @return correctional status
	 */
	CorrectionalStatus find(String name);
	
	/**
	 * Returns correctional statuses that are allowed to transfer from the 
	 * specified correctional status
	 * 
	 * @param fromCorrectionalStatus correctional status transferring from
	 * @return allowed correctional statuses
	 */
	List<CorrectionalStatus> findAllowedCorrectionalStatuses(
			CorrectionalStatus fromCorrectionalStatus);
	
	/** Returns correctional statuses.
	 * @return correctional statuses. */
	List<CorrectionalStatus> findAll();
}