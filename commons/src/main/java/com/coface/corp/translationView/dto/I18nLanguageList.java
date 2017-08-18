package com.coface.corp.translationView.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.coface.corp.translationView.model.I18nLanguage;

@XmlRootElement
public class I18nLanguageList
{
  private List<I18nLanguage> languages;

  public I18nLanguageList()
  {
  }

  public I18nLanguageList(List<I18nLanguage> languages)
  {
    super();
    this.languages = languages;
  }

  public List<I18nLanguage> getLanguages()
  {
    return languages;
  }

  public void setLanguages(List<I18nLanguage> languages)
  {
    this.languages = languages;
  }
}
