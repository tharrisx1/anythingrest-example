package org.tharrisx.test.util;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.jaxen.XPath;
import org.jaxen.dom.DOMXPath;
import org.tharrisx.util.DateUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public final class BeanXPathHelper {

  @SuppressWarnings("unchecked")
  // interfacing to non-generic library
  private static String getSingleNodeValue(Document doc, String xpath) throws Exception {
    XPath xpathList = new DOMXPath(xpath);
    List<Node> results = (List<Node>) xpathList.evaluate(doc);
    Iterator<Node> iterator = results.iterator();
    Node result = iterator.next();
    return result.getNodeValue();
  }

  public static String pullMandatoryStringNode(Document document, String xpath) throws Exception {
    return getSingleNodeValue(document, xpath + "/text()");
  }

  public static String pullOptionalStringNode(Document document, String xpath) throws Exception {
    String ret = null;
    try {
      ret = getSingleNodeValue(document, xpath + "/text()");
    } catch(NoSuchElementException e) { /* OK, leave null */
    }
    return ret;
  }

  public static Integer pullMandatoryIntegerNode(Document document, String xpath) throws Exception {
    return getIntegerFromString(pullMandatoryStringNode(document, xpath));
  }

  public static Integer pullOptionalIntegerNode(Document document, String xpath) throws Exception {
    return getIntegerFromString(pullOptionalStringNode(document, xpath));
  }

  public static Boolean pullMandatoryBooleanNode(Document document, String xpath) throws Exception {
    return getBooleanFromString(pullMandatoryStringNode(document, xpath));
  }

  public static Boolean pullOptionalBooleanNode(Document document, String xpath) throws Exception {
    return getBooleanFromString(pullOptionalStringNode(document, xpath));
  }

  public static Date pullMandatoryDateNode(Document document, String xpath) throws Exception {
    return DateUtils.getDateFromString(pullMandatoryStringNode(document, xpath));
  }

  public static Date pullOptionalDateNode(Document document, String xpath) throws Exception {
    return DateUtils.getDateFromString(pullOptionalStringNode(document, xpath));
  }

  public static Integer getIntegerFromString(String arg) {
    Integer ret = null;
    if(null != arg)
      ret = Integer.valueOf(arg);
    return ret;
  }

  public static Boolean getBooleanFromString(String arg) {
    Boolean ret = null;
    if(null != arg)
      ret = Boolean.valueOf(arg);
    return ret;
  }
}
