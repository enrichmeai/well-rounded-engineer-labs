"""Drill 06: the report sorted the wrong way. Make test_sales.py pass."""


def daily_report(sales: list[dict]) -> list[tuple[str, int]]:
    """Leave out cancelled sales; one (store, total) per sale; stores A to Z, biggest sale first within a store."""
    kept = [s for s in sales if s["status"] != "cancelled"]
    rows = [(s["store"], s["total"]) for s in kept]
    rows.sort(key=lambda r: r[0])                   # by store
    rows.sort(key=lambda r: r[1], reverse=True)     # then by total, biggest first
    return rows
