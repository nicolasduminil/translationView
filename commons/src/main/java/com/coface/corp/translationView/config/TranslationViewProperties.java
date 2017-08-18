package com.coface.corp.translationView.config;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coface.corp.framework.configuration.ConfigurationException;
import com.coface.corp.framework.configuration.ManagedProperties;
import com.coface.corp.framework.configuration.PropertiesFactory;

public class TranslationViewProperties {

	private static final Log logger = LogFactory.getLog(TranslationViewProperties.class);

	private static TranslationViewProperties instance = new TranslationViewProperties();

	private static final String PROPERTY_FILE_NAME = "translationView.config";

	private static final Object LOCK = new Object();

	private ManagedProperties configuration;

	public static TranslationViewProperties getInstance() {
		return instance;
	}

	private ManagedProperties getConfiguration() {
		// Lazy loading
		if (this.configuration == null) {
			synchronized (LOCK) {
				// Double check
				if (this.configuration == null) {
					try {
						logger.debug("Loading managed properties " + PROPERTY_FILE_NAME + "...");
						this.configuration = PropertiesFactory.getProperties(PROPERTY_FILE_NAME);
					}
					catch (ConfigurationException e) {
						logger.warn("Cannot load managed properties file " + PROPERTY_FILE_NAME, e);
					}
				}
			}
		}
		return configuration;
	}

	private String getProperty(String propertyName) {
		String property = null;

		// Get property from managed properties
		if (getConfiguration() != null) {
			property = getConfiguration().getProperty(propertyName);
		}

		// Return
		return property;
	}

	private String getProperty(String propertyName, String defaultValue) {
		String property = null;

		try {
			// Get required property from configuration
			property = getProperty(propertyName);
		}
		catch (Exception e) {
			logger.debug("Cannot read " + propertyName + " from " + PROPERTY_FILE_NAME + ": " + e.getMessage()
					+ ". Using default value " + defaultValue);
			logger.trace(e);
		}

		// Default value
		if (StringUtils.isBlank(property)) {
			property = defaultValue;
		}

		// Return
		return property;
	}

	public String getEnvBackgroundColor() {
		return getProperty("environment.signal.colorBackground", "#03375f");
	}

	public String getEnvForegroundColor() {
		return getProperty("environment.signal.colorForeground", "#FFFFFF");
	}

	public String getEnvShortName() {
		return getProperty("environment.signal.shortName", null);
	}

}
