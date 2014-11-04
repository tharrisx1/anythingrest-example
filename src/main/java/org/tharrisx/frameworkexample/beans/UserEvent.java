package org.tharrisx.frameworkexample.beans;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.tharrisx.framework.pipe.converters.CDataConverter;
import org.tharrisx.framework.pipe.converters.StringCalendarConverter;
import org.tharrisx.framework.rest.annotations.InstanceResource;
import org.tharrisx.framework.rest.annotations.TypeAllResource;
import org.tharrisx.framework.rest.annotations.TypeMatchResource;
import org.tharrisx.framework.rest.annotations.TypeResource;
import org.tharrisx.framework.store.StorableBean;
import org.tharrisx.frameworkexample.resources.UserEventResources.UserEventInstanceResource;
import org.tharrisx.frameworkexample.resources.UserEventResources.UserEventsAllResource;
import org.tharrisx.frameworkexample.resources.UserEventResources.UserEventsMatchResource;
import org.tharrisx.frameworkexample.resources.UserEventResources.UserEventsResource;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@Entity
@Table(name = "rest_user_event")
@NamedQueries({ @NamedQuery(name = "getUserEventsByUserId", query = "select ret from org.tharrisx.frameworkexample.beans.UserEvent ret" + " where ret.userId = :userId"),
    @NamedQuery(name = "getUserEventsByUserIDAndViewed", query = "select ret from org.tharrisx.frameworkexample.beans.UserEvent ret" + " where ret.userId = :userId and ret.viewed = :isViewed") })
@TypeResource(UserEventsResource.class)
@TypeAllResource(UserEventsAllResource.class)
@TypeMatchResource(UserEventsMatchResource.class)
@InstanceResource(UserEventInstanceResource.class)
@XStreamAlias("event")
public class UserEvent extends StorableBean {

  public static final String NAMED_QUERY_GET_EVENTS = "getUserEventsByUserID";
  public static final String NAMED_QUERY_GET_EVENTS_VIEW_FILTER = "getUserEventsByUserIDAndViewed";

  @Basic
  //@Type(type = "LongTimestamp")
  @Column(length = 7, nullable = false, updatable = false)
  @XStreamConverter(StringCalendarConverter.class)
  private Date stamp = null;

  public Date getStamp() {
    return this.stamp;
  }

  public void setStamp(Date arg) {
    this.stamp = arg;
  }

  @Basic
  @Column(length = 32, nullable = false, updatable = true)
  //@PipeEncryption(EncryptedIdCodec.class)
  private String userId = null;

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String arg) {
    this.userId = arg;
  }

  @Basic
  @Column(length = 100, nullable = false, updatable = true)
  private String event = null;

  public String getEvent() {
    return this.event;
  }

  public void setEvent(String arg) {
    this.event = arg;
  }

  @Basic
  @Column(length = 240, nullable = false, updatable = true)
  @XStreamConverter(CDataConverter.class)
  private String eventArguments = null;

  public String getEventArguments() {
    return this.eventArguments;
  }

  public void setEventArguments(String arg) {
    this.eventArguments = arg;
  }

  @Basic
  private boolean viewed = false;

  public boolean isViewed() {
    return this.viewed;
  }

  public void setViewed(boolean arg) {
    this.viewed = arg;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper(super.toString()).append("userEventId", getId()).append("stamp", getStamp()).append("userId", getUserId()).append("event", getEvent())
        .append("eventArguments", getEventArguments()).append("isViewed", isViewed()).toString();
  }
}
