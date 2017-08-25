package com.coface.corp.translationView.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TRANSLATION", schema = "FWTEST")
@IdClass(TranslationId.class)
@NamedQueries ({
  @NamedQuery(name = "Translation.findAll", query = "select tx from Translation tx"),
  @NamedQuery(name = "Translation.findAllWithDefaultEmptyValue", query = "select tx from Translation tx join tx.message msg where msg.codeApp = :bundleId and msg.suppressed = '0' and tx.clang = :langId")
})
@XmlRootElement
public class Translation implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String atagId;
  private String bcodeApp;
  private String clang;
  private I18nLanguage i18nLanguage;
  private Message message;
  private Date lastUpdate;
  private String status;
  private String value;
  private String newValue;
  private String comments;
  private String updatorName;

  public Translation()
  {
  }

  public Translation(TranslationId id, I18nLanguage i18nLanguage, Message message, Date lastUpdate, String status)
  {
    this.atagId = id.getAtagId();
    this.bcodeApp = id.getBcodeApp();
    this.clang = id.getClang();
    this.i18nLanguage = i18nLanguage;
    this.message = message;
    this.lastUpdate = lastUpdate;
    this.status = status;
  }

  public Translation(TranslationId id, I18nLanguage i18nLanguage, Message message, Date lastUpdate, String status, String value, String newValue, String comments, String updatorName)
  {
    this(id, i18nLanguage, message, lastUpdate, status);
    this.value = value;
    this.newValue = newValue;
    this.comments = comments;
    this.updatorName = updatorName;
  }

  @Id
  @Column(name = "TAG_ID", length = 100)
  public String getAtagId()
  {
    return atagId;
  }

  public void setAtagId(String atagId)
  {
    this.atagId = atagId;
  }

  @Id
  @Column(name = "CODE_APP", length = 100)
  public String getBcodeApp()
  {
    return bcodeApp;
  }

  public void setBcodeApp(String bcodeApp)
  {
    this.bcodeApp = bcodeApp;
  }

  @Id
  @Column(name = "LANG", length = 16)
  public String getClang()
  {
    return clang;
  }

  public void setClang(String clang)
  {
    this.clang = clang;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @PrimaryKeyJoinColumn(name = "LANG", referencedColumnName = "CODE_PK")
  public I18nLanguage getI18nLanguage()
  {
    return this.i18nLanguage;
  }

  public void setI18nLanguage(I18nLanguage i18nLanguage)
  {
    this.i18nLanguage = i18nLanguage;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @PrimaryKeyJoinColumns({ @PrimaryKeyJoinColumn(name = "MESSAGE_TAG_ID", referencedColumnName = "TAG_ID"), @PrimaryKeyJoinColumn(name = "MESSAGE_CODE_APP", referencedColumnName = "CODE_APP") })
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
}
