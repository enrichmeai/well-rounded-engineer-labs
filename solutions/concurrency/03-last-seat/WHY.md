# Why the fix works

"Check, then act" is only safe if nothing can change between the check and the act. Forty threads all checked
`seats_left > 0` while it was still 10, then all acted. Putting the check and the act under one lock makes them a
single step: the eleventh thread checks after the tenth has taken the last seat.

**Wrong fixes people try first:**
- Locking only `seats_left -= 1`. Every thread has already passed the check.
- Checking again after the payment. Better, but there is still a gap between the second check and the act.

**The trade-off this fix makes:** the payment call now happens while the lock is held, so bookings on this flight
queue one at a time. Real systems often reserve the seat first (one quick locked step), take the payment outside the
lock, and release the reservation if the payment fails.

**Databases:** the same bug is a `SELECT seats_left` followed by an `UPDATE`. Fix it with one conditional update,
`UPDATE flights SET seats_left = seats_left - 1 WHERE id = ? AND seats_left > 0`, and check how many rows it changed.

See the [Concurrency](https://enrichmeai.com/well-rounded-engineer/concurrency/) page.
