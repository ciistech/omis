package omis.user.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.domain.Person;
import omis.user.dao.UserAccountDao;
import omis.user.domain.UserAccount;
import omis.user.domain.UserGroup;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of user account data access object.
 * 
 * @author Stephen Abson
 * @version 0.1.6 (April 2, 2013)
 * @since OMIS 3.0
 */
public class UserAccountDaoHibernateImpl
		extends GenericHibernateDaoImpl<UserAccount>
		implements UserAccountDao {
	
	/* Queries. */
	
	private static final String FIND_BY_USER_QUERY_NAME
		= "findUserAccountsByUser";
	
	private static final String FIND_BY_USERNAME_CASE_SENSITIVE_QUERY_NAME
		= "findUserAccountByUsername";
	
	private static final String FIND_BY_USERNAME_CASE_INSENSITIVE_QUERY_NAME
		= "findUserAccountByUsernameCaseInsensitive";
	
	private static final String FIND_ALL_QUERY_NAME = "findUserAccounts";
	
	private static final String SEARCH_QUERY_NAME = "searchUserAccounts";
	
	private static final String SEARCH_CASE_INSENSITIVE_QUERY_NAME
		= "searchUserAccountsCaseInsensitive";
	
	private static final String FIND_BY_USER_GROUP_QUERY_NAME
		= "findUserAccountsByUserGroup";
	
	/* Parameters. */
	
	private static final String USER_PARAM_NAME = "user";
	
	private static final String USERNAME_PARAM_NAME = "username";

	private static final String QUERY_PARAM_NAME = "query";

	private static final String USER_GROUP_PARAM_NAME = "userGroup";

	/* Constructor. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for user
	 * accounts with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public UserAccountDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<UserAccount> findByUser(final Person user) {
		@SuppressWarnings("unchecked")
		List<UserAccount> accounts = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_USER_QUERY_NAME)
				.setParameter(USER_PARAM_NAME, user).list();
		return accounts;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<UserAccount> findAll() {
		@SuppressWarnings("unchecked")
		List<UserAccount> accounts = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return accounts;
	}
	
	/** {@inheritDoc} */
	@Override
	public UserAccount findByUsername(
			final String username, final boolean caseSensitive) {
		Query query;
		if (caseSensitive) {
			query = this.getSessionFactory().getCurrentSession()
					.getNamedQuery(FIND_BY_USERNAME_CASE_SENSITIVE_QUERY_NAME)
					.setParameter(USERNAME_PARAM_NAME, username);
		} else {
			query = this.getSessionFactory().getCurrentSession()
					.getNamedQuery(FIND_BY_USERNAME_CASE_INSENSITIVE_QUERY_NAME)
					.setParameter(USERNAME_PARAM_NAME, username.toUpperCase());
		}
		
		UserAccount userAccount = (UserAccount) query.uniqueResult();
		return userAccount;
	}

	/** {@inheritDoc} */
	@Override
	public boolean userAccountExists(final String username,
			final boolean caseSensitive) {
		return this.findByUsername(username, caseSensitive) != null;
	}

	/** {@inheritDoc} */
	@Override
	public UserAccount findByUsername(final String username) {
		return this.findByUsername(username, true);
	}

	/** {@inheritDoc} */
	@Override
	public List<UserAccount> search(final String query) {
		@SuppressWarnings("unchecked")
		List<UserAccount> userAccounts = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(SEARCH_QUERY_NAME)
			.setParameter(QUERY_PARAM_NAME, query)
			.list();
		return userAccounts;
	}

	/** {@inheritDoc} */
	@Override
	public List<UserAccount> searchCaseInsensitive(final String query) {
		@SuppressWarnings("unchecked")
		List<UserAccount> userAccounts = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(
					SEARCH_CASE_INSENSITIVE_QUERY_NAME)
			.setParameter(QUERY_PARAM_NAME, query)
			.list();
		return userAccounts;
	}

	/** {@inheritDoc} */
	@Override
	public List<UserAccount> findByGroup(final UserGroup group) {
		@SuppressWarnings("unchecked")
		List<UserAccount> userAccounts = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(
					FIND_BY_USER_GROUP_QUERY_NAME)
			.setParameter(USER_GROUP_PARAM_NAME, group)
			.list();
		return userAccounts;
	}
}