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
  @AttributeOverrides({ @AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false, length = 80)),
      @AttributeOverride(name = "application", column = @Column(name = "APPLICATION", nullable = false, length = 400)) })
  public UserApplicationId getId()
  {
    return this.id;
  }

  public void setId(UserApplicationId id)
  {
    this.id = id;
  }
}
