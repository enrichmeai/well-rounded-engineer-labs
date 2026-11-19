## Hint 1
`random.Random` (Java: `java.util.Random`) is built for simulations: the same seed gives the same sequence. Seeding it
with the time makes the token a function of the time.

## Hint 2
Python: `secrets.token_urlsafe(32)`. Java: `SecureRandom` filling a 32-byte array, then
`Base64.getUrlEncoder().withoutPadding()`.

## Hint 3
Single use: remove the token from the store when it is redeemed (`dict.pop`, `Map.remove`), and check the expiry as
well.
