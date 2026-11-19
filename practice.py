#!/usr/bin/env python3
"""One drill a day: pick today's drill, check your answer, take a hint, keep a streak.

    python3 practice.py today            what to do today
    python3 practice.py list             every drill, and the ones you have done
    python3 practice.py check 01         run drill 01's tests (Python); add --java for Java
    python3 practice.py hint 01          the next hint for drill 01, one at a time
    python3 practice.py streak           days in a row with at least one drill passed

Your progress is kept in .progress.json next to this file. It never leaves your machine.
Standard library only. Java drills need a JDK 21 or newer; Python drills need pytest.
"""

import argparse
import datetime as dt
import json
import re
import shutil
import subprocess
import sys
import tempfile
from pathlib import Path

ROOT = Path(__file__).resolve().parent
PROGRESS = ROOT / ".progress.json"
TRACKS = ["concurrency"]


def drills() -> list[Path]:
    found = []
    for track in TRACKS:
        found += sorted(p for p in (ROOT / track).iterdir() if p.is_dir() and re.match(r"\d\d-", p.name))
    return found


def drill_id(path: Path) -> str:
    return f"{path.parent.name}/{path.name}"


def find(name: str) -> Path:
    """Accept '01', 'lost-update', '01-lost-update' or 'concurrency/01-lost-update'."""
    matches = [d for d in drills() if name in (drill_id(d), d.name, d.name[:2], d.name[3:])]
    if len(matches) != 1:
        sys.exit(f"no single drill matches {name!r}; try: python3 practice.py list")
    return matches[0]


def title(path: Path) -> str:
    first = (path / "README.md").read_text().splitlines()[0]
    return first.lstrip("# ").strip()


def minutes(path: Path) -> str:
    m = re.search(r"\*\*Time:\*\* about (\d+) minutes", (path / "README.md").read_text())
    return f"{m.group(1)} min" if m else ""


def prediction(path: Path) -> str:
    text = (path / "README.md").read_text()
    m = re.search(r"## Before you start: predict\n\n(.*?)\n\n", text, re.S)
    return " ".join(m.group(1).split()) if m else ""


def load() -> dict:
    try:
        return json.loads(PROGRESS.read_text())
    except (OSError, ValueError):
        return {"passed": {}, "hints": {}}


def save(state: dict) -> None:
    PROGRESS.write_text(json.dumps(state, indent=2, sort_keys=True))


def streak(state: dict, today: dt.date) -> int:
    days = {dt.date.fromisoformat(d) for runs in state["passed"].values() for d in runs}
    n, day = 0, today
    if day not in days:
        day -= dt.timedelta(days=1)          # a streak is not broken until the day is over
    while day in days:
        n += 1
        day -= dt.timedelta(days=1)
    return n


def cmd_list(_args) -> None:
    state = load()
    for d in drills():
        done = "done" if any(k.startswith(drill_id(d)) for k in state["passed"]) else "    "
        print(f"  [{done}] {d.name[:2]}  {title(d):45} {minutes(d)}")


def cmd_today(_args) -> None:
    state = load()
    all_drills = drills()
    todo = [d for d in all_drills if not any(k.startswith(drill_id(d)) for k in state["passed"])]
    if todo:
        d, why = todo[0], "next one you have not done"
    else:
        d, why = all_drills[dt.date.today().toordinal() % len(all_drills)], "a review: you have done them all"
    print(f"Today: {title(d)}  ({minutes(d)}, {why})\n")
    if prediction(d):
        print(f"Before you start, predict: {prediction(d)}\n")
    print(f"Read:   {d.relative_to(ROOT)}/README.md")
    print(f"Check:  python3 practice.py check {d.name[:2]}        (add --java for Java)")
    print(f"Stuck:  python3 practice.py hint {d.name[:2]}")


def run_tests(d: Path, java: bool, folder: Path | None = None) -> bool:
    """Run one drill's tests. `folder` overrides where the code is (used by the CI check).

    A test run that hangs past two minutes counts as a failure, not a crash.
    """
    try:
        return _run_tests(d, java, folder)
    except subprocess.TimeoutExpired:
        print("the tests did not finish within 2 minutes")
        return False


def _run_tests(d: Path, java: bool, folder: Path | None) -> bool:
    lang = folder or (d / ("java" if java else "python"))
    if java:
        tests = [p.stem for p in lang.glob("*Test.java")]
        with tempfile.TemporaryDirectory() as out:
            compiled = subprocess.run(["javac", "-d", out, *map(str, lang.glob("*.java"))], cwd=lang)
            if compiled.returncode != 0:
                return False
            return all(subprocess.run(["java", "-cp", out, t], cwd=lang, timeout=120).returncode == 0 for t in tests)
    result = subprocess.run([sys.executable, "-m", "pytest", "-q", "-p", "no:cacheprovider"], cwd=lang, timeout=120)
    return result.returncode == 0


def cmd_check(args) -> None:
    d = find(args.drill)
    if args.java and not shutil.which("javac"):
        sys.exit("javac not found: install a JDK 21 or newer")
    ok = run_tests(d, args.java)
    key = f"{drill_id(d)}/{'java' if args.java else 'python'}"
    if not ok:
        print(f"\nNot yet. Read the failure above, then try again, or: python3 practice.py hint {d.name[:2]}")
        sys.exit(1)
    state = load()
    today = dt.date.today().isoformat()
    state["passed"].setdefault(key, [])
    if today not in state["passed"][key]:
        state["passed"][key].append(today)
    save(state)
    print(f"\nPassed. Streak: {streak(state, dt.date.today())} day(s).")
    print(f"Now read why it works: solutions/{drill_id(d)}/WHY.md")


def cmd_hint(args) -> None:
    d = find(args.drill)
    hints = re.split(r"^## Hint \d+\n", (d / "HINTS.md").read_text(), flags=re.M)[1:]
    state = load()
    shown = state["hints"].get(drill_id(d), 0)
    if shown >= len(hints):
        print(f"No more hints. The worked answer is in solutions/{drill_id(d)}/")
        return
    print(f"Hint {shown + 1} of {len(hints)}:\n\n{hints[shown].strip()}")
    state["hints"][drill_id(d)] = shown + 1
    save(state)


def cmd_streak(_args) -> None:
    print(f"Streak: {streak(load(), dt.date.today())} day(s).")


def main() -> None:
    p = argparse.ArgumentParser(description="One drill a day.")
    sub = p.add_subparsers(dest="command", required=True)
    sub.add_parser("today").set_defaults(fn=cmd_today)
    sub.add_parser("list").set_defaults(fn=cmd_list)
    sub.add_parser("streak").set_defaults(fn=cmd_streak)
    c = sub.add_parser("check")
    c.add_argument("drill")
    c.add_argument("--java", action="store_true")
    c.set_defaults(fn=cmd_check)
    h = sub.add_parser("hint")
    h.add_argument("drill")
    h.set_defaults(fn=cmd_hint)
    args = p.parse_args()
    args.fn(args)


if __name__ == "__main__":
    main()
