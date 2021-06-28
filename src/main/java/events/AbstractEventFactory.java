package events;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/***
 * https://ahmadatwi.me/2019/04/16/factory-pattern-replacing-the-conditional-statement-with-lambda-expressions/
 */
public class AbstractEventFactory implements EventFactory<Event> {

  private static final Map<String, EventCreator> EVENT_FACTORY_MAP =
      ImmutableMap.of(
          "alert",
          type -> AlertEvent.of(String.valueOf(type.hashCode())),
          "info",
          type -> InfoEvent.of(String.valueOf(type.hashCode())));

  @Override
  public Event create(String type) {
    if (EVENT_FACTORY_MAP.containsKey(type)) {
      return EVENT_FACTORY_MAP.get(type).initialize(type);
    }
    throw new UnsupportedOperationException("error");
  }

  private interface EventCreator {
    Event initialize(String brand);
  }
}
