# Why the fix works

Comparing every pair is n × (n − 1) / 2 comparisons. For 2,000 ids that is about 2 million; for 20,000 it is about
200 million. Ten times the data, a hundred times the work: that is O(n²), and it is why the job slowed down without
anyone changing it.

A hash set answers "have I seen this?" in about the same time however much it holds, so one pass over the list does
the job: O(n). Twenty thousand ids, twenty thousand lookups.

**What it costs:** memory for the set, as large as the list in the worst case. Usually the right trade.

**Other correct answers:** sort a copy and check neighbours, O(n log n), with no extra structure if you may sort in place.
In SQL, `GROUP BY id HAVING count(*) > 1` does it in the database.

**Where you meet this for real:** any check of each item against every other one: matching two lists, finding
overlapping bookings, comparing every user with every user.

See the step counts in [`big_o.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/dsa/big_o.py) and the [guide](https://enrichmeai.com/well-rounded-engineer/dsa/).
