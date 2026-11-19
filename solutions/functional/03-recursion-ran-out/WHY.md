# Why the fix works

Every recursive call that has not finished keeps a frame on the call stack. A chain 100,000 deep needs 100,000 frames.
CPython stops at its recursion limit (1,000 by default, `sys.getrecursionlimit()`) with `RecursionError`; the JVM runs
out of thread stack and throws `StackOverflowError` (measured here: fine at 10,000 levels, overflowing at 100,000).

Some functional languages turn a call in "tail position" into a jump, so deep recursion costs no stack. Neither CPython
nor Java does this, and `1 + depth(parent)` is not a tail call anyway: the addition still has to happen after it returns.

**The loop** keeps one variable and runs in constant stack space, whatever the depth.

**When recursion is still the clearer choice:** structures whose depth is small and bounded by design, such as a balanced
tree (about 20 levels for a million items), parsing nested JSON from a trusted source, or divide-and-conquer such as
merge sort. Ask how deep the data can get, and who controls that.

See [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide.
