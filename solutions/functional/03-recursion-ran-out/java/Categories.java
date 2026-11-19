// Drill 03, solved: a loop walks up the chain with one variable, not one stack frame per level.

public class Categories {
    record Category(String name, Category parent) {}

    static int depth(Category node) {
        int count = 0;
        while (node.parent() != null) {
            node = node.parent();
            count++;
        }
        return count;
    }
}
