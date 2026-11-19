"""Drill 02, solved: every transfer takes the two locks in the same order, by account id."""

import threading
import time


def record_audit_entry() -> None:
    time.sleep(0.001)


class Account:
    def __init__(self, account_id: int, balance: int) -> None:
        self.id = account_id
        self.balance = balance
        self.lock = threading.Lock()


def transfer(source: Account, target: Account, amount: int) -> None:
    first, second = sorted((source, target), key=lambda account: account.id)
    with first.lock:
        record_audit_entry()
        with second.lock:
            source.balance -= amount
            target.balance += amount
