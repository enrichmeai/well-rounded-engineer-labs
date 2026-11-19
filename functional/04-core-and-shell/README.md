# 04 · The core and the shell

**Time:** about 15 minutes · **Languages:** Python and Java · **Read first:** [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide

## The story

Weekend orders get 10% off. The pricing code reads today's date and sends the receipt email itself. To test the weekend discount, someone has to wait for Saturday, and every test run tries to send an email.

## Before you start: predict

Which parts of `checkout` depend on the outside world (the clock, the email system), and which are plain arithmetic on the order? Which part do you most want to test thoroughly?

## Your task

Write `price(order, day)`: a pure function that takes the day as an argument, reads no clock and sends nothing. Make `checkout` use it, so the outside world stays in the thin outer layer.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out PricingTest
```

Or, from the top of the repo: `python3 practice.py check 04 --track functional` (add `--java` for Java).

Stuck? `python3 practice.py hint 04 --track functional`. The worked answer and why it works are in
[`solutions/functional/04-core-and-shell/`](../../solutions/functional/04-core-and-shell/). Try first.

## Done when

- `price` gives the weekday and weekend totals for any date you pass in, and sends no email.
- `checkout` still sends exactly one receipt, with the same total.

## Then

- A pure core with a thin imperative shell, and when it pays off: [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide.
