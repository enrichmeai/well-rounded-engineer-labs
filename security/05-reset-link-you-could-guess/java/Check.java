// A tiny test helper: no framework, so there is nothing to download. Identical in every drill.

import java.util.Objects;

public class Check {
    static int failures = 0;

    static void equal(String what, Object expected, Object actual) {
        if (Objects.equals(expected, actual)) {
            System.out.println("PASS " + what);
        } else {
            failures++;
            System.out.println("FAIL " + what + ": expected " + expected + ", got " + actual);
        }
    }

    static void isTrue(String what, boolean ok) {
        if (ok) {
            System.out.println("PASS " + what);
        } else {
            failures++;
            System.out.println("FAIL " + what);
        }
    }

    static void done() {
        System.out.println(failures == 0 ? "all checks passed" : failures + " check(s) failed");
        System.exit(failures == 0 ? 0 : 1);
    }
}
