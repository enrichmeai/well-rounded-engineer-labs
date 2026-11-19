"""Drill 03, solved: a deque removes from the front in O(1)."""

from collections import deque


def handle(job: int) -> int:
    return job * 2


def process_all(jobs: list[int]) -> list[int]:
    queue = deque(jobs)
    done = []
    while queue:
        job = queue.popleft()   # O(1): nothing else moves
        done.append(handle(job))
    return done
