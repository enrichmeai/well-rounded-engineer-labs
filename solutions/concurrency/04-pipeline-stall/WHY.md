# Why the fix works

The collector waits for one `STOP` from each worker. A worker that dies on an exception never sends its `STOP`, so the
collector waits for ever. The exception was printed once, in a thread nobody was watching, and the job looked as if
it were still running.

The fix keeps the worker alive: one order's failure becomes a **message** (`failed`, with the order id) instead of a
crash, and the worker carries on to the next order and, at the end, to its `STOP`.

**Wrong fixes people try first:**
- Giving the collector a timeout. The job now ends, but it cannot say which order failed, and a slow night looks the
  same as a broken one.
- Catching the exception and ignoring it. The job finishes and silently leaves an order unpriced.

**The general rule:** in any pipeline, every stage must report success **or** failure for every item, and must always
signal that it has finished. A stage that can die silently turns errors into hangs.

See the working pipeline in [`pipeline.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/concurrency/pipeline.py) and the [Concurrency](https://enrichmeai.com/well-rounded-engineer/concurrency/) page.
