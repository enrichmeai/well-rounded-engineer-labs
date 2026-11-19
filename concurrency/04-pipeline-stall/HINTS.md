## Hint 1
Run it and look at the output: one worker died with an exception, so it never sent its `STOP`. The collector is still
waiting for it.

## Hint 2
A worker must always send its result **or** its failure, and must always send `STOP`, whatever happens to one order.

## Hint 3
Wrap the pricing of one order in `try`/`except` (Java: `try`/`catch`). On failure, send `("failed", order_id)` (Java:
`Result.failed(id)`) and carry on with the next order. `run()` already collects failures.
