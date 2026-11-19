# Why the fix works

Every program has to read clocks, send email and write to databases. The trouble starts when that is mixed into the
logic: the weekend discount could only be tested on a weekend, and every test tried to send mail.

Splitting it gives a **pure core** (`price`: order and day in, total out) and a **thin shell** (`checkout`: read the clock,
call the core, send the receipt). The core is where the rules live, and now it can be tested for any date in
microseconds. The shell is small enough to check with one or two tests that stub the outside world.

Passing the day in, instead of reading it inside, is the general trick: time, randomness, configuration and other
services all become arguments. In Java, `java.time.Clock` exists for exactly this.

**The cost:** more parameters, and the shell has to gather them. For a ten-line script that is not worth it; for rules
that change and must be right, it usually is.

See [Functional or Imperative](https://enrichmeai.com/well-rounded-engineer/functional/) in the guide.
