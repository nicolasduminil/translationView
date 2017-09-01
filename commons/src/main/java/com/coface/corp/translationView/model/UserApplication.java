package com.coface.corp.translationView.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "USER_APPLICATION", schema = "FWTEST")
@NamedQuery(name = "UserApplication.findAll", query = "select ua from UserApplication ua")
@XmlRootElement
public class UserApplication implements Serializable
{
  private static final long serialVersionUID = 1L;
  private UserApplicationId id;

  public UserApplication()
  {
  }

  public UserApplication(UserApplicationId id)
  {
    this.id = id;
  }

  @EmbeddedId
  public UserApplicationId getId()
  {
    return this.id;
  }

  public void setId(UserApplicationId id)
  {
    this.id = id;
  }

  @Override
  public int hashCode()
  {
    return 57;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (!(obj instanceof Translation))
      return false;
    return id != null && id.equals(((UserApplication) obj).id);
  }
}
