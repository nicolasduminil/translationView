package com.coface.corp.translationView.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.coface.corp.translationView.model.UserLanguage;

@XmlRootElement
public class UserLanguageList
{
  private List<UserLanguage> userLanguages;

  public UserLanguageList()
  {
  }

  public UserLanguageList(List<UserLanguage> userLanguages)
  {
    super();
    this.userLanguages = userLanguages;
  }

  public List<UserLanguage> getUserLanguages()
  {
    return userLanguages;
  }

  public void setUserLanguages(List<UserLanguage> userLanguages)
  {
    this.userLanguages = userLanguages;
  }
}
