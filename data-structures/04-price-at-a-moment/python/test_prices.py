import random

from prices import price_at
from timing import timed


def test_small_history():
    times, prices = [10, 20, 30], [100, 120, 90]
    assert price_at(times, prices, 5) is None          # before the first price
    assert price_at(times, prices, 10) == 100          # exactly when it took effect
    assert price_at(times, prices, 25) == 120
    assert price_at(times, prices, 99) == 90


def test_five_thousand_refunds_in_under_a_second():
    times = [i * 10 for i in range(100_000)]
    prices = [1000 + i for i in range(100_000)]
    rng = random.Random(1)
    moments = [rng.randrange(0, 1_000_000) for _ in range(5_000)]
    result, _ = timed(lambda: [price_at(times, prices, m) for m in moments], 1.0)
    assert result == [1000 + m // 10 for m in moments]
