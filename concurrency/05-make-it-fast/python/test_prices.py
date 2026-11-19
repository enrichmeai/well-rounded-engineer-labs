import time

import prices


def test_same_prices_same_order_under_half_a_second():
    skus = [f"sku-{i}" for i in range(20)]
    start = time.perf_counter()
    result = prices.fetch_all(skus)
    elapsed = time.perf_counter() - start
    assert result == [100 + i for i in range(20)]
    assert elapsed < 0.5, f"took {elapsed:.2f} s for 20 calls of 0.1 s each; the goal is under 0.5 s"
