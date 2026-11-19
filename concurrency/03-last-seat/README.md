# 03 · The last seat

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Concurrency](https://enrichmeai.com/well-rounded-engineer/concurrency/) in the guide

## The story

A flight has 10 seats left. Forty people press "Book" at the same moment. The airline sells 40 seats.

## Before you start: predict

`book()` checks `seats_left > 0`, takes a payment, then takes a seat. Which line is the problem: the check, the
payment, or the seat? Why is "check, then act" not safe when other threads act in between?

## Your task

Make the tests pass. Keep the call to `check_payment()`: it is the slow step every real booking has.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out FlightTest
```

## Done when

- Exactly 10 of the 40 bookings succeed, and the flight ends with 0 seats, not fewer.
- You can explain why locking only `seats_left -= 1` would still oversell.

## Then

- The same "check, then act" bug appears in databases as `SELECT` then `UPDATE`. The
  [Concurrency](https://enrichmeai.com/well-rounded-engineer/concurrency/) page shows the optimistic fix with a version number, and
  [`optimistic.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/concurrency/optimistic.py) runs it.
