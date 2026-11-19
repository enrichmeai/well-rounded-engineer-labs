# One security standard, for people and for agents

Seven rules, one per drill. They are written so the same text works three ways: as a checklist for a person writing or
reviewing code, as instructions for a coding agent, and as the description of the tests that enforce them.

| # | Rule | Drill |
|---|---|---|
| 1 | Never build a file path from user input without resolving it and checking it is still inside the intended folder; prefer looking files up by an ID the user is allowed to see. | [01](01-file-anyone-could-read/) |
| 2 | Every read or change of a stored object checks that the caller may access that specific object, in one shared function; answer "not found" for objects the caller may not see. | [02](02-order-that-was-not-yours/) |
| 3 | Never build HTML, SQL or shell commands by joining strings with user input: use an auto-escaping template, parameterised queries and argument lists, and treat every opt-out of escaping as a review item. | [03](03-comment-that-ran-code/) |
| 4 | Store passwords only with a slow, salted password-hashing function from a maintained library (Argon2id first; scrypt if it is not available; PBKDF2 with at least 600,000 iterations where FIPS-140 is required; bcrypt on legacy systems), never a plain hash, and compare in constant time. | [04](04-password-stored-fast/) |
| 5 | Anything that grants access (tokens, session IDs, API keys, reset or invitation links) comes from the secure random generator (`secrets`, `SecureRandom`), has at least 32 bytes of randomness, and expires; one-time tokens are deleted when used. | [05](05-reset-link-you-could-guess/) |
| 6 | Never log secrets or personal data: log through one shared function that keeps allow-listed fields and redacts everything else by name. | [06](06-secret-in-the-log/) |
| 7 | Never bind a request directly onto a stored object: accept a dedicated input type, or an explicit allow-list of editable fields, and refuse anything else before changing anything. | [07](07-field-nobody-meant-to-change/) |

## For a person

While you write: each rule has one safe helper in your code base (a safe path lookup, an owner-scoped fetch, the
template engine, the password library, the token function, the redacting logger, the input type). Use the helper; do not
write the thing by hand. While you review: each drill's `WHY.md` ends with what to look for in a diff.

## For an agent

Paste this block into the instructions file your agent reads (for example `CLAUDE.md`, `AGENTS.md`, or
`.github/copilot-instructions.md`), and keep the rules in step with this file:

```markdown
## Security rules (always apply; do not weaken without asking)
1. File paths from user input: resolve and check they stay inside the intended folder; prefer lookup by ID.
2. Every object read or change checks the caller may access that object, in one shared function; "not found" otherwise.
3. No HTML, SQL or shell built by joining strings with input: auto-escaping templates, parameterised queries, argument lists.
4. Passwords: Argon2id (else scrypt; PBKDF2 600,000+ only where FIPS-140 applies; bcrypt only on legacy) via a maintained library; constant-time compare.
5. Tokens, session IDs, keys and links: secure random (secrets / SecureRandom), 32+ bytes, expiring; one-time tokens deleted on use.
6. Logs: never secrets or personal data; log through the shared redacting function (allow-list, redact the rest by name).
7. Requests never bind onto stored objects: a dedicated input type or an allow-list; refuse unknown fields before changing anything.
When a task seems to need breaking one of these, stop and say so instead of working around it.
Write a test for each rule a change touches, in the style of the security drills.
```

## For both: the tests

Instructions are a request, to a person or to an agent, and requests are forgotten under pressure. The tests are what
holds. Each drill's tests are an example of a security test you can keep in your own suite: a path that tries to climb
out, a second user asking for the first user's order, a script in a comment, two hashes of one password, two tokens at
one moment, a log line searched for secrets, a request that tries to make itself an admin. They run on every change, and
they do not care who wrote it.

<i>Our view:</i> one standard, written once, given to people and agents alike, and enforced by tests and CI rather than
by memory. <i>The other view:</i> a fixed list can become a box to tick, and it covers only what someone thought of;
threat modelling a design (see [Security](https://enrichmeai.com/well-rounded-engineer/security/) in the guide) finds the risks a list does not. Use both: the list for the
basics every change must get right, the threat model for what is specific to your system.

The rules draw on the OWASP Top 10:2025 and the OWASP Cheat Sheet Series; each drill links its sources.
