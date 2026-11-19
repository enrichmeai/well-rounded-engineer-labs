# Functional style drills

Six drills, each in Python and Java, about writing code as data flowing through functions (filter, map, sort, reduce)
and about the places where that style breaks or needs care. Read first: [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide.

| Drill | You learn | Time |
|---|---|---|
| [01 · The function that changed its input](01-changed-its-input/) | Pure functions return new values; they do not change their arguments | 10 min |
| [02 · The pipeline you can only read once](02-read-once/) | Generators and streams are lazy and single-use | 10 min |
| [03 · The recursion that ran out](03-recursion-ran-out/) | Deep recursion overflows in Python and Java; a loop does not | 10 min |
| [04 · The core and the shell](04-core-and-shell/) | Keep the logic pure; push the clock and the email to the edge | 15 min |
| [05 · The parallel stream that lost items](05-parallel-lost-items/) | In parallel, collect with the library; never change shared state | 15 min |
| [06 · The report sorted the wrong way](06-sorted-wrong-way/) | Filter, map, and sort by two keys; stable sorts | 10 min |

The answers and why they work are in [`../solutions/functional/`](../solutions/functional/).
