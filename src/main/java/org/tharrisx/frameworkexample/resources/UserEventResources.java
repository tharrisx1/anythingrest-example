package org.tharrisx.frameworkexample.resources;

import javax.ws.rs.Path;

import org.tharrisx.framework.rest.core.ResourceCore;
import org.tharrisx.framework.rest.core.ResourceRequestInfo;
import org.tharrisx.framework.rest.resources.BeanInstanceResource;
import org.tharrisx.framework.rest.resources.BeanTypeAllResource;
import org.tharrisx.framework.rest.resources.BeanTypeMatchResource;
import org.tharrisx.framework.rest.resources.BeanTypeResource;
import org.tharrisx.frameworkexample.beans.UserEvent;

/**
 * Example child resource definitions
 * 
 * @author tharrisx
 * @since 1.0.0
 * @version 1.0.0
 */
public class UserEventResources {

  @Path("/rest/users/{userId: [0-9,A-F]+}/events")
  public static class UserEventsResource extends BeanTypeResource<UserEvent> {

    public UserEventsResource(final ResourceCore<UserEvent> resourceCore1, final ResourceRequestInfo resourceRequestInfo1) {
      super(resourceCore1, resourceRequestInfo1);
    }
  }

  @Path("/rest/users/{userId: [0-9,A-F]+}/events/all/")
  public static class UserEventsAllResource extends BeanTypeAllResource<UserEvent> {

    public UserEventsAllResource(final ResourceCore<UserEvent> resourceCore1, final ResourceRequestInfo resourceRequestInfo1) {
      super(resourceCore1, resourceRequestInfo1);
    }
  }

  @Path("/rest/users/{userId: [0-9,A-F]+}/events/match/")
  public static class UserEventsMatchResource extends BeanTypeMatchResource<UserEvent> {

    public UserEventsMatchResource(final ResourceCore<UserEvent> resourceCore1, final ResourceRequestInfo resourceRequestInfo1) {
      super(resourceCore1, resourceRequestInfo1);
    }
  }

  @Path("/rest/users/{userId: [0-9,A-F]+}/events/{beanId: [0-9,A-F]+}")
  public static class UserEventInstanceResource extends BeanInstanceResource<UserEvent> {

    public UserEventInstanceResource(final ResourceCore<UserEvent> resourceCore1, final ResourceRequestInfo resourceRequestInfo1, final String beanId1) {
      super(resourceCore1, resourceRequestInfo1, beanId1);
    }
  }
}
