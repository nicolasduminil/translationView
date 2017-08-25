package com.coface.corp.translationView.service.export;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.coface.corp.translationView.model.Translation;

/**
 * This is the data model used to generate the translations' Excel spreadsheets. 
 */
public class ExcelBundleDataModel {

	private Map<String, Map<String, List<String>>> bundleDataSet = new HashMap<String, Map<String, List<String>>>();
	private List<String> locales = new ArrayList<String>();
	private Map<String, Integer> localesColumIndex = new HashMap<String, Integer>();

	public void feedWithLocalizedTranslations(final String applicationName, final String locale,
			final List<Translation> localizedTranslations) {
		if (this.bundleDataSet.get(applicationName) == null) {
			this.bundleDataSet.put(applicationName, new LinkedHashMap<String, List<String>>());
		}
		Map<String, List<String>> bundleData = this.bundleDataSet.get(applicationName);

		if (!this.locales.contains(locale)) {
			this.addLocale(locale);
			for (List<String> element : bundleData.values())
				element.add(null);
		}

		for (Translation localizedTranslation : localizedTranslations) {
			if (bundleData.get(localizedTranslation.getAtagId()) == null) {
				bundleData.put(localizedTranslation.getAtagId(), new ArrayList<String>());
			}
			int indexForTranslation = this.getIndexForTranslation(locale);
			bundleData.get(localizedTranslation.getAtagId()).add(indexForTranslation, localizedTranslation.getValue());
		}
	}

	private void addLocale(final String locale) {
		this.localesColumIndex.put(locale, this.locales.size());
		this.locales.add(locale);
	}

	private int getIndexForTranslation(final String locale) {
		return this.localesColumIndex.get(locale);
	}

	public Map<String, List<String>> getBundleDataFor(final String applicationName) {
		return this.bundleDataSet.get(applicationName);
	}

	public List<String> getApplications() {
		return Arrays.asList(this.bundleDataSet.keySet().toArray(new String[0]));
	}

	public List<String> getLocalesAvailables() {
		return this.locales;
	}

	public List<String> getTagIdAvailablesFor(final String applicationName) {
		return Arrays.asList(this.bundleDataSet.get(applicationName).keySet().toArray(new String[0]));
	}

	public List<String> getTranslationsFor(final String applicationName, final String tagId) {
		return this.bundleDataSet.get(applicationName).get(tagId);

	}	
	
}
