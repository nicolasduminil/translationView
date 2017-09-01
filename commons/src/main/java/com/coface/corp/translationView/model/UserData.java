package com.coface.corp.translationView.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "USER_DATA", schema = "FWTEST")
@NamedQuery(name="UserData.findAll", query="select ud from UserData ud")
@XmlRootElement
public class UserData implements Serializable
{
  private static final long serialVersionUID = 1L;
  private UserDataId id;
  private String password;
  private String firstname;
  private String lastname;
  private String app;
  private String lang;
  private String refLang;

  public UserData()
  {
  }

  public UserData(UserDataId id, String password, String firstname, String lastname, String app, String lang, String refLang)
  {
    this.id = id;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
    this.app = app;
    this.lang = lang;
    this.refLang = refLang;
  }

  @EmbeddedId
  public UserDataId getId()
  {
    return this.id;
  }

  public void setId(UserDataId id)
  {
    this.id = id;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getFirstname()
  {
    return firstname;
  }

  public void setFirstname(String firstname)
  {
    this.firstname = firstname;
  }

  public String getLastname()
  {
    return lastname;
  }

  public void setLastname(String lastname)
  {
    this.lastname = lastname;
  }

  public String getApp()
  {
    return app;
  }

  public void setApp(String app)
  {
    this.app = app;
  }

  public String getLang()
  {
    return lang;
  }

  public void setLang(String lang)
  {
    this.lang = lang;
  }

  public String getRefLang()
  {
    return refLang;
  }

  public void setRefLang(String refLang)
  {
    this.refLang = refLang;
  }

  @Override
  public int hashCode()
  {
    return 61;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (!(obj instanceof Translation))
      return false;
    return id != null && id.equals(((UserData) obj).id);
  }
}
