package com.coface.corp.translationView.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MESSAGE", schema = "FWTEST")
@IdClass(MessageId.class)
@NamedQuery(name="Message.findAll", query="select msg from Message msg")
@XmlRootElement
public class Message implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String tagId;
  private String codeApp;
  private I18nBundle i18nBundle;
  private String usedInJavascript;
  private String usedInXml;
  private String suppressed;
  private String usedInHtml;

  public Message()
  {
  }

  public Message(MessageId messageId, I18nBundle i18nBundle)
  {
    this.tagId = messageId.getTagId();
    this.codeApp = messageId.getCodeApp();
    this.i18nBundle = i18nBundle;
  }
  
  public Message(MessageId messageId, I18nBundle i18nBundle, String usedInJavascript, String usedInXml, String suppressed, String usedInHtml)
  {
    this (messageId, i18nBundle);
    this.usedInJavascript = usedInJavascript;
    this.usedInXml = usedInXml;
    this.suppressed = suppressed;
    this.usedInHtml = usedInHtml;
  }

  @Id
  @Column(name = "TAG_ID", length = 100)
  public String getTagId()
  {
    return this.tagId;
  }
  
  public void setTagId(String tagId)
  {
    this.tagId = tagId;
  }

  @Id
  @Column(name = "CODE_APP", length = 100)
  public String getCodeApp()
  {
    return codeApp;
  }
  
  public void setCodeApp(String codeApp)
  {
    this.codeApp = codeApp;
  }

  @ManyToOne
  @PrimaryKeyJoinColumn(name = "CODE_APP", referencedColumnName = "CODE_PK")
  public I18nBundle getI18nBundle()
  {
    return this.i18nBundle;
  }

  public void setI18nBundle(I18nBundle i18nBundle)
  {
    this.i18nBundle = i18nBundle;
  }

  @Column(name = "USED_IN_JAVASCRIPT", length = 4)
  public String getUsedInJavascript()
  {
    return this.usedInJavascript;
  }

  public void setUsedInJavascript(String usedInJavascript)
  {
    this.usedInJavascript = usedInJavascript;
  }

  @Column(name = "USED_IN_XML", length = 4)
  public String getUsedInXml()
  {
    return this.usedInXml;
  }

  public void setUsedInXml(String usedInXml)
  {
    this.usedInXml = usedInXml;
  }

  @Column(name = "SUPPRESSED", length = 4)
  public String getSuppressed()
  {
    return this.suppressed;
  }

  public void setSuppressed(String suppressed)
  {
    this.suppressed = suppressed;
  }

  @Column(name = "USED_IN_HTML", length = 4)
  public String getUsedInHtml()
  {
    return this.usedInHtml;
  }

  public void setUsedInHtml(String usedInHtml)
  {
    this.usedInHtml = usedInHtml;
  }
}
