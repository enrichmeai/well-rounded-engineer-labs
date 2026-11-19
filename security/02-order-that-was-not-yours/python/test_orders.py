import pytest

from orders import NotFound, cancel_order, get_order


@pytest.fixture
def store():
    return {1043: {"owner": "ana", "total": 2599, "status": "paid"},
            1044: {"owner": "ben", "total": 4800, "status": "paid"}}


def test_customers_see_and_cancel_their_own(store):
    assert get_order(store, "ana", 1043)["total"] == 2599
    cancel_order(store, "ana", 1043)
    assert store[1043]["status"] == "cancelled"


def test_another_customers_order_cannot_be_read(store):
    with pytest.raises(NotFound):
        get_order(store, "ana", 1044)


def test_another_customers_order_cannot_be_cancelled(store):
    with pytest.raises(NotFound):
        cancel_order(store, "ana", 1044)
    assert store[1044]["status"] == "paid"


def test_not_yours_looks_the_same_as_does_not_exist(store):
    with pytest.raises(NotFound) as not_yours:
        get_order(store, "ana", 1044)
    with pytest.raises(NotFound) as missing:
        get_order(store, "ana", 1045)
    assert str(not_yours.value).replace("1044", "N") == str(missing.value).replace("1045", "N")
