package com.coface.corp.translationView.service.export;

import java.util.List;

import com.coface.corp.translationView.model.I18nLanguage;
import com.coface.corp.translationView.model.Translation;

public interface ExportFileService {
	public ExportContext generateClassesJarFile(String applicationBundleId, List<I18nLanguage> languageList) throws Exception;
	public ExportContext generatePropertiesJarFile(String applicationBundleId, String defaultLanguageId, List<I18nLanguage> languageList) throws Exception;
	public ExportContext generateXlsFile(String[] applicationBundleIds, String[] languageIds) throws Exception;
	public ExportContext generateXlsFileForListTranslation(List<Translation> translations, String languageId, String bundleId) throws Exception;
}
