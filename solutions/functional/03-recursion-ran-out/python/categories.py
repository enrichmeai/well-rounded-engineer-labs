"""Drill 03, solved: a loop walks up the chain with one variable, not one stack frame per level."""


class Category:
    def __init__(self, name: str, parent: "Category | None" = None) -> None:
        self.name = name
        self.parent = parent


def depth(node: Category) -> int:
    count = 0
    while node.parent is not None:
        node = node.parent
        count += 1
    return count
