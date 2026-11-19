"""Drill 02: the pipeline you can only read once. Make test_report.py pass."""


def order_totals(orders: list[dict]):
    """The totals of the orders that were not cancelled, produced lazily."""
    return (o["total"] for o in orders if o["status"] != "cancelled")


def report(orders: list[dict]) -> dict:
    totals = order_totals(orders)
    return {"count": sum(1 for _ in totals), "revenue": sum(totals)}
