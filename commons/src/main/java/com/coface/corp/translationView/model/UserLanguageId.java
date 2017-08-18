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
    final int prime = 31;
    int result = 1;
    result = prime * result + ((lang == null) ? 0 : lang.hashCode());
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserLanguageId other = (UserLanguageId) obj;
    if (lang == null)
    {
      if (other.lang != null)
        return false;
    }
    else if (!lang.equals(other.lang))
      return false;
    if (userId == null)
    {
      if (other.userId != null)
        return false;
    }
    else if (!userId.equals(other.userId))
      return false;
    return true;
  }
}
