package com.coface.corp.translationView.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.coface.corp.translationView.model.Translation;

@XmlRootElement
public class TranslationList
{
  private List<Translation> translations;

  public TranslationList()
  {
  }

  public TranslationList(List<Translation> translations)
  {
    super();
    this.translations = translations;
  }

  public List<Translation> getTranslations()
  {
    return translations;
  }

  public void setTranslations(List<Translation> translations)
  {
    this.translations = translations;
  }
}
