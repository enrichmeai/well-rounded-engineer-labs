import threading

import pipeline

PRICES = {"tea": 3, "cake": 5}


def run_with_timeout(orders, seconds=5):
    box = {}
    t = threading.Thread(target=lambda: box.update(result=pipeline.run(orders, PRICES)), daemon=True)
    t.start()
    t.join(seconds)
    assert not t.is_alive(), f"run() did not finish within {seconds} seconds: a worker died without sending STOP"
    return box["result"]


def test_good_orders():
    orders = [{"id": i, "item": "tea" if i % 2 else "cake", "qty": i} for i in range(1, 7)]
    totals, failed = run_with_timeout(orders)
    assert totals == {1: 3, 2: 10, 3: 9, 4: 20, 5: 15, 6: 30}
    assert failed == []


def test_one_unpriced_item_is_reported_not_hung():
    orders = [{"id": 1, "item": "tea", "qty": 2}, {"id": 2, "item": "scone", "qty": 1},
              {"id": 3, "item": "cake", "qty": 1}]
    totals, failed = run_with_timeout(orders)
    assert totals == {1: 6, 3: 5}
    assert failed == [2]
