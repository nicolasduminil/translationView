package com.coface.corp.translationView.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MESSAGE", schema = "FWTEST")
@NamedQueries({
@NamedQuery(name = "Message.findAll", query = "select msg from Message msg"),
@NamedQuery (name="Message.findAllWithNoTranslations", query = "select ts from Translation ts join ts.message msg where msg.id.codeApp = :msgId and msg.suppressed = '0' and (ts.i18nLanguage = :lang or ts.i18nLanguage is null) and ts.id.tagId is null"),
@NamedQuery (name="Message.findByBundle", query = "select msg from Message msg where msg.id.codeApp = :codeApp and msg.id.codeApp = msg.i18nBundle.codePk")})
@XmlRootElement
public class Message implements Serializable
{
  private static final long serialVersionUID = 1L;
  private MessageId id;
  private I18nBundle i18nBundle;
  private String usedInJavascript;
  private String usedInXml;
  private String suppressed;
  private String usedInHtml;

  public Message()
  {
  }

  public Message(MessageId id, I18nBundle i18nBundle)
  {
    this.id = id;
    this.i18nBundle = i18nBundle;
  }

  public Message(MessageId id, I18nBundle i18nBundle, String usedInJavascript, String usedInXml, String suppressed, String usedInHtml)
  {
    this.id = id;
    this.i18nBundle = i18nBundle;
    this.usedInJavascript = usedInJavascript;
    this.usedInXml = usedInXml;
    this.suppressed = suppressed;
    this.usedInHtml = usedInHtml;
  }

  @EmbeddedId
  public MessageId getId()
  {
    return this.id;
  }

  public void setId(MessageId id)
  {
    this.id = id;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "CODE_APP", nullable = false, insertable = false, updatable = false)
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

  @Override
  public int hashCode()
  {
    return 41;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (!(obj instanceof Message))
      return false;
    return id != null && id.equals(((Message) obj).id);
  }
}
