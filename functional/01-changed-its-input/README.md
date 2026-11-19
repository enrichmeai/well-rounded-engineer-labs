# 01 · The function that changed its input

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide

## The story

The checkout page shows prices with tax. The basket page, which uses the same list a moment later, now shows tax added twice. Nobody touched the basket page.

## Before you start: predict

`with_tax` returns the right numbers. What else does it do to the list it was given, and who else is holding that list?

## Your task

Make the tests pass: the same results, and the caller's list left exactly as it was.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out PricesTest
```

Or, from the top of the repo: `python3 practice.py check 01 --track functional` (add `--java` for Java).

Stuck? `python3 practice.py hint 01 --track functional`. The worked answer and why it works are in
[`solutions/functional/01-changed-its-input/`](../../solutions/functional/01-changed-its-input/). Try first.

## Done when

- Calling `with_tax` twice on the same list gives the same answer twice.
- You can say what a pure function is in one sentence.

## Then

- Pure functions, and where functional style stops fitting: [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide.
