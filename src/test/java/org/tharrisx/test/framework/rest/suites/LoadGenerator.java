package org.tharrisx.test.framework.rest.suites;

import junit.extensions.RepeatedTest;
import junit.extensions.TestDecorator;
import junit.framework.Test;
import junit.framework.TestResult;

import org.tharrisx.test.framework.rest.stories.BaseStory;
import org.tharrisx.test.util.JUnit4TestFactory;

import com.clarkware.junitperf.LoadTest;
import com.clarkware.junitperf.RandomTimer;
import com.clarkware.junitperf.TimedTest;

public class LoadGenerator {

  public static class CodeTimingTest extends TestDecorator {

    public CodeTimingTest(Test test) {
      super(test);
    }

    @Override
    public void run(TestResult result) {
      super.run(result);
      System.out.println(BaseStory.getCodeTimer().getReport());
    }
  }

  public static Test createLoadTest(Class<? extends BaseStory> story, int users, int iterations, long constantDelay, double delayVariation, long maxDuration) {
    Test storyTest = new JUnit4TestFactory(story).makeTestSuite();
    Test repeatTest = new RepeatedTest(storyTest, iterations);
    Test loadTest = new LoadTest(repeatTest, users, new RandomTimer(constantDelay, delayVariation));
    Test timedTest = new TimedTest(loadTest, maxDuration);
    Test timerTest = new CodeTimingTest(timedTest);
    return timerTest;
  }
}
