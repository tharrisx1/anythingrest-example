package org.tharrisx.test.util;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.clarkware.junitperf.TestFactory;

public class JUnit4TestFactory extends TestFactory {

  static class DummyTestCase extends TestCase {
    public void test() {
      // dummy
    }
  }

  private final Class<?> junit4TestClass;

  public JUnit4TestFactory(Class<?> testClass1) {
    super(DummyTestCase.class);
    this.junit4TestClass = testClass1;
  }

  @Override
  public TestSuite makeTestSuite() {
    JUnit4TestAdapter unit4TestAdapter = new JUnit4TestAdapter(this.junit4TestClass);
    TestSuite testSuite = new TestSuite("JUnit4TestFactory");
    testSuite.addTest(unit4TestAdapter);
    return testSuite;
  }
}
