package org.tharrisx.test.framework.rest.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONObject;
import org.json.XML;
import org.tharrisx.test.framework.rest.beans.ExampleBean;
import org.tharrisx.util.StringFedInputStream;
import org.w3c.dom.Document;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class BaseBeanApi<T extends ExampleBean> {

  private static final String BASE_CONTEXT_PATH = "/anythingrest/";

  private final Class<T> type;

  protected Class<T> getType() {
    return this.type;
  }

  private final PipeFormat pipeFormat;

  protected PipeFormat getBeanPipeFormat() {
    return this.pipeFormat;
  }

  private final String[] pathedBeanTypeUriNames;

  protected String[] getPathedBeanTypeUriNames() {
    return this.pathedBeanTypeUriNames;
  }

  private final String beanInstanceNodeName;

  protected String getBeanInstanceNodeName() {
    return this.beanInstanceNodeName;
  }

  private final String serverBaseUri;

  protected String getServerBaseUri() {
    return this.serverBaseUri;
  }

  private final boolean loggingEnabled;

  protected final boolean isLoggingEnabled() {
    return this.loggingEnabled;
  }

  private final Client client;

  protected Client getClient() {
    return this.client;
  }

  public BaseBeanApi(Class<T> type1, PipeFormat pipeFormat1, String[] pathedBeanTypeUriNames1, String beanInstanceNodeName1, String serverHost, int serverPort, boolean loggingEnabled1) {
    this.type = type1;
    this.pipeFormat = pipeFormat1;
    this.pathedBeanTypeUriNames = pathedBeanTypeUriNames1;
    this.beanInstanceNodeName = beanInstanceNodeName1;
    this.serverBaseUri = "http://" + serverHost + ":" + serverPort + BASE_CONTEXT_PATH;
    this.loggingEnabled = loggingEnabled1;
    this.client = Client.create();
  }

  protected class RestUriBuilder {

    private final StringBuilder buffer = new StringBuilder(getServerBaseUri());

    //http://host:port/context/
    //http://host:port/context/users/
    //http://host:port/context/users/ID/
    //http://host:port/context/users/?start=5&end=8
    //http://host:port/context/users/all/
    //http://host:port/context/users/match/name1=value1,name2=value2/
    //http://host:port/context/users/match/name1=value1,name2=value2/?start=5&end=8
    //http://host:port/context/users/ID/events/
    //http://host:port/context/users/ID/events/ID
    //http://host:port/context/users/ID/events/?start=5&end=8
    //http://host:port/context/users/ID/events/all/
    //http://host:port/context/users/ID/events/match/name1=value1,name2=value2/?start=5&end=8

    public RestUriBuilder typeResource(String beanType) {
      this.buffer.append(beanType).append('/');
      return this;
    }

    public RestUriBuilder instanceResource(String beanType, String beanId) {
      this.buffer.append(beanType).append('/').append(beanId).append('/');
      return this;
    }

    public RestUriBuilder allResource(String beanType) {
      this.buffer.append(beanType).append("/all/");
      return this;
    }

    public RestUriBuilder matchResource(String beanType, Map<String, String> matchingProperties) {
      this.buffer.append(beanType).append("/match/");
      for(Map.Entry<String, String> propEntry : matchingProperties.entrySet()) {
        this.buffer.append(propEntry.getKey()).append('=').append(propEntry.getValue()).append(',');
      }
      this.buffer.append('/');
      return this;
    }

    public RestUriBuilder appendPaging(int start, int end) {
      this.buffer.append("?start=").append(start).append("&end=").append(end);
      return this;
    }

    public URI toUri() throws URISyntaxException {
      return new URI(this.buffer.toString());
    }
  }

  protected Document getResourceItem(URI uri) throws Exception {
    if(isLoggingEnabled())
      logInteraction("Getting resource: " + uri);
    WebResource resource = getClient().resource(uri);
    Builder builder = resource.accept(getMediaType()).type(getMediaType());
    String result = builder.get(String.class);
    if(isLoggingEnabled())
      logInteraction("result: " + result);
    return getDocument(result);
  }

  protected URI postResourceItem(URI uri, ExampleBean exampleBean) throws Exception {
    if(isLoggingEnabled())
      logInteraction("Posting resource: " + uri);
    URI ret = null;
    try {
      String representation = exampleBean.toRepresentation(getBeanPipeFormat());
      if(isLoggingEnabled())
        logInteraction("representation: " + representation);
      WebResource resource = getClient().resource(uri);
      Builder builder = resource.accept(getMediaType()).type(getMediaType()).entity(representation);
      ret = builder.post(ClientResponse.class).getLocation();
      return ret;
    } finally {
      if(isLoggingEnabled())
        logInteraction("Post successful.");
    }
  }

  protected void putResourceItem(URI uri, ExampleBean exampleBean) throws Exception {
    if(isLoggingEnabled())
      logInteraction("Putting resource: " + uri);
    String representation = exampleBean.toRepresentation(getBeanPipeFormat());
    if(isLoggingEnabled())
      logInteraction("representation: " + representation);
    WebResource resource = getClient().resource(uri);
    Builder builder = resource.accept(getMediaType()).type(getMediaType()).entity(representation);
    builder.put(ClientResponse.class);
    if(isLoggingEnabled())
      logInteraction("Put successful.");
  }

  protected void deleteResourceItem(URI uri) throws Exception {
    if(isLoggingEnabled())
      logInteraction("Deleting resource: " + uri);
    WebResource resource = getClient().resource(uri);
    Builder builder = resource.accept(getMediaType()).type(getMediaType());
    builder.delete(ClientResponse.class);
    if(isLoggingEnabled())
      logInteraction("Delete successful.");
  }

  protected Document getDocument(String text) throws Exception {
    String textInner = text;
    // if the text is json, read that and convert into xml, since xpath is in use for lookups already
    // a cheat, but coding time vs. execution time, and these are tests after all.
    if(getBeanPipeFormat().equals(PipeFormat.JSON)) {
      textInner = "<?xml version='1.0' encoding='UTF-8'?>" + XML.toString(new JSONObject(textInner));
    }
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    return builder.parse(new StringFedInputStream(textInner));
  }

  protected MediaType getMediaType() {
    MediaType ret = getBeanPipeFormat().equals(PipeFormat.XML) ? MediaType.APPLICATION_XML_TYPE : MediaType.APPLICATION_JSON_TYPE;
    return ret;
  }

  protected void logInteraction(String arg) {
    System.out.println(arg);
  }
}
