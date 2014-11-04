package org.tharrisx.test.util;

//import java.util.Date;

import org.tharrisx.util.RandomGeneratorUtils;

public class Playground {

  public static void main(String[] args) {

    //long dateValue = 72853200000L;
    //System.out.println(new Date(dateValue));

    for(int cv = 0; cv < 50; cv++) {
      System.out.println(RandomGeneratorUtils.randomBoolean());
    }

  }
}
