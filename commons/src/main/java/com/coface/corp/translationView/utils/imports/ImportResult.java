package com.coface.corp.translationView.utils.imports;

import java.util.ArrayList;
import java.util.List;

import com.coface.corp.translationView.model.I18nBundle;
import com.coface.corp.translationView.model.Message;
import com.coface.corp.translationView.model.Translation;

public class ImportResult
{

  private List<Translation> translationsUpdated = new ArrayList<Translation>();
  private List<Translation> translationsCreated = new ArrayList<Translation>();
  private List<Message> messagesCreated = new ArrayList<Message>();
  private List<I18nBundle> applicationBundleCreated = new ArrayList<I18nBundle>();;

  public void addAll(ImportResult importProperties)
  {
    translationsUpdated.addAll(importProperties.getTranslationsUpdated());
    translationsCreated.addAll(importProperties.getTranslationsCreated());
    messagesCreated.addAll(importProperties.getMessagesCreated());
    applicationBundleCreated.addAll(importProperties.getI18nBundlesCreated());
  }

  public List<Translation> getTranslationsUpdated()
  {
    return translationsUpdated;
  }

  public void setTranslationsUpdated(List<Translation> translationsUpdated)
  {
    this.translationsUpdated = translationsUpdated;
  }

  public List<Translation> getTranslationsCreated()
  {
    return translationsCreated;
  }

  public void setTranslationsCreated(List<Translation> translationsCreated)
  {
    this.translationsCreated = translationsCreated;
  }

  public List<Message> getMessagesCreated()
  {
    return messagesCreated;
  }

  public void setMessagesCreated(List<Message> messagesCreated)
  {
    this.messagesCreated = messagesCreated;
  }

  public void addI18nBundleCreated(I18nBundle applicationBundle)
  {
    applicationBundleCreated.add(applicationBundle);
  }

  public List<I18nBundle> getI18nBundlesCreated()
  {
    return applicationBundleCreated;
  }

}
