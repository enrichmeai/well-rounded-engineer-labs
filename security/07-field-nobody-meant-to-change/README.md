# 07 · The field nobody meant to change

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Security](https://enrichmeai.com/well-rounded-engineer/security/) in the guide · **OWASP:** [A08:2025 Software or Data Integrity Failures](https://owasp.org/Top10/2025/A08_2025-Software_or_Data_Integrity_Failures/), CWE-915 (mass assignment)

## The story

The profile form sends a display name and an email. The update code copies every field in the request onto the user, which saved writing one line per field. A customer added `"is_admin": true` to the request by hand.

## Before you start: predict

The form never shows an `is_admin` field. Does that matter to someone sending the request with their own tool?

## Your task

Make `update_profile(user, changes)` (Java: `Profile.update`) accept only `display_name` and `email` (Java: `displayName`, `email`). If the request contains any other field, raise `ValueError` (Java: `IllegalArgumentException`) and change nothing at all.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out ProfileTest
```

Or, from the top of the repo: `python3 practice.py check 07 --track security` (add `--java` for Java).

Stuck? `python3 practice.py hint 07 --track security`. The worked answer and why it works are in
[`solutions/security/07-field-nobody-meant-to-change/`](../../solutions/security/07-field-nobody-meant-to-change/). Try first.

## Done when

- A customer can change their display name and email.
- Any other field, such as `is_admin` or `credit_pence`, is refused, and the refused request changes nothing, not even the allowed fields that came with it.

## Then

- The OWASP [Mass Assignment Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Mass_Assignment_Cheat_Sheet.html): dedicated input objects and allow-lists.
- Try it with an agent: [the experiment in the track README](../README.md#try-it-with-an-agent).
