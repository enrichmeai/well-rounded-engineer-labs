"""Drill 03, solved: the storage refuses any write with a token older than one it has accepted."""


class LockService:
    def __init__(self) -> None:
        self.last_token = 0

    def acquire(self) -> int:
        self.last_token += 1
        return self.last_token


class Storage:
    def __init__(self) -> None:
        self.report: str | None = None
        self.highest_token = 0

    def write(self, report: str, token: int) -> bool:
        if token < self.highest_token:        # from a lease that has since been replaced
            return False
        self.highest_token = token
        self.report = report
        return True
