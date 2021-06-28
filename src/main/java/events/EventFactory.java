package events;

public interface EventFactory <T>{
    T create(String type);
}
