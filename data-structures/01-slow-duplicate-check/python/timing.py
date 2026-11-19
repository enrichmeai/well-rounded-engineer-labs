import threading
import time


def timed(fn, budget):
    """Run fn() and return (result, seconds). Fails the test if it takes longer than `budget` seconds."""
    box = {}

    def run():
        start = time.perf_counter()
        box["result"] = fn()
        box["seconds"] = time.perf_counter() - start

    t = threading.Thread(target=run, daemon=True)
    t.start()
    t.join(budget)
    assert not t.is_alive(), f"still running after {budget} s: the work grows faster than the data does"
    return box["result"], box["seconds"]
