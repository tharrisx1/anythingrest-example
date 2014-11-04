package org.tharrisx.test.framework.rest.suites;

import junit.framework.Test;

import org.tharrisx.test.framework.rest.stories.UserStory;

public class UserLoadSuite {

  private static final int USERS = 3;
  private static final int ITERATIONS = 20;
  private static final long CONSTANT_DELAY = 50;
  private static final double DELAY_VARIATION = 0.2;
  private static final long MAX_ELAPSED_TIME = 60000;

  public static Test suite() {
    return LoadGenerator.createLoadTest(UserStory.class, USERS, ITERATIONS, CONSTANT_DELAY, DELAY_VARIATION, MAX_ELAPSED_TIME);
  }
}
