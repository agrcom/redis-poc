package persistent;

import lombok.extern.slf4j.Slf4j;
import user.UserRecord;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Slf4j
public class RedisService {

  private final JedisPoolConfig poolCfg = new JedisPoolConfig();
  private String hostname;
  private String dbName;
  private int port;
  private JedisPool pool;

  public RedisService(final String hostname, int port, String dbName) {
    this.hostname = hostname;
    this.port = port;
    this.dbName = dbName;

    poolCfg.setMaxTotal(3);
    this.pool = new JedisPool(poolCfg, hostname, port, 100, false);
  }

  public void connect() throws Exception {
    try (Jedis jedis = pool.getResource()) {
      jedis.connect();
    } catch (Exception ex) {
      throw new Exception("Error occurs during connection to Redis");
    }
  }

  public void add(String key, UserRecord userRecord) {
    try (Jedis jedis = pool.getResource()) {
      jedis.set(key, userRecord.toString());
    } catch (Exception ex) {
      log.error(ex.getMessage());
    }
  }

  public void add(String key, String json) {
    try (Jedis jedis = pool.getResource()) {
      jedis.set(key, json);
    } catch (Exception ex) {
      log.error(ex.getMessage());
    }
  }

  public String get(String key) {
    try (Jedis jedis = pool.getResource()) {
      return jedis.get(key);

    } catch (Exception ex) {
      log.error(ex.getMessage());
    }
    return key;
  }

}
