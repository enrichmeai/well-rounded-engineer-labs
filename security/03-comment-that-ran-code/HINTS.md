## Hint 1
The browser cannot tell your HTML from the reviewer's: both arrive as one string. The reviewer's text has to be changed
so that it cannot be read as markup.

## Hint 2
Escape it when you put it into the HTML (on output), not when you store it. Python has `html.escape`. Java's standard
library has none, so write a small one.

## Hint 3
Escape `&` first, then `<`, `>`, `"` and `'` (as `&#x27;`, the same as Python's `html.escape`). Escaping `&` last would
turn your own `&lt;` into `&amp;lt;`.
