package org.tharrisx.test.framework.rest.beans;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.tharrisx.test.framework.rest.api.PipeFormat;
import org.tharrisx.test.util.BeanXPathHelper;
import org.w3c.dom.Document;

public class ExampleUser extends ExampleBean {

  private String id = null;

  public String getId() {
    return this.id;
  }

  public void setId(String arg) {
    this.id = arg;
  }

  private String externalProfileId = null;

  public String getExternalProfileId() {
    return this.externalProfileId;
  }

  public void setExternalProfileId(String arg) {
    this.externalProfileId = arg;
  }

  private String externalProfileSource = null;

  public String getExternalProfileSource() {
    return this.externalProfileSource;
  }

  public void setExternalProfileSource(String arg) {
    this.externalProfileSource = arg;
  }

  private Integer status = null;

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer arg) {
    this.status = arg;
  }

  private Integer testUser = null;

  public Integer getTestUser() {
    return this.testUser;
  }

  public void setTestUser(Integer arg) {
    this.testUser = arg;
  }

  private String pin = null;

  public String getPin() {
    return this.pin;
  }

  public void setPin(String arg) {
    this.pin = arg;
  }

  private String authToken = null;

  public String getAuthToken() {
    return this.authToken;
  }

  public void setAuthToken(String arg) {
    this.authToken = arg;
  }

  private String gender = null;

  public String getGender() {
    return this.gender;
  }

  public void setGender(String arg) {
    this.gender = arg;
  }

  private String email = null;

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String arg) {
    this.email = arg;
  }

  private Date dateOfBirth = null;

  public Date getDateOfBirth() {
    return this.dateOfBirth;
  }

  public void setDateOfBirth(Date arg) {
    this.dateOfBirth = new Date(arg.getTime());
  }

  private String city = null;

  public String getCity() {
    return this.city;
  }

  public void setCity(String arg) {
    this.city = arg;
  }

  private String state = null;

  public String getState() {
    return this.state;
  }

  public void setState(String arg) {
    this.state = arg;
  }

  private String language = null;

  public String getLanguage() {
    return this.language;
  }

  public void setLanguage(String arg) {
    this.language = arg;
  }

  private String country = null;

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String arg) {
    this.country = arg;
  }

  private String postalCode = null;

  public String getPostalCode() {
    return this.postalCode;
  }

  public void setPostalCode(String arg) {
    this.postalCode = arg;
  }

  private Date created = null;

  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date arg) {
    this.created = new Date(arg.getTime());
  }

  private Date lastChanged = null;

  public Date getLastChanged() {
    return this.lastChanged;
  }

  public void setLastChanged(Date arg) {
    this.lastChanged = new Date(arg.getTime());
  }

  public ExampleUser() {
    // nothing
  }

  @Override
  public void fillFromRepresentation(Document document, String xpathBase) throws Exception {
    setId(BeanXPathHelper.pullMandatoryStringNode(document, xpathBase + "id"));
    setExternalProfileId(BeanXPathHelper.pullOptionalStringNode(document, xpathBase + "externalProfileId"));
    setExternalProfileSource(BeanXPathHelper.pullOptionalStringNode(document, xpathBase + "externalProfileSource"));
    setStatus(BeanXPathHelper.pullOptionalIntegerNode(document, xpathBase + "status"));
    setTestUser(BeanXPathHelper.pullOptionalIntegerNode(document, xpathBase + "testUser"));
    setPin(BeanXPathHelper.pullOptionalStringNode(document, xpathBase + "pin"));
    setAuthToken(BeanXPathHelper.pullOptionalStringNode(document, xpathBase + "authToken"));
    setGender(BeanXPathHelper.pullOptionalStringNode(document, xpathBase + "gender"));
    setEmail(BeanXPathHelper.pullOptionalStringNode(document, xpathBase + "email"));
    setLanguage(BeanXPathHelper.pullOptionalStringNode(document, xpathBase + "language"));
    setDateOfBirth(BeanXPathHelper.pullOptionalDateNode(document, xpathBase + "dateOfBirth"));
    setCity(BeanXPathHelper.pullOptionalStringNode(document, xpathBase + "city"));
    setState(BeanXPathHelper.pullOptionalStringNode(document, xpathBase + "state"));
    setCountry(BeanXPathHelper.pullOptionalStringNode(document, xpathBase + "country"));
    setPostalCode(BeanXPathHelper.pullOptionalStringNode(document, xpathBase + "postalCode"));
    setCreated(BeanXPathHelper.pullMandatoryDateNode(document, xpathBase + "created"));
    setLastChanged(BeanXPathHelper.pullMandatoryDateNode(document, xpathBase + "lastChanged"));
  }

  @Override
  public String toRepresentation(PipeFormat pipeFormat) throws Exception {
    return getBeanResourceBuilder("user", pipeFormat).addNode("id", getId()).addNode("externalProfileId", getExternalProfileId()).addNode("externalProfileSource", getExternalProfileSource())
        .addNode("status", getStatus()).addNode("testUser", getTestUser()).addNode("pin", getPin()).addNode("authToken", getAuthToken()).addNode("gender", getGender()).addNode("email", getEmail())
        .addNode("language", getLanguage()).addNode("dateOfBirth", getDateOfBirth()).addNode("city", getCity()).addNode("state", getState()).addNode("country", getCountry())
        .addNode("postalCode", getPostalCode()).addNode("created", getCreated()).addNode("lastChanged", getLastChanged()).getRepresentation();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", getId()).append("externalProfileId", getExternalProfileId()).append("externalProfileSource", getExternalProfileSource())
        .append("status", getStatus()).append("testUser", getTestUser()).append("pin", getPin()).append("authToken", getAuthToken()).append("gender", getGender()).append("email", getEmail())
        .append("language", getLanguage()).append("dateOfBirth", getDateOfBirth()).append("city", getCity()).append("state", getState()).append("country", getCountry())
        .append("postalCode", getPostalCode()).append("created", getCreated()).append("lastChanged", getLastChanged()).toString();
  }
}
