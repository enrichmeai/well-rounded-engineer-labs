import java.nio.file.Files;
import java.nio.file.Path;

public class InvoiceFilesTest {
    static void refused(String what, Path base, String name) {
        try {
            InvoiceFiles.read(base, name);
            Check.isTrue(what + " is refused (it was returned)", false);
        } catch (SecurityException e) {
            Check.isTrue(what + " is refused", true);
        } catch (Exception e) {
            Check.isTrue(what + " is refused with SecurityException, not " + e, false);
        }
    }

    public static void main(String[] args) throws Exception {
        Path tmp = Files.createTempDirectory("labs");
        Path base = Files.createDirectories(tmp.resolve("invoices").resolve("2026")).getParent();
        Files.writeString(base.resolve("inv-1042.pdf"), "invoice 1042");
        Files.writeString(base.resolve("2026").resolve("inv-2001.pdf"), "invoice 2001");
        Path secret = Files.writeString(tmp.resolve("secret.txt"), "DB_PASSWORD=hunter2");
        Files.writeString(Files.createDirectories(tmp.resolve("invoices-old")).resolve("inv-0001.pdf"), "another customer's invoice");

        Check.equal("an invoice in the folder", "invoice 1042", new String(InvoiceFiles.read(base, "inv-1042.pdf")));
        Check.equal("an invoice in a subfolder", "invoice 2001", new String(InvoiceFiles.read(base, "2026/inv-2001.pdf")));
        refused("../secret.txt", base, "../secret.txt");
        refused("a sibling folder with a similar name", base, "../invoices-old/inv-0001.pdf");
        refused("an absolute path", base, secret.toString());
        try {
            Files.createSymbolicLink(base.resolve("inv-9999.pdf"), secret);
            refused("a link pointing outside", base, "inv-9999.pdf");
        } catch (UnsupportedOperationException | java.io.IOException e) {
            System.out.println("SKIP this system cannot create symbolic links");
        }
        Check.done();
    }
}
