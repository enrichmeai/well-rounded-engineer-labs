# Why the fix works

Two things turned a blip into an outage. **Synchrony:** every client waited the same 100 ms, so they all came back at
the same instant, and kept doing so on every attempt. **No back-off:** the waits never grew, so the service never got a
quiet moment to recover.

**Exponential backoff** doubles the ceiling each attempt (0.1 s, 0.2 s, 0.4 s, …) up to a cap, so a struggling
service sees less and less retry traffic. **Jitter** picks the actual wait at random below that ceiling, so clients that
failed together come back spread out instead of in a wave. "Full jitter", a random wait between zero and the ceiling,
is the simplest form.

**What the real thing adds:**
- A limit on attempts, and a total deadline, so a client eventually gives up and reports the failure.
- Retry only what is safe to repeat (drill 01), and only errors that might be temporary: a timeout or a 503, not a 400.
- A circuit breaker on top, so that when a dependency is clearly down, clients stop calling it for a while.

See retries, timeouts and circuit breakers together on the [Microservices](https://enrichmeai.com/well-rounded-engineer/microservices/) page of the guide.
