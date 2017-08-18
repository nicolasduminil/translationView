package com.coface.corp.translationView.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.coface.corp.translationView.model.I18nBundle;

@XmlRootElement
public class I18nBundleList
{
  private List<I18nBundle> bundles;

  public I18nBundleList()
  {
  }

  public I18nBundleList(List<I18nBundle> bundles)
  {
    this.bundles = bundles;
  }

  public List<I18nBundle> getBundles()
  {
    return bundles;
  }

  public void setBundles(List<I18nBundle> bundles)
  {
    this.bundles = bundles;
  }

}
