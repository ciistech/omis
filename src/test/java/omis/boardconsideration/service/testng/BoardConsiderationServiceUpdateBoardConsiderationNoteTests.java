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
package omis.boardconsideration.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.boardconsideration.domain.BoardConsiderationNote;
import omis.boardconsideration.service.BoardConsiderationService;
import omis.boardconsideration.service.delegate.BoardConsiderationNoteDelegate;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.hearinganalysis.domain.HearingAnalysis;
import omis.hearinganalysis.domain.HearingAnalysisCategory;
import omis.hearinganalysis.service.delegate.HearingAnalysisCategoryDelegate;
import omis.hearinganalysis.service.delegate.HearingAnalysisDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.paroleboardmember.domain.ParoleBoardMember;
import omis.paroleboardmember.service.delegate.ParoleBoardMemberDelegate;
import omis.paroleeligibility.domain.AppearanceCategory;
import omis.paroleeligibility.domain.EligibilityStatusCategory;
import omis.paroleeligibility.domain.EligibilityStatusReason;
import omis.paroleeligibility.domain.ParoleEligibility;
import omis.paroleeligibility.domain.component.ParoleEligibilityStatus;
import omis.paroleeligibility.service.delegate.AppearanceCategoryDelegate;
import omis.paroleeligibility.service.delegate.EligibilityStatusReasonDelegate;
import omis.paroleeligibility.service.delegate.ParoleEligibilityDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.staff.domain.StaffAssignment;
import omis.staff.domain.StaffTitle;
import omis.staff.service.delegate.StaffAssignmentDelegate;
import omis.staff.service.delegate.StaffTitleDelegate;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.service.delegate.SupervisoryOrganizationDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update board consideration notes.
 *
 * @author Josh Divine
 * @version 0.1.1 (Dec 3, 2018)
 * @since OMIS 3.0
 */
