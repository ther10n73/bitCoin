import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.StreamingOutput;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by анна on 12.06.2016.
 */
public class RestInvocationHandler implements InvocationHandler {
    private String baseUri;
    private List<String> pathParams;
    private final RestTemplate restTemplate = new RestTemplate();

    public RestInvocationHandler(String uri){
        this.baseUri = uri;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(baseUri);
        if (method.isAnnotationPresent(Path.class)) {
            uriBuilder.path(method.getAnnotation(Path.class).value());
        }

        int[] ids = {0};
        pathParams = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        Stream.of(parameters).forEach(p -> {
            if (method.isAnnotationPresent(PathParam.class)){
                pathParams.add((String) args[ids[0]]);
            }
            ids[0]++;
        });

        String finalUri = uriBuilder.build().expand(pathParams.toArray()).toUriString();

        return restTemplate.getForObject(finalUri, method.getReturnType());
    }
}
