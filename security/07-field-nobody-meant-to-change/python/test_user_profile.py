import pytest

from user_profile import User, update_profile


def ana():
    return User(id=7, email="ana@example.com", display_name="Ana")


def test_a_customer_can_change_name_and_email():
    user = update_profile(ana(), {"display_name": "Ana P", "email": "ana.p@example.com"})
    assert (user.display_name, user.email) == ("Ana P", "ana.p@example.com")


def test_becoming_an_admin_is_refused():
    user = ana()
    with pytest.raises(ValueError):
        update_profile(user, {"is_admin": True})
    assert user.is_admin is False


def test_a_refused_request_changes_nothing():
    user = ana()
    with pytest.raises(ValueError):
        update_profile(user, {"display_name": "Ana P", "credit_pence": 100_000})
    assert (user.display_name, user.credit_pence) == ("Ana", 0)
