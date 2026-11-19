# 02 · The order that was not yours

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Security](https://enrichmeai.com/well-rounded-engineer/security/) in the guide · **OWASP:** [A01:2025 Broken Access Control](https://owasp.org/Top10/2025/A01_2025-Broken_Access_Control/), CWE-639 (a user-controlled key)

## The story

The order page is `/orders/1043`. A customer changed it to `/orders/1044` and saw a stranger's name, address and basket. Then they cancelled it.

## Before you start: predict

The user is signed in, so the login check passed. Which check is missing, and in how many places?

## Your task

Make `get_order` and `cancel_order` (Java: `Orders.get`, `Orders.cancel`) work only on the caller's own orders. For anyone else's order, raise `NotFound` (Java: `NotFoundException`) with the same message as for an order that does not exist.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out OrdersTest
```

Or, from the top of the repo: `python3 practice.py check 02 --track security` (add `--java` for Java).

Stuck? `python3 practice.py hint 02 --track security`. The worked answer and why it works are in
[`solutions/security/02-order-that-was-not-yours/`](../../solutions/security/02-order-that-was-not-yours/). Try first.

## Done when

- Customers can read and cancel their own orders.
- Another customer's order cannot be read or cancelled, and the answer is the same as for an order that does not exist, so nobody can learn which order numbers are real.

## Then

- Checking access on every object: the OWASP [IDOR Prevention Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Insecure_Direct_Object_Reference_Prevention_Cheat_Sheet.html).
- Try it with an agent: [the experiment in the track README](../README.md#try-it-with-an-agent).
