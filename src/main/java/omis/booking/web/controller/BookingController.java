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
package omis.booking.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.booking.domain.Booking;
import omis.booking.domain.BookingAdmissionCategory;
import omis.booking.domain.BookingCommitSourceCategory;
import omis.booking.domain.BookingNote;
import omis.booking.exception.BookingExistsException;
import omis.booking.exception.BookingNoteExistsException;
import omis.booking.service.BookingService;
import omis.booking.web.form.BookingForm;
import omis.booking.web.form.BookingNoteFormItem;
import omis.booking.web.form.BookingNoteItemOperation;
import omis.booking.web.validator.BookingFormValidator;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.region.domain.County;
import omis.staff.domain.StaffAssignment;
import omis.supervision.domain.CorrectionalStatus;

/** Controller for booking related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 8, 2019)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/booking")
@PreAuthorize("hasRole('USER')")
public class BookingController {

	/* Views */
	private static final String EDIT_VIEW_NAME = "booking/edit";
	private static final String ACTION_MENU_VIEW_NAME 
		= "booking/includes/bookingActionMenu";
	private static final String BOOKING_NOTE_ITEM_VIEW_NAME 
		= "booking/includes/bookingNoteItemTableRow";
	
	/* Redirects. */
	private static final String LIST_REDIRECT_VIEW_NAME 
		= "redirect:/booking/list.html?offender=%d";
	
	/* Model keys. */
	private static final String FORM_MODEL_KEY = "bookingForm";
	private static final String OFFENDER_MODEL_KEY = "offender";
	private static final String COMMIT_SOURCES_MODEL_KEY = "commitSources";
	private static final String BOOKING_ADMISSION_CATEGORIES_MODEL_KEY 
		= "bookingAdmissionCategories";
	private static final String CORRECTIONAL_STATUSES_MODEL_KEY 
		= "correctionalStatuses";
	private static final String COUNTIES_MODEL_KEY = "counties";
	private static final String BOOKING_NOTE_ITEM_INDEX_MODEL_KEY 
		= "bookingNoteItemIndex";
	private static final String CURRENT_BOOKING_NOTE_INDEX_MODEL_KEY 
		= "currentNoteIndex";
	private static final String BOOKING_NOTE_ITEM_MODEL_KEY 
		= "bookingNoteItem";
	private static final String BOOKING_MODEL_KEY = "booking";
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	private PropertyEditorFactory bookingCommitSourceCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	private PropertyEditorFactory correctionalStatusPropertyEditorFactory;
	
	@Autowired
	private PropertyEditorFactory staffAssignmentPropertyEditorFactory;
	
	@Autowired
	private PropertyEditorFactory bookingAdmissionCategoryPropertyEditorFactory;
	
	@Autowired
	private PropertyEditorFactory countyPropertyEditorFactory;
	
	@Autowired
	private PropertyEditorFactory bookingNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("bookingPropertyEditorFactory")
	private PropertyEditorFactory bookingPropertyEditorFactory;
	
	/* Services. */
	@Autowired
	private BookingService bookingService;
	
	/* Delegates. */
	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Validators. */
	@Autowired
	private BookingFormValidator bookingFormValidator;
	
	/** Display for for creating a booking record.
	 * @param offender - offender.
	 * @return model and view. */
	@RequestContentMapping(nameKey = "bookingCreateScreen",
			descriptionKey = "bookingCreateScreenDescription",
			messageBundle = "omis.booking.msgs.booking",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value="/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('BOOKING_CREATE') or hasRole('ADMIN')")
	public ModelAndView createBooking(@RequestParam(value = "offender", 
			required = true)final Offender offender) {
		ModelMap map = new ModelMap();
		ModelAndView mav;
		BookingForm form = new BookingForm();
		
		this.prepareMap(map);
		
		this.offenderSummaryModelDelegate.add(map, offender);
		map.put(FORM_MODEL_KEY, form);
		map.put(CURRENT_BOOKING_NOTE_INDEX_MODEL_KEY, 0);
		
		mav = new ModelAndView(EDIT_VIEW_NAME, map);
		return mav;
	}
	
	/** Saves booking record.
	 * @param offender - offender.
	 * @param bookingForm - booking form.
	 * @param result - binding result.
	 * @return list redirect. 
	 * @throws BookingExistsException - when booking record already exists.
	 * @throws BookingNoteExistsException - when booking note already 
	 * exists. */
	@RequestContentMapping(nameKey= "bookingSaveScreen",
			descriptionKey = "bookingSaveScreenDescription",
			messageBundle = "omis.booking.msgs.booking",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value="/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('BOOKING_SAVE') or hasRole('ADMIN')")
	public ModelAndView saveBooking(final BookingForm form,
			@RequestParam(value = "offender", required = true)
				final Offender offender,  
				final BindingResult result) throws BookingExistsException, 
	BookingNoteExistsException {
		final ModelAndView mav;
		this.bookingFormValidator.validate(form, result);
		if (result.hasErrors()) {
			final ModelMap map = new ModelMap();
			this.prepareMap(map);
			map.put(FORM_MODEL_KEY,  form);
			map.put(BindingResult.MODEL_KEY_PREFIX + FORM_MODEL_KEY,  result);
			map.put(CURRENT_BOOKING_NOTE_INDEX_MODEL_KEY, 
					form.getBookingNoteItems().size());
			mav = new ModelAndView(EDIT_VIEW_NAME, map);
		} else {
			Booking booking = this.bookingService.createBooking(
					form.getBookingNumber(), form.getCategory(), offender, 
					form.getDate(), form.getCounty(), 
					form.getCorrectionalStatus(), form.getCommitSource(),
					form.getTransportOfficer());
			
			this.processBookingNotes(booking, form.getBookingNoteItems());
			mav = this.prepareListRedirect(booking.getOffender());
		}
		return mav;
	}
	
	/** Edits booking record.
	 * @param booking - booking.
	 * @return booking edit form. */
	@RequestContentMapping(nameKey = "bookingEditScreen",
			descriptionKey = "bookingEditScreenDescription",
			messageBundle = "omis.booking.msgs.booking",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('BOOKING_EDIT') or hasRole('ADMIN')")
	public ModelAndView editBooking(
			@RequestParam(value = "booking", required = true)
			final Booking booking) {
		final ModelAndView mav;
		final ModelMap map = new ModelMap();
		this.prepareMap(map);
		this.offenderSummaryModelDelegate.add(map, booking.getOffender());
		BookingForm form = this.prepareForm(booking);
		form.setBookingNoteItems(this.prepareBookingNotes(this.bookingService
				.findBookingNotesByBooking(booking)));
		map.put(FORM_MODEL_KEY, form);
		map.put(BOOKING_MODEL_KEY, booking);
		map.put(CURRENT_BOOKING_NOTE_INDEX_MODEL_KEY, 
				form.getBookingNoteItems().size());
		mav = new ModelAndView(EDIT_VIEW_NAME, map);
		return mav;
	}
	
	/** Update booking record.
	 * @param form - booking form.
	 * @param booking - booking.
	 * @return redirect to listing. 
	 * @throws BookingExistsException - when booking record exists. 
	 * @throws BookingNoteExistsException - when booking note exists. */
	@RequestContentMapping(nameKey = "bookingUpdateScreen",
			descriptionKey = "bookingUpdateScreenDescription",
			messageBundle = "omis.booking.msgs.booking",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value="/edit.html", method=RequestMethod.POST)
	@PreAuthorize("hasRole('BOOKING_UPDATE') or hasRole('ADMIN')")
	public ModelAndView updateBooking(
			final BookingForm form, 
			@RequestParam(value = "booking", required = true)
			final Booking booking,
			final BindingResult result) throws BookingExistsException, 
		BookingNoteExistsException {
		final ModelAndView mav;
		this.bookingFormValidator.validate(form, result);
		if (result.hasErrors()) {
			final ModelMap map = new ModelMap();
			this.prepareMap(map);
			map.put(FORM_MODEL_KEY,  form);
			map.put(BindingResult.MODEL_KEY_PREFIX + FORM_MODEL_KEY,  result);
			map.put(CURRENT_BOOKING_NOTE_INDEX_MODEL_KEY, 
					form.getBookingNoteItems().size());
			mav = new ModelAndView(EDIT_VIEW_NAME, map);
		} else {
			this.bookingService.updateBooking(booking, 
					form.getBookingNumber(), form.getCategory(), 
					form.getDate(), form.getCounty(), 
					form.getCorrectionalStatus(), form.getCommitSource(), 
					form.getTransportOfficer());
			
			this.processBookingNotes(booking, form.getBookingNoteItems());
			mav = this.prepareListRedirect(booking.getOffender());
		}
		return mav;
	}
	/** Action menu for booking record.
	 * @param offender - offender.
	 * @return action menu model and view. */
	@RequestContentMapping(nameKey = "bookingActionMenuScreen",
			descriptionKey = "bookingActionMenuScreenDescription",
			messageBundle = "omis.booking.msgs.booking",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value="/bookingActionMenu.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ModelAndView bookingActionMenu(
			@RequestParam(value = "offender", required=true) 
			final Offender offender) {
		ModelAndView mav;
		ModelMap map = new ModelMap();
		map.put(OFFENDER_MODEL_KEY, offender);
		
		mav = new ModelAndView(ACTION_MENU_VIEW_NAME, map);
		return mav;
	}
	
	/** Booking note.
	 * @param bookingNoteIndex - booking note index.
	 * @return booking note row item model and view. */
	@RequestContentMapping(nameKey = "bookingNoteAddNoteScreen",
			descriptionKey = "bookingNoteAddNoteScreenDescription",
			messageBundle = "omis.booking.msgs.booking",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value="/addBookingNote.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ModelAndView bookingNoteAddNote(
			@RequestParam(value = "bookingNoteIndex", required = true)
			final int bookingNoteIndex) {
		final ModelAndView mav;
		final ModelMap map = new ModelMap();
		final BookingNoteFormItem formItem = new BookingNoteFormItem();
		formItem.setItemOperation(BookingNoteItemOperation.CREATE);
		map.put(BOOKING_NOTE_ITEM_MODEL_KEY, formItem);
		map.put(BOOKING_NOTE_ITEM_INDEX_MODEL_KEY, bookingNoteIndex);
		mav = new ModelAndView(BOOKING_NOTE_ITEM_VIEW_NAME, map);
		return mav;	
	}
	
	/** Remove booking.
	 * @param booking - booking.
	 * @return listing booking list redirect. */
	@RequestContentMapping(nameKey = "removeBookingScreen",
			descriptionKey = "removeBookingScreenDescription",
			messageBundle = "omis.booking.msgs.booking",
			screenType = RequestContentType.OTHER)
	@RequestMapping(value="/remove.html", method=RequestMethod.GET)
	@PreAuthorize("hasRole('BOOKING_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value ="booking", required=true)
				final Booking booking) {
		final ModelAndView mav;
		this.bookingService.removeBooking(booking);
		
		mav = this.prepareListRedirect(booking.getOffender());
		return mav;
	}

	/* Private prepare map. */
	private  void prepareMap(final ModelMap map) {
		map.put(COMMIT_SOURCES_MODEL_KEY, this.bookingService
				.findBookingCommitSourceCategories());
		
		map.put(BOOKING_ADMISSION_CATEGORIES_MODEL_KEY, this.bookingService
				.findBookingAdmissionCategories());
		
		map.put(CORRECTIONAL_STATUSES_MODEL_KEY, this.bookingService
				.findCorrectionalStatuses());
		
		map.put(COUNTIES_MODEL_KEY, this.bookingService.findAllCountiesByState(
				this.bookingService.findHomeState()));
	}
	
	/* Prepres list redirect. */
	private ModelAndView prepareListRedirect(final Offender offender) {
		return new ModelAndView(String.format(LIST_REDIRECT_VIEW_NAME, 
				offender.getId()));
	}

	/* Process booking notes. */
	private void processBookingNotes(final Booking booking, 
			final List<BookingNoteFormItem> items) 
					throws BookingNoteExistsException {
		if (items != null) {
			for (BookingNoteFormItem item: items) {
				if (BookingNoteItemOperation.REMOVE.equals(
						item.getItemOperation())) {
					this.bookingService.removeBookingNote(item.getBookingNote());
				} else if (BookingNoteItemOperation.CREATE.equals(
						item.getItemOperation())) {
					this.bookingService.createBookingNote(booking, 
							item.getDescription(), item.getDate());
				} else if (BookingNoteItemOperation.UPDATE.equals(
						item.getItemOperation())) {
					this.bookingService.updateBookingNote(item.getBookingNote(), 
							item.getDescription(), item.getDate());
				} else {
					/* No Changes do nothing. */
				}
			}
		}
	}
	
	/* Prepares form. */
	BookingForm prepareForm(final Booking booking) {
		final BookingForm form = new BookingForm();
		form.setBookingNumber(booking.getBookingNumber());
		form.setCategory(booking.getCategory());
		form.setCommitSource(booking.getCommitSource());
		form.setCorrectionalStatus(booking.getCorrectionalStatus());
		form.setCounty(booking.getCounty());
		form.setDate(booking.getDate());
		form.setOffender(booking.getOffender());
		form.setTransportOfficer(booking.getTransportOfficer());
		return form;
	}
	
	/* Prepare booking note items. */
	List<BookingNoteFormItem> prepareBookingNotes(final List<BookingNote> notes) {
		List<BookingNoteFormItem> noteItems = null;
		if (notes != null) {
			noteItems = new ArrayList<BookingNoteFormItem>();
			for (BookingNote note : notes) {
				BookingNoteFormItem formItem = new BookingNoteFormItem();
				formItem.setBookingNote(note);
				formItem.setDate(note.getDate());
				formItem.setDescription(note.getDescription());
				noteItems.add(formItem);
			}
		}
		return noteItems;
		
	}
	
	
	/* Register property editors. */
	@InitBinder
	void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		
		binder.registerCustomEditor(BookingCommitSourceCategory.class, 
				this.bookingCommitSourceCategoryPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(Date.class, 
				this.customDateEditorFactory.createCustomDateOnlyEditor(
						true));
		
		binder.registerCustomEditor(CorrectionalStatus.class, 
				this.correctionalStatusPropertyEditorFactory.createPropertyEditor());
		
		binder.registerCustomEditor(BookingAdmissionCategory.class, 
				this.bookingAdmissionCategoryPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(StaffAssignment.class, 
				this.staffAssignmentPropertyEditorFactory.createPropertyEditor());
		
		binder.registerCustomEditor(County.class, 
				this.countyPropertyEditorFactory.createPropertyEditor());
		
		binder.registerCustomEditor(Booking.class, 
				this.bookingPropertyEditorFactory.createPropertyEditor());
		
		binder.registerCustomEditor(BookingNote.class, 
				this.bookingNotePropertyEditorFactory.createPropertyEditor());
	}
}
