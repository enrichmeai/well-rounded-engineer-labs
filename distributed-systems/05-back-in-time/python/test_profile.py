from itertools import permutations

from user_profile import EmailChanged, Profile, apply

UPDATES = [EmailChanged("ana@old.example", 1), EmailChanged("ana@typo.exmaple", 2), EmailChanged("ana@new.example", 3)]


def test_every_arrival_order_ends_with_the_newest():
    for order in permutations(UPDATES):
        p = Profile("ana@first.example", 0)
        for u in order:
            apply(p, u)
        assert (p.email, p.version) == ("ana@new.example", 3), f"arrival order {[u.version for u in order]} left {p.email}"


def test_a_duplicate_changes_nothing():
    p = Profile("ana@first.example", 0)
    for u in [UPDATES[0], UPDATES[2], UPDATES[1], UPDATES[0]]:          # a late duplicate of version 1 arrives last
        apply(p, u)
    assert (p.email, p.version) == ("ana@new.example", 3)
