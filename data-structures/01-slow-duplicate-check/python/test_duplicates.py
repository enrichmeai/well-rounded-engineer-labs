from duplicates import has_duplicate
from timing import timed


def test_small_cases():
    assert has_duplicate([]) is False
    assert has_duplicate([7]) is False
    assert has_duplicate([3, 1, 4, 1, 5]) is True
    assert has_duplicate([1, 2, 3]) is False


def test_twenty_thousand_ids_in_under_a_second():
    ids = list(range(20_000))                    # no duplicates: the slowest case for any check
    result, _ = timed(lambda: has_duplicate(ids), 1.0)
    assert result is False
    result, _ = timed(lambda: has_duplicate(ids + [19_999]), 1.0)
    assert result is True
