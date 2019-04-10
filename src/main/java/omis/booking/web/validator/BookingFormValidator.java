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
package omis.booking.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.booking.web.form.BookingForm;

/** Validator for booking form.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 10, 2019)
 * @since OMIS 3.0 */
public class BookingFormValidator implements Validator {
	
	/** Constructor. */
	public BookingFormValidator() { }
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return BookingForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "date", "date.empty");
		ValidationUtils.rejectIfEmpty(errors, "bookingNumber","bookingNumber.empty");
		ValidationUtils.rejectIfEmpty(errors, "category", "bookingAdmissionCategory.empty");
		ValidationUtils.rejectIfEmpty(errors, "commitSource", "bookingCommitSourceCategory.empty");
	}

}
