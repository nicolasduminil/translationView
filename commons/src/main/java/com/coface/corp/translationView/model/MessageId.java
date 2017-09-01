package com.coface.corp.translationView.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

@Embeddable
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

  @Column(name = "TAG_ID", nullable = false, length = 400)
  public String getTagId()
  {
    return this.tagId;
  }

  public void setTagId(String tagId)
  {
    this.tagId = tagId;
  }

  @Column(name = "CODE_APP", nullable = false, length = 400)
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
    return 47;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (!(obj instanceof MessageId))
      return false;
    return codeApp != null && codeApp.equals(((MessageId) obj).codeApp) && tagId != null && tagId.equals(((MessageId) obj).tagId);
  }
}
