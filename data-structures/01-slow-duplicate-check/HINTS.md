## Hint 1
Comparing every pair is n × (n − 1) / 2 comparisons: 200 million for 20,000 ids. The work grows with the square of the data.

## Hint 2
Walk the list once and remember what you have already seen, in something where "have I seen this?" takes the same time
however much you have stored.

## Hint 3
A hash set: Python `set()`, Java `HashSet`. For each id, if it is already in the set, return true; otherwise add it.
