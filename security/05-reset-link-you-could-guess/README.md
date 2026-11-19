# 05 · The reset link you could guess

**Time:** about 15 minutes · **Languages:** Python and Java · **Read first:** [Security](https://enrichmeai.com/well-rounded-engineer/security/) in the guide · **OWASP:** [A04:2025 Cryptographic Failures](https://owasp.org/Top10/2025/A04_2025-Cryptographic_Failures/) and [A07:2025 Authentication Failures](https://owasp.org/Top10/2025/A07_2025-Authentication_Failures/), CWE-338 and CWE-640

## The story

Password reset emails contain a link with a 12-character token. An attacker asked for a reset for the finance director's account, worked out the token from the time the email was sent, and took over the account. A week later, an old reset link from a forwarded email was used again.

## Before you start: predict

If the random generator is seeded with the current time, how many seeds does an attacker need to try? And what should happen to a reset token after it has been used once?

## Your task

Make `issue` (Java: `ResetTokens.issue`) create a token from a cryptographically secure generator with at least 32 bytes of randomness, and make `redeem` work only once per token, and only within 15 minutes.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out ResetTokensTest
```

Or, from the top of the repo: `python3 practice.py check 05 --track security` (add `--java` for Java).

Stuck? `python3 practice.py hint 05 --track security`. The worked answer and why it works are in
[`solutions/security/05-reset-link-you-could-guess/`](../../solutions/security/05-reset-link-you-could-guess/). Try first.

## Done when

- Two tokens issued at the same moment are different, and each carries at least 32 bytes of randomness.
- A token works once; the second use, and any use after 15 minutes, gets nothing.

## Then

- The OWASP [Forgot Password Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Forgot_Password_Cheat_Sheet.html): tokens that are random from a secure generator, single use, and expire.
- Try it with an agent: [the experiment in the track README](../README.md#try-it-with-an-agent).
