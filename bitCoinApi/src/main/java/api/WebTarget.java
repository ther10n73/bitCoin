package api;

/**
 * Created by анна on 12.06.2016.
 */
public interface WebTarget {
    <T> T proxy(Class<T> clazz) throws Exception;
}
