# Why the fix works

Over a network, a client that hears nothing cannot know whether its request was lost or the reply was. Not retrying
loses payments; retrying blindly doubles them. The way out is to make the retry harmless: the client picks a key for
the operation and sends the same key every time it retries, and the service remembers what it did for each key.

**Why the client chooses the key:** only the client knows that two requests are the same attempt. A key the server
made up would be new on every request, retries included.

**What the real thing adds:**
- The key and the charge must be stored together, atomically, or a crash between them brings the problem back.
- Keys are kept for a limited time, long enough to cover any retry.
- A second request still in flight with the same key should wait for, or be refused until, the first one finishes.

**Where you meet this for real:** payments, orders, bookings, any `POST` that must not happen twice. A database
unique constraint on the key is a simple way to make the "seen it?" check safe under concurrency.

See it run over a lossy network in [`idempotency.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/distsys/idempotency.py), and the [guide](https://enrichmeai.com/well-rounded-engineer/distsys/).
