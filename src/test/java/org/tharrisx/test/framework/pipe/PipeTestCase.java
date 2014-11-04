package org.tharrisx.test.framework.pipe;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;
import org.tharrisx.framework.bean.Bean;
import org.tharrisx.framework.bean.BeanList;
import org.tharrisx.framework.bean.PageableBeanList;
import org.tharrisx.framework.pipe.PipeContext;
import org.tharrisx.framework.pipe.PipeManager;
import org.tharrisx.test.framework.pipe.beans.ExampleBean;
import org.tharrisx.test.framework.pipe.beans.ExampleInnerBean;

public class PipeTestCase extends TestCase {

  protected static final String NL = System.getProperty("line.separator");

  protected static final int ITERATIONS = 10;

  protected String[] pipeNames = new String[] { "json" }; //"xml", 

  protected PipeContext[] contexts = new PipeContext[] { PipeContext.AS_PUBLIC, PipeContext.AS_ADMIN, PipeContext.AS_SUPERADMIN, PipeContext.UNMARSHAL };

  private PipeManager pipeManager = null;

  protected PipeManager getPipeManager() {
    return this.pipeManager;
  }

  protected void setPipeManager(PipeManager arg) {
    this.pipeManager = arg;
  }

  private Bean targetBean = null;

  protected Bean getTargetBean() {
    return this.targetBean;
  }

  protected void setTargetBean(Bean arg) {
    this.targetBean = arg;
  }

  public PipeTestCase(String name) {
    super(name);
  }

  @Override
  protected void setUp() throws Exception {

    setPipeManager(new PipeManager());
    getPipeManager().registerBean(BeanList.class);
    getPipeManager().registerBean(PageableBeanList.class);
    getPipeManager().registerBean(ExampleBean.class);
    getPipeManager().registerBean(ExampleInnerBean.class);
    //getPipeManager().definePipe(this.pipeNames[0], PipeManager.PIPE_FORMAT_XML);
    getPipeManager().definePipe(this.pipeNames[0], PipeManager.PIPE_FORMAT_JSON);
    getPipeManager().initialize();

    ExampleInnerBean exInner1Bean = new ExampleInnerBean();
    exInner1Bean.setName("exampleInnerObject1");
    exInner1Bean.setName2("foo la la la");

    ExampleInnerBean exInner2Bean = new ExampleInnerBean();
    exInner2Bean.setName("exampleInnerObject2");
    exInner2Bean.setName2("foo la la la");

    ExampleBean exampleBean = new ExampleBean();
    exampleBean.setName("exampleObject");
    exampleBean.setSomeNumber(127321);
    exampleBean.setSomeBigNumber(Long.valueOf(920495832L));
    exampleBean.setOmittedNumber(Short.valueOf((short) 17));
    Set<ExampleInnerBean> setOfInnerBeans = new HashSet<>();
    setOfInnerBeans.add(exInner1Bean);
    setOfInnerBeans.add(exInner2Bean);
    exampleBean.setSetOfInnerBeans(setOfInnerBeans);
    List<Map<String, ExampleInnerBean>> crazyListOfMaps = new LinkedList<>();
    Map<String, ExampleInnerBean> listItem1 = new HashMap<>();
    listItem1.put("exampleInnerObject1", exInner1Bean);
    listItem1.put("exampleInnerObject2", exInner2Bean);
    crazyListOfMaps.add(listItem1);
    exampleBean.setCrazyListOfMaps(crazyListOfMaps);

    setTargetBean(exampleBean);
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  @Test
  public void testPiping() throws Exception {
    for(int cv = 0; cv < ITERATIONS; cv++) {
      for(String channel : this.pipeNames) {
        for(PipeContext context : this.contexts) {
          performPipeRead(channel, performPipeWrite(channel, context));
        }
      }
    }
  }

  protected String performPipeWrite(String pipeName, PipeContext pipeContext) {
    String ret = getPipeManager().getPipe(pipeName).writeToString(getTargetBean(), pipeContext);
    //System.out.println("Original Bean: " + getTargetBean().toString());
    //System.out.println(pipeContext.toString() + " " + pipeName + ":");
    //System.out.println(NL + ret);
    return ret;
  }

  protected Bean performPipeRead(String pipeName, String pipeData) {
    Bean ret = getPipeManager().getPipe(pipeName).read(pipeData);
    //System.out.println("Bean from String: " + ret.toString());
    //System.out.println("Are beans equal? " + getTargetBean().equals(ret));
    //assertEquals(getTargetBean(), ret);
    return ret;
  }
}
