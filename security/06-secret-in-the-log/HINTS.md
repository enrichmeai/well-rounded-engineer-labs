## Hint 1
A list of things to hide (a deny-list) misses whatever nobody thought of: `X-Api-Key`, `card_cvc`, `otp`. A list of
things that are safe to show (an allow-list) fails safe.

## Hint 2
Build a copy of the headers and the body where each value is kept if its name is on the allow-list, and replaced with
`[redacted]` if not. Header names are case-insensitive; compare them in lower case.

## Hint 3
Log the copies, never the originals. The test checks the exact secrets, so make sure none of them reaches `write`.
