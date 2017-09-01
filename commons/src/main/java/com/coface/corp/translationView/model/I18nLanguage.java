package com.coface.corp.translationView.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "I18N_LANGUAGES", schema = "FWTEST")
@NamedQuery(name="Language.findAll", query="select lang from I18nLanguage lang")
@XmlRootElement
public class I18nLanguage implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String codePk;
  private String label;
  private String isReference;
  private String isActive;

  public I18nLanguage()
  {
  }

  public I18nLanguage(String codePk, String label)
  {
    this.codePk = codePk;
    this.label = label;
  }

  public I18nLanguage(String codePk, String label, String isReference, String isActive)
  {
    this.codePk = codePk;
    this.label = label;
    this.isReference = isReference;
    this.isActive = isActive;
  }

  @Id
  @Column(name = "CODE_PK", length = 60)
  public String getCodePk()
  {
    return this.codePk;
  }

  public void setCodePk(String codePk)
  {
    this.codePk = codePk;
  }

  @Column(name = "LABEL", nullable = false, length = 240)
  public String getLabel()
  {
    return this.label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  @Column(name = "IS_REFERENCE", length = 4)
  public String getIsReference()
  {
    return this.isReference;
  }

  public void setIsReference(String isReference)
  {
    this.isReference = isReference;
  }

  @Column(name = "IS_ACTIVE", length = 4)
  public String getIsActive()
  {
    return this.isActive;
  }

  public void setIsActive(String isActive)
  {
    this.isActive = isActive;
  }

  @Override
  public int hashCode()
  {
    return 37;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj) return true;
    if (!(obj instanceof I18nLanguage)) return false;
    return codePk != null && codePk.equals(((I18nLanguage)obj).codePk);
  }
}
