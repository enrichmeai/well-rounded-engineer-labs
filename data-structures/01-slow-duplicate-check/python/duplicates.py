"""Drill 01: the slow duplicate check. Make test_duplicates.py pass, fast."""


def has_duplicate(ids: list[int]) -> bool:
    for i in range(len(ids)):
        for j in range(i + 1, len(ids)):
            if ids[i] == ids[j]:
                return True
    return False
