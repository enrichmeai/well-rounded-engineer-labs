# 01 · The lost update

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Concurrency](https://enrichmeai.com/well-rounded-engineer/concurrency/) in the guide

## The story

A shop keeps a running balance. Eight checkout threads each add 1 to it, hundreds of times. At the end the balance is
short, and nobody can reproduce it on their laptop.

## Before you start: predict

`deposit()` reads the balance, does a little slow work, then writes the balance back plus the amount. With 8 threads
doing 500 deposits each, what will the balance be? Write your guess down before you run anything.

## Your task

Make the tests pass. **Keep the call to `record_audit_entry()`** where it is: it stands in for real work (a database
call, a log line), and the tests count that it still happens once per deposit.

```bash
cd python && python3 -m pytest -q                              # Python
cd java && javac -d out *.java && java -cp out BankTest        # Java
```

Or, from the top of the repo: `python3 practice.py check 01 --track concurrency` (add `--java` for Java).

Stuck? `python3 practice.py hint 01 --track concurrency` shows one hint at a time. The worked answer and why it works are in
[`solutions/concurrency/01-lost-update/`](../../solutions/concurrency/01-lost-update/). Try first.

## Done when

- The tests pass, every time you run them, not just once.
- You can say in one sentence why the balance came out short.

## Then

- The same bug, forced to happen every time and drawn step by step:
  [`lost_update.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/concurrency/lost_update.py) in the fundamentals repo.
