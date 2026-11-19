# Why the fix works

A hash map looks a key up in two steps: the hash picks a bucket, then equality finds the key inside it. The rule that
makes this work: **equal objects must have equal hashes.**

`SkuKey` defined equality on (sku, size) but not the hash. In Java it inherited `Object.hashCode`, which differs for
every object, so `new SkuKey("tee", 42)` twice went to two different buckets. Every `add` made a new entry, and every
lookup missed. Nothing failed; the counts were just wrong. Python is stricter: a class that defines `__eq__` without
`__hash__` is made unhashable, so the mistake shows up as an error at the first `dict` insert.

**The fix:** hash exactly the fields that equality compares. A Java `record` or a Python `@dataclass(frozen=True)`
writes both for you, and keeps the key immutable.

**The second trap:** a key whose fields change after it is in the map. Its hash changes, it is now in the wrong bucket,
and it cannot be found again. Keys should be immutable.

**How to catch it early:** `javac -Xlint:overrides` warns about `equals` without `hashCode`; most IDEs and linters do too.

See buckets drawn in the [guide](https://enrichmeai.com/well-rounded-engineer/dsa/) and written out in [`hash_table.py`](https://github.com/enrichmeai/well-rounded-engineer-fundamentals/tree/main/dsa/hash_table.py).
