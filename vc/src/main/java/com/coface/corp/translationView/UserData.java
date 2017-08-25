package com.coface.corp.translationView;

import java.io.Serializable;
import java.util.List;

public class UserData implements Serializable{

	private static final long serialVersionUID = 1L;

	private String accountId;

	private String displayedName;
	
	boolean isSupervisor;
	
	boolean canValidate;
	boolean canTranslate;
	boolean canDoImport;
	boolean canDeleteTranslation;
	boolean canRefreshCache;
	boolean canManageBundles;
	boolean canManageLanguages;
	boolean canManageMessages;
	boolean hasAllLanguages;
	boolean hasAllBundles;
	
	List<String> languages;
	List<String> bundles;
	
	public UserData() {
		super();
	}


	public String getAccountId() {
		return accountId;
	}

	public String getDisplayedName() {
		return displayedName;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setDisplayedName(String displayedName) {
		this.displayedName = displayedName;
	}
	
	public boolean isSupervisor() {
		return isSupervisor;
	}


	public void setSupervisor(boolean isSupervisor) {
		this.isSupervisor = isSupervisor;
	}


	public boolean isCanValidate() {
		return canValidate;
	}


	public void setCanValidate(boolean canValidate) {
		this.canValidate = canValidate;
	}


	public boolean isCanTranslate() {
		return canTranslate;
	}


	public void setCanTranslate(boolean canTranslate) {
		this.canTranslate = canTranslate;
	}


	public boolean isCanDoImport() {
		return canDoImport;
	}


	public void setCanDoImport(boolean canDoImport) {
		this.canDoImport = canDoImport;
	}


	public boolean isCanDeleteTranslation() {
		return canDeleteTranslation;
	}


	public void setCanDeleteTranslation(boolean canDeleteTranslation) {
		this.canDeleteTranslation = canDeleteTranslation;
	}


	public boolean isCanRefreshCache() {
		return canRefreshCache;
	}


	public void setCanRefreshCache(boolean canRefreshCache) {
		this.canRefreshCache = canRefreshCache;
	}


	public boolean isCanManageBundles() {
		return canManageBundles;
	}


	public void setCanManageBundles(boolean canManageBundles) {
		this.canManageBundles = canManageBundles;
	}


	public boolean isCanManageLanguages() {
		return canManageLanguages;
	}


	public void setCanManageLanguages(boolean canManageLanguages) {
		this.canManageLanguages = canManageLanguages;
	}


	public boolean isCanManageMessages() {
		return canManageMessages;
	}


	public void setCanManageMessages(boolean canManageMessages) {
		this.canManageMessages = canManageMessages;
	}


	public boolean isHasAllLanguages() {
		return hasAllLanguages;
	}


	public void setHasAllLanguages(boolean hasAllLanguages) {
		this.hasAllLanguages = hasAllLanguages;
	}


	public boolean isHasAllBundles() {
		return hasAllBundles;
	}


	public void setHasAllBundles(boolean hasAllBundles) {
		this.hasAllBundles = hasAllBundles;
	}

	public List<String> getLanguages() {
		return languages;
	}


	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}


	public List<String> getBundles() {
		return bundles;
	}


	public void setBundles(List<String> bundles) {
		this.bundles = bundles;
	}

	@Override
	public String toString() {
		return "UserRights [isSupervisor=" + isSupervisor + ", canValidate=" + canValidate + ", canTranslate="
				+ canTranslate + ", canDoImport=" + canDoImport + ", canDeleteTranslation=" + canDeleteTranslation
				+ ", canRefreshCache=" + canRefreshCache + ", canManageBundles=" + canManageBundles
				+ ", canManageLanguages=" + canManageLanguages + ", canManageMessages=" + canManageMessages
				+ ", hasAllLanguages=" + hasAllLanguages + ", hasAllBundles=" + hasAllBundles + ", languages="
				+ languages + ", bundles=" + bundles + "]";
	}

}

