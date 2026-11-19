# Data structures drills

Five drills, each in Python and Java. Four are about choosing the structure whose operations fit the job; one is about
the rule every hash map depends on. Three of them are timed: the starter code gives the right answer, just far too slowly.

Read first: [Data Structures &amp; Algorithms](https://enrichmeai.com/well-rounded-engineer/dsa/) in the guide.

| Drill | You learn | Time |
|---|---|---|
| [01 · The slow duplicate check](01-slow-duplicate-check/) | Why a nested loop is O(n²), and a set makes it O(n) | 10 min |
| [02 · The key that cannot be found](02-key-not-found/) | Equal objects must have equal hashes | 15 min |
| [03 · The slow queue](03-slow-queue/) | Why removing from the front of a list is O(n), and a deque is O(1) | 10 min |
| [04 · The price at a moment](04-price-at-a-moment/) | Binary search on sorted data, and its off-by-one | 15 min |
| [05 · The cache that forgets the wrong thing](05-cache-forgets/) | Least recently used, not first in, first out | 10 min |

The timed tests allow one second; the fixed versions take a few milliseconds and the starters take several seconds,
so the limit is far from both. The answers and why they work are in [`../solutions/data-structures/`](../solutions/data-structures/).
