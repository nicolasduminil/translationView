package com.coface.corp.translationView.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "I18N_BUNDLES", schema = "FWTEST")
@NamedQueries ({
@NamedQuery(name="Bundle.findAll", query="select bundle from I18nBundle bundle"),
@NamedQuery(name="Bundle.findByUser", query="select bundle from I18nBundle bundle where bundle.isActive = '1' and bundle.codePk = :codePk")})
@XmlRootElement
public class I18nBundle implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String codePk;
  private String label;
  private String isActive;

  public I18nBundle()
  {
  }

  public I18nBundle(String codePk)
  {
    this.codePk = codePk;
  }

  public I18nBundle(String codePk, String label, String isActive)
  {
    this.codePk = codePk;
    this.label = label;
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

  @Column(name = "LABEL", length = 240)
  public String getLabel()
  {
    return this.label;
  }

  public void setLabel(String label)
  {
    this.label = label;
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
}
