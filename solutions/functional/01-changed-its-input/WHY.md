# Why the fix works

A **pure function** depends only on its arguments and changes nothing outside itself: same input, same output, no
surprises for anyone else. `with_tax` gave the right answer and quietly rewrote the caller's list, so every other part of
the program holding that list saw the change. The bug showed up on a page that never called it.

Returning a new list keeps the input untouched. That is most of what "functional style" buys day to day: you can call a
function anywhere, twice, in any order, or from several threads, and reason about it on its own.

**Python trap:** `list.sort()` sorts in place and returns `None`; `sorted(...)` returns a new list. **Java trap:**
`Collections.sort` sorts in place; `Stream.sorted()` does not. `Stream.toList()` (Java 16+) returns an unmodifiable list,
so a caller cannot change your result by accident either.

**When changing in place is the right call:** a huge list in a measured hot path, where a copy costs too much memory or
time. Then say so in the function's name and docs, and own the list (do not change one somebody else passed you).

See [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide.
