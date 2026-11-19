## Hint 1
Each refund walks on average half of 100,000 entries: about 250 million steps for 5,000 refunds.

## Hint 2
The times are sorted. Look at the middle entry: if it is after the order time, the answer is in the first half; if not,
in the second half. Twenty halvings cover a million entries.

## Hint 3
Python: `bisect.bisect_right(times, at) - 1` is the index of the last time <= at (-1 means "before the first price").
Java: `Arrays.binarySearch(times, at)`; a negative result r means "not found, insert at -r - 1", so use -r - 2.
