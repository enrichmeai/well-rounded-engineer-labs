import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InvoiceFiles {
    /** Return the invoice called name from the folder base, and nothing outside it. */
    static byte[] read(Path base, String name) throws IOException {
        Path root = base.toRealPath();
        Path target = root.resolve(name).normalize();   // removes "..", and an absolute name replaces root
        if (!target.startsWith(root)) {
            throw new SecurityException("not an invoice: " + name);
        }
        Path real = target.toRealPath();                // follows links
        if (!real.startsWith(root)) {
            throw new SecurityException("not an invoice: " + name);
        }
        return Files.readAllBytes(real);
    }
}
