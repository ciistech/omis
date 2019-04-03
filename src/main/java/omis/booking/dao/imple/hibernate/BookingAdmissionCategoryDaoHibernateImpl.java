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
package omis.booking.dao.imple.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.booking.dao.BookingAdmissionCategoryDao;
import omis.booking.domain.BookingAdmissionCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/** Hibernate implementation of booking admission category dao.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 2, 2019)
 * @since OMIS 3.0 */
public class BookingAdmissionCategoryDaoHibernateImpl extends GenericHibernateDaoImpl<BookingAdmissionCategory>
		implements BookingAdmissionCategoryDao {
	private static final String FIND_QUERY_NAME = "findBookingAdmissionCategories";
	/** Constructor.
	 * @param sessionFactory session factory.
	 * @param entityName entity name. */
	public BookingAdmissionCategoryDaoHibernateImpl(final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<BookingAdmissionCategory> findAll() {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(FIND_QUERY_NAME);
		return (List<BookingAdmissionCategory>) q.list();
	}

}
