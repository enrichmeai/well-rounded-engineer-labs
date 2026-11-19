"""Drill 04, solved: pricing is a pure function; the clock and the email stay in checkout, the thin shell."""

import datetime


def send_receipt(email: str, total_pence: int) -> None:
    raise RuntimeError("tests must not send real email")


def price(order: dict, day: datetime.date) -> int:
    total = sum(line["qty"] * line["pence"] for line in order["lines"])
    if day.weekday() >= 5:                     # Saturday or Sunday
        total = total * 90 // 100
    return total


def checkout(order: dict) -> int:
    total = price(order, datetime.date.today())   # the shell reads the clock...
    send_receipt(order["email"], total)           # ...and talks to the world
    return total
