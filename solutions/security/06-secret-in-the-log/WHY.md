# Why the fix works

Logs are copied, shipped, indexed, kept for months and read by many more people than the database. A secret that
reaches a log has left every control you put around the place it came from.

The tempting fix is a **deny-list**: remove `password` and `card_number`. It fails as soon as someone adds a field
nobody listed, and this request already had three (`X-Api-Key`, `card_cvc`, `otp`). An **allow-list** fails safe: a new
field is redacted until someone decides it is safe to show. Keeping the **names** of redacted fields keeps the log
useful: you can still see that a card was sent, without the card.

**Make it the default, not a habit.** Put the redaction in one place every log line goes through (a logging filter, a
structured logger's processor, a request-logging middleware), so a debugging line added at 2 a.m. cannot bypass it.
OWASP's Logging Cheat Sheet lists what should usually not be recorded directly, including passwords, session
identifiers, access tokens and payment card data.

## The rule, for people and for agents

> Never log secrets or personal data: log through one shared function that keeps allow-listed fields and redacts everything else by name.

The same sentence is in [`security/STANDARD.md`](../../../security/STANDARD.md), which you can paste into your agent's
instructions file. In a review, look for: logging of whole request objects, headers, bodies, exceptions that carry them, or `toString()` of an entity.

The drill's tests are the part that does not depend on anyone remembering the rule: keep tests like them in your own
suite, and they check every change, whoever or whatever wrote it.
