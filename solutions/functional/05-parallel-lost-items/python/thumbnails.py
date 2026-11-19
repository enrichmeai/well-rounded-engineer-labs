"""Drill 05, solved: pool.map runs in parallel and returns results in the order of the input."""

import time
from concurrent.futures import ThreadPoolExecutor


def resize(image_id: int) -> str:
    time.sleep(0.001 * (10 - image_id % 10))
    return f"thumb-{image_id}"


def make_thumbnails(ids: list[int]) -> list[str]:
    with ThreadPoolExecutor(max_workers=8) as pool:
        return list(pool.map(resize, ids))
