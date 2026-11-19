from request_log import log_request

HEADERS = {"User-Agent": "shop-app/5.2", "Content-Type": "application/json", "X-Request-Id": "req-7781",
           "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.c2Vzc2lvbg.sig", "X-Api-Key": "sk_live_51Hx9QaZ",
           "Cookie": "session=8f14e45fceea167a"}
BODY = {"order_id": 1043, "currency": "GBP", "items": 3, "card_number": "4111111111111111", "card_cvc": "737",
        "password": "Summer2026!", "otp": "492817"}
SECRETS = ["eyJhbGciOiJIUzI1NiJ9", "sk_live_51Hx9QaZ", "8f14e45fceea167a", "4111111111111111", "737", "Summer2026!",
           "492817"]


def logged():
    lines = []
    log_request(HEADERS, BODY, lines.append)
    return "\n".join(lines)


def test_no_secret_reaches_the_log():
    out = logged()
    leaked = [s for s in SECRETS if s in out]
    assert leaked == []


def test_the_log_is_still_useful():
    out = logged()
    for useful in ["shop-app/5.2", "req-7781", "1043", "GBP", "card_number", "Authorization", "[redacted]"]:
        assert useful in out
