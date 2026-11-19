# Why the fix works

A lease is time-limited ownership: if the holder stops renewing, the lock is given to someone else. That protects the
system from a crashed holder, but not from a **paused** one. A garbage-collection pause, a stalled virtual machine or a
slow network can freeze a process for longer than its lease, and when it wakes it has no idea time has passed.

Asking "do I still hold the lock?" just before writing does not close the gap: the pause can happen between the check
and the write. Only the **storage**, which sees every write, can tell the old holder from the new one. The fencing token
lets it: every lease gets a higher number, every write carries its lease's number, and the storage refuses anything
lower than the highest number it has accepted.

This is the same idea as a **generation clock** (also called term or epoch): a number that goes up with each new leader,
so a leader that paused and came back can be recognised as out of date. Raft's terms work this way.

**What the real thing adds:** the token check and the write must be one atomic step in the storage, for example a
conditional update (`... WHERE fencing_token <= :token`).

See leases, split brain and the generation clock on the [Distributed Systems](https://enrichmeai.com/well-rounded-engineer/distsys/) page of the guide.
