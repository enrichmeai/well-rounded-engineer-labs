"""Drill 01: the double charge. Make test_payments.py pass."""


class PaymentService:
    def __init__(self) -> None:
        self.charges: list[tuple[str, int]] = []      # what the card was actually charged

    def charge(self, idempotency_key: str, order: str, amount: int) -> str:
        """Charge the card and return a receipt id. The client sends the same key when it retries."""
        self.charges.append((order, amount))
        return f"receipt-{len(self.charges)}"

    def total_charged(self) -> int:
        return sum(amount for _, amount in self.charges)
