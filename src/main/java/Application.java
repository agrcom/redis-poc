import common.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import persistent.RedisService;
import user.User;
import user.UserRecord;

@Slf4j
public class Application {

  private static final JsonConverter converter = new JsonConverter();

  public static void main(String[] args) {
    User user = new User("John", "Test", "tester");
    User user2 = new User("Jan", "Test", "tester2");

    RedisService service = new RedisService("localhost", 6379, "redis-server");

    var key = "user";

    try {
      service.add(key, new UserRecord("john", "test"));
      String json = converter.convertToJson(new UserRecord("john", "Testowy"));
      service.add(key, json);

      var response = service.get(key);
      log.info("Successfully added {} value for key {} ", response, key);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
