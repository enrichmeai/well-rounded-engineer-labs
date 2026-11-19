# Why the fix works

Each sort reorders the whole list by its own key. Sorting by store and then by total leaves the list ordered by total;
the store order only survives among sales with the same total. The last sort wins.

**Two correct ways to sort by two keys:**
- **One sort, combined key.** Python: a tuple key, `(store, -total)`; negate a number to reverse just that part. Java:
  `comparing(store).thenComparing(total, reverseOrder())`.
- **Several sorts, primary last.** Python's sort and Java's `Stream.sorted()` on an ordered stream are both **stable**:
  equal items keep their relative order. So sort by the secondary key first and the primary key last, and the primary
  order wins while the secondary order survives within it. (Python's sorting guide documents this pattern.)

**Filter before you sort:** sorting is the expensive step (O(n log n)), so throw away what you do not need first.

See filter, map, sort and group side by side on [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide.
