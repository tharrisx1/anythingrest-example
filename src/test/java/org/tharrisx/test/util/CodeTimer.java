package org.tharrisx.test.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.tharrisx.util.Statistics;
import org.tharrisx.util.text.StringPadder;

import edu.emory.mathcs.backport.java.util.concurrent.helpers.Utils;

public class CodeTimer {

  static final String NL = System.getProperty("line.separator");

  static class OpStats {

    private static NumberFormat COUNT_FORMAT = new DecimalFormat("#######0");
    private static NumberFormat TIME_NS_FORMAT = new DecimalFormat("#######0");
    private static NumberFormat TIME_MCS_FORMAT = new DecimalFormat("##0.0###");
    private static NumberFormat TIME_MS_FORMAT = new DecimalFormat("##0.0###");
    private static NumberFormat TIME_S_FORMAT = new DecimalFormat("###0.000");
    private static NumberFormat TIME_M_FORMAT = new DecimalFormat("###0.000");
    private static NumberFormat TIME_H_FORMAT = new DecimalFormat("###0.000");
    private static NumberFormat TIME_D_FORMAT = new DecimalFormat("###0.000");

    private static double TIMECONV_TO_MCS = 1000.0;
    private static double TIMECONV_TO_MS = TIMECONV_TO_MCS * 1000.0;
    private static double TIMECONV_TO_S = TIMECONV_TO_MS * 1000.0;
    private static double TIMECONV_TO_M = TIMECONV_TO_S * 60.0;
    private static double TIMECONV_TO_H = TIMECONV_TO_M * 60.0;
    private static double TIMECONV_TO_D = TIMECONV_TO_H * 24.0;

    int callCount = 0;
    List<Double> times = new LinkedList<>(); // containing nanoseconds
    long startTime; // containing nanoseconds

    void startTiming() {
      this.startTime = Utils.nanoTime();
    }

    void endTiming() {
      this.times.add(new Double(Utils.nanoTime() - this.startTime));
      this.callCount++;
    }

    private Double[] getTimesAsDoubleArray() {
      return this.times.toArray(new Double[this.callCount]);
    }

    @Override
    public String toString() {
      StringBuilder retBuf = new StringBuilder().append("| ");
      retBuf.append(StringPadder.rightPad(getCount(), " ", 8)).append("| ");
      retBuf.append(StringPadder.rightPad(getTotal(), " ", 10)).append("| ");
      if(this.callCount > 2) {
        retBuf.append(StringPadder.rightPad(getMean(), " ", 10)).append("| ");
        retBuf.append(StringPadder.rightPad(getMedian(), " ", 10)).append("| ");
        retBuf.append(StringPadder.rightPad(getMeanMedianDiff(), " ", 10)).append("| ");
        retBuf.append(StringPadder.rightPad(getStdDev(), " ", 10)).append("| ");
        retBuf.append(StringPadder.rightPad(getVariance(), " ", 10)).append("| ");
      } else {
        retBuf.append(StringPadder.rightPad(" n/a", " ", 10)).append("| ");
        retBuf.append(StringPadder.rightPad(" n/a", " ", 10)).append("| ");
        retBuf.append(StringPadder.rightPad(" n/a", " ", 10)).append("| ");
        retBuf.append(StringPadder.rightPad(" n/a", " ", 10)).append("| ");
        retBuf.append(StringPadder.rightPad(" n/a", " ", 10)).append("| ");
      }
      return retBuf.toString();
    }

    private String getCount() {
      return COUNT_FORMAT.format(this.callCount);
    }

    private String getTotal() {
      return formatTime(Statistics.computeTotal(getTimesAsDoubleArray()));
    }

    private String getMean() {
      return formatTime(Statistics.computeMean(getTimesAsDoubleArray()));
    }

    private String getMedian() {
      return formatTime(Statistics.computeMedian(getTimesAsDoubleArray()));
    }

    private String getMeanMedianDiff() {
      Double[] data = getTimesAsDoubleArray();
      return formatTime(new Double(Math.abs(Statistics.computeMean(data).doubleValue() - Statistics.computeMedian(data).doubleValue())));
    }

    private String getStdDev() {
      return formatTime(Statistics.computeStandardDeviation(getTimesAsDoubleArray()));
    }

    private String getVariance() {
      return formatTime(Statistics.computeVariance(getTimesAsDoubleArray()));
    }

