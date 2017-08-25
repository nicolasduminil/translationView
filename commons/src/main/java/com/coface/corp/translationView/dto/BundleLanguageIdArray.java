package com.coface.corp.translationView.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BundleLanguageIdArray
{
  @XmlElement
  private String[] bundleIds;
  @XmlElement
  private String[] langIds;
  
  public BundleLanguageIdArray() 
  {
  }

  public BundleLanguageIdArray(String[] bundleIds, String[] langIds)
  {
    super();
    this.bundleIds = bundleIds;
    this.langIds = langIds;
  }

  public String[] getBundleIds()
  {
    return bundleIds;
  }

  public void setBundleIds(String[] bundleIds)
  {
    this.bundleIds = bundleIds;
  }

  public String[] getLangIds()
  {
    return langIds;
  }

  public void setLangIds(String[] langIds)
  {
    this.langIds = langIds;
  }
}
