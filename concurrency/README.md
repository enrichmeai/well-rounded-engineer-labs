# Concurrency drills

Five drills, about an hour in all if you do them back to back, or one a day for a week. Each is in Python and Java,
so you can do it in the language you use and then in the other one to compare.

Read first: [Concurrency](https://enrichmeai.com/well-rounded-engineer/concurrency/) in the guide.

| Drill | You learn | Time |
|---|---|---|
| [01 · The lost update](01-lost-update/) | Why read-then-write needs one lock around both | 10 min |
| [02 · The deadlock](02-deadlock/) | Why one lock order prevents deadlock | 15 min |
| [03 · The last seat](03-last-seat/) | Why "check, then act" must be one step | 10 min |
| [04 · The pipeline that never finishes](04-pipeline-stall/) | Why every stage must report failures and always finish | 15 min |
| [05 · Make it fast](05-make-it-fast/) | Why threads speed up waiting, even with Python's GIL | 10 min |

Each drill folder has a `README.md` (the story, the prediction, the task), `HINTS.md`, and `python/` and `java/`
folders with the code and its tests. The answers and why they work are in
[`../solutions/concurrency/`](../solutions/concurrency/).

After the drills, the [fundamentals concurrency folder](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/concurrency)
shows each problem forced to happen and drawn step by step.
