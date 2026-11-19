import threading

import bank


def test_one_thread_adds_up():
    account = bank.Account()
    for _ in range(100):
        account.deposit(1)
    assert account.balance == 100


def test_eight_threads_lose_nothing(monkeypatch):
    calls = []
    real = bank.record_audit_entry

    def counted():
        calls.append(1)
        real()

    monkeypatch.setattr(bank, "record_audit_entry", counted)
    account = bank.Account()

    def checkout():
        for _ in range(500):
            account.deposit(1)

    threads = [threading.Thread(target=checkout) for _ in range(8)]
    for t in threads:
        t.start()
    for t in threads:
        t.join()
    assert len(calls) == 8 * 500, "keep record_audit_entry(): it is the slow work a real deposit does"
    assert account.balance == 8 * 500, f"8 threads x 500 deposits of 1 should be 4000, got {account.balance}"
