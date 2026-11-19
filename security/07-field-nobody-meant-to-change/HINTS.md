## Hint 1
The browser form is not a security control. Anyone can send any JSON they like.

## Hint 2
Name the fields a customer may edit in an allow-list, and check every key in the request against it.

## Hint 3
Check all the keys first, then apply the changes. Checking while applying leaves a half-updated user when the third
key is refused.
