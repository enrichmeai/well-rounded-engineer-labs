# 05 · Make it fast

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Concurrency](https://enrichmeai.com/well-rounded-engineer/concurrency/) in the guide

## The story

The product page calls a price service for each of 20 items, one after another. Each call takes 0.1 seconds, so the
page takes 2 seconds. The goal is under half a second, with the same prices in the same order.

## Before you start: predict

Python has a global interpreter lock (the GIL): only one thread runs Python code at a time. Will threads still make
this faster? What is each call actually doing for those 0.1 seconds?

## Your task

Make `fetch_all` (Java: `fetchAll`) return the same list, in the same order, in under 0.5 seconds. Do not change
`fetch_price`: it stands in for a network call you do not control.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out PricesTest
```

## Done when

- The test passes: same prices, same order, under 0.5 seconds.
- You can say why threads help here, and would not help if `fetch_price` were doing arithmetic.

## Then

- Ten waits with and without threads, arithmetic with threads and with processes, and (in Java) 10,000 waits on
  virtual threads: [`io_vs_cpu.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/concurrency/io_vs_cpu.py) and [`java/IoVsCpu.java`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/concurrency/java/IoVsCpu.java).
