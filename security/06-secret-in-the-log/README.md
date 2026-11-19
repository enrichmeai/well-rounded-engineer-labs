# 06 · The secret in the log

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Security](https://enrichmeai.com/well-rounded-engineer/security/) in the guide · **OWASP:** [A09:2025 Security Logging and Alerting Failures](https://owasp.org/Top10/2025/A09_2025-Security_Logging_and_Alerting_Failures/), CWE-532 (sensitive information in a log file)

## The story

To debug a checkout problem, someone logged every request: headers and body. The logs went to a search tool that forty people can read. It now holds thousands of customers' card numbers, security codes and session tokens.

## Before you start: predict

Someone suggests removing `password` and `card_number` from the log line. Look at the test's request: what would still leak?

## Your task

Make `log_request(headers, body, write)` (Java: `RequestLog.log`) log only the fields known to be safe (`user-agent`, `content-type` and `x-request-id` headers; `order_id`, `currency` and `items` in the body) and write every other value as `[redacted]`, so the log still shows which fields were sent.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out RequestLogTest
```

Or, from the top of the repo: `python3 practice.py check 06 --track security` (add `--java` for Java).

Stuck? `python3 practice.py hint 06 --track security`. The worked answer and why it works are in
[`solutions/security/06-secret-in-the-log/`](../../solutions/security/06-secret-in-the-log/). Try first.

## Done when

- No token, key, password, card number or security code appears in the log.
- The safe fields are still there, and the redacted ones are named, so the log is still useful for debugging.

## Then

- The OWASP [Logging Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Logging_Cheat_Sheet.html): what to log, and what should usually not be recorded directly.
- Try it with an agent: [the experiment in the track README](../README.md#try-it-with-an-agent).
