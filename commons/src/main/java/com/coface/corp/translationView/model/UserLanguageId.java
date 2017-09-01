package com.coface.corp.translationView.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Embeddable
public class UserLanguageId implements java.io.Serializable
{
  private static final long serialVersionUID = 1L;
  private String userId;
  private String lang;

  public UserLanguageId()
  {
  }

  public UserLanguageId(String userId, String lang)
  {
    this.userId = userId;
    this.lang = lang;
  }

  @Column(name = "USER_ID", length = 80)
  public String getUserId()
  {
    return this.userId;
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  @Column(name = "LANG", length = 64)
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
    return 73;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (!(obj instanceof TranslationId))
      return false;
    return userId != null && userId.equals(((UserLanguageId) obj).userId) && lang != null && lang.equals(((UserLanguageId) obj).lang);
  }
}
