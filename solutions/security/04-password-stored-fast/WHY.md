# Why the fix works

A hash cannot be reversed, but it does not need to be. SHA-256 is built to be **fast**: an attacker hashes every
word in a password list and every common pattern, and looks for matches. With **no salt**, one guess is checked against
every user at once, and users with the same password have the same hash, so the most common ones stand out.

The fix makes each guess **expensive** and **per user**:
- a **slow** function (here PBKDF2 with 600,000 iterations), so the same hardware tries far fewer guesses;
- a **random salt** per password, so every user has to be attacked separately and equal passwords look different;
- **constant-time comparison**, so the time taken does not hint at how much of the hash matched;
- the **algorithm and cost stored with the hash**, so you can raise them later and re-hash at the next login.

**Which function?** OWASP's cheat sheet puts Argon2id first, then scrypt, then bcrypt, with PBKDF2 at 600,000 or more
iterations where FIPS-140 compliance is required. This drill uses PBKDF2 because it is in both standard libraries. In
real code, use a maintained password-hashing library rather than writing this yourself.

## The rule, for people and for agents

> Store passwords only with a slow, salted password-hashing function from a maintained library (Argon2id first; scrypt if it is not available; PBKDF2 with at least 600,000 iterations where FIPS-140 is required; bcrypt on legacy systems), never a plain hash, and compare in constant time.

The same sentence is in [`security/STANDARD.md`](../../../security/STANDARD.md), which you can paste into your agent's
instructions file. In a review, look for: `md5`, `sha1` or `sha256` near the word password; `==` comparing secrets; a home-made salt or hash format.

The drill's tests are the part that does not depend on anyone remembering the rule: keep tests like them in your own
suite, and they check every change, whoever or whatever wrote it.
