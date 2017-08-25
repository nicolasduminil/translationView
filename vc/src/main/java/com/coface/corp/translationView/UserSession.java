package com.coface.corp.translationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coface.corp.base.bdt.entitlement.RightBDT;
import com.coface.corp.entitlement.Entitlement;
import com.coface.corp.framework.remote.BcProxy;
import com.coface.corp.framework.sso.SSOClientHelper;
import com.coface.corp.framework.sso.SSOUserContext;
import com.coface.corp.translationView.enums.ActionRight;

public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static Log vcLog = LogFactory.getLog(UserSession.class);

	private SSOUserContext ssoUserContext;
	
	private UserData userData = new UserData();
	
	private Locale locale;

//	Map<String, BooleanWithTTL> hasActionMap = new HashMap<String, BooleanWithTTL>();

	public static final String SUPERVISOR_GROUP_ID = "TRANSLATIONVIEW_Supervisors";

	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.locale = request.getLocale();
		try {
			ssoUserContext = SSOClientHelper.getAuthenticatedUserContext(request);
			userData.setDisplayedName(ssoUserContext.getQualifiedUserName());
			userData.setAccountId(ssoUserContext.getAuthenticationInformation().getAccount().getAccountId());
			initUserRights(ssoUserContext);
		} catch (NullPointerException e) {
			vcLog.error("SSO session not found. " + e.getMessage());
			vcLog.debug(e.getMessage(), e);
			logout(request, response);
		}
	}

	public UserData getUserData() {
		return userData;
	}

	public String getAccountId() {
		return userData.getAccountId();
	}

//	public String getDisplayedName() {
//		return userData.getDisplayedName();
//	}

//	public boolean isSupervisor() {
//		return userData.isSupervisor;
//	}
	public Locale getLocale() {
		return locale;
	}

	public Entitlement getEntitlement() {
		return BcProxy.getInstance(Entitlement.class);
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		SSOClientHelper.backToPortal(request, response);
	}
	
	private void initUserRights(SSOUserContext ssoUserContext){
		userData.setCanDeleteTranslation(isAccountAllowedForAction(ActionRight.I18N_DELETE_TRANSLATION));
		userData.setCanDoImport(isAccountAllowedForAction(ActionRight.I18N_IMPORT));
		userData.setCanManageBundles(isAccountAllowedForAction(ActionRight.I18N_MANAGE_BUNDLES));
		userData.setCanManageLanguages(isAccountAllowedForAction(ActionRight.I18N_MANAGE_LANGUAGES));
		userData.setCanManageMessages(isAccountAllowedForAction(ActionRight.I18N_MANAGE_MESSAGES));
		userData.setCanRefreshCache(isAccountAllowedForAction(ActionRight.I18N_REFRESH_CACHE));
		userData.setCanTranslate(isAccountAllowedForAction(ActionRight.I18N_TRANSLATE));
		userData.setCanValidate(isAccountAllowedForAction(ActionRight.I18N_VALIDATE));
		userData.setHasAllBundles(isAccountAllowedForAction(ActionRight.I18N_ALL_BUNDLES));
		userData.setHasAllLanguages(isAccountAllowedForAction(ActionRight.I18N_ALL_LANGUAGES));
		
		userData.setSupervisor(ssoUserContext.getAuthenticationInformation().getGroupIdentifierAsList().contains(SUPERVISOR_GROUP_ID));
		
		userData.setBundles(getUserBundles());
		userData.setLanguages(getUserLanguages());
	}
	
	public boolean isAccountAllowedForAction(ActionRight action){
		String actionId = action.name();
		boolean result;
//		BooleanWithTTL tmp = hasActionMap.get(actionId);
//		if (tmp == null || !tmp.isValid()) {
			result = getEntitlement().isAccountAuthorizedForAction(getAccountId(), actionId, null);
//			hasActionMap.put(actionId, new BooleanWithTTL(result));
//		}
//		else {
//			result = tmp.isTrue();
//		}
		return result;
	}
	
	public List<String> getUserLanguages(){
		if (isAccountAllowedForAction(ActionRight.I18N_ALL_LANGUAGES)){
			return null;
		}else{
			return getUserPerimeterForAction(ActionRight.I18N_LANGUAGE_PERIMETER);
		}
	}
	
	public List<String> getUserBundles(){
		if (isAccountAllowedForAction(ActionRight.I18N_ALL_BUNDLES)){
			return null;
		}else{
			return getUserPerimeterForAction(ActionRight.I18N_BUNDLE_PERIMETER);
		}
	}
	
	private List<String> getUserPerimeterForAction(ActionRight action){
		String actionId = action.name();
		List<String> result = null;
		RightBDT[] rights = getEntitlement().getAccountRightsForAction(getAccountId(), actionId);
		if (rights != null && rights.length > 0 && rights[0].getRestriction() != null && rights[0].getRestriction().length > 0) {
			result = new ArrayList<String>();
			for (RightBDT right : rights){
				for (String domainId : right.getRestriction()[0].getArrayValue()) {
					result.add(domainId);
				}
			}
		}
		return result;
	}
	
	public void recomputeRights(){
		initUserRights(ssoUserContext);
	}

}
