## Hint 1
`depth(node)` calls `depth(node.parent)` before it can finish, so 100,000 levels need 100,000 calls waiting at once.

## Hint 2
Walk up the chain in a loop instead, counting as you go. The loop keeps one variable, not one stack frame per level.

## Hint 3
`count = 0; while node.parent is not None: node = node.parent; count += 1; return count` (Java: the same `while` loop).
