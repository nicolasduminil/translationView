package com.coface.corp.translationView;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coface.corp.base.bdt.address.CommunicationBDT;
import com.coface.corp.base.bdt.entitlement.AccountBDT;
import com.coface.corp.base.bdt.entitlement.RightBDT;
import com.coface.corp.base.bdt.organization.OrgEmployeeFullBDT;
import com.coface.corp.base.exception.EmployeeNotFoundException;
import com.coface.corp.entitlement.Entitlement;
import com.coface.corp.framework.remote.BcProxy;
import com.coface.corp.framework.sso.SSOClientHelper;
import com.coface.corp.framework.sso.SSOUserContext;
import com.coface.corp.organization.Organization;

public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static Log vcLog = LogFactory.getLog(UserSession.class);

	private SSOUserContext ssoUserContext;

	private String accountId;

	private String displayedName;
	
	private String employeeEmail;

	private Locale locale;

	private boolean supervisor;

	private Set<String> authorizedServices = new HashSet<String>();

	public static final String SERVICES_AUTHORIZED_ACTION_ID = "FWK_TEST_SERVICES";

	public static final String SUPERVISOR_GROUP_ID = "bcTestView_Supervisors";

	
	public UserSession(String id, String name) {
		this.accountId = id;
		this.displayedName = name;
	}

	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.locale = request.getLocale();
		try {
			AccountBDT account;
			ssoUserContext = SSOClientHelper.getAuthenticatedUserContext(request);
			account = ssoUserContext.getAuthenticationInformation().getAccount();
			displayedName = ssoUserContext.getQualifiedUserName();
			accountId = account.getAccountId();

      doInit (account);
      
			supervisor = ssoUserContext.getAuthenticationInformation().getGroupIdentifierAsList().contains(SUPERVISOR_GROUP_ID);
			RightBDT[] rights = getEntitlement().getAccountRightsForAction(accountId, SERVICES_AUTHORIZED_ACTION_ID);
			if (rights == null || rights.length <= 0 || rights[0].getRestriction() == null
					|| rights[0].getRestriction().length <= 0) {
			    return;
			}
				for (RightBDT right : rights) {
					for (String domainId : right.getRestriction()[0].getArrayValue()) {
						authorizedServices.add(domainId);
					}
				}
		}
		catch (NullPointerException e) {
			vcLog.error("SSO session not found. " + e.getMessage());
			vcLog.debug(e.getMessage(), e);
			logout(request, response);
		}
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		SSOClientHelper.backToPortal(request, response);
	}

	public String getAccountId() {
		return accountId;
	}
	
	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public boolean isSupervisor() {
		return supervisor;
	}

	public Set<String> getAuthorizedServices() {
		return authorizedServices;
	}

	public Locale getLocale() {
		return locale;
	}

	public String getDisplayedName() {
		return displayedName;
	}

	public SSOUserContext getSsoUserContext() {
		return ssoUserContext;
	}

	public Entitlement getEntitlement() {
		return BcProxy.getInstance(Entitlement.class);
	}
	
	public Organization getOrganization() {
		return BcProxy.getInstance(Organization.class);
	}
	
	private void doInit(AccountBDT account) {
    try {
      OrgEmployeeFullBDT emp = getOrganization().getEmployee(account.getUserId());
      CommunicationBDT[] emails = emp.getEmailAddress();
      if (emails!=null && emails.length > 0){
        employeeEmail = emails[0].getCommunicationValue();
      }
    } catch (EmployeeNotFoundException e) {
      vcLog.info(e.getMessage(), e);
    }
	  
	}
}
