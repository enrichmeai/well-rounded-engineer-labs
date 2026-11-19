import datetime

import pricing

ORDER = {"email": "ana@example.com", "lines": [{"qty": 2, "pence": 1250}, {"qty": 1, "pence": 500}]}
WEDNESDAY = datetime.date(2026, 11, 18)
SATURDAY = datetime.date(2026, 11, 21)


def test_price_on_a_weekday_and_a_weekend(monkeypatch):
    def no_email(*args):
        raise AssertionError("price() sent an email")

    monkeypatch.setattr(pricing, "send_receipt", no_email)
    assert pricing.price(ORDER, WEDNESDAY) == 3000
    assert pricing.price(ORDER, SATURDAY) == 2700


def test_checkout_sends_one_receipt_with_the_price(monkeypatch):
    sent = []
    monkeypatch.setattr(pricing, "send_receipt", lambda email, total: sent.append((email, total)))
    total = pricing.checkout(ORDER)
    assert sent == [("ana@example.com", total)]
    assert total == pricing.price(ORDER, datetime.date.today())
