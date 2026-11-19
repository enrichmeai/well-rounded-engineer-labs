# Why the fix works

A Python list and a Java `ArrayList` keep their items side by side in one block of memory. Removing the first item
means moving every other item one place left: O(n) for each removal, O(n²) to empty the queue. Each job was cheap; the
queue was the cost, and it grew with the backlog.

A deque (Python `collections.deque`, Java `ArrayDeque`) is built for both ends: adding at the back and removing at the
front are O(1), so emptying the queue is O(n).

**Wrong fixes people try first:**
- Reversing the list and popping from the end. It works, but adding a new job at the "back" is now the slow end.
- Keeping an index of the next job and never removing anything. It works, and the list never shrinks.

**Where you meet this for real:** work queues, breadth-first search, rate-limit windows, anything "first in, first out".
Across processes, the same job is done by a message queue.

See breadth-first search use a deque in [`graph.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/dsa/graph.py) and the [guide](https://enrichmeai.com/well-rounded-engineer/dsa/).
