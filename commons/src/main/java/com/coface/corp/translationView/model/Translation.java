package com.coface.corp.translationView.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TRANSLATION", schema = "FWTEST")
@NamedQueries({ @NamedQuery(name = "Translation.findAll", query = "select tx from Translation tx"),
    @NamedQuery(name = "Translation.findAllWithDefaultEmptyValue", query = "select tx from Translation tx join tx.message msg where msg.i18nBundle.codePk = :bundleId and msg.suppressed = '0' and tx.i18nLanguage.codePk = :langId"),
    @NamedQuery(name = "Translation.findByBundleAndLanguage", query = "select tx from Translation tx join tx.message msg where msg.i18nBundle.codePk = :bundleId and msg.suppressed = '0' and tx.i18nLanguage.codePk = :langId") })
@XmlRootElement
public class Translation implements Serializable
{
  private static final long serialVersionUID = 1L;
  private TranslationId id;
  private I18nLanguage i18nLanguage;
  private Date lastUpdate;
  private String status;
  private String value;
  private String newValue;
  private String comments;
  private String updatorName;
  private Message message;

  public Translation()
  {
  }

  public Translation(TranslationId id, I18nLanguage i18nLanguage, Message message, Date lastUpdate, String status)
  {
    this.id = id;
    this.i18nLanguage = i18nLanguage;
    this.lastUpdate = lastUpdate;
    this.status = status;
    this.message = message;
  }

  public Translation(TranslationId id, I18nLanguage i18nLanguage, Message message, Date lastUpdate, String status, String value, String newValue, String comments, String updatorName)
  {
    this(id, i18nLanguage, message, lastUpdate, status);
    this.value = value;
    this.newValue = newValue;
    this.comments = comments;
    this.updatorName = updatorName;
  }

  @EmbeddedId
  @AttributeOverrides({ @AttributeOverride(name = "tagId", column = @Column(name = "TAG_ID", nullable = false, length = 400)), @AttributeOverride(name = "codeApp", column = @Column(name = "CODE_APP", nullable = false, length = 400)),
      @AttributeOverride(name = "lang", column = @Column(name = "LANG", nullable = false, length = 64)) })
  public TranslationId getId()
  {
    return this.id;
  }

  public void setId(TranslationId id)
  {
    this.id = id;
  }

  @ManyToOne
  @JoinColumn(name = "LANG", nullable = false, insertable = false, updatable = false)
  public I18nLanguage getI18nLanguage()
  {
    return this.i18nLanguage;
  }

  public void setI18nLanguage(I18nLanguage i18nLanguages)
  {
    this.i18nLanguage = i18nLanguages;
  }

  @ManyToOne
  @JoinColumns({ @JoinColumn(name = "TAG_ID", referencedColumnName = "TAG_ID", nullable = false, insertable = false, updatable = false),
      @JoinColumn(name = "CODE_APP", referencedColumnName = "CODE_APP", nullable = false, insertable = false, updatable = false) })
  public Message getMessage()
  {
    return this.message;
  }

  public void setMessage(Message message)
  {
    this.message = message;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "LAST_UPDATE", nullable = false, length = 7)
  public Date getLastUpdate()
  {
    return this.lastUpdate;
  }

  public void setLastUpdate(Date lastUpdate)
  {
    this.lastUpdate = lastUpdate;
  }

  @Column(name = "STATUS", nullable = false, length = 120)
  public String getStatus()
  {
    return this.status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  @Column(name = "VALUE", length = 4000)
  public String getValue()
  {
    return this.value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  @Column(name = "NEW_VALUE", length = 4000)
  public String getNewValue()
  {
    return this.newValue;
  }

  public void setNewValue(String newValue)
  {
    this.newValue = newValue;
  }

  @Column(name = "COMMENTS", length = 4000)
  public String getComments()
  {
    return this.comments;
  }

  public void setComments(String comments)
  {
    this.comments = comments;
  }

  @Column(name = "UPDATOR_NAME", length = 80)
  public String getUpdatorName()
  {
    return this.updatorName;
  }

  public void setUpdatorName(String updatorName)
  {
    this.updatorName = updatorName;
  }

  @Override
  public int hashCode()
  {
    return 51;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (!(obj instanceof Translation))
      return false;
    return id != null && id.equals(((Translation) obj).id);
  }
}
