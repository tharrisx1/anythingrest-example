package org.tharrisx.test.framework.rest.beans;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.tharrisx.test.framework.rest.api.PipeFormat;
import org.tharrisx.test.util.BeanXPathHelper;
import org.w3c.dom.Document;

public class ExamplePageableBeanList<T extends ExampleBean> extends ExampleBean {

  private int totalCount = 0;

  public int getTotalCount() {
    return this.totalCount;
  }

  private int start = 0;

  public int getStart() {
    return this.start;
  }

  private int end = 0;

  public int getEnd() {
    return this.end;
  }

  private final List<T> list;

  public List<T> getList() {
    return this.list;
  }

  public ExamplePageableBeanList(List<T> list1, int totalCount1, int start1, int end1) throws Exception {
    this.list = list1;
    this.totalCount = totalCount1;
    this.start = start1;
    this.end = end1;
  }

  public ExamplePageableBeanList(Document document, Class<T> resourceType, String resourceNode) throws Exception {
    this.totalCount = Integer.valueOf(BeanXPathHelper.pullMandatoryIntegerNode(document, "//pageOfItems/totalCount")).intValue();
    this.start = Integer.valueOf(BeanXPathHelper.pullMandatoryIntegerNode(document, "//pageOfItems/start")).intValue();
    this.end = Integer.valueOf(BeanXPathHelper.pullMandatoryIntegerNode(document, "//pageOfItems/end")).intValue();
    this.list = new LinkedList<>();
    boolean hasMore = true;
    int cv = 1;
    T item = null;
    while(hasMore) {
      if(null != BeanXPathHelper.pullOptionalStringNode(document, "//pageOfItems/" + resourceNode + "[" + cv + "]/id")) {
        item = resourceType.newInstance();
        item.fillFromRepresentation(document, "//pageOfItems/" + resourceNode + "[" + (cv++) + "]/");
        getList().add(item);
      } else {
        hasMore = false;
      }
    }
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("totalCount", getTotalCount()).append("list", getList()).toString();
  }

  @Override
  public String toRepresentation(PipeFormat pipeFormat) {
    // $$$ IMPLEMENT! at some point
    throw new UnsupportedOperationException();
  }

  @Override
  public void fillFromRepresentation(Document document, String xpathBase) throws Exception {
    // $$$ IMPLEMENT! at some point
    throw new UnsupportedOperationException();
  }
}
