package org.tharrisx.test.framework.rest.beans;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.tharrisx.test.framework.rest.api.PipeFormat;
import org.tharrisx.test.util.BeanXPathHelper;
import org.w3c.dom.Document;

public class ExampleUserEvent extends ExampleBean {

  private String id = null;

  public String getId() {
    return this.id;
  }

  public void setId(String arg) {
    this.id = arg;
  }

  private Date stamp = null;

  public Date getStamp() {
    return this.stamp;
  }

  public void setStamp(Date arg) {
    this.stamp = new Date(arg.getTime());
  }

  private String userId = null;

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String arg) {
    this.userId = arg;
  }

  private String event = null;

  public String getEvent() {
    return this.event;
  }

  public void setEvent(String arg) {
    this.event = arg;
  }

  private String eventArguments = null;

  public String getEventArguments() {
    return this.eventArguments;
  }

  public void setEventArguments(String arg) {
    this.eventArguments = arg;
  }

  private Boolean viewed = null;

  public Boolean isViewed() {
    return this.viewed;
  }

  public void setViewed(Boolean arg) {
    this.viewed = arg;
  }

  public ExampleUserEvent() {
    // nothing
  }

  @Override
  public void fillFromRepresentation(Document document, String xpathBase) throws Exception {
    setId(BeanXPathHelper.pullMandatoryStringNode(document, xpathBase + "id"));
    setStamp(BeanXPathHelper.pullOptionalDateNode(document, xpathBase + "stamp"));
    setUserId(BeanXPathHelper.pullMandatoryStringNode(document, xpathBase + "userId"));
    setEvent(BeanXPathHelper.pullMandatoryStringNode(document, xpathBase + "event"));
    setEventArguments(BeanXPathHelper.pullMandatoryStringNode(document, xpathBase + "eventArguments"));
    setViewed(BeanXPathHelper.pullMandatoryBooleanNode(document, xpathBase + "viewed"));
  }

  @Override
  public String toRepresentation(PipeFormat pipeFormat) throws Exception {
    return getBeanResourceBuilder("user", pipeFormat).addNode("id", getId()).addNode("stamp", getStamp()).addNode("userId", getUserId()).addNode("event", getEvent())
        .addNode("eventArguments", getEventArguments()).addNode("viewed", isViewed()).getRepresentation();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", getId()).append("stamp", getStamp()).append("userId", getUserId()).append("event", getEvent()).append("eventArguments", getEventArguments())
        .append("viewed", isViewed()).toString();
  }
}
