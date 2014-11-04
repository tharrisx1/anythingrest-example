package org.tharrisx.test.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.tharrisx.util.collection.MapStringUtils;

public class MapStringUtilsTestCase extends TestCase {

  public void testSimple() {
    List<String> aReq = new ArrayList<>(1);
    aReq.add("name1=value1");
    Map<String, String> aRes = MapStringUtils.constructMap(aReq);
    assertEquals(true, aRes.containsKey("name1"));
    assertEquals(true, aRes.containsValue("value1"));
    assertEquals(1, aRes.size());
    System.out.println("testSimple request 1 list = " + aReq);
    System.out.println("testSimple response 1 map = " + aRes);
    List<String> bRes = MapStringUtils.constructList(aRes, 30);
    assertEquals(1, bRes.size());
    System.out.println("testSimple request 2 map = " + aRes);
    System.out.println("testSimple response 2 list = " + bRes);
  }

  public void testBigger() {
    List<String> aReq = new ArrayList<>(2);
    aReq.add("name1=value1,name2=value2,name");
    aReq.add("3=value3");
    Map<String, String> aRes = MapStringUtils.constructMap(aReq);
    assertEquals(true, aRes.containsKey("name1"));
    assertEquals(true, aRes.containsKey("name2"));
    assertEquals(true, aRes.containsKey("name3"));
    assertEquals(true, aRes.containsValue("value1"));
    assertEquals(true, aRes.containsValue("value2"));
    assertEquals(true, aRes.containsValue("value3"));
    assertEquals(3, aRes.size());
    System.out.println("testBigger request list = " + aReq);
    System.out.println("testBigger response map = " + aRes);
    List<String> bRes = MapStringUtils.constructList(aRes, 30);
    assertEquals(2, bRes.size());
    System.out.println("testBigger request 2 map = " + aRes);
    System.out.println("testBigger response 2 list = " + bRes);
  }

  public void testEvenBigger() {
    List<String> aReq = new ArrayList<>(3);
    aReq.add("name1=value1,name2=value2,name");
    aReq.add("3=value3,name4=value4,name5=va");
    aReq.add("lue5");
    Map<String, String> aRes = MapStringUtils.constructMap(aReq);
    assertEquals(true, aRes.containsKey("name1"));
    assertEquals(true, aRes.containsKey("name2"));
    assertEquals(true, aRes.containsKey("name3"));
    assertEquals(true, aRes.containsValue("value1"));
    assertEquals(true, aRes.containsValue("value2"));
    assertEquals(true, aRes.containsValue("value3"));
    assertEquals(5, aRes.size());
    System.out.println("testEvenBigger request list = " + aReq);
    System.out.println("testEvenBigger response map = " + aRes);
    List<String> bRes = MapStringUtils.constructList(aRes, 30);
    System.out.println("testEvenBigger request 2 map = " + aRes);
    System.out.println("testEvenBigger response 2 list = " + bRes);
    assertEquals(3, bRes.size());
  }
}
