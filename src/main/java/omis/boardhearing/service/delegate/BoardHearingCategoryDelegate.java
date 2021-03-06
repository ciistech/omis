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
package omis.boardhearing.service.delegate;

import java.util.List;
import omis.boardhearing.dao.BoardHearingCategoryDao;
import omis.boardhearing.domain.BoardHearingCategory;
import omis.instance.factory.InstanceFactory;
import omis.paroleeligibility.domain.AppearanceCategory;

/**
 * Board Hearing Category Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 24, 2018)
 *@since OMIS 3.0
 *
 */
public class BoardHearingCategoryDelegate {
	
	private final BoardHearingCategoryDao boardHearingCategoryDao;
	
	private final InstanceFactory<BoardHearingCategory>
			boardHearingCategoryInstanceFactory;

	/**
	 * @param boardHearingCategoryDao - Board Hearing Category Dao
	 * @param boardHearingCategoryInstanceFactory - Board Hearing Category
	 * Instance Factory
	 */
	public BoardHearingCategoryDelegate(
			final BoardHearingCategoryDao boardHearingCategoryDao,
			final InstanceFactory<BoardHearingCategory>
					boardHearingCategoryInstanceFactory) {
		this.boardHearingCategoryDao = boardHearingCategoryDao;
		this.boardHearingCategoryInstanceFactory =
				boardHearingCategoryInstanceFactory;
	}
	
	/**
	 * Returns a list of Board Hearing Categories by specified Appearance
	 * Category.
	 * 
	 * @param appearanceCategory - Appearance Category.
	 * @return List of all Board Hearing Categories by specified Appearance
	 * Category.
	 * */
	public List<BoardHearingCategory> findByAppearanceCategory(
			final AppearanceCategory appearanceCategory) {
		return this.boardHearingCategoryDao.findByAppearanceCategory(
				appearanceCategory);
	}
	
	/**
	 * Creates a Board Hearing Category, for the purposes of unit testing.
	 * 
	 * @param name - String name
	 * @param valid - Boolean validity
	 * @return Newly created Board Hearing Category
	 */
	public BoardHearingCategory create(final String name, final Boolean valid) {
		BoardHearingCategory boardHearingCategory =
				this.boardHearingCategoryInstanceFactory.createInstance();
		boardHearingCategory.setName(name);
		boardHearingCategory.setValid(valid);
		
		return this.boardHearingCategoryDao.makePersistent(
				boardHearingCategory);
	}
}
