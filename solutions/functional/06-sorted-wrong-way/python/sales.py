"""Drill 06, solved: one sort with a combined key."""


def daily_report(sales: list[dict]) -> list[tuple[str, int]]:
    kept = (s for s in sales if s["status"] != "cancelled")       # filter
    rows = [(s["store"], s["total"]) for s in kept]                # map
    return sorted(rows, key=lambda r: (r[0], -r[1]))               # store A-Z, then total biggest first
