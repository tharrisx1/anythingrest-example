package org.tharrisx.test.util;

import java.util.logging.Logger;
import java.util.regex.Pattern;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.tharrisx.util.text.RegexPatterns;

public class PatternTests extends TestCase {

  private static final Logger logger = Logger.getLogger(PatternTests.class.getName());

  public PatternTests(String arg0) {
    super(arg0);
  }

  /**
   * the suite() method gives the opportunity to select which test method
   * to launch, otherwise the default configuration would be to launch every
   * method with the test prefix 
   * @return Test
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTest(new PatternTests("testEMailPattern"));
    return suite;
  }

  public void testEMailPattern() {
    logger.info("testEMailPattern");

    String validEMail1 = "matthias.hader@rga.com";
    logger.info("test: " + validEMail1);
    assertTrue(Pattern.compile(RegexPatterns.EMAIL_PATTERN).matcher(validEMail1).matches());

    String validEMail2 = "matthias.hader@rga.ny.co.uk";
    logger.info("test: " + validEMail2);
    assertTrue(Pattern.compile(RegexPatterns.EMAIL_PATTERN).matcher(validEMail2).matches());

    String validEMail3 = "matt-hias.ha-der@123rga-ny.info";
    logger.info("test: " + validEMail3);
    assertTrue(Pattern.compile(RegexPatterns.EMAIL_PATTERN).matcher(validEMail3).matches());

    String invalidEMail1 = "matthias.hader@rga.communist";
    logger.info("test: " + invalidEMail1);
    assertFalse(Pattern.compile(RegexPatterns.EMAIL_PATTERN).matcher(invalidEMail1).matches());

    String invalidEMail2 = "matthias.haderrga.com";
    logger.info("test: " + invalidEMail2);
    assertFalse(Pattern.compile(RegexPatterns.EMAIL_PATTERN).matcher(invalidEMail2).matches());

  }

}
