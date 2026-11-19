# Why the fix works

The form is a suggestion to a browser, not a rule for the server. Copying "every field in the request" onto a
stored object hands every attacker a form with every field on it: `is_admin`, `credit_pence`, `owner_id`, `price`.
Frameworks make this easy on purpose (automatic binding of request parameters to objects), which is why OWASP gives it its
own cheat sheet.

The fix names what may change, **an allow-list**, and refuses the rest. Two details matter:
- **refuse, do not silently ignore**, so a mistake in the client shows up in testing instead of quietly dropping data;
- **check everything before applying anything** (the names and the values), so a refused request leaves no half-made
  change. The loop that applies the changes is safe only because the allow-list ran first.

**In real code, use a dedicated input type**: a `ProfileUpdate` record or DTO with only the editable fields, bound from
the request, then copied onto the user. The type itself becomes the allow-list, and a new sensitive field on `User` is
safe by default. OWASP's cheat sheet adds that this does not replace checking the caller may edit that user at all
(drill 02).

## The rule, for people and for agents

> Never bind a request directly onto a stored object: accept a dedicated input type, or an explicit allow-list of editable fields, and refuse anything else before changing anything.

The same sentence is in [`security/STANDARD.md`](../../../security/STANDARD.md), which you can paste into your agent's
instructions file. In a review, look for: `setattr` in a loop, `__dict__.update`, or a framework binding a request straight onto an entity or database model.

The drill's tests are the part that does not depend on anyone remembering the rule: keep tests like them in your own
suite, and they check every change, whoever or whatever wrote it.
