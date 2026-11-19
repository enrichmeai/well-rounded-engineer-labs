from itertools import combinations

import quorum


def fresh():
    return [quorum.Replica("12 Old Road", 1) for _ in range(quorum.N)]


def test_every_read_sees_the_newest_value():
    replicas = fresh()
    # replica 2 is cut off during the write
    assert quorum.write(replicas, [0, 1], "3 New Street", 2) is True
    for ids in combinations(range(quorum.N), quorum.R):
        for order in (ids, ids[::-1]):
            got = quorum.read([replicas[i] for i in order])
            assert got == "3 New Street", f"reading replicas {order} returned {got!r}"


def test_reads_and_writes_overlap():
    assert quorum.W + quorum.R > quorum.N, "a read set and a write set must always share a replica"


def test_a_write_that_reaches_too_few_is_refused():
    replicas = fresh()
    assert quorum.write(replicas, [0], "3 New Street", 2) is False, "one replica is not a quorum"
