import hashlib


def hash_password(password: str) -> str:
    """What we store in the users table."""
    return hashlib.sha256(password.encode()).hexdigest()


def verify(password: str, stored: str) -> bool:
    return hash_password(password) == stored
