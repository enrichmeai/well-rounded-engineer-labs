# Why the fix works

Authentication answers "who is this?". Authorization answers "may they do this, to this object?". The login check
passed, and nothing asked the second question, so any order number worked for any signed-in customer. OWASP lists this
under A01, Broken Access Control: the most common category in the 2025 Top 10's data.

The fix checks ownership **on every object, in every function that touches it**. Putting the fetch and the check in one
function (`_order_for`) means a new endpoint cannot forget it, which is the point: one safe way to get an order, and
no unsafe one.

**Same answer for "not yours" and "missing"**, so the error does not confirm which order numbers exist. Random,
unguessable IDs help as a second layer, but they are not the fix: OWASP's cheat sheet is explicit that the access check is
still needed, because IDs leak in links, logs and screenshots.

## The rule, for people and for agents

> Every read or change of a stored object checks that the caller may access that specific object, in one shared function; answer "not found" for objects the caller may not see.

The same sentence is in [`security/STANDARD.md`](../../../security/STANDARD.md), which you can paste into your agent's
instructions file. In a review, look for: any lookup by an ID from the request that is not scoped to the current user.

The drill's tests are the part that does not depend on anyone remembering the rule: keep tests like them in your own
suite, and they check every change, whoever or whatever wrote it.
