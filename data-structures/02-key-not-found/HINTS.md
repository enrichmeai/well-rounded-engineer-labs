## Hint 1
Python: read the error. Defining `__eq__` without `__hash__` makes the class unhashable, so it cannot be a dict key.
Java: compile with `javac -Xlint:overrides *.java` and read the warning.

## Hint 2
The rule: if two objects are equal, they must have the same hash. `SkuKey` defines equality on (sku, size) but not the
hash, so two equal keys land in different buckets and never meet.

## Hint 3
Python: add `def __hash__(self): return hash((self.sku, self.size))`, or make it `@dataclass(frozen=True)`.
Java: add `hashCode()` returning `Objects.hash(sku, size)`, or make `SkuKey` a `record`.
