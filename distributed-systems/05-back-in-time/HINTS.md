## Hint 1
"Last to arrive wins" is only right if messages arrive in the order they were sent. Across a network they need not.

## Hint 2
The profile already stores the version it is at. An update with a version that is not newer than that is old news.

## Hint 3
In `apply`: `if update.version <= profile.version: return` before changing anything. That also makes a duplicate
delivery harmless.
