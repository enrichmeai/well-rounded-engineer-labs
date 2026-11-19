import hashlib
import hmac
import secrets

ITERATIONS = 600_000      # OWASP Password Storage Cheat Sheet, PBKDF2-HMAC-SHA256


def _derive(password: str, salt: bytes, iterations: int) -> bytes:
    return hashlib.pbkdf2_hmac("sha256", password.encode(), salt, iterations)


def hash_password(password: str) -> str:
    """What we store: the algorithm, its cost and the salt travel with the hash, so they can be raised later."""
    salt = secrets.token_bytes(16)
    return f"pbkdf2_sha256${ITERATIONS}${salt.hex()}${_derive(password, salt, ITERATIONS).hex()}"


def verify(password: str, stored: str) -> bool:
    algorithm, iterations, salt, expected = stored.split("$")
    if algorithm != "pbkdf2_sha256":
        return False
    attempt = _derive(password, bytes.fromhex(salt), int(iterations))
    return hmac.compare_digest(attempt, bytes.fromhex(expected))
