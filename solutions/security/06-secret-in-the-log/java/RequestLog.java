import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class RequestLog {
    static final Set<String> SAFE_HEADERS = Set.of("user-agent", "content-type", "x-request-id");
    static final Set<String> SAFE_FIELDS = Set.of("order_id", "currency", "items");

    static <V> Map<String, Object> redact(Map<String, V> values, Set<String> safe, boolean caseInsensitive) {
        Map<String, Object> out = new LinkedHashMap<>();
        values.forEach((name, value) -> {
            String key = caseInsensitive ? name.toLowerCase(Locale.ROOT) : name;
            out.put(name, safe.contains(key) ? value : "[redacted]");
        });
        return out;
    }

    /** Write one line describing the request. Only allow-listed values appear; the rest are named but redacted. */
    static void log(Map<String, String> headers, Map<String, Object> body, Consumer<String> write) {
        write.accept("request headers=" + redact(headers, SAFE_HEADERS, true) + " body=" + redact(body, SAFE_FIELDS, false));
    }
}
