package com.coface.corp.translationView.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "USER_LANGUAGE", schema = "FWTEST")
@NamedQuery(name = "UserLanguage.findAll", query = "select ul from UserLanguage ul")
@XmlRootElement
public class UserLanguage implements Serializable
{
  private static final long serialVersionUID = 1L;
  private UserLanguageId id;

  public UserLanguage()
  {
  }

  public UserLanguage(UserLanguageId id)
  {
    this.id = id;
  }

  @EmbeddedId
  public UserLanguageId getId()
  {
    return this.id;
  }

  public void setId(UserLanguageId id)
  {
    this.id = id;
  }

  @Override
  public int hashCode()
  {
    return 71;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (!(obj instanceof Translation))
      return false;
    return id != null && id.equals(((UserLanguage) obj).id);
  }
}
