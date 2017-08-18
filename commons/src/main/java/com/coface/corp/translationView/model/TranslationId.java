package com.coface.corp.translationView.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TranslationId implements java.io.Serializable
{
  private static final long serialVersionUID = 1L;
  private String atagId;
  private String bcodeApp;
  private String clang;

  public TranslationId()
  {
  }

  public TranslationId(String tagId, String codeApp, String lang)
  {
    this.atagId = tagId;
    this.bcodeApp = codeApp;
    this.clang = lang;
  }

  public String getAtagId()
  {
    return this.atagId;
  }

  public void setAtagId(String tagId)
  {
    this.atagId = tagId;
  }

  public String getBcodeApp()
  {
    return this.bcodeApp;
  }

  public void setBcodeApp(String codeApp)
  {
    this.bcodeApp = codeApp;
  }

  public String getClang()
  {
    return this.clang;
  }

  public void setClang(String lang)
  {
    this.clang = lang;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((atagId == null) ? 0 : atagId.hashCode());
    result = prime * result + ((bcodeApp == null) ? 0 : bcodeApp.hashCode());
    result = prime * result + ((clang == null) ? 0 : clang.hashCode());
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
    TranslationId other = (TranslationId) obj;
    if (atagId == null)
    {
      if (other.atagId != null)
        return false;
    }
    else if (!atagId.equals(other.atagId))
      return false;
    if (bcodeApp == null)
    {
      if (other.bcodeApp != null)
        return false;
    }
    else if (!bcodeApp.equals(other.bcodeApp))
      return false;
    if (clang == null)
    {
      if (other.clang != null)
        return false;
    }
    else if (!clang.equals(other.clang))
      return false;
    return true;
  }
}
