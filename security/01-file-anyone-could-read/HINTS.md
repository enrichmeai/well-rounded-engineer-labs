## Hint 1
`..` means "the folder above". Joining paths does not stop it; neither does it stop an absolute path, which replaces
the base completely.

## Hint 2
Work out where the path really points, then check that it is still inside the folder. Python: `Path.resolve()` and
`is_relative_to`. Java: `normalize()`, `toRealPath()` and `startsWith`.

## Hint 3
Resolve the base folder too, and check after following links: a link inside the folder can point anywhere. Compare
paths, not strings: `/srv/invoices-old` starts with the string `/srv/invoices`, but it is not inside that folder.
