"""Drill 05: the parallel loop that lost the order. Make test_thumbnails.py pass. Keep it parallel."""

import time
from concurrent.futures import ThreadPoolExecutor


def resize(image_id: int) -> str:
    """Stands in for slow work; some images take longer than others."""
    time.sleep(0.001 * (10 - image_id % 10))
    return f"thumb-{image_id}"


def make_thumbnails(ids: list[int]) -> list[str]:
    results = []
    with ThreadPoolExecutor(max_workers=8) as pool:
        for i in ids:
            pool.submit(lambda i=i: results.append(resize(i)))
    return results
