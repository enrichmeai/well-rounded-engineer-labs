# Why the fix works

The page was built by gluing strings together, so the browser received the reviewer's text and the site's HTML
as one document and could not tell them apart. That is the shape of every injection: SQL, shell commands, HTML. Data
crossed into a place where it is read as instructions.

The fix is **encoding on output, for the place the data goes**: HTML-escaping turns `<` into `&lt;`, which the browser
shows as a character and never parses as a tag. Escape `&` first, or you will double-escape your own output.

**Make the safe way the default.** Most modern frameworks escape every value they render by default, which OWASP
recommends because it does not depend on deciding correctly which data is untrusted. The danger is in each framework's
escape hatches, such as React's `dangerouslySetInnerHTML` or setting `innerHTML` directly. Two more layers limit the harm if one slips through: a Content Security Policy,
and session cookies marked `HttpOnly`, which OWASP's Session Management Cheat Sheet describes as stopping scripts
from reading them through `document.cookie`.

**Different places need different encoding**: HTML text, an attribute, a URL and JavaScript each have their own rules,
which is why a template engine beats hand-written escaping in real code.

## The rule, for people and for agents

> Never build HTML, SQL or shell commands by joining strings with user input: use an auto-escaping template, parameterised queries and argument lists, and treat every opt-out of escaping as a review item.

The same sentence is in [`security/STANDARD.md`](../../../security/STANDARD.md), which you can paste into your agent's
instructions file. In a review, look for: string concatenation or f-strings that build HTML, SQL or a shell command, and any framework escape hatch such as `dangerouslySetInnerHTML` or `innerHTML`.

The drill's tests are the part that does not depend on anyone remembering the rule: keep tests like them in your own
suite, and they check every change, whoever or whatever wrote it.
