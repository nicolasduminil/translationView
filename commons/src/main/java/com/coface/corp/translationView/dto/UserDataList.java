package com.coface.corp.translationView.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.coface.corp.translationView.model.UserData;

@XmlRootElement
public class UserDataList
{
  private List<UserData> userData;

  public UserDataList()
  {
  }

  public UserDataList(List<UserData> userData)
  {
    super();
    this.userData = userData;
  }

  public List<UserData> getUserData()
  {
    return userData;
  }

  public void setUserData(List<UserData> userData)
  {
    this.userData = userData;
  }
}
