# Why the fix works

Each thread did **read → slow work → write**. Two threads could both read 100, both write 101, and one deposit vanished.
Nothing crashed and nothing was logged: the only symptom was a wrong total.

A lock makes **read → slow work → write** one step that only one thread can be inside. The others wait at the door.

**Wrong fixes people try first:**
- Locking only the write. The stale value was already read before the lock.
- Moving `record_audit_entry()` out of the way. The tests stop it, and in real code that call is the database write you
  cannot remove.
- Running the test until it passes. With fewer threads or a fast machine the race can hide for days; it has not gone.

**Other correct answers:** in Java, `AtomicInteger.addAndGet` does the read-add-write in one hardware step, if there is
no slow work in between. In a database, `UPDATE accounts SET balance = balance + 1` does the same on the server.

See the lost update drawn step by step in the [guide](https://enrichmeai.com/well-rounded-engineer/concurrency/) and in [`lost_update.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/concurrency/lost_update.py).
