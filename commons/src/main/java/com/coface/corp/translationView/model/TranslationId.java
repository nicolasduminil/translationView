package com.coface.corp.translationView.model;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

@Embeddable
@XmlRootElement
public class TranslationId implements java.io.Serializable
{
  private static final long serialVersionUID = 1L;
  private String tagId;
  private String codeApp;
  private String lang;

  public TranslationId()
  {
  }

  public TranslationId(String tagId, String codeApp, String lang)
  {
    this.tagId = tagId;
    this.codeApp = codeApp;
    this.lang = lang;
  }

  public String getTagId()
  {
    return this.tagId;
  }

  public void setTagId(String tagId)
  {
    this.tagId = tagId;
  }

  public String getCodeApp()
  {
    return this.codeApp;
  }

  public void setCodeApp(String codeApp)
  {
    this.codeApp = codeApp;
  }

  public String getLang()
  {
    return this.lang;
  }

  public void setLang(String lang)
  {
    this.lang = lang;
  }

  @Override
  public int hashCode()
  {
    return 53;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (!(obj instanceof TranslationId))
      return false;
    return codeApp != null && codeApp.equals(((TranslationId) obj).codeApp) && tagId != null && tagId.equals(((TranslationId) obj).tagId)
        && lang != null && lang.equals(((TranslationId) obj).lang);
  }
}
