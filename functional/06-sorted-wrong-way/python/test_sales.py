from sales import daily_report

SALES = [
    {"store": "Leeds", "total": 40, "status": "paid"},
    {"store": "Bath", "total": 15, "status": "paid"},
    {"store": "Leeds", "total": 90, "status": "paid"},
    {"store": "Bath", "total": 70, "status": "cancelled"},
    {"store": "York", "total": 55, "status": "paid"},
    {"store": "Bath", "total": 60, "status": "paid"},
]


def test_stores_a_to_z_biggest_first():
    assert daily_report(SALES) == [("Bath", 60), ("Bath", 15), ("Leeds", 90), ("Leeds", 40), ("York", 55)]


def test_input_left_alone():
    before = [dict(s) for s in SALES]
    daily_report(SALES)
    assert SALES == before
