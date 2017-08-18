package com.coface.corp.translationView.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.coface.corp.translationView.model.UserApplication;

@XmlRootElement
public class UserApplicationList
{
  private List<UserApplication> applications;

  public UserApplicationList()
  {
  }

  public UserApplicationList(List<UserApplication> applications)
  {
    super();
    this.applications = applications;
  }

  public List<UserApplication> getApplications()
  {
    return applications;
  }

  public void setApplications(List<UserApplication> applications)
  {
    this.applications = applications;
  }
}
