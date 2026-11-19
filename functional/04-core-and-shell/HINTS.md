## Hint 1
Two things make `checkout` hard to test: it asks the clock what day it is, and it sends an email. Neither is about pricing.

## Hint 2
Move the pricing into `price(order, day)`, where `day` is a parameter. Then `checkout` does three things: read the
clock, call `price`, send the receipt.

## Hint 3
Weekend means `day.weekday() >= 5` in Python (Monday is 0), `day.getDayOfWeek().getValue() >= 6` in Java (Monday is 1).
The discount is `total * 90 // 100` in pence.
