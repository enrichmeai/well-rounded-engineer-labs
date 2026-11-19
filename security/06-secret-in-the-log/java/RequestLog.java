import java.util.Map;
import java.util.function.Consumer;

public class RequestLog {
    /** Write one line describing the request, to help debug checkout problems. */
    static void log(Map<String, String> headers, Map<String, Object> body, Consumer<String> write) {
        write.accept("request headers=" + headers + " body=" + body);
    }
}
