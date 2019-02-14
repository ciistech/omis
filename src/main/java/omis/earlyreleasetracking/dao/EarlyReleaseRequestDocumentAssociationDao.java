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
package omis.earlyreleasetracking.dao;

import java.util.List;
import omis.dao.GenericDao;
import omis.document.domain.Document;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestDocumentAssociation;

/**
 * Early Release Request Document Association Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public interface EarlyReleaseRequestDocumentAssociationDao
		extends GenericDao<EarlyReleaseRequestDocumentAssociation> {
	
	/**
	 * Finds a Early Release Request Document Association by the specified
	 * properties.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @param document Document
	 * @return Early Release Request Document Association found by the specified
	 * properties.
	 */
	EarlyReleaseRequestDocumentAssociation find(
			EarlyReleaseRequest earlyReleaseRequest, Document document);
	
	/**
	 * Finds a Early Release Request Document Association by the specified
	 * properties excluding the specified Early Release Request
	 * Document Association.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @param document Document
	 * @param earlyReleaseRequestDocumentAssociationExcluding Early Release
	 * Request Document Association to exclude
	 * @return Early Release Request Document Association found by the specified
	 * properties excluding the specified Early Release Request
	 * Document Association.
	 */
	EarlyReleaseRequestDocumentAssociation findExcluding(
			EarlyReleaseRequest earlyReleaseRequest, Document document,
			EarlyReleaseRequestDocumentAssociation
				earlyReleaseRequestDocumentAssociationExcluding);
	
	/**
	 * Returns a list of Early Release Request Document Associations for the
	 * specified Early Release Request.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @return List of Early Release Request Document Associations for the
	 * specified Early Release Request.
	 */
	List<EarlyReleaseRequestDocumentAssociation> findByEarlyReleaseRequest(
			EarlyReleaseRequest earlyReleaseRequest);
}
