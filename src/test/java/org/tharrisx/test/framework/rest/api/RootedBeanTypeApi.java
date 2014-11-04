package org.tharrisx.test.framework.rest.api;

import java.net.URI;
import java.util.Map;

import org.tharrisx.test.framework.rest.beans.ExampleBean;
import org.tharrisx.test.framework.rest.beans.ExampleBeanList;
import org.tharrisx.test.framework.rest.beans.ExamplePageableBeanList;

/**
 * Wraps a REST Client API for any rooted ExampleBean
 * @param <T extends ExampleBean>
 * 
 * @author tharrisx
 * @since 1.0.0
 * @version 1.0.0
 */
public class RootedBeanTypeApi<T extends ExampleBean> extends BaseBeanApi<T> {

  public RootedBeanTypeApi(Class<T> type1, PipeFormat pipeFormat1, String[] pathedBeanTypeUriNames1, String beanInstanceNodeName1, String serverHost, int serverPort, boolean loggingEnabled1) {
    super(type1, pipeFormat1, pathedBeanTypeUriNames1, beanInstanceNodeName1, serverHost, serverPort, loggingEnabled1);
  }

  public ExampleBeanList<T> getAllItems() throws Exception {
    URI uri = new RestUriBuilder().allResource(getPathedBeanTypeUriNames()[0]).toUri();
    return new ExampleBeanList<>(getResourceItem(uri), getType(), getBeanInstanceNodeName());
  }

  public ExamplePageableBeanList<T> getItems() throws Exception {
    URI uri = new RestUriBuilder().typeResource(getPathedBeanTypeUriNames()[0]).toUri();
    return new ExamplePageableBeanList<>(getResourceItem(uri), getType(), getBeanInstanceNodeName());
  }

  public ExamplePageableBeanList<T> getItems(int start, int end) throws Exception {
    URI uri = new RestUriBuilder().typeResource(getPathedBeanTypeUriNames()[0]).appendPaging(start, end).toUri();
    return new ExamplePageableBeanList<>(getResourceItem(uri), getType(), getBeanInstanceNodeName());
  }

  public ExamplePageableBeanList<T> getItems(Map<String, String> matchingProperties) throws Exception {
    URI uri = new RestUriBuilder().matchResource(getPathedBeanTypeUriNames()[0], matchingProperties).toUri();
    return new ExamplePageableBeanList<>(getResourceItem(uri), getType(), getBeanInstanceNodeName());
  }

  public ExamplePageableBeanList<T> getItems(int start, int end, Map<String, String> matchingProperties) throws Exception {
    URI uri = new RestUriBuilder().matchResource(getPathedBeanTypeUriNames()[0], matchingProperties).appendPaging(start, end).toUri();
    return new ExamplePageableBeanList<>(getResourceItem(uri), getType(), getBeanInstanceNodeName());
  }

  public T getItem(String beanId) throws Exception {
    T ret = getType().newInstance();
    URI uri = new RestUriBuilder().instanceResource(getPathedBeanTypeUriNames()[0], beanId).toUri();
    ret.fillFromRepresentation(getResourceItem(uri), "//" + getBeanInstanceNodeName() + "/");
    return ret;
  }

  public URI postItem(T item) throws Exception {
    URI uri = new RestUriBuilder().typeResource(getPathedBeanTypeUriNames()[0]).toUri();
    return postResourceItem(uri, item);
  }

  public void putItem(T item, String beanId) throws Exception {
    URI uri = new RestUriBuilder().instanceResource(getPathedBeanTypeUriNames()[0], beanId).toUri();
    putResourceItem(uri, item);
  }

  public void deleteItem(String beanId) throws Exception {
    URI uri = new RestUriBuilder().instanceResource(getPathedBeanTypeUriNames()[0], beanId).toUri();
    deleteResourceItem(uri);
  }
}
