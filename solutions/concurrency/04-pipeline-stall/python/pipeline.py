"""Drill 04, solved: a worker reports a failed order and carries on, so it always sends STOP."""

import queue
import threading

STOP = object()


def worker(inbox: queue.Queue, outbox: queue.Queue, prices: dict[str, int]) -> None:
    while True:
        order = inbox.get()
        if order is STOP:
            outbox.put(STOP)
            return
        try:
            outbox.put(("ok", order["id"], prices[order["item"]] * order["qty"]))
        except Exception:                      # one bad order must not kill the worker
            outbox.put(("failed", order["id"]))


def run(orders: list[dict], prices: dict[str, int], workers: int = 2) -> tuple[dict[int, int], list[int]]:
    """Price every order. Returns (total by order id, ids of orders that could not be priced)."""
    todo: queue.Queue = queue.Queue()
    done: queue.Queue = queue.Queue()
    for order in orders:
        todo.put(order)
    for _ in range(workers):
        todo.put(STOP)
    threads = [threading.Thread(target=worker, args=(todo, done, prices)) for _ in range(workers)]
    for t in threads:
        t.start()
    totals, failed, stopped = {}, [], 0
    while stopped < workers:
        item = done.get()
        if item is STOP:
            stopped += 1
        elif item[0] == "ok":
            totals[item[1]] = item[2]
        else:
            failed.append(item[1])
    for t in threads:
        t.join()
    return totals, sorted(failed)
