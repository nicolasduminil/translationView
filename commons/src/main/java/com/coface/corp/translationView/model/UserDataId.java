package com.coface.corp.translationView.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Embeddable
public class UserDataId implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String login;
  private String groupId;

  public UserDataId()
  {
  }

  public UserDataId(String login, String groupId)
  {
    this.login = login;
    this.groupId = groupId;
  }

  @Column(name = "LOGIN", nullable = false, length = 80)
  public String getLogin()
  {
    return this.login;
  }

  public void setLogin(String login)
  {
    this.login = login;
  }


  @Column(name = "GROUP_ID", nullable = false, length = 160)
  public String getGroupId()
  {
    return this.groupId;
  }

  public void setGroupId(String groupId)
  {
    this.groupId = groupId;
  }

  @Override
  public int hashCode()
  {
    return 67;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (!(obj instanceof TranslationId))
      return false;
    return login != null && login.equals(((UserDataId) obj).login) && groupId != null && groupId.equals(((UserDataId) obj).groupId);
  }
}
