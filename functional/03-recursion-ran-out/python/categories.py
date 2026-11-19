"""Drill 03: the recursion that ran out. Make test_categories.py pass."""


class Category:
    def __init__(self, name: str, parent: "Category | None" = None) -> None:
        self.name = name
        self.parent = parent


def depth(node: Category) -> int:
    """How many levels below the top this category is (the top category is 0)."""
    return 0 if node.parent is None else 1 + depth(node.parent)
