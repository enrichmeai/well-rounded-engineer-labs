"""Drill 03: the slow queue. Make test_jobs.py pass, fast. Jobs must come out oldest first."""


def handle(job: int) -> int:
    return job * 2              # stands in for real work; it is not the slow part


def process_all(jobs: list[int]) -> list[int]:
    queue = list(jobs)
    done = []
    while queue:
        job = queue.pop(0)      # oldest first
        done.append(handle(job))
    return done
