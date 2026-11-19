# Why the fix works

With W = 1 and R = 1, a write can land on one replica and a read can ask a different one: nothing forces them to meet.
If **W + R > N**, every set of W replicas and every set of R replicas share at least one replica, so every read reaches
at least one copy of the newest write. For N = 3, W = 2 and R = 2 works, and still allows one replica to be down.

Overlap is half of it. The read gets answers from several replicas, some of them old, so it must keep the one with the
**highest version**. Reading "the first answer" throws away the guarantee you paid for.

**The trade-off:** W = 3 would make every replica current, but then a single unreachable replica blocks every write: the
system chooses consistency over availability. W = 2, R = 2 is the usual majority setting.

**What the real thing adds:** repairing the stale replica when a read notices it (read repair), and versions that order
writes correctly even when two clients write at once.

See every (W, R) pair run in [`quorum.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/distsys/quorum.py), and the [guide](https://enrichmeai.com/well-rounded-engineer/distsys/).
