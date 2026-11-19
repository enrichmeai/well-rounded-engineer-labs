"""Drill 01, solved: the read and the write happen under one lock."""

import threading
import time


def record_audit_entry() -> None:
    """Stands in for slow work between the read and the write: a database call, a log line."""
    time.sleep(0)


class Account:
    def __init__(self, balance: int = 0) -> None:
        self.balance = balance
        self._lock = threading.Lock()

    def deposit(self, amount: int) -> None:
        with self._lock:              # only one thread at a time from here...
            seen = self.balance
            record_audit_entry()
            self.balance = seen + amount   # ...to here
