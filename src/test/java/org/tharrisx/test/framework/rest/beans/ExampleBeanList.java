package org.tharrisx.test.framework.rest.beans;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.tharrisx.test.framework.rest.api.PipeFormat;
import org.tharrisx.test.util.BeanXPathHelper;
import org.w3c.dom.Document;

public class ExampleBeanList<T extends ExampleBean> extends ExampleBean {

  private final List<T> list = new LinkedList<>();

  public List<T> getList() {
    return this.list;
  }

  public ExampleBeanList(Document document, Class<T> resourceType, String resourceNode) throws Exception {
    boolean hasMore = true;
    int cv = 1;
    T item = null;
    while(hasMore) {
      if(null != BeanXPathHelper.pullOptionalStringNode(document, "//items/" + resourceNode + "[" + cv + "]/id")) {
        item = resourceType.newInstance();
        item.fillFromRepresentation(document, "//items/" + resourceNode + "[" + (cv++) + "]/");
        getList().add(item);
      } else {
        hasMore = false;
      }
    }
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("list", getList()).toString();
  }

  @Override
  public String toRepresentation(PipeFormat pipeFormat) {
    // $$$ IMPLEMENT!
    throw new UnsupportedOperationException();
  }

  @Override
  public void fillFromRepresentation(Document document, String xpathBase) throws Exception {
    // $$$ IMPLEMENT!
    throw new UnsupportedOperationException();
  }
}
