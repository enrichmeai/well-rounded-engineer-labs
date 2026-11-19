from passwords import hash_password, verify


def test_right_and_wrong_passwords():
    stored = hash_password("Summer2026!")
    assert verify("Summer2026!", stored)
    assert not verify("summer2026!", stored)


def test_the_same_password_twice_is_stored_differently():
    assert hash_password("Summer2026!") != hash_password("Summer2026!")


def test_the_stored_value_names_a_slow_algorithm():
    algorithm, iterations, salt, digest = hash_password("x").split("$")
    assert algorithm == "pbkdf2_sha256"
    assert int(iterations) >= 600_000
    assert len(bytes.fromhex(salt)) >= 16
