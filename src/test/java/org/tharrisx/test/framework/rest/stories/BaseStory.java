package org.tharrisx.test.framework.rest.stories;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.junit.Test;
import org.tharrisx.test.util.CodeTimer;

public abstract class BaseStory {

  private static final String SERVER_HOST_PROP = "server.host";
  private static final String SERVER_PORT_PROP = "server.port";
  //private static final String LOGGING_PROP = "test.logging";
  //private static final String TIMING_PROP = "test.timing";

  private static final CodeTimer codeTimer = new CodeTimer();

  public static CodeTimer getCodeTimer() {
    return codeTimer;
  }

  private final String serverHost;

  public String getServerHost() {
    return this.serverHost;
  }

  private final int serverPort;

  public int getServerPort() {
    return this.serverPort;
  }

  private final boolean loggingEnabled;

  public boolean getLoggingEnabled() {
    return this.loggingEnabled;
  }

  private final boolean codeTimingEnabled;

  public boolean isCodeTimingEnabled() {
    return this.codeTimingEnabled;
  }

  public BaseStory() {
    this.serverHost = System.getProperty(SERVER_HOST_PROP);
    this.serverPort = Integer.valueOf(System.getProperty(SERVER_PORT_PROP));
    this.loggingEnabled = true; //Boolean.getBoolean(System.getProperty(LOGGING_PROP));
    this.codeTimingEnabled = true; //Boolean.getBoolean(System.getProperty(TIMING_PROP));
    getCodeTimer().setDisabled(isCodeTimingEnabled());
  }

  @Test
  public abstract void runXmlStory() throws Exception;

  @Test
  public abstract void runJsonStory() throws Exception;

  protected Date simpleDateFromString(String arg) throws ParseException {
    return DateFormat.getDateInstance(DateFormat.SHORT).parse(arg);
  }

  public void startTiming(String operationName) {
    if(isCodeTimingEnabled())
      getCodeTimer().startTiming(operationName);
  }

  public void endTiming(String operationName) {
    if(isCodeTimingEnabled())
      getCodeTimer().endTiming(operationName);
  }
}
