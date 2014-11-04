package org.tharrisx.test.framework.rest.beans;

import java.util.Date;

import org.json.XML;
import org.tharrisx.test.framework.rest.api.PipeFormat;
import org.tharrisx.util.DateUtils;
import org.w3c.dom.Document;

public abstract class ExampleBean {

  public abstract void fillFromRepresentation(Document document, String xpathBase) throws Exception;

  public abstract String toRepresentation(PipeFormat pipeFormat) throws Exception;

  protected final BeanResourceBuilder getBeanResourceBuilder(String beanName1, PipeFormat pipeFormat) {
    return pipeFormat.equals(PipeFormat.XML) ? new XmlBeanResourceBuilder(beanName1) : new JsonBeanResourceBuilder(beanName1);
  }

  public static abstract class BeanResourceBuilder {

    private final String beanName;

    protected final String getBeanName() {
      return this.beanName;
    }

    private final StringBuilder builder = new StringBuilder();

    protected final StringBuilder getBuilder() {
      return this.builder;
    }

    public BeanResourceBuilder(String beanName1) {
      this.beanName = beanName1;
    }

    public abstract BeanResourceBuilder addNode(String nodeName, Object value);

    public abstract String getRepresentation() throws Exception;

    protected String pullValueText(Object value) {
      String ret = null;
      if(Date.class.isAssignableFrom(value.getClass())) {
        ret = DateUtils.getStringFromDate((Date) value);
      } else if(Number.class.isAssignableFrom(value.getClass())) {
        ret = ((Number) value).toString();
      } else {
        ret = value.toString();
      }
      return ret;
    }
  }

  protected static class XmlBeanResourceBuilder extends BeanResourceBuilder {

    public XmlBeanResourceBuilder(String beanName1) {
      super(beanName1);
      getBuilder().append("<?xml version='1.0' encoding='UTF-8'?>");
      getBuilder().append("<").append(getBeanName()).append(">");
    }

    @Override
    public BeanResourceBuilder addNode(String nodeName, Object value) {
      if(null != value) {
        getBuilder().append('<').append(nodeName).append('>');
        getBuilder().append(pullValueText(value));
        getBuilder().append("</").append(nodeName).append('>');
      }
      return this;
    }

    @Override
    public String getRepresentation() throws Exception {
      getBuilder().append("</").append(getBeanName()).append(">");
      return getBuilder().toString();
    }
  }

  protected static class JsonBeanResourceBuilder extends XmlBeanResourceBuilder {

    public JsonBeanResourceBuilder(String beanName1) {
      super(beanName1);
    }

    @Override
    public String getRepresentation() throws Exception {
      // a cheat, i know. but these are tests.
      return XML.toJSONObject(super.getRepresentation().trim()).toString();
    }
  }
}
