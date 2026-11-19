## Hint 1
For 0.1 seconds each call is waiting, not computing. A waiting thread lets another one run, even with the GIL.

## Hint 2
Start all 20 calls at once and collect the answers. A thread pool does exactly that, and keeps the order for you.

## Hint 3
Python: `with ThreadPoolExecutor(max_workers=20) as pool: return list(pool.map(fetch_price, skus))`.
Java: `Executors.newVirtualThreadPerTaskExecutor()`, submit one task per SKU, then call `get()` on the futures in order.
