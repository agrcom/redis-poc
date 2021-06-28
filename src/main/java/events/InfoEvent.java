package events;

import com.google.common.base.Preconditions;

import java.time.Instant;

public record InfoEvent(String name, Instant createdAt) implements Event {
  private static String type = "Info";

  public InfoEvent{
    Preconditions.checkNotNull(name, "Event Name cannot be null!");
  }

  public static InfoEvent of(String name) {
    return new InfoEvent(name, Instant.now());
  }

  @Override
  public String getType() {
    return type;
  }
}
