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
package omis.presentenceinvestigation.service.impl;

import java.util.Date;
import java.util.List;

import omis.court.domain.Court;
import omis.court.service.delegate.CourtDelegate;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.docket.service.delegate.DocketDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.domain.Suffix;
import omis.person.service.delegate.PersonDelegate;
import omis.person.service.delegate.SuffixDelegate;
import omis.presentenceinvestigation.domain.PresentenceInvestigationCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationDelay;
import omis.presentenceinvestigation.domain.PresentenceInvestigationDelayCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationDocketAssociation;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequestNote;
import omis.presentenceinvestigation.service.PresentenceInvestigationRequestService;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationCategoryDelegate;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationDelayCategoryDelegate;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationDelayDelegate;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationDocketAssociationDelegate;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationRequestDelegate;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationRequestNoteDelegate;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;

/** 
 * Implementation of presentence investigation request service.
 * 
 * @author Ryan Johns
 * @author Annie Wahl
 * @author Josh Divine
 * @version 0.1.5 (oct 25, 2018)
 * @since OMIS 3.0
 */
public class PresentenceInvestigationRequestServiceImpl 
	implements PresentenceInvestigationRequestService {
	
	private final PresentenceInvestigationRequestDelegate 
		presentenceInvestigationRequestDelegate;
	
	private final PresentenceInvestigationRequestNoteDelegate
		presentenceInvestigationRequestNoteDelegate;
	
	private final PresentenceInvestigationCategoryDelegate
		presentenceInvestigationCategoryDelegate;
	
	private final PersonDelegate personDelegate;
	
	private final DocketDelegate docketDelegate;
	
	private final SuffixDelegate suffixDelegate;
	
	private final CourtDelegate courtDelegate;
	
	private final OffenderDelegate offenderDelegate;
	
	private final UserAccountDelegate userAccountDelegate;
	
	private final PresentenceInvestigationDelayDelegate 
			presentenceInvestigationDelayDelegate;
	
	private final PresentenceInvestigationDelayCategoryDelegate 
			presentenceInvestigationDelayCategoryDelegate;
	
	private final PresentenceInvestigationDocketAssociationDelegate 
			presentenceInvestigationDocketAssociationDelegate;
	
	/** Constructor.
	 * @param presentenceInvestigationRequestDelegate - presentence 
	 * investigation request delegate. */
	public PresentenceInvestigationRequestServiceImpl(
			final PresentenceInvestigationRequestDelegate 
				presentenceInvestigationRequestDelegate,
			final PresentenceInvestigationRequestNoteDelegate
				presentenceInvestigationRequestNoteDelegate,
			final PresentenceInvestigationCategoryDelegate
				presentenceInvestigationCategoryDelegate,
			final PersonDelegate personDelegate,
			final DocketDelegate docketDelegate,
			final SuffixDelegate suffixDelegate,
			final CourtDelegate courtDelegate,
			final OffenderDelegate offenderDelegate,
			final UserAccountDelegate userAccountDelegate,
			final PresentenceInvestigationDelayDelegate 
					presentenceInvestigationDelayDelegate,
			final PresentenceInvestigationDelayCategoryDelegate 
					presentenceInvestigationDelayCategoryDelegate,
			final PresentenceInvestigationDocketAssociationDelegate 
					presentenceInvestigationDocketAssociationDelegate) {
		this.presentenceInvestigationRequestDelegate 
			= presentenceInvestigationRequestDelegate;
		this.presentenceInvestigationRequestNoteDelegate =
				presentenceInvestigationRequestNoteDelegate;
		this.presentenceInvestigationCategoryDelegate =
				presentenceInvestigationCategoryDelegate;
		this.personDelegate = personDelegate;
		this.docketDelegate = docketDelegate;
		this.suffixDelegate = suffixDelegate;
		this.courtDelegate = courtDelegate;
		this.offenderDelegate = offenderDelegate;
		this.userAccountDelegate = userAccountDelegate;
		this.presentenceInvestigationDelayCategoryDelegate = 
				presentenceInvestigationDelayCategoryDelegate;
		this.presentenceInvestigationDelayDelegate = 
				presentenceInvestigationDelayDelegate;
		this.presentenceInvestigationDocketAssociationDelegate = 
				presentenceInvestigationDocketAssociationDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public PresentenceInvestigationRequest create(
			final UserAccount assignedUser, final Date requestDate, 
			final Date expectedCompletionDate, final Person person,
			final Date sentenceDate, 
			final PresentenceInvestigationCategory category, 
			final Date submissionDate) throws DuplicateEntityFoundException {
		return this.presentenceInvestigationRequestDelegate.create(assignedUser, 
				requestDate, expectedCompletionDate, person, sentenceDate, 
				category, submissionDate);
	}

	/** {@inheritDoc} */
	@Override
	public PresentenceInvestigationRequest update(
			final PresentenceInvestigationRequest 
				presentenceInvestigationRequest,
			final UserAccount assignedUser, final Date requestDate, 
			final Date expectedCompletionDate, final Person person, 
			final Date sentenceDate,
			final PresentenceInvestigationCategory category, 
			final Date submissionDate) throws DuplicateEntityFoundException {
		return this.presentenceInvestigationRequestDelegate.update(
				presentenceInvestigationRequest, assignedUser, requestDate,
				expectedCompletionDate, person, sentenceDate, category, 
				submissionDate);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(
			final PresentenceInvestigationRequest 
				presentenceInvestigationRequest) {
		this.presentenceInvestigationRequestDelegate.remove(
				presentenceInvestigationRequest);
	}
	
	/**{@inheritDoc} */
	@Override
	public Person createPerson(final String lastName, final String firstName,
			final String middleName, final String suffix)
					throws DuplicateEntityFoundException {
		return this.personDelegate.create(lastName, firstName, middleName, suffix);
	}


	/**{@inheritDoc} */
	@Override
	public Person updatePerson(final Person person, final String lastName,
			final String firstName, final String middleName, final String suffix)
					throws DuplicateEntityFoundException {
		return this.personDelegate.update(person, lastName, firstName,
				middleName, suffix);
	}

	/**{@inheritDoc} */
	@Override
	public void removePerson(final Person person) {
		this.personDelegate.remove(person);
	}

	/**{@inheritDoc} */
	@Override
	public Docket createDocket(final Person person, final Court court,
			final String value) throws DocketExistsException {
		return this.docketDelegate.create(person, court, value);
	}

	/**{@inheritDoc} */
	@Override
	public Docket updateDocket(final Docket docket, final Court court,
			final String value) throws DocketExistsException {
		return this.docketDelegate.update(docket, court, value);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDocket(final Docket docket) {
		this.docketDelegate.remove(docket);
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationRequestNote
		createPresentenceInvestigationRequestNote(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String description, final Date date)
					throws DuplicateEntityFoundException {
		return this.presentenceInvestigationRequestNoteDelegate.create(
				presentenceInvestigationRequest, description, date);
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationRequestNote
		updatePresentenceInvestigationRequestNote(
			final PresentenceInvestigationRequestNote
				presentenceInvestigationRequestNote, final String description,
			final Date date)
					throws DuplicateEntityFoundException {
		return this.presentenceInvestigationRequestNoteDelegate.update(
				presentenceInvestigationRequestNote, description, date);
	}

	/**{@inheritDoc} */
	@Override
	public void removePresentenceInvestigationRequestNote(
			final PresentenceInvestigationRequestNote
				presentenceInvestigationRequestNote) {
		this.presentenceInvestigationRequestNoteDelegate.remove(
				presentenceInvestigationRequestNote);
	}

	/**{@inheritDoc} */
	@Override
	public List<PresentenceInvestigationRequestNote>
		findPresentenceInvestigationRequestNotesByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		return this.presentenceInvestigationRequestNoteDelegate
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}

	/**{@inheritDoc} */
	@Override
	public List<Suffix> findSuffixes() {
		return this.suffixDelegate.findAll();
	}

	/**{@inheritDoc} */
	@Override
	public List<Court> findCourts() {
		return this.courtDelegate.findAllCourts();
	}

	/**{@inheritDoc} */
	@Override
	public Boolean isOffender(final Person person) {
		return this.offenderDelegate.isOffender(person);
	}
	
	/** {@inheritDoc} */
	@Override
	public UserAccount findUserAccountByUsername(final String username) {
		return this.userAccountDelegate.findByUsername(username);
	}

	/**{@inheritDoc} */
	@Override
	public List<PresentenceInvestigationCategory>
			findAllPresentenceInvestigationCategories() {
		return this.presentenceInvestigationCategoryDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public PresentenceInvestigationDelay createPresentenceInvestigationDelay(
			final PresentenceInvestigationRequest 
					presentenceInvestigationRequest, final Date date,
			final PresentenceInvestigationDelayCategory reason) 
					throws DuplicateEntityFoundException {
		return this.presentenceInvestigationDelayDelegate.create(
				presentenceInvestigationRequest, date, reason);
	}

	/** {@inheritDoc} */
	@Override
	public PresentenceInvestigationDelay updatePresentenceInvestigationDelay(
			final PresentenceInvestigationDelay presentenceInvestigationDelay, 
			final Date date, final PresentenceInvestigationDelayCategory reason) 
					throws DuplicateEntityFoundException {
		return this.presentenceInvestigationDelayDelegate.update(
				presentenceInvestigationDelay, 
				presentenceInvestigationDelay
					.getPresentenceInvestigationRequest(), date, reason);
	}

	/** {@inheritDoc} */
	@Override
	public void removePresentenceInvestigationDelay(
			final PresentenceInvestigationDelay presentenceInvestigationDelay) {
		this.presentenceInvestigationDelayDelegate.remove(
				presentenceInvestigationDelay);
	}

	/** {@inheritDoc} */
	@Override
	public List<PresentenceInvestigationDelayCategory> 
			findPresentenceInvestigationDelayCategories() {
		return this.presentenceInvestigationDelayCategoryDelegate
				.findPresentenceInvestigationDelayCategories();
	}

	/** {@inheritDoc} */
	@Override
	public List<PresentenceInvestigationDelay> 
			findPresentenceInvestigationDelays(
					final PresentenceInvestigationRequest 
							presentenceInvestigationRequest) {
		return this.presentenceInvestigationDelayDelegate
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}

	/** {@inheritDoc} */
	@Override
	public List<Docket> findDocketsByOffender(final Offender offender) {
		return this.docketDelegate.findByPerson(offender);
	}

	/** {@inheritDoc} */
	@Override
	public List<Docket> findDocketsByValue(final String value) {
		return this.docketDelegate.findByValue(value);
	}

	/** {@inheritDoc} */
	@Override
	public PresentenceInvestigationDocketAssociation 
			createPresentenceInvestigationDocketAssociation(
					final PresentenceInvestigationRequest 
						presentenceInvestigationRequest,
					final Docket docket) throws DuplicateEntityFoundException {
		return this.presentenceInvestigationDocketAssociationDelegate
				.create(
						presentenceInvestigationRequest, docket);
	}

	/** {@inheritDoc} */
	@Override
	public void removePresentenceInvestigationDocketAssociation(
			final PresentenceInvestigationDocketAssociation 
				presentenceInvestigationDocketAssociation) {
		this.presentenceInvestigationDocketAssociationDelegate
				.removePresentenceInvestigationDocketAssociation(
						presentenceInvestigationDocketAssociation);
	}

	/** {@inheritDoc} */
	@Override
	public List<PresentenceInvestigationDocketAssociation> 
			findPresentenceInvestigationDocketAssociationsByPresentenceInvestigationRequest(
					final PresentenceInvestigationRequest 
						presentenceInvestigationRequest) {
		return this.presentenceInvestigationDocketAssociationDelegate
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}
}