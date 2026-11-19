"""Drill 02: the deadlock. Make test_transfers.py pass. Keep one lock per account."""

import threading
import time


def record_audit_entry() -> None:
    """Stands in for slow work while the first lock is held: writing an audit row."""
    time.sleep(0.001)


class Account:
    def __init__(self, account_id: int, balance: int) -> None:
        self.id = account_id
        self.balance = balance
        self.lock = threading.Lock()


def transfer(source: Account, target: Account, amount: int) -> None:
    with source.lock:
        record_audit_entry()
        with target.lock:
            source.balance -= amount
            target.balance += amount
