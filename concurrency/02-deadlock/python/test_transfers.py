import threading

import transfers


def test_one_transfer():
    a, b = transfers.Account(1, 100), transfers.Account(2, 100)
    transfers.transfer(a, b, 30)
    assert (a.balance, b.balance) == (70, 130)


def test_both_directions_at_once_finish():
    a, b = transfers.Account(1, 100), transfers.Account(2, 100)
    there = threading.Thread(target=lambda: [transfers.transfer(a, b, 1) for _ in range(200)], daemon=True)
    back = threading.Thread(target=lambda: [transfers.transfer(b, a, 1) for _ in range(200)], daemon=True)
    there.start()
    back.start()
    there.join(5)
    back.join(1)
    assert not (there.is_alive() or back.is_alive()), "deadlock: both threads are still waiting after 5 seconds"
    assert a.balance + b.balance == 200, "money was created or lost"
    assert (a.balance, b.balance) == (100, 100)
