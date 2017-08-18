package com.coface.corp.translationView.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
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
  @AttributeOverrides({ @AttributeOverride(name = "userId", column = @Column(name = "USER_ID", length = 80)), @AttributeOverride(name = "lang", column = @Column(name = "LANG", length = 64)) })
  public UserLanguageId getId()
  {
    return this.id;
  }

  public void setId(UserLanguageId id)
  {
    this.id = id;
  }
}
