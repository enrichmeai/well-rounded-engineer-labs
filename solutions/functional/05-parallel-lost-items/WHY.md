# Why the fix works

`forEach(results::add)` from a parallel stream has several threads changing one `ArrayList`, which is not thread-safe:
updates are lost, and sometimes its internal array is resized under another thread's feet. Measured on four cores:
100,000 adds gave the wrong count in 19 of 20 runs and an exception in the other. Even a synchronized list does not
fix the order: `forEach` on a parallel stream is, in the JDK's words, "explicitly nondeterministic" about order
(20 of 20 runs out of order here).

`map(...).toList()` does not share anything you wrote. Each thread works on its own part, and the stream joins the parts
in **encounter order**. The Python version had the same shape: appending as each task finished gives finishing order;
`pool.map` gives input order.

**The rule:** in a parallel pipeline, describe *what* to compute (`map`, `filter`, `collect`) and let the library do the
combining. Never change shared state from inside it.

**Is parallel worth it?** Only for enough work per item. Measured here on four cores: 2,000,000 items with heavy work in
`map` went from 1,466 ms to 441 ms; 1,000 items with light work went from 0.033 ms to 0.180 ms, slower. Measure first.

See [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide.
