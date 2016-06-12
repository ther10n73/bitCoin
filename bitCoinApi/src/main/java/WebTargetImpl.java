import api.WebTarget;
import com.sun.deploy.net.proxy.ProxyHandler;
import org.codehaus.jackson.annotate.JsonProperty;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.stream.Stream;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by анна on 12.06.2016.
 */
public class WebTargetImpl implements WebTarget {
    private String baseUrl;

    public WebTargetImpl(){}

    public WebTargetImpl(String uri) {
        this.baseUrl = uri;
    }

    @Override
    public <T> T proxy(Class<T> clazz) throws Exception {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new RestInvocationHandler(baseUrl));
    }
}
