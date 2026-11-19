# Why the fix works

`random.Random` and `java.util.Random` are **pseudo-random**: a seed decides the whole sequence. Seeded with the
time, the token is a function of a number the attacker can narrow down to a few thousand candidates, then try each one.
Python's documentation for `secrets` says it directly: use it "in preference to the default pseudo-random number
generator in the random module, which is designed for modelling and simulation, not security or cryptography".

The fix:
- **a secure generator**: `secrets` in Python, `SecureRandom` in Java; both draw on the operating system's source;
- **enough of it**: 32 bytes, which Python's documentation (as of 2015) says is believed sufficient for the typical
  use case of the `secrets` module;
- **single use**: the token is removed when redeemed, so a forwarded or logged link cannot be replayed;
- **a short life**: 15 minutes here; OWASP's cheat sheet asks for tokens that are single use and expire after an
  appropriate period.

The same rule applies to session IDs, API keys, invitation links and anything else that acts as proof: if it grants
access, it comes from the secure generator.

## The rule, for people and for agents

> Anything that grants access (tokens, session IDs, API keys, reset or invitation links) comes from the secure random generator (`secrets`, `SecureRandom`), has at least 32 bytes of randomness, and expires; one-time tokens are deleted when used.

The same sentence is in [`security/STANDARD.md`](../../../security/STANDARD.md), which you can paste into your agent's
instructions file. In a review, look for: `random.`, `java.util.Random`, `Math.random`, `uuid1` or a timestamp anywhere near a token, and tokens that are never deleted or never expire.

The drill's tests are the part that does not depend on anyone remembering the rule: keep tests like them in your own
suite, and they check every change, whoever or whatever wrote it.