@Test
public class BoardConsiderationServiceUpdateBoardConsiderationNoteTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	private BoardConsiderationNoteDelegate boardConsiderationNoteDelegate;

	@Autowired
	private HearingAnalysisDelegate hearingAnalysisDelegate;

	@Autowired
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	private AppearanceCategoryDelegate appearanceCategoryDelegate;
	
	@Autowired
	private EligibilityStatusReasonDelegate eligibilityStatusReasonDelegate;
	
	@Autowired
	private ParoleEligibilityDelegate paroleEligibilityDelegate;
	
	@Autowired
	private StaffAssignmentDelegate staffAssignmentDelegate;
	
	@Autowired
	private PersonDelegate personDelegate;
	
	@Autowired
	private SupervisoryOrganizationDelegate supervisoryOrganizationDelegate;
	
	@Autowired
	private StaffTitleDelegate staffTitleDelegate;
	
	@Autowired
	private ParoleBoardMemberDelegate paroleBoardMemberDelegate;
	
	@Autowired
	private HearingAnalysisCategoryDelegate hearingAnalysisCategoryDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("boardConsiderationService")
	private BoardConsiderationService boardConsiderationService;

	/* Test methods. */

	/**
	 * Tests the update of the description for a board consideration note.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if parole board member date range not 
	 * within staff assignment date range
	 */
	@Test
	public void testUpdateBoardConsiderationNoteDescription() 
			throws DuplicateEntityFoundException, DateConflictException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		EligibilityStatusReason statusReason = eligibilityStatusReasonDelegate
				.create("Reason", EligibilityStatusCategory.APPEARING, true);
		ParoleEligibilityStatus paroleEligibilityStatus = 
				new ParoleEligibilityStatus(this.parseDateText("01/01/2017"), 
						"Comment", EligibilityStatusCategory.APPEARING, 
						statusReason);
		AppearanceCategory appearanceCategory = this.appearanceCategoryDelegate
				.create("Category", true);
		ParoleEligibility eligibility = this.paroleEligibilityDelegate.create(
				offender, this.parseDateText("01/01/2017"), 
				this.parseDateText("01/01/2017"), paroleEligibilityStatus, 
				appearanceCategory);
		Person staffMember = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		SupervisoryOrganization supervisoryOrganization = this
				.supervisoryOrganizationDelegate.create("SupOrg", "SO", null);
		StaffTitle staffTitle = this.staffTitleDelegate.create("Title", 
				(short) 1, true);
		StaffAssignment staffAssignment = this.staffAssignmentDelegate.create(
				staffMember, supervisoryOrganization, null, 
				new DateRange(this.parseDateText("01/01/2017"), null), 
				staffTitle, true, null, null, null, null, null);
		ParoleBoardMember boardMember = this.paroleBoardMemberDelegate.create(
				staffAssignment, 
				new DateRange(this.parseDateText("01/01/2017"), null));
		HearingAnalysisCategory analysisCategory = this
				.hearingAnalysisCategoryDelegate.create("Category", true);
		HearingAnalysis hearingAnalysis = this.hearingAnalysisDelegate.create(
				eligibility, analysisCategory, boardMember, null);
		String description = "Description";
		Date date = this.parseDateText("05/30/2018");
		BoardConsiderationNote boardConsiderationNote = this
				.boardConsiderationNoteDelegate.create(hearingAnalysis, 
						description, date);
		String newDescription = "New description";

		// Action
		boardConsiderationNote = this.boardConsiderationService
				.updateBoardConsiderationNote(boardConsiderationNote, 
						newDescription, date);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("hearingAnalysis", hearingAnalysis)
			.addExpectedValue("description", newDescription)
			.addExpectedValue("date", date)
			.performAssertions(boardConsiderationNote);
	}
	
	/**
	 * Tests the update of the date for a board consideration note.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if parole board member date range not 
	 * within staff assignment date range
	 */
	@Test
	public void testUpdateBoardConsiderationNoteDate() 
			throws DuplicateEntityFoundException, DateConflictException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		EligibilityStatusReason statusReason = eligibilityStatusReasonDelegate
				.create("Reason", EligibilityStatusCategory.APPEARING, true);
		ParoleEligibilityStatus paroleEligibilityStatus = 
				new ParoleEligibilityStatus(this.parseDateText("01/01/2017"), 
						"Comment", EligibilityStatusCategory.APPEARING, 
						statusReason);
		AppearanceCategory appearanceCategory = this.appearanceCategoryDelegate
				.create("Category", true);
		ParoleEligibility eligibility = this.paroleEligibilityDelegate.create(
				offender, this.parseDateText("01/01/2017"), 
				this.parseDateText("01/01/2017"), paroleEligibilityStatus, 
				appearanceCategory);
		Person staffMember = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		SupervisoryOrganization supervisoryOrganization = this
				.supervisoryOrganizationDelegate.create("SupOrg", "SO", null);
		StaffTitle staffTitle = this.staffTitleDelegate.create("Title", 
				(short) 1, true);
		StaffAssignment staffAssignment = this.staffAssignmentDelegate.create(
				staffMember, supervisoryOrganization, null, 
				new DateRange(this.parseDateText("01/01/2017"), null), 
				staffTitle, true, null, null, null, null, null);
		ParoleBoardMember boardMember = this.paroleBoardMemberDelegate.create(
				staffAssignment, 
				new DateRange(this.parseDateText("01/01/2017"), null));
		HearingAnalysisCategory analysisCategory = this
				.hearingAnalysisCategoryDelegate.create("Category", true);
		HearingAnalysis hearingAnalysis = this.hearingAnalysisDelegate.create(
				eligibility, analysisCategory, boardMember, null);
		String description = "Description";
		Date date = this.parseDateText("05/30/2018");
		BoardConsiderationNote boardConsiderationNote = this
				.boardConsiderationNoteDelegate.create(hearingAnalysis, 
						description, date);
		Date newDate = this.parseDateText("05/31/2018");

		// Action
		boardConsiderationNote = this.boardConsiderationService
				.updateBoardConsiderationNote(boardConsiderationNote, 
						description, newDate);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("hearingAnalysis", hearingAnalysis)
			.addExpectedValue("description", description)
			.addExpectedValue("date", newDate)
			.performAssertions(boardConsiderationNote);
	}

	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if parole board member date range not 
	 * within staff assignment date range
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException, DateConflictException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		EligibilityStatusReason statusReason = eligibilityStatusReasonDelegate
				.create("Reason", EligibilityStatusCategory.APPEARING, true);
		ParoleEligibilityStatus paroleEligibilityStatus = 
				new ParoleEligibilityStatus(this.parseDateText("01/01/2017"), 
						"Comment", EligibilityStatusCategory.APPEARING, 
						statusReason);
		AppearanceCategory appearanceCategory = this.appearanceCategoryDelegate
				.create("Category", true);
		ParoleEligibility eligibility = this.paroleEligibilityDelegate.create(
				offender, this.parseDateText("01/01/2017"), 
				this.parseDateText("01/01/2017"), paroleEligibilityStatus, 
				appearanceCategory);
		Person staffMember = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		SupervisoryOrganization supervisoryOrganization = this
				.supervisoryOrganizationDelegate.create("SupOrg", "SO", null);
		StaffTitle staffTitle = this.staffTitleDelegate.create("Title", 
				(short) 1, true);
		StaffAssignment staffAssignment = this.staffAssignmentDelegate.create(
				staffMember, supervisoryOrganization, null, 
				new DateRange(this.parseDateText("01/01/2017"), null), 
				staffTitle, true, null, null, null, null, null);
		ParoleBoardMember boardMember = this.paroleBoardMemberDelegate.create(
				staffAssignment, 
				new DateRange(this.parseDateText("01/01/2017"), null));
		HearingAnalysisCategory analysisCategory = this
				.hearingAnalysisCategoryDelegate.create("Category", true);
		HearingAnalysis hearingAnalysis = this.hearingAnalysisDelegate.create(
				eligibility, analysisCategory, boardMember, null);
		String description = "Description";
		Date date = this.parseDateText("05/30/2018");
		this.boardConsiderationNoteDelegate.create(hearingAnalysis, description,
				date);
		Date secondDate = this.parseDateText("05/31/2018");
		BoardConsiderationNote boardConsiderationNote = this
				.boardConsiderationNoteDelegate.create(hearingAnalysis, 
						description, secondDate);
		
		// Action
		boardConsiderationNote = this.boardConsiderationService
				.updateBoardConsiderationNote(boardConsiderationNote, 
						description, date);
	}

	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
}