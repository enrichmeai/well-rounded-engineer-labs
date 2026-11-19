## Hint 1
A fast hash lets an attacker try billions of guesses. And with no salt, one guess tests every user at once, and equal
passwords have equal hashes.

## Hint 2
Python: `hashlib.pbkdf2_hmac("sha256", password.encode(), salt, 600_000)` and `secrets.token_bytes(16)`.
Java: `SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")` with a `PBEKeySpec(chars, salt, 600000, 256)`, and
`SecureRandom` for the salt.

## Hint 3
`verify` reads the iterations and salt back out of the stored value, hashes the attempt the same way, and compares with
`hmac.compare_digest` (Java: `MessageDigest.isEqual`), which takes the same time whether the first byte or the last is wrong.
