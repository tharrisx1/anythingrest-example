package org.tharrisx.test.framework.rest.stories;

import java.net.URI;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.tharrisx.test.framework.rest.api.PipeFormat;
import org.tharrisx.test.framework.rest.api.RootedBeanTypeApi;
import org.tharrisx.test.framework.rest.beans.ExampleBeanList;
import org.tharrisx.test.framework.rest.beans.ExamplePageableBeanList;
import org.tharrisx.test.framework.rest.beans.ExampleUser;
import org.tharrisx.util.RandomGUID;
import org.tharrisx.util.RandomGeneratorUtils;

public class UserStory extends BaseStory {

  private Map<PipeFormat, RootedBeanTypeApi<ExampleUser>> userApiMap;

  private Map<PipeFormat, RootedBeanTypeApi<ExampleUser>> getUserApiMap() {
    return this.userApiMap;
  }

  private RootedBeanTypeApi<ExampleUser> getUserApi(PipeFormat pipeFormat) {
    return getUserApiMap().get(pipeFormat);
  }

  @Before
  public void setUp() throws Exception {
    this.userApiMap = new HashMap<>();
    for(PipeFormat pipeFormat : PipeFormat.values()) {
      getUserApiMap().put(pipeFormat, new RootedBeanTypeApi<>(ExampleUser.class, pipeFormat, new String[] { "users" }, "user", getServerHost(), getServerPort(), getLoggingEnabled()));
    }
  }

  @Override
  @Test
  public void runXmlStory() throws Exception {
    runStory(PipeFormat.XML);
  }

  @Override
  @Test
  public void runJsonStory() throws Exception {
    runStory(PipeFormat.JSON);
  }

  public void runStory(PipeFormat pipeFormat) throws Exception {

    ExamplePageableBeanList<ExampleUser> usersList1 = null;
    ExamplePageableBeanList<ExampleUser> usersList2 = null;
    ExampleBeanList<ExampleUser> completeUserList = null;
    ExampleUser firstUser = null;
    ExampleUser newUser = null;
    String aUserId = null;
    Date birthDate = null;

    startTiming(pipeFormat.name() + " users post");
    RootedBeanTypeApi<ExampleUser> userApi = getUserApi(pipeFormat);
    URI uri = userApi.postItem(generateTestUser());
    String postedUserLocation = uri.getPath();
    endTiming(pipeFormat.name() + " users post");

    aUserId = postedUserLocation.substring(postedUserLocation.lastIndexOf('/') + 1, postedUserLocation.length());

    startTiming(pipeFormat.name() + " users get all");
    completeUserList = getUserApi(pipeFormat).getAllItems();
    endTiming(pipeFormat.name() + " users get all");
    assert (completeUserList.getList().size() > 0);

    startTiming(pipeFormat.name() + " users get default");
    usersList1 = getUserApi(pipeFormat).getItems();
    endTiming(pipeFormat.name() + " users get default");
    assert (usersList1.getList().size() > 0);

    startTiming(pipeFormat.name() + " users get page");
    usersList2 = getUserApi(pipeFormat).getItems(0, 30, "lastName,firstName", "asc,asc");
    endTiming(pipeFormat.name() + " users get page");
    assert (usersList2.getTotalCount() > 0);

    Map<String, String> matchProps = new HashMap<>();
    matchProps.put("state", "NY");

    startTiming(pipeFormat.name() + " users get matching");
    usersList1 = getUserApi(pipeFormat).getItems(matchProps);
    endTiming(pipeFormat.name() + " users get matching");
    //assert (usersList1.getList().size() > 0);

    startTiming(pipeFormat.name() + " users get matching page");
    usersList2 = getUserApi(pipeFormat).getItems(0, 30, "lastName,firstName", "asc,asc", matchProps);
    endTiming(pipeFormat.name() + " users get matching page");
    //assert (usersList2.getTotalCount() > 0);

    // dont use a random one, since the different threads step on each other
    //aUserId = usersList1.getList().get(RandomGeneratorUtils.randomInt(0, usersList1.getList().size())).getId();

    startTiming(pipeFormat.name() + " users get id before put");
    firstUser = getUserApi(pipeFormat).getItem(aUserId);
    endTiming(pipeFormat.name() + " users get id before put");

    birthDate = simpleDateFromString("05/23/1972");
    firstUser.setDateOfBirth(birthDate);

    startTiming(pipeFormat.name() + " users put id");
    getUserApi(pipeFormat).putItem(firstUser, aUserId);
    endTiming(pipeFormat.name() + " users put id");

    startTiming(pipeFormat.name() + " users get id after put");
    newUser = getUserApi(pipeFormat).getItem(aUserId);
    endTiming(pipeFormat.name() + " users get id after put");

    //System.out.println("newUser=" + newUser);

    //System.out.println("birthDate=" + birthDate);
    //System.out.println("newUser.getDateOfBirth()=" + newUser.getDateOfBirth());
    assert (firstUser.getEmail().equals(newUser.getEmail()));

    //startTiming(pipeFormat.name() + " users delete id");
    //getUserApi(pipeFormat).deleteItem(firstUser.getId());
    //endTiming(pipeFormat.name() + " users delete id");

  }

  private ExampleUser generateTestUser() throws ParseException {
    ExampleUser exampleUser = new ExampleUser();
    exampleUser.setLastName(RandomGeneratorUtils.randomString(6, 20));
    exampleUser.setFirstName(RandomGeneratorUtils.randomString(6, 12));
    exampleUser.setGender(RandomGeneratorUtils.randomBoolean() ? "m" : "f");
    exampleUser.setEmail(RandomGeneratorUtils.randomEmail());
    exampleUser.setCity(RandomGeneratorUtils.randomString(10, 30));
    exampleUser.setState(RandomGeneratorUtils.randomState());
    exampleUser.setPostalCode(RandomGeneratorUtils.randomZipcode());
    exampleUser.setCountry("US"); // $$$ play with other country addresses later
    exampleUser.setLanguage("en");
    exampleUser.setExternalProfileSource("http://www.myexample.com/profile/" + new RandomGUID(true).toString() + "/");
    exampleUser.setExternalProfileId(new RandomGUID(true).toString());
    exampleUser.setDateOfBirth(simpleDateFromString("04/23/1972"));
    exampleUser.setPin(new RandomGUID(true).toString());
    exampleUser.setAuthToken(new RandomGUID(true).toString());
    return exampleUser;
  }
}
