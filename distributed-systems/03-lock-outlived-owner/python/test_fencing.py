from fencing import LockService, Storage


def test_a_paused_worker_cannot_overwrite_the_new_holder():
    locks, storage = LockService(), Storage()
    a = locks.acquire()                       # worker A gets the lease (token 1)...
    # ...then freezes for a long GC pause; its lease expires
    b = locks.acquire()                       # worker B gets the lease (token 2)
    assert storage.write("report by B", b) is True
    accepted = storage.write("stale report by A", a)   # A wakes up and writes anyway
    assert accepted is False, "a write under an expired lease was accepted"
    assert storage.report == "report by B"


def test_the_holder_can_write_more_than_once():
    locks, storage = LockService(), Storage()
    t = locks.acquire()
    assert storage.write("draft", t) is True
    assert storage.write("final", t) is True
    assert storage.report == "final"


def test_newer_tokens_keep_working():
    locks, storage = LockService(), Storage()
    for i in range(5):
        assert storage.write(f"report {i}", locks.acquire()) is True
    assert storage.report == "report 4"
