package org.tharrisx.frameworkexample.resources;

import javax.ws.rs.Path;

import org.tharrisx.framework.rest.core.ResourceCore;
import org.tharrisx.framework.rest.core.ResourceRequestInfo;
import org.tharrisx.framework.rest.resources.BeanInstanceResource;
import org.tharrisx.framework.rest.resources.BeanTypeAllResource;
import org.tharrisx.framework.rest.resources.BeanTypeMatchResource;
import org.tharrisx.framework.rest.resources.BeanTypeResource;
import org.tharrisx.frameworkexample.beans.User;
import org.tharrisx.frameworkexample.beans.UserEvent;

/**
 * Example root resource definitions
 * 
 * @author tharrisx
 * @since 1.0.0
 * @version 1.0.0
 */
public class UserResources {

  @Path("/rest/users")
  public static class UsersResource extends BeanTypeResource<User> {
    public UsersResource(final ResourceCore<User> resourceCore1, final ResourceRequestInfo resourceRequestInfo1) {
      super(resourceCore1, resourceRequestInfo1);
    }
  }

  @Path("/rest/users/all/")
  public static class UsersAllResource extends BeanTypeAllResource<User> {
    public UsersAllResource(final ResourceCore<User> resourceCore1, final ResourceRequestInfo resourceRequestInfo1) {
      super(resourceCore1, resourceRequestInfo1);
    }
  }

  @Path("/rest/users/match/")
  public static class UsersMatchResource extends BeanTypeMatchResource<User> {
    public UsersMatchResource(final ResourceCore<User> resourceCore1, final ResourceRequestInfo resourceRequestInfo1) {
      super(resourceCore1, resourceRequestInfo1);
    }
  }

  @Path("/rest/users/{beanId: [0-9,A-F]+}")
  public static class UserInstanceResource extends BeanInstanceResource<User> {
    public UserInstanceResource(final ResourceCore<User> resourceCore1, final ResourceRequestInfo resourceRequestInfo1, final String beanId1) {
      super(resourceCore1, resourceRequestInfo1, beanId1);
    }

    @Path("events")
    public BeanTypeResource<User> followUserEventsPath() {
      return getResourceCore().makeBeanTypeResource(getResourceRequestInfo(), UserEvent.class);
    }
  }
}
