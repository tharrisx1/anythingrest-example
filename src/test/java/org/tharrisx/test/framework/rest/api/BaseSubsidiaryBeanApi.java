package org.tharrisx.test.framework.rest.api;

import org.tharrisx.test.framework.rest.beans.ExampleBean;

public class BaseSubsidiaryBeanApi<T extends ExampleBean> extends BaseBeanApi<T> {

  private final String subsidiaryBeanTypeUri;

  protected String getSubsidiaryBeanTypeUri() {
    return this.subsidiaryBeanTypeUri;
  }

  public BaseSubsidiaryBeanApi(Class<T> type1, PipeFormat pipeFormat1, String[] pathedBeanTypeUriNames1, String subsidiaryBeanTypeUri1, String beanInstanceNodeName1, String serverHost,
      int serverPort, boolean loggingEnabled1) {
    super(type1, pipeFormat1, pathedBeanTypeUriNames1, beanInstanceNodeName1, serverHost, serverPort, loggingEnabled1);
    this.subsidiaryBeanTypeUri = subsidiaryBeanTypeUri1;
  }
  /*
    public ExamplePageableBeanList<T> getItems(String[] pathedIds) throws Exception {
      String uri = generateUri(pathedIds);
      return new ExamplePageableBeanList<T>(getResourceItem(uri), getType(), getBeanInstanceNodeName());
    }

    public ExamplePageableBeanList<T> getItems(String[] pathedIds, int start, int end) throws Exception {
      String uri = generateUri(pathedIds) + "?start=" + start + "&end=" + end;
      return new ExamplePageableBeanList<T>(getResourceItem(uri), getType(), getBeanInstanceNodeName());
    }

    public ExamplePageableBeanList<T> getItems(String[] pathedIds, Map<String, String> matchingProperties) throws Exception {
      StringBuilder uriBuf = new StringBuilder(generateUri(pathedIds));
      uriBuf.append('/').append(getMatchPropsUriFragment(matchingProperties)).append('/');
      String uri = uriBuf.toString();
      return new ExamplePageableBeanList<T>(getResourceItem(uri), getType(), getBeanInstanceNodeName());
    }

    public ExamplePageableBeanList<T> getItems(String[] pathedIds, int start, int end, Map<String, String> matchingProperties) throws Exception {
      StringBuilder uriBuf = new StringBuilder(generateUri(pathedIds));
      uriBuf.append("/match");
      uriBuf.append('/').append(getMatchPropsUriFragment(matchingProperties)).append('/');
      uriBuf.append("?start=").append(start).append("&end=").append(end);
      String uri = uriBuf.toString();
      return new ExamplePageableBeanList<T>(getResourceItem(uri), getType(), getBeanInstanceNodeName());
    }

    public T getItem(String[] pathedIds, String id) throws Exception {
      T ret = getType().newInstance();
      String uri = generateUri(pathedIds) + "/" + id;
      Document document = getResourceItem(uri);
      ret.fillFromRepresentation(document, "//" + getBeanInstanceNodeName() + "/");
      return ret;
    }

    public URI postItem(String[] pathedIds, T item) throws Exception {
      String uri = generateUri(pathedIds);
      return postResourceItem(uri, item);
    }

    public void putItem(String[] pathedIds, T item, String id) throws Exception {
      String uri = generateUri(pathedIds) + "/" + id;
      putResourceItem(uri, item);
    }

    public void deleteItem(String[] pathedIds, String id) throws Exception {
      String uri = generateUri(pathedIds) + "/" + id;
      deleteResourceItem(uri);
    }

    private String generateUri(String[] pathedIds) {
      StringBuilder retBuf = new StringBuilder();
      for(int cv = 0; cv < pathedIds.length; cv++) {
        retBuf.append('/').append(getPathedBeanTypeUriNames()[cv]).append('/');
        retBuf.append(pathedIds[cv]);
      }
      return retBuf.toString();
    }
  */
}
