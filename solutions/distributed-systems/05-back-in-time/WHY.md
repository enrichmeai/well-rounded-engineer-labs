# Why the fix works

"Last to arrive wins" quietly assumes that messages arrive in the order they were sent. Across a network, with retries,
several partitions and several consumers, they need not. The arrival order is a fact about the network, not about the
customer.

Each update carries the version it was made at, and the profile remembers the version it is at. Applying an update
only when it is newer makes the result independent of arrival order, and makes duplicates harmless too: a redelivered
update is simply not newer.

**Where the version comes from matters:** it must be assigned by one place that sees every change in order, such as the
profile's own database row, or a Kafka partition keyed by customer. Two machines stamping versions from their own clocks
can disagree; that is what Lamport clocks and hybrid logical clocks are for.

**Where you meet this for real:** caches and search indexes fed from events, read models in event sourcing, and any
"upsert from a stream". The same check in SQL: `UPDATE ... SET ..., version = :v WHERE id = :id AND version < :v`.

See a delayed duplicate break a store, found by simulation, in [`dst.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/distsys/dst.py), and the [guide](https://enrichmeai.com/well-rounded-engineer/distsys/).
