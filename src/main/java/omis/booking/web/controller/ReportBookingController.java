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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.booking.report.BookingSummaryReportService;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

/** Reporting controller for booking.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 10, 2019)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/booking")
@PreAuthorize("hasRole('USER')")
public class ReportBookingController {

	/* View names. */
	private static final String LIST_VIEW_NAME = "booking/list";
	
	/* Model keys. */
	private static final String BOOKING_SUMMARIES_MODEL_KEY_NAME = "bookings";
	
	/* Property Editor factories. */
	@Autowired
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Report services. */
	@Autowired
	private BookingSummaryReportService bookingSummaryReportService;
	
	/* Delegates. */
	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/** Lists bookings by offender.
	 * @param offender - offender.
	 * @return lists of offenders bookings. */
	@RequestContentMapping(nameKey = "bookingListScreen",
			descriptionKey = "bookingListScreenDescription",
			messageBundle = "omis.msgs.booking",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value="/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('BOOKING_LIST') or hasRole('ADMIN')")
	public ModelAndView listBooking(
			@RequestParam(value="offender", required=true) 
			final Offender offender) {
		final ModelAndView mav;
		final ModelMap map = new ModelMap();
		this.offenderSummaryModelDelegate.add(map, offender);
		map.put(BOOKING_SUMMARIES_MODEL_KEY_NAME, 
				this.bookingSummaryReportService.findByOffender(offender));
		mav = new ModelAndView(LIST_VIEW_NAME, map);
		return mav;
	}
	
	/* Registre property editors. */
	@InitBinder
	void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		
	}
}
