# Security drills

Seven drills, each in Python and Java, each a basic that is cheap to get right while you write the code and expensive to
add after a breach. Read first: [Security](https://enrichmeai.com/well-rounded-engineer/security/) and [Quality Attributes](https://enrichmeai.com/well-rounded-engineer/quality/) in the guide.

| Drill | You learn | OWASP Top 10:2025 | Time |
|---|---|---|---|
| [01 · The file anyone could read](01-file-anyone-could-read/) | Resolve a path, then check it is still inside the folder | A01 Broken Access Control | 15 min |
| [02 · The order that was not yours](02-order-that-was-not-yours/) | Check access on every object, not only at login | A01 Broken Access Control | 10 min |
| [03 · The comment that ran code](03-comment-that-ran-code/) | Encode output; never build markup by joining strings | A05 Injection | 10 min |
| [04 · The password stored the fast way](04-password-stored-fast/) | Slow, salted password hashing; constant-time compare | A04 Cryptographic Failures | 15 min |
| [05 · The reset link you could guess](05-reset-link-you-could-guess/) | Secure randomness, single use, expiry | A04 Cryptographic Failures, A07 Authentication Failures | 15 min |
| [06 · The secret in the log](06-secret-in-the-log/) | Allow-list what you log; redact the rest by name | A09 Logging and Alerting Failures | 10 min |
| [07 · The field nobody meant to change](07-field-nobody-meant-to-change/) | Allow-list what a request may change | A08 Software or Data Integrity Failures | 10 min |

The answers and why they work are in [`../solutions/security/`](../solutions/security/).

## Basics while you write, not after

Every one of these bugs was a normal-looking line of code: joining two paths, fetching by ID, an f-string, a hash
function, a random number, a log line, a loop over a request. None needed a security expert to fix; they needed the
safe way to be the obvious way at the moment of writing. Three habits make that happen:

1. **One safe helper per risk, and no other way.** An owner-scoped `order_for(user, id)`, an auto-escaping template, a
   `new_token()`, a redacting logger. When the safe way is the only way in the code base, nobody has to remember.
2. **Safe defaults.** Escaping on, fields refused unless allowed, logs redacted unless allowed. Each drill's fix turns a
   default from open to closed.
3. **A test with each rule.** Write the attacker's request as a test while you write the feature (the climbing path, the
   other user's ID, the extra field). It costs a few lines and it runs for ever.

## One standard, by hand and with agents

[`STANDARD.md`](STANDARD.md) has the seven rules once, in three forms: a checklist for writing and reviewing, a block to
paste into your agent's instructions (`CLAUDE.md`, `AGENTS.md`, `.github/copilot-instructions.md`), and the tests that
enforce them. The same rules, the same tests, whoever writes the code.

## Try it with an agent

After you have done a drill by hand:

1. Give a coding agent only the drill's story and task, **without** the standard, and ask it to write the function.
   Copy its code into a fresh copy of the drill's folder and run the tests.
2. Do it again **with** the security block from `STANDARD.md` in its instructions.
3. Compare. Did the tests pass both times? What did the agent do that you did not, or miss that you caught? Would you
   have spotted the difference in a review without the tests?

The point is not to grade the agent. It is to see, on code you now understand, that the tests and the written standard
are what make the result trustworthy, by hand or not.
