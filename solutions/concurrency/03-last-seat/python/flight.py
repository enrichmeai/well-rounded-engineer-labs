"""Drill 03, solved: the check and the act are one step under a lock."""

import threading
import time


def check_payment() -> None:
    time.sleep(0.001)


class Flight:
    def __init__(self, seats: int) -> None:
        self.seats_left = seats
        self.passengers: list[str] = []
        self._lock = threading.Lock()

    def book(self, passenger: str) -> bool:
        with self._lock:
            if self.seats_left > 0:
                check_payment()
                self.seats_left -= 1
                self.passengers.append(passenger)
                return True
            return False
