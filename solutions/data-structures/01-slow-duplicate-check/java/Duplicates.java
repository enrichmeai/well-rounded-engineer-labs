// Drill 01, solved: one pass, remembering what we have seen in a HashSet. O(n).

import java.util.HashSet;
import java.util.Set;

public class Duplicates {
    static boolean hasDuplicate(int[] ids) {
        Set<Integer> seen = new HashSet<>();
        for (int id : ids) {
            if (!seen.add(id)) return true;      // add returns false if it was already there
        }
        return false;
    }
}
