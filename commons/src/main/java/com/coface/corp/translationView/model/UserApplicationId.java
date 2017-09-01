package com.coface.corp.translationView.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Embeddable
public class UserApplicationId implements java.io.Serializable
{
  private static final long serialVersionUID = 1L;
  private String userId;
  private String application;

  public UserApplicationId()
  {
  }

  public UserApplicationId(String userId, String application)
  {
    this.userId = userId;
    this.application = application;
  }

  @Column(name = "USER_ID", nullable = false, length = 80)
  public String getUserId()
  {
    return this.userId;
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  @Column(name = "APPLICATION", nullable = false, length = 400)
  public String getApplication()
  {
    return this.application;
  }

  public void setApplication(String application)
  {
    this.application = application;
  }

  @Override
  public int hashCode()
  {
    return 59;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (!(obj instanceof TranslationId))
      return false;
    return userId != null && userId.equals(((UserApplicationId) obj).userId) && application != null && application.equals(((UserApplicationId) obj).application);
  }
}
