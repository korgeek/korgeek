package korgeek.aradon.common.unit;

import net.ion.framework.util.StringUtil;
import net.ion.radon.repository.RepositoryCentral;
import net.ion.radon.repository.Session;
import net.ion.radon.repository.SessionQuery;

public abstract class AbstractCarrier implements Constants {

	private Session session;
	private RepositoryCentral rc;
	private Bundle bundle;

	protected AbstractCarrier(RepositoryCentral rc) {
		this.rc = rc;
		this.bundle = new Bundle();
		this.session = rc.login(getWorkSpaceName());
	}
	
	protected AbstractCarrier(RepositoryCentral rc, Bundle bundle) {
		this.rc = rc;
		this.bundle = bundle;
		this.session = rc.login(getWorkSpaceName());
	}

	protected abstract String getWorkSpaceName();

	protected String getMessage(String key) {
		return bundle.getMessage(key);
	}
	
	protected Bundle getBundle() {
		return bundle;
	}

	protected Session getSession() {
		return getSession(getWorkSpaceName());
	}
	
	protected Session getSession(String workspaceName) {
		if (workspaceName.equals(session.getCurrentWorkspaceName())) {
			return session;
		} else {
			return session.changeWorkspace(workspaceName);
		}
	}

	protected SessionQuery createQuery(String workspaceName) {
		return session.createQuery(workspaceName);
	}

	protected SessionQuery createQuery() {
		return createQuery(getWorkSpaceName());
	}

	protected int commit() {
		return commit(getWorkSpaceName());
	}

	protected int commit(String workspaceName) {
		return getSession(workspaceName).commit();
	}

	protected <T extends AbstractCarrier> T getCarrier(Class<T> clz) {
		return CarrierFactory.create(getRepositoryCentral(), clz);
	}

	
	public RepositoryCentral getRepositoryCentral() {
		return rc;
	}
	
	protected boolean isAnonymous(String oid) {
		return StringUtil.equalsIgnoreCase(oid, ANONYMOUS);
	}
	
}
