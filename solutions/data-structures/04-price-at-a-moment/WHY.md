# Why the fix works

Walking the history from the start is O(n) for each refund: about 50,000 steps on average for 100,000 price changes,
or 250 million steps for 5,000 refunds.

The history is sorted by time, so you can do what you do with a dictionary or a phone book: open it in the middle,
see which half the answer is in, and repeat. Each step halves what is left, so 100,000 entries take about 17 steps and
a million about 20: O(log n).

**The careful part:** you want the last price at or before the moment, not an exact match. Python's
`bisect_right(times, at) - 1` gives exactly that. Java's `Arrays.binarySearch` returns `-(insertion point) - 1` when
the time is not present, so the price in effect is at `insertion point - 1`. Off-by-one mistakes here are common:
the small test cases (before the first price, exactly on a change, after the last) are there to catch them.

**Where you meet this for real:** exchange rates, tax rates, prices and contracts "as of" a date. In SQL it is a
point-in-time join; in BigQuery or pandas, an "as-of" join.

See the step counts in [`big_o.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/dsa/big_o.py) and the [guide](https://enrichmeai.com/well-rounded-engineer/dsa/).
