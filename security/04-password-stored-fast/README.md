# 04 · The password stored the fast way

**Time:** about 15 minutes · **Languages:** Python and Java · **Read first:** [Security](https://enrichmeai.com/well-rounded-engineer/security/) in the guide · **OWASP:** [A04:2025 Cryptographic Failures](https://owasp.org/Top10/2025/A04_2025-Cryptographic_Failures/), CWE-916 and CWE-759 (fast, unsalted hashes)

## The story

The users table leaked. The passwords were hashed with SHA-256, so the team thought they were safe. Within a day, most of them were posted online in plain text, and everyone who used `Summer2026!` could be found at a glance because their hashes were identical.

## Before you start: predict

SHA-256 cannot be reversed. So how did the attackers get the passwords back? (Think about how fast SHA-256 is, and what identical hashes tell you.)

## Your task

Make `hash_password` and `verify` (Java: `Passwords.hash`, `Passwords.verify`) use PBKDF2-HMAC-SHA256 with 600,000 iterations and a new random 16-byte salt for every password. Store it as `pbkdf2_sha256$<iterations>$<salt hex>$<hash hex>` and compare in constant time.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out PasswordsTest
```

Or, from the top of the repo: `python3 practice.py check 04 --track security` (add `--java` for Java).

Stuck? `python3 practice.py hint 04 --track security`. The worked answer and why it works are in
[`solutions/security/04-password-stored-fast/`](../../solutions/security/04-password-stored-fast/). Try first.

## Done when

- The right password verifies and a wrong one does not.
- The same password hashed twice gives two different stored values.
- The stored value names the algorithm and at least 600,000 iterations, so it can be upgraded later.

## Then

- The OWASP [Password Storage Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html): Argon2id first, scrypt, bcrypt, and PBKDF2 at 600,000 iterations where FIPS-140 compliance is required.
- Try it with an agent: [the experiment in the track README](../README.md#try-it-with-an-agent).
