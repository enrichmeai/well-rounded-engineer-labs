"""Drill 04: the core and the shell. Make test_pricing.py pass."""

import datetime


def send_receipt(email: str, total_pence: int) -> None:
    """Stands in for the real email service."""
    raise RuntimeError("tests must not send real email")


def price(order: dict, day: datetime.date) -> int:
    """The order total in pence on the given day: 10% off at weekends. Pure: no clock, no email."""
    raise NotImplementedError("write me")


def checkout(order: dict) -> int:
    total = sum(line["qty"] * line["pence"] for line in order["lines"])
    if datetime.date.today().weekday() >= 5:
        total = total * 90 // 100
    send_receipt(order["email"], total)
    return total
