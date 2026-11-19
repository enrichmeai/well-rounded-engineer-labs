## Hint 1
Each call to `charge` charges the card, whatever the key. The key is passed in and then ignored.

## Hint 2
The first time a key is seen, charge and remember the receipt under that key. Every later call with the same key returns
the remembered receipt and charges nothing.

## Hint 3
A dict (Java: `HashMap`) from key to receipt. Check it first: `if key in self.receipts: return self.receipts[key]`.
