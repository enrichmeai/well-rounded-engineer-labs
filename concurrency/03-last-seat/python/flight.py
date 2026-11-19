"""Drill 03: the last seat. Make test_flight.py pass. Keep the call to check_payment()."""

import time


def check_payment() -> None:
    """Stands in for the payment call every real booking makes."""
    time.sleep(0.001)


class Flight:
    def __init__(self, seats: int) -> None:
        self.seats_left = seats
        self.passengers: list[str] = []

    def book(self, passenger: str) -> bool:
        if self.seats_left > 0:
            check_payment()
            self.seats_left -= 1
            self.passengers.append(passenger)
            return True
        return False
