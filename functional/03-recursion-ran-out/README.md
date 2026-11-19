# 03 · The recursion that ran out

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide

## The story

The category tree on a shop works fine in testing. A customer's import creates a chain of categories 100,000 deep, and the page that shows a category's depth crashes with `RecursionError` (Java: `StackOverflowError`).

## Before you start: predict

Each recursive call waits for the next one to finish, and every waiting call holds a frame on the call stack. How many frames does a chain 100,000 deep need? Python's default limit is 1,000.

## Your task

Make the tests pass for a chain 100,000 deep, with the same answers for the short chains.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out DepthTest
```

Or, from the top of the repo: `python3 practice.py check 03 --track functional` (add `--java` for Java).

Stuck? `python3 practice.py hint 03 --track functional`. The worked answer and why it works are in
[`solutions/functional/03-recursion-ran-out/`](../../solutions/functional/03-recursion-ran-out/). Try first.

## Done when

- `depth` works for 0, 1 and 100,000 levels.
- You can say why Python and Java do not turn this recursion into a loop for you.

## Then

- Where recursion is the clearer choice, and where it is not: [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide.
