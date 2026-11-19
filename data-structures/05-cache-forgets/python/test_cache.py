from cache import Cache


def test_reading_an_entry_protects_it():
    c = Cache(2)
    c.put("home", 1)
    c.put("sale", 2)
    assert c.get("home") == 1          # "home" is now the most recently used
    c.put("about", 3)                  # full: evict the least recently used, which is "sale"
    assert c.evicted == ["sale"]
    assert c.get("home") == 1 and c.get("sale") is None


def test_updating_an_entry_counts_as_use():
    c = Cache(2)
    c.put("a", 1)
    c.put("b", 2)
    c.put("a", 10)                     # an update is a use too
    c.put("c", 3)
    assert c.evicted == ["b"]
    assert c.get("a") == 10


def test_never_more_than_capacity():
    c = Cache(3)
    for i in range(10):
        c.put(i, i)
    assert len(c.data) == 3
