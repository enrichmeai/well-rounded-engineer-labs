"""Drill 01, solved: one pass, remembering what we have seen in a set. O(n)."""


def has_duplicate(ids: list[int]) -> bool:
    seen = set()
    for i in ids:
        if i in seen:          # average O(1): a hash lookup, not a search
            return True
        seen.add(i)
    return False
