// Drill 03: the recursion that ran out. Make DepthTest pass.

public class Categories {
    record Category(String name, Category parent) {}

    /** How many levels below the top this category is (the top category is 0). */
    static int depth(Category node) {
        return node.parent() == null ? 0 : 1 + depth(node.parent());
    }
}
