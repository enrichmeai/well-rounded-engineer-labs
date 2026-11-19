import random
from collections import Counter

from backoff import backoff_delays


def test_one_wait_per_attempt_within_the_ceiling():
    delays = backoff_delays(8, random.Random(1))
    assert len(delays) == 8
    for n, d in enumerate(delays):
        assert 0 <= d <= min(5.0, 0.1 * 2 ** n), f"attempt {n} waited {d:.3f} s, above its ceiling"


def test_waits_grow():
    clients = [backoff_delays(7, random.Random(seed)) for seed in range(1000)]
    first = sum(c[0] for c in clients) / len(clients)
    later = sum(c[6] for c in clients) / len(clients)
    assert later > 4 * first, "later retries should wait much longer on average than the first"


def test_a_thousand_clients_do_not_retry_in_step():
    # 1,000 clients fail at the same moment; when does each one send its first retry?
    retry_at = [backoff_delays(1, random.Random(seed))[0] for seed in range(1000)]
    busiest = max(Counter(int(t * 1000) // 10 for t in retry_at).values())   # retries per 10 ms window
    assert busiest <= 250, f"{busiest} of 1,000 clients retried in the same 10 ms"
