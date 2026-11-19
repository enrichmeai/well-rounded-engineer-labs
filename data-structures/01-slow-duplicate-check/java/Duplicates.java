// Drill 01: the slow duplicate check. Make DuplicatesTest pass, fast.

public class Duplicates {
    static boolean hasDuplicate(int[] ids) {
        for (int i = 0; i < ids.length; i++) {
            for (int j = i + 1; j < ids.length; j++) {
                if (ids[i] == ids[j]) return true;
            }
        }
        return false;
    }
}
