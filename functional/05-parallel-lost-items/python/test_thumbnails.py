import time

from thumbnails import make_thumbnails


def test_every_thumbnail_in_input_order():
    ids = list(range(40))
    assert make_thumbnails(ids) == [f"thumb-{i}" for i in ids]


def test_still_parallel():
    start = time.perf_counter()
    make_thumbnails(list(range(80)))          # about 0.44 s one after another
    assert time.perf_counter() - start < 0.25, "the work should still run on several threads"
