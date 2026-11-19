from pathlib import Path


def read_invoice(base: Path, name: str) -> bytes:
    """Return the invoice called `name` from the folder `base`, and nothing outside it."""
    root = base.resolve()
    target = (root / name).resolve()          # follows "..", absolute paths and links
    if not target.is_relative_to(root):
        raise PermissionError(f"not an invoice: {name}")
    return target.read_bytes()
