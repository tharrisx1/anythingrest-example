package org.tharrisx.test.framework.pipe;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.tharrisx.framework.bean.BeanList;
import org.tharrisx.framework.bean.PageableBeanList;
import org.tharrisx.framework.pipe.PipeContext;
import org.tharrisx.framework.pipe.PipeManager;
import org.tharrisx.frameworkexample.beans.User;
import org.tharrisx.test.util.CodeTimer;
import org.tharrisx.util.RandomGUID;
import org.tharrisx.util.RandomGeneratorUtils;

public class UserPipeTestCase extends PipeTestCase {

  public UserPipeTestCase(String name) {
    super(name);
  }

  @Override
  protected void setUp() throws Exception {

    setPipeManager(new PipeManager());
    getPipeManager().registerBean(BeanList.class);
    getPipeManager().registerBean(PageableBeanList.class);
    getPipeManager().registerBean(User.class);
    //getPipeManager().definePipe(this.pipeNames[0], PipeManager.PIPE_FORMAT_XML);
    getPipeManager().definePipe(this.pipeNames[0], PipeManager.PIPE_FORMAT_JSON);
    getPipeManager().initialize();

    User user = new User();
    user.setId("111495867");
    user.setGender(RandomGeneratorUtils.randomBoolean() ? "m" : "f");
    user.setEmail(RandomGeneratorUtils.randomEmail());
    user.setCity(RandomGeneratorUtils.randomString(10, 30));
    user.setState(RandomGeneratorUtils.randomState());
    user.setPostalCode(RandomGeneratorUtils.randomZipcode());
    user.setCountry("US"); // $$$ play with other country addresses later
    user.setLanguage("en");
    user.setExternalProfileSource("http://www.myexample.com/profile/" + new RandomGUID(true).toString() + "/");
    user.setExternalProfileId(new RandomGUID(true).toString());
    user.setDateOfBirth(simpleDateFromString("05/23/1972"));
    user.setPin(new RandomGUID(true).toString());
    user.setAuthToken(new RandomGUID(true).toString());
    user.setCreated(new Date());
    user.setLastChanged(new Date());

    List<User> list = new LinkedList<>();
    list.add(user);
    setTargetBean(new PageableBeanList<>(list, 1, 1, 1, "", ""));
  }

  @Test
  @Override
  public void testPiping() throws Exception {
    CodeTimer timer = new CodeTimer();
    timer.setDisabled(false);

    for(int cv = 0; cv < ITERATIONS; cv++) {
      for(String channel : this.pipeNames) {
        for(PipeContext context : this.contexts) {

          timer.startTiming("pipe write " + context.toString() + " " + channel);
          String written = performPipeWrite(channel, context);
          timer.endTiming("pipe write " + context.toString() + " " + channel);

          timer.startTiming("pipe read " + context.toString() + " " + channel);
          performPipeRead(channel, written);
          timer.endTiming("pipe read " + context.toString() + " " + channel);
        }
      }
    }

    System.out.println(timer.getReport());
  }

  private Date simpleDateFromString(String arg) throws ParseException {
    return DateFormat.getDateInstance(DateFormat.SHORT).parse(arg);
  }
}
