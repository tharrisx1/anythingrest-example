package org.tharrisx.frameworkexample.beans;

import java.util.Date;
import java.util.Locale;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.tharrisx.framework.bean.Bean;
import org.tharrisx.framework.bean.StampedBean;
import org.tharrisx.framework.pipe.ProtectionCheck;
import org.tharrisx.framework.pipe.ProtectionType;
import org.tharrisx.framework.pipe.annotations.PipePropertyOrder;
import org.tharrisx.framework.pipe.annotations.PipeProtection;
import org.tharrisx.framework.pipe.codec.EncryptedIdCodec;
import org.tharrisx.framework.pipe.converters.StringDateConverter;
import org.tharrisx.framework.rest.annotations.InstanceResource;
import org.tharrisx.framework.rest.annotations.RootedTypeResourceName;
import org.tharrisx.framework.rest.annotations.TypeAllResource;
import org.tharrisx.framework.rest.annotations.TypeMatchResource;
import org.tharrisx.framework.rest.annotations.TypeResource;
import org.tharrisx.frameworkexample.resources.UserResources.UserInstanceResource;
import org.tharrisx.frameworkexample.resources.UserResources.UsersAllResource;
import org.tharrisx.frameworkexample.resources.UserResources.UsersMatchResource;
import org.tharrisx.frameworkexample.resources.UserResources.UsersResource;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * Example User bean
 * 
 * @author tharrisx
 * @since 1.0.0
 * @version 1.0.0
 */
@Entity
@Table(name = "rest_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@RootedTypeResourceName("users")
@TypeResource(UsersResource.class)
@TypeAllResource(UsersAllResource.class)
@TypeMatchResource(UsersMatchResource.class)
@InstanceResource(UserInstanceResource.class)
@XStreamAlias("user")
public class User extends StampedBean {

  public static final String NAME_USER_OWNER = "PUBLIC";
  public static final ProtectionType USER_OWNER;
  static {
    USER_OWNER = ProtectionType.defineValue(NAME_USER_OWNER, new ProtectionCheck() {

      @Override
      public boolean isAuthorized(Bean bean, String userId) {
        return ((User) bean).getId().equals(new EncryptedIdCodec().decode(userId));
      }
    });
  }

  @Basic
  @Column(length = 200, nullable = true, updatable = true)
  @PipePropertyOrder(2.0)
  private String externalProfileId;

  public String getExternalProfileId() {
    return this.externalProfileId;
  }

  public void setExternalProfileId(String arg) {
    this.externalProfileId = arg;
  }

  @Basic
  @Column(length = 200, nullable = true, updatable = true)
  @PipePropertyOrder(3.0)
  private String externalProfileSource;

  public String getExternalProfileSource() {
    return this.externalProfileSource;
  }

  public void setExternalProfileSource(String arg) {
    this.externalProfileSource = arg;
  }

  @Basic
  @PipePropertyOrder(4.0)
  private Integer status = Integer.valueOf(1);

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer arg) {
    this.status = arg;
  }

  @Basic
  @PipePropertyOrder(10.0)
  @PipeProtection(ProtectionType.NAME_ADMIN_ONLY)
  private Integer testUser = Integer.valueOf(0);

  public Integer getTestUser() {
    return this.testUser;
  }

  public void setTestUser(Integer arg) {
    this.testUser = arg;
  }

  @Basic
  @Column(length = 40, nullable = true, updatable = true)
  //@PipeProtection(NAME_USER_OWNER)
  @PipeProtection(ProtectionType.NAME_ADMIN_ONLY)
  private String pin;

  public String getPin() {
    return this.pin;
  }

  public void setPin(String arg) {
    this.pin = arg;
  }

  @Basic
  @Column(length = 40, nullable = true, updatable = true)
  //@PipeProtection(NAME_USER_OWNER)
  @PipeProtection(ProtectionType.NAME_ADMIN_ONLY)
  private String authToken;

  public String getAuthToken() {
    return this.authToken;
  }

  public void setAuthToken(String arg) {
    this.authToken = arg;
  }

  @Basic
  @Column(length = 1, nullable = true, updatable = true)
  @PipePropertyOrder(20.0)
  private String gender;

  public String getGender() {
    return this.gender;
  }

  public void setGender(String arg) {
    this.gender = arg;
  }

  @Basic
  @Column(length = 240, nullable = true, updatable = true)
  @PipePropertyOrder(21.0)
  private String email;

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String arg) {
    this.email = arg;
  }

  @Basic
  //@Type(type = "LongTimestamp")
  @Column(length = 7, nullable = false, updatable = false)
  @PipePropertyOrder(23.0)
  @XStreamConverter(StringDateConverter.class)
  private Date dateOfBirth;

  public Date getDateOfBirth() {
    return this.dateOfBirth;
  }

  public void setDateOfBirth(Date arg) {
    this.dateOfBirth = arg;
  }

  @Basic
  @Column(length = 100, nullable = true, updatable = true)
  @PipePropertyOrder(30.0)
  //@XStreamConverter(CDataConverter.class)
  private String city;

  public String getCity() {
    return this.city;
  }

  public void setCity(String arg) {
    this.city = arg;
  }

  @Basic
  @Column(length = 2, nullable = true, updatable = true)
  @PipePropertyOrder(31.0)
  public String state;

  public String getState() {
    return this.state;
  }

  public void setState(String arg) {
    this.state = arg;
  }

  @Basic
  @Column(length = 2, nullable = true, updatable = true)
  @PipePropertyOrder(22.0)
  private String language;

  public String getLanguage() {
    return this.language;
  }

  public void setLanguage(String arg) {
    this.language = arg;
  }

  @Basic
  @Column(length = 2, nullable = true, updatable = true)
  @PipePropertyOrder(32.0)
  private String country;

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String arg) {
    this.country = arg;
  }

  @Basic
  @Column(length = 10, nullable = true, updatable = true)
  @PipePropertyOrder(33.0)
  private String postalCode;

  public String getPostalCode() {
    return this.postalCode;
  }

  public void setPostalCode(String arg) {
    this.postalCode = arg;
  }

  /**
   * get the locale based on language and country
   * @return Locale
   */
  public Locale getLocale() {
    Locale locale = Locale.US;
    if(getCountry() != null && !getCountry().equals("") && getLanguage() != null && !getLanguage().equals("")) {
      locale = new Locale(getLanguage(), getCountry());
    }
    return locale;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", getId()).append("created", getCreated()).append("lastChanged", getLastChanged()).append("externalProfileId", getExternalProfileId())
        .append("externalProfileSource", getExternalProfileSource()).append("status", getStatus()).append("testUser", getTestUser()).append("pin", getPin()).append("authToken", getAuthToken())
        .append("gender", getGender()).append("email", getEmail()).append("language", getLanguage()).append("dateOfBirth", getDateOfBirth()).append("city", getCity()).append("state", getState())
        .append("country", getCountry()).append("postalCode", getPostalCode()).toString();
  }
}
