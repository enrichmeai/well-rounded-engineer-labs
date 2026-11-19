"""Drill 01, solved: the service remembers each idempotency key and its receipt."""


class PaymentService:
    def __init__(self) -> None:
        self.charges: list[tuple[str, int]] = []
        self.receipts: dict[str, str] = {}            # idempotency key -> receipt

    def charge(self, idempotency_key: str, order: str, amount: int) -> str:
        if idempotency_key in self.receipts:          # seen before: a retry
            return self.receipts[idempotency_key]
        self.charges.append((order, amount))
        receipt = f"receipt-{len(self.charges)}"
        self.receipts[idempotency_key] = receipt
        return receipt

    def total_charged(self) -> int:
        return sum(amount for _, amount in self.charges)
