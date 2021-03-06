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
package omis.earlyreleasetracking.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import omis.document.domain.Document;
import omis.document.web.form.DocumentTagItem;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestDocumentAssociation;

/**
 * Early Release Request Document Association Item.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 13, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestDocumentAssociationItem
					implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EarlyReleaseRequestDocumentAssociation
				earlyReleaseRequestDocumentAssociation;
	
	private Document document;
	
	private String title;
	
	private List<DocumentTagItem> documentTagItems =
			new ArrayList<DocumentTagItem>();
	
	private Date date;
	
	private String fileExtension;
	
	private byte[] data;
	
	private EarlyReleaseRequestItemOperation itemOperation;
	
	/**
	 * Default Constructor for Early Release Request Document Association Item.
	 */
	public EarlyReleaseRequestDocumentAssociationItem() {
	}

	/**
	 * Returns the document.
	 * @return document - Document
	 */
	public Document getDocument() {
		return this.document;
	}

	/**
	 * Sets the document.
	 * @param document - Document
	 */
	public void setDocument(final Document document) {
		this.document = document;
	}

	/**
	 * Returns the title.
	 * @return title - String
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 * @param title - String
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Returns the documentTagItems.
	 * @return documentTagItems - List<DocumentTagItem>
	 */
	public List<DocumentTagItem> getDocumentTagItems() {
		return this.documentTagItems;
	}

	/**
	 * Sets the documentTagItems.
	 * @param documentTagItems - List<DocumentTagItem>
	 */
	public void setDocumentTagItems(
			final List<DocumentTagItem> documentTagItems) {
		this.documentTagItems = documentTagItems;
	}

	/**
	 * Returns the date.
	 * @return date - Date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 * @param date - Date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the fileExtension.
	 * @return fileExtension - String
	 */
	public String getFileExtension() {
		return this.fileExtension;
	}

	/**
	 * Sets the fileExtension.
	 * @param fileExtension - String
	 */
	public void setFileExtension(final String fileExtension) {
		this.fileExtension = fileExtension;
	}

	/**
	 * Returns the data.
	 * @return data - byte[]
	 */
	public byte[] getData() {
		return this.data;
	}

	/**
	 * Sets the data.
	 * @param data - byte[]
	 */
	public void setData(final byte[] data) {
		this.data = data;
	}

	/**
	 * Returns the earlyReleaseRequestDocumentAssociation.
	 *
	 * @return earlyReleaseRequestDocumentAssociation
	 */
	public EarlyReleaseRequestDocumentAssociation
			getEarlyReleaseRequestDocumentAssociation() {
		return this.earlyReleaseRequestDocumentAssociation;
	}

	/**
	 * Sets the earlyReleaseRequestDocumentAssociation.
	 *
	 * @param earlyReleaseRequestDocumentAssociation -
	 * earlyReleaseRequestDocumentAssociation
	 */
	public void setEarlyReleaseRequestDocumentAssociation(
			final EarlyReleaseRequestDocumentAssociation
					earlyReleaseRequestDocumentAssociation) {
		this.earlyReleaseRequestDocumentAssociation =
				earlyReleaseRequestDocumentAssociation;
	}

	/**
	 * Returns the itemOperation.
	 *
	 * @return itemOperation
	 */
	public EarlyReleaseRequestItemOperation getItemOperation() {
		return this.itemOperation;
	}

	/**
	 * Sets the itemOperation.
	 *
	 * @param itemOperation - itemOperation
	 */
	public void setItemOperation(
			final EarlyReleaseRequestItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
}
