## Hint 1
A list keeps its items side by side in memory. Removing the first one shifts every other item one place to the left:
300,000 jobs means about 45 billion item moves in all.

## Hint 2
You want a structure where adding at the back and removing from the front are both O(1): a double-ended queue.

## Hint 3
Python: `collections.deque` and `popleft()`. Java: `ArrayDeque` and `poll()`.
