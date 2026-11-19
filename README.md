# Well Rounded Engineer: labs

**One drill a day, 10 to 20 minutes, in Python or Java.** Each drill is a small piece of broken code taken from a real
kind of failure, with tests that fail until you fix it. You predict what will happen, you fix it, the tests tell you
when you are right, and then you read why.

More and more code will be written by agents and workflows. The thinking behind it still has to be ours: knowing why a
balance came out short, why a service hangs, why a page is slow. A short drill most days keeps that thinking in practice.
(Our view: the habit matters more than the length. Twenty minutes a day is the same idea as reading a book for twenty
minutes. The other view: some people learn better in a longer block once a week. Both work if you keep doing them.)

This repository is part of the free guide
[Well Rounded Engineer — More Human](https://enrichmeai.com/well-rounded-engineer/). The guide explains, the
[fundamentals](https://github.com/enrichmeai/well-rounded-engineer-fundamentals) repository shows working examples,
and these labs are where you do it yourself.

## Start

You need Python 3.10 or newer with pytest, and a JDK 21 or newer if you want the Java versions.

```bash
git clone https://github.com/enrichmeai/well-rounded-engineer-labs
cd well-rounded-engineer-labs
python3 -m pip install pytest
python3 practice.py today
```

`practice.py` tells you today's drill, runs its tests, gives hints one at a time and keeps your streak, all on your own
machine (in `.progress.json`; nothing is sent anywhere):

```bash
python3 practice.py today            # what to do today
python3 practice.py check 01 --track concurrency     # run a drill's tests (Python); add --java for Java
python3 practice.py hint 01 --track concurrency      # one hint at a time
python3 practice.py list             # every drill, and the ones you have done
python3 practice.py streak           # days in a row with at least one drill passed
```

## How a drill works

1. **Read the story** in the drill's README: what went wrong in production.
2. **Predict** before you run anything. Write your guess down. Being wrong is where the learning is.
3. **Fix the code** until the tests pass, every time, not just once.
4. **Read `WHY.md`** in [`solutions/`](solutions/): why the fix works, the wrong fixes people try first, and where you
   will meet the same problem for real.

The solutions are in their own folder so you are not tempted. Try first; take a hint before you take the answer.

## The drills

| Track | Drill | The bug | Time |
|---|---|---|---|
| [Concurrency](concurrency/) | [01 · The lost update](concurrency/01-lost-update/) | Two threads read, then both write: deposits vanish | 10 min |
| | [02 · The deadlock](concurrency/02-deadlock/) | Transfers in both directions wait for each other for ever | 15 min |
| | [03 · The last seat](concurrency/03-last-seat/) | Check, then act: 40 people book 10 seats | 10 min |
| | [04 · The pipeline that never finishes](concurrency/04-pipeline-stall/) | One bad order kills a worker, and the job hangs | 15 min |
| | [05 · Make it fast](concurrency/05-make-it-fast/) | Twenty network calls one after another | 10 min |
| [Data structures](data-structures/) | [01 · The slow duplicate check](data-structures/01-slow-duplicate-check/) | A nested loop that grows with the square of the data | 10 min |
| | [02 · The key that cannot be found](data-structures/02-key-not-found/) | Equal keys with different hashes: stock vanishes | 15 min |
| | [03 · The slow queue](data-structures/03-slow-queue/) | Taking from the front of a list, 300,000 times | 10 min |
| | [04 · The price at a moment](data-structures/04-price-at-a-moment/) | Scanning a sorted history from the start, for every refund | 15 min |
| | [05 · The cache that forgets the wrong thing](data-structures/05-cache-forgets/) | First in, first out, where least recently used was meant | 10 min |

| [Distributed systems](distributed-systems/) | [01 · The double charge](distributed-systems/01-double-charge/) | A lost reply, a retry, and the customer pays twice | 10 min |
| | [02 · The retry storm](distributed-systems/02-retry-storm/) | A thousand clients retry in step and knock the service over again | 15 min |
| | [03 · The lock that outlived its owner](distributed-systems/03-lock-outlived-owner/) | A paused worker wakes up and overwrites the new lock holder | 15 min |
| | [04 · The stale read](distributed-systems/04-stale-read/) | One replica missed the update, and the parcel goes to the old address | 15 min |
| | [05 · The update that went back in time](distributed-systems/05-back-in-time/) | Events arrive out of order; the oldest one wins | 10 min |

Drill numbers repeat across tracks, so name the track when you check or ask for a hint:
`python3 practice.py check 03 --track data-structures`.

More tracks will follow the guide: SQL, testing, security.

## Every drill is checked

CI runs [`tools/verify_drills.py`](tools/verify_drills.py) on every change. For every drill, in both languages, it
checks that **the starter code fails** its tests and **the solution passes** them. A drill whose broken code happened
to pass would teach nothing, so that is treated as a bug.

The data structures drills are timed: the starter gives the right answer, several seconds too slowly. The tests
allow one second; the fixed versions take milliseconds, so the limit is far from both. The concurrency bugs are made
reliable on purpose: each starter has a small, named stand-in for real slow work (an
audit write, a payment call) that gives other threads a chance to run at the wrong moment, as real I/O does.

## Licence

Apache License 2.0. See [LICENSE](LICENSE).
