from report import report

ORDERS = [
    {"total": 30, "status": "paid"},
    {"total": 12, "status": "cancelled"},
    {"total": 25, "status": "paid"},
    {"total": 8, "status": "paid"},
]


def test_count_and_revenue():
    assert report(ORDERS) == {"count": 3, "revenue": 63}


def test_nothing_to_report():
    assert report([]) == {"count": 0, "revenue": 0}
