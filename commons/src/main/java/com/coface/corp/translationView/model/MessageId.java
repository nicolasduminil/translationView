package com.coface.corp.translationView.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessageId implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String tagId;
  private String codeApp;

  public MessageId()
  {
  }

  public MessageId(String tagId, String codeApp)
  {
    this.tagId = tagId;
    this.codeApp = codeApp;
  }

  public String getTagId()
  {
    return this.tagId;
  }

  public void setTagId(String tagId)
  {
    this.tagId = tagId;
  }

  public String getCodeApp()
  {
    return this.codeApp;
  }

  public void setCodeApp(String codeApp)
  {
    this.codeApp = codeApp;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((codeApp == null) ? 0 : codeApp.hashCode());
    result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
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
    MessageId other = (MessageId) obj;
    if (codeApp == null)
    {
      if (other.codeApp != null)
        return false;
    }
    else if (!codeApp.equals(other.codeApp))
      return false;
    if (tagId == null)
    {
      if (other.tagId != null)
        return false;
    }
    else if (!tagId.equals(other.tagId))
      return false;
    return true;
  }
}
