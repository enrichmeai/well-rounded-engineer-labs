"""Drill 04, solved: W + R > N, and a read keeps the answer with the highest version."""

N = 3
W = 2   # a majority must confirm a write
R = 2   # ask a majority on a read: it always includes one replica that saw the last write


class Replica:
    def __init__(self, value: str, version: int) -> None:
        self.value = value
        self.version = version


def write(replicas: list[Replica], reachable: list[int], value: str, version: int) -> bool:
    if len(reachable) < W:
        return False
    for i in reachable:
        replicas[i].value, replicas[i].version = value, version
    return True


def read(asked: list[Replica]) -> str:
    return max(asked, key=lambda r: r.version).value
