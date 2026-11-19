"""Drill 04: the stale read. Make test_quorum.py pass."""

N = 3
W = 1   # replicas that must confirm a write
R = 1   # replicas asked on a read


class Replica:
    def __init__(self, value: str, version: int) -> None:
        self.value = value
        self.version = version


def write(replicas: list[Replica], reachable: list[int], value: str, version: int) -> bool:
    """Write to the reachable replicas. The write succeeds only if at least W of them confirmed it."""
    if len(reachable) < W:
        return False
    for i in reachable:
        replicas[i].value, replicas[i].version = value, version
    return True


def read(asked: list[Replica]) -> str:
    """Read from R replicas and return the current value."""
    return asked[0].value
