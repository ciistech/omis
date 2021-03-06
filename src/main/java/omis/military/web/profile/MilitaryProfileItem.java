package omis.military.web.profile;


import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.military.report.MilitaryProfileItemReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/** Profile item for military.
 * @author Ryan Johns
 * @author Trevor Isles
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public class MilitaryProfileItem 
	extends AbstractProfileItem 
	implements ProfileItem {
	private static final long serialVersionUID = 1L;
	private static final String MILITARY_SERVICE_TERM_EXISTS 
		= "militaryServiceTermExists";
	
	private final MilitaryProfileItemReportService 
		militaryProfileItemReportService;

	/** Constructor.
	 * @param requiredAuthorizations - required authorizations.
	 * @param includePage - include page.
	 * @param name - name.
	 * @param profileItemRegistry - profile item registry.
	 * @param militaryProfileItemService - military profile item service. 
	 * @param enabled - whether enabled */
	public MilitaryProfileItem(final Set<String> requiredAuthorizations, 
			final String includePage, final String name, 
			final ProfileItemRegistry profileItemRegistry, 
			final MilitaryProfileItemReportService 
				militaryProfileItemReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.militaryProfileItemReportService 
			= militaryProfileItemReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, 
			final Offender offender, final UserAccount userAccount, 
			final Date date) {
		map.put(MILITARY_SERVICE_TERM_EXISTS, 
				this.militaryProfileItemReportService
				.findMilitaryServiceTermExistenceByOffender(offender));
	}
}
