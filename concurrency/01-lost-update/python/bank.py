"""Drill 01: the lost update. Make test_bank.py pass. Keep the call to record_audit_entry()."""

import time


def record_audit_entry() -> None:
    """Stands in for slow work between the read and the write: a database call, a log line."""
    time.sleep(0)  # gives other threads a chance to run here, as real I/O does


class Account:
    def __init__(self, balance: int = 0) -> None:
        self.balance = balance

    def deposit(self, amount: int) -> None:
        seen = self.balance
        record_audit_entry()
        self.balance = seen + amount
