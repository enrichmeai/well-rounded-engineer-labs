# Why the fix works

Each call spends its 0.1 seconds **waiting** for the network. A thread that waits gives up the CPU, and in CPython it
also releases the GIL, so the other 19 calls can start. All 20 waits overlap, and the whole thing takes about 0.1 s.

`pool.map` (and collecting the Java futures in submission order) keeps the answers in the order you asked for them,
whichever call finishes first.

**When this would not work:** if `fetch_price` were doing arithmetic for 0.1 s, standard CPython threads would take
turns on the GIL and finish in about 2 s again. Then you need processes (Python) or real parallel threads (Java).
Java's virtual threads make waiting cheap; they do not make arithmetic faster.

**What to think about in real code:** 20 calls at once is 20 requests at once on the price service. Put a limit on it
(a pool size, a semaphore), and a timeout on each call.

See the measured difference in [`io_vs_cpu.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/concurrency/io_vs_cpu.py) and
[`java/IoVsCpu.java`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/concurrency/java/IoVsCpu.java).
