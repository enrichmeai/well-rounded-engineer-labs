# 01 · The file anyone could read

**Time:** about 15 minutes · **Languages:** Python and Java · **Read first:** [Security](https://enrichmeai.com/well-rounded-engineer/security/) in the guide · **OWASP:** [A01:2025 Broken Access Control](https://owasp.org/Top10/2025/A01_2025-Broken_Access_Control/), CWE-22 (path traversal)

## The story

Customers download their invoices from `/invoices?name=inv-1042.pdf`. The code joins the name onto the invoice folder and returns the file. Someone tried `name=../config/secrets.env`, and got it.

## Before you start: predict

What does joining `invoices/` and `../secret.txt` give you? And what happens when the name is an absolute path, such as `/etc/passwd`?

## Your task

Make `read_invoice(base, name)` (Java: `InvoiceFiles.read`) refuse any name that ends up outside the invoice folder, with `PermissionError` (Java: `SecurityException`). Files inside the folder must still work, including in subfolders.

```bash
cd python && python3 -m pytest -q
cd java && javac -d out *.java && java -cp out InvoiceFilesTest
```

Or, from the top of the repo: `python3 practice.py check 01 --track security` (add `--java` for Java).

Stuck? `python3 practice.py hint 01 --track security`. The worked answer and why it works are in
[`solutions/security/01-file-anyone-could-read/`](../../solutions/security/01-file-anyone-could-read/). Try first.

## Done when

- An invoice inside the folder, or in a subfolder of it, is returned.
- `../secret.txt`, an absolute path, and a link inside the folder that points outside are all refused.

## Then

- Path traversal and how to validate input: [Security](https://enrichmeai.com/well-rounded-engineer/security/) in the guide, and the OWASP [Input Validation Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Input_Validation_Cheat_Sheet.html).
- Try it with an agent: [the experiment in the track README](../README.md#try-it-with-an-agent).
