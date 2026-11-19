import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InvoiceFiles {
    /** Return the invoice called name from the folder base. */
    static byte[] read(Path base, String name) throws IOException {
        return Files.readAllBytes(base.resolve(name));
    }
}