    private String formatTime(Number time) {
      String ret = null;
      if(time.doubleValue() < TIMECONV_TO_MCS) {
        ret = TIME_NS_FORMAT.format(time.longValue()) + "ns";
      } else if(time.doubleValue() < TIMECONV_TO_MS) {
        ret = TIME_MCS_FORMAT.format(time.longValue() / TIMECONV_TO_MCS) + "\u00B5s";
      } else if(time.doubleValue() < TIMECONV_TO_S) {
        ret = TIME_MS_FORMAT.format(time.longValue() / TIMECONV_TO_MS) + "ms";
      } else if(time.doubleValue() < TIMECONV_TO_M) {
        ret = TIME_S_FORMAT.format(time.longValue() / TIMECONV_TO_S) + "s";
      } else if(time.doubleValue() < TIMECONV_TO_H) {
        ret = TIME_M_FORMAT.format(time.longValue() / TIMECONV_TO_M) + "m";
      } else if(time.doubleValue() < TIMECONV_TO_D) {
        ret = TIME_H_FORMAT.format(time.longValue() / TIMECONV_TO_H) + "h";
      } else {
        ret = TIME_D_FORMAT.format(time.longValue() / TIMECONV_TO_D) + "d";
      }
      return ret;
    }
  }

  boolean disabled = false;

  public boolean isDisabled() {
    return this.disabled;
  }

  public void setDisabled(boolean arg) {
    this.disabled = arg;
  }

  private final SortedMap<String, OpStats> opStatMap = new TreeMap<>();

  private SortedMap<String, OpStats> getOpStatMap() {
    return this.opStatMap;
  }

  public synchronized void startTiming(String op) {
    if(!isDisabled())
      getOpStats(op).startTiming();
  }

  public synchronized void endTiming(String op) {
    if(!isDisabled())
      getOpStats(op).endTiming();
  }

  public synchronized String getReport() {
    if(isDisabled())
      return "CodeTimer was disabled.";
    StringBuilder retBuf = new StringBuilder();
    int maxOpWidth = 0;
    int keyLength = 0;
    for(String key : getOpStatMap().keySet()) {
      keyLength = key.length();
      if(keyLength > maxOpWidth)
        maxOpWidth = keyLength;
    }
    maxOpWidth++;
    retBuf.append(NL).append("Code Timer Report");
    reportSeparatorLine(retBuf, maxOpWidth);
    reportHeaderLine(retBuf, maxOpWidth);
    reportSeparatorLine(retBuf, maxOpWidth);
    for(Map.Entry<String, OpStats> entry : getOpStatMap().entrySet()) {
      retBuf.append(NL).append("|").append(StringPadder.rightPad(entry.getKey(), " ", maxOpWidth)).append(entry.getValue());
    }
    reportSeparatorLine(retBuf, maxOpWidth);
    return retBuf.toString();
  }

  private void reportHeaderLine(StringBuilder retBuf, int opWidth) {
    retBuf.append(NL).append("|").append(StringPadder.rightPad(" Operation ", " ", opWidth)).append("| ");
    retBuf.append(StringPadder.rightPad("Count", " ", 8)).append("| ");
    retBuf.append(StringPadder.rightPad("Total", " ", 10)).append("| ");
    retBuf.append(StringPadder.rightPad("Mean", " ", 10)).append("| ");
    retBuf.append(StringPadder.rightPad("Median", " ", 10)).append("| ");
    retBuf.append(StringPadder.rightPad("< Diff", " ", 10)).append("| ");
    retBuf.append(StringPadder.rightPad("Std.Dev", " ", 10)).append("| ");
    retBuf.append(StringPadder.rightPad("Variance", " ", 10)).append("| ");
  }

  private void reportSeparatorLine(StringBuilder retBuf, int opWidth) {
    retBuf.append(NL).append("+").append(StringPadder.rightPad("", "-", opWidth)).append("+");
    retBuf.append(StringPadder.rightPad("", "-", 9)).append("+");
    retBuf.append(StringPadder.rightPad("", "-", 11)).append("+");
    retBuf.append(StringPadder.rightPad("", "-", 11)).append("+");
    retBuf.append(StringPadder.rightPad("", "-", 11)).append("+");
    retBuf.append(StringPadder.rightPad("", "-", 11)).append("+");
    retBuf.append(StringPadder.rightPad("", "-", 11)).append("+");
    retBuf.append(StringPadder.rightPad("", "-", 11)).append("+");
  }

  private OpStats getOpStats(String op) {
    OpStats ret = getOpStatMap().get(op);
    if(null == ret) {
      ret = new OpStats();
      getOpStatMap().put(op, ret);
    }
    return ret;
  }
}
