from pathlib import Path


def read_invoice(base: Path, name: str) -> bytes:
    """Return the invoice called `name` from the folder `base`."""
    return (base / name).read_bytes()
