"""Drill 03: the lock that outlived its owner. Make test_fencing.py pass."""


class LockService:
    """Hands out leases. Each new lease gets a fencing token one higher than the last."""

    def __init__(self) -> None:
        self.last_token = 0

    def acquire(self) -> int:
        self.last_token += 1
        return self.last_token


class Storage:
    def __init__(self) -> None:
        self.report: str | None = None

    def write(self, report: str, token: int) -> bool:
        """Write the report under the lease with this fencing token. Returns whether the write was accepted."""
        self.report = report
        return True
