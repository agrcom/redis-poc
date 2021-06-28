package events;

import java.time.Instant;


public record AlertEvent(String name, Instant createdAt) implements Event{

    private static String type = "Alert";

    public static AlertEvent of(String name){
        return new AlertEvent(name, Instant.now());
    }

    @Override
    public String getType() {
        return type;
    }
}
