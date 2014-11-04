package org.tharrisx.test.framework.rest.stories;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.tharrisx.test.framework.rest.api.BaseSubsidiaryBeanApi;
import org.tharrisx.test.framework.rest.api.PipeFormat;
import org.tharrisx.test.framework.rest.beans.ExampleUserEvent;

public class UserEventStory extends BaseStory {

  private Map<PipeFormat, BaseSubsidiaryBeanApi<ExampleUserEvent>> userEventApiMap;

  private Map<PipeFormat, BaseSubsidiaryBeanApi<ExampleUserEvent>> getUserEventApiMap() {
    return this.userEventApiMap;
  }

  @SuppressWarnings("unused")
  private BaseSubsidiaryBeanApi<ExampleUserEvent> getUserEventApi(PipeFormat pipeFormat) {
    return getUserEventApiMap().get(pipeFormat);
  }

  @Before
  public void setUp() throws Exception {
    this.userEventApiMap = new HashMap<>();
    for(PipeFormat pipeFormat : PipeFormat.values()) {
      getUserEventApiMap().put(pipeFormat,
          new BaseSubsidiaryBeanApi<>(ExampleUserEvent.class, pipeFormat, new String[] { "users" }, "events", "event", getServerHost(), getServerPort(), getLoggingEnabled()));
    }
  }

  @Override
  @Test
  public void runXmlStory() throws Exception {
    //runStory(PipeFormat.XML);
  }

  @Override
  @Test
  public void runJsonStory() throws Exception {
    //runStory(PipeFormat.JSON);
  }

  //  public void runStory(PipeFormat pipeFormat) throws Exception {
  //  ExampleUser randomUser = null;
  //ExampleBeanList<ExampleUser> userList = getUserEventApi(pipeFormat).g

  /*
  ExamplePageableBeanList<ExampleUser> usersList1 = null;
  ExamplePageableBeanList<ExampleUser> usersList2 = null;
  ExampleUser firstUser = null;
  ExampleUser newUser = null;
  String aUserId = null;
  Date birthDate = null;

  getCodeTimer().startTiming(pipeFormat.name() +  " users post");
  String postedUserLocation = getUserApi(pipeFormat).postItem(generateTestUser()).getPath();
  getCodeTimer().endTiming(pipeFormat.name() +  " users post");

  //System.out.println("postedUserLocation=" + postedUserLocation);
  aUserId = postedUserLocation.substring(postedUserLocation.lastIndexOf('/') + 1, postedUserLocation.length());
  //System.out.println("aUserId=" + aUserId);

  getCodeTimer().startTiming(pipeFormat.name() +  " users get default");
  usersList1 = getUserApi(pipeFormat).getItems();
  getCodeTimer().endTiming(pipeFormat.name() +  " users get default");
  assert (usersList1.getList().size() > 0);

  getCodeTimer().startTiming(pipeFormat.name() +  " users get page");
  usersList2 = getUserApi(pipeFormat).getItems(0, 30);
  getCodeTimer().endTiming(pipeFormat.name() +  " users get page");
  assert (usersList2.getTotalCount() > 0);

  Map<String, String> matchProps = new HashMap<String, String>();
  matchProps.put("state", "NY");

  getCodeTimer().startTiming(pipeFormat.name() +  " users get matching");
  usersList1 = getUserApi(pipeFormat).getItems(matchProps);
  getCodeTimer().endTiming(pipeFormat.name() +  " users get matching");
  assert (usersList1.getList().size() > 0);

  getCodeTimer().startTiming(pipeFormat.name() +  " users get matching page");
  usersList2 = getUserApi(pipeFormat).getItems(0, 30, matchProps);
  getCodeTimer().endTiming(pipeFormat.name() +  " users get matching page");
  assert (usersList2.getTotalCount() > 0);

  // dont use a random one, since the different threads step on each other
  //aUserId = usersList1.getList().get(RandomGeneratorUtils.randomInt(0, usersList1.getList().size())).getId();

  getCodeTimer().startTiming(pipeFormat.name() +  " users get id before put");
  firstUser = getUserApi(pipeFormat).getItem(aUserId);
  getCodeTimer().endTiming(pipeFormat.name() +  " users get id before put");

  birthDate = simpleDateFromString("05/23/1972");
  firstUser.setDateOfBirth(birthDate);

  getCodeTimer().startTiming(pipeFormat.name() +  " users put id");
  getUserApi(pipeFormat).putItem(firstUser, aUserId);
  getCodeTimer().endTiming(pipeFormat.name() +  " users put id");

  getCodeTimer().startTiming(pipeFormat.name() +  " users get id after put");
  newUser = getUserApi(pipeFormat).getItem(aUserId);
  getCodeTimer().endTiming(pipeFormat.name() +  " users get id after put");

  //System.out.println("newUser=" + newUser);

  //System.out.println("birthDate=" + birthDate);
  //System.out.println("newUser.getDateOfBirth()=" + newUser.getDateOfBirth());
  assert (firstUser.getEmail().equals(newUser.getEmail()));

  getCodeTimer().startTiming(pipeFormat.name() +  " users delete id");
  getUserApi(pipeFormat).deleteItem(firstUser.getId());
  getCodeTimer().endTiming(pipeFormat.name() +  " users delete id");

  private ExampleUserEvent generateTestUserEvent() throws ParseException {
  ExampleUserEvent item = new ExampleUserEvent();
  item.setGender(RandomGeneratorUtils.randomBoolean() ? "m" : "f");
  item.setEmail(RandomGeneratorUtils.randomEmail());
  item.setCity(RandomGeneratorUtils.randomString(10, 30));
  item.setState(RandomGeneratorUtils.randomState());
  item.setPostalCode(RandomGeneratorUtils.randomZipcode());
  item.setCountry("US"); // $$$ play with other country addresses later
  item.setLanguage("en");
  item.setExternalProfileSource("http://www.myexample.com/profile/" + new RandomGUID(true).toString() + "/");
  item.setExternalProfileId(new RandomGUID(true).toString());
  item.setDateOfBirth(simpleDateFromString("04/23/1972"));
  item.setPin(new RandomGUID(true).toString());
  item.setAuthToken(new RandomGUID(true).toString());
  return item;
  }
  */
}
