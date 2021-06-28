@FunctionalInterface
public interface DataCombiner<T> {
    String calculate(T t);
}
