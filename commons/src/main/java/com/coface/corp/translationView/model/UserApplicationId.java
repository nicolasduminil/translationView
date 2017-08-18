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
    final int prime = 31;
    int result = 1;
    result = prime * result + ((application == null) ? 0 : application.hashCode());
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
    UserApplicationId other = (UserApplicationId) obj;
    if (application == null)
    {
      if (other.application != null)
        return false;
    }
    else if (!application.equals(other.application))
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
