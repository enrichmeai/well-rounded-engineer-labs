import os

import pytest

from invoices import read_invoice


@pytest.fixture
def folders(tmp_path):
    base = tmp_path / "invoices"
    (base / "2026").mkdir(parents=True)
    (base / "inv-1042.pdf").write_bytes(b"invoice 1042")
    (base / "2026" / "inv-2001.pdf").write_bytes(b"invoice 2001")
    secret = tmp_path / "secret.txt"
    secret.write_bytes(b"DB_PASSWORD=hunter2")
    (tmp_path / "invoices-old").mkdir()
    (tmp_path / "invoices-old" / "inv-0001.pdf").write_bytes(b"another customer's invoice")
    return base, secret


def test_invoices_inside_the_folder(folders):
    base, _ = folders
    assert read_invoice(base, "inv-1042.pdf") == b"invoice 1042"
    assert read_invoice(base, "2026/inv-2001.pdf") == b"invoice 2001"


def test_going_up_is_refused(folders):
    base, _ = folders
    with pytest.raises(PermissionError):
        read_invoice(base, "../secret.txt")


def test_a_sibling_folder_with_a_similar_name_is_refused(folders):
    base, _ = folders
    with pytest.raises(PermissionError):
        read_invoice(base, "../invoices-old/inv-0001.pdf")


def test_an_absolute_path_is_refused(folders):
    base, secret = folders
    with pytest.raises(PermissionError):
        read_invoice(base, str(secret))


def test_a_link_pointing_outside_is_refused(folders):
    base, secret = folders
    try:
        os.symlink(secret, base / "inv-9999.pdf")
    except OSError:
        pytest.skip("this system cannot create symbolic links")
    with pytest.raises(PermissionError):
        read_invoice(base, "inv-9999.pdf")
