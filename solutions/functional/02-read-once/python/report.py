"""Drill 02, solved: the lazy totals are stored once, then read as often as needed."""


def order_totals(orders: list[dict]):
    return (o["total"] for o in orders if o["status"] != "cancelled")


def report(orders: list[dict]) -> dict:
    totals = list(order_totals(orders))   # materialise once
    return {"count": len(totals), "revenue": sum(totals)}
