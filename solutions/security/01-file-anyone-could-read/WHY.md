# Why the fix works

Joining a folder and a name does not keep you in the folder. `..` climbs out of it, and in both Python and Java an
absolute name replaces the folder entirely. Checking the *string* for `..` is the fix people try first, and it fails:
encodings, absolute paths and links all get past it.

The fix checks the **result**, not the input: resolve where the path really points (following `..` and links), then
check it is still inside the resolved folder, comparing path components rather than strings (`/srv/invoices-old` starts
with the string `/srv/invoices`). Refuse everything else.

**Better still (our view), do not take a path at all.** Look the invoice up by an ID the user owns, and keep the file name on the
server: then there is no path to traverse, and the access check from drill 02 applies.

## The rule, for people and for agents

> Never build a file path from user input without resolving it and checking it is still inside the intended folder; prefer looking files up by an ID the user is allowed to see.

The same sentence is in [`security/STANDARD.md`](../../../security/STANDARD.md), which you can paste into your agent's
instructions file. In a review, look for: `open`, `read`, `Path`, `File` or `resolve` called with anything that came from a request.

The drill's tests are the part that does not depend on anyone remembering the rule: keep tests like them in your own
suite, and they check every change, whoever or whatever wrote it.
