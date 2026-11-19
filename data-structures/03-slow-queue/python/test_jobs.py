from jobs import process_all
from timing import timed


def test_oldest_first():
    assert process_all([3, 1, 2]) == [6, 2, 4]
    assert process_all([]) == []


def test_three_hundred_thousand_jobs_in_under_a_second():
    jobs = list(range(300_000))
    result, _ = timed(lambda: process_all(jobs), 1.0)
    assert result[:3] == [0, 2, 4] and result[-1] == 599_998 and len(result) == 300_000
