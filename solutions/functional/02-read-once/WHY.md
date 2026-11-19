# Why the fix works

Generators and streams are **lazy**: they produce each value only when asked, and only once. That is what lets them
handle huge or endless inputs with little memory. The cost is that they are single-use. Python silently gives you an
empty generator the second time (revenue £0, no error); Java refuses with `IllegalStateException`, which is at least loud.

Two ways out: **store once** (`list(...)`, `.toList()`) when the data fits in memory and you need it several times, or
**compute everything in one pass** (Java's `summaryStatistics()`, or a single loop) when it is large.

**Where you meet this for real:** reading a file or a database cursor twice, a function that returns a generator being
passed to two consumers, and `Iterator`s held in fields.

See lazy pipelines on [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide.
