from categories import Category, depth


def chain(n):
    node = Category("top")
    for i in range(n):
        node = Category(f"level-{i + 1}", node)
    return node


def test_short_chains():
    assert depth(Category("top")) == 0
    assert depth(chain(1)) == 1
    assert depth(chain(5)) == 5


def test_a_chain_one_hundred_thousand_deep():
    assert depth(chain(100_000)) == 100_000
